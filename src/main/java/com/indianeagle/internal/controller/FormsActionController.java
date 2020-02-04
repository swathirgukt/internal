package com.indianeagle.internal.controller;


import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.form.FormESIC;
import com.indianeagle.internal.form.FormsResultData;
import com.indianeagle.internal.form.ITForm;
import com.indianeagle.internal.service.FormsService;
import com.indianeagle.internal.service.MonthlyReportService;
import com.indianeagle.internal.util.SimpleUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FormsActionController {
    @Autowired
    private FormsService formsService;
    @Autowired
    private MonthlyReportService monthlyReportService;


    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/organisationForm")
    public String getOrganizationForm() {
        return "html/organisationForm";
    }

    /**
     * for pt form generation
     *
     * @param model
     * @param salaryDate
     * @return
     */
    @GetMapping("/formV")
    public String fromV(ModelMap model, @RequestParam Date salaryDate) {
        if (salaryDate == null) {
            System.out.println("in form date null");
            model.addAttribute("error", "Please Choose Date ");
            return "html/reportError";
        }
        FormsResultData formsResultData = new FormsResultData();
        formsResultData.setPtForm(formsService.getMonthlyRtReport(salaryDate));
        if (formsResultData.getPtForm() == null) {
            System.out.println("in form result null");
            model.addAttribute("error", "No Record Found");
            return "html/reportError";
        }
        System.out.println(formsResultData);
        model.addAttribute("formsResultData", formsResultData);
        return "html/formv";
    }

    /**
     * Displays the pf details on browser
     *
     * @param modelMap
     * @param salaryDate
     * @param response
     * @return
     */
    @ResponseBody
    @GetMapping("/pfForm")
    public byte[] pfForm(ModelMap modelMap, @RequestParam Date salaryDate, HttpServletResponse response) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        response.setContentType("text/enriched");
        // response.setContentType("text/plain");
        response.setBufferSize(1024);
        byte[] b = null;
        try {
            if (salaryDate == null) {
                // modelMap.addAttribute("error", "Please Choose a Date");
                //return "html/reportError";
                String line = "Please Choose Date";
                byteArrayOutputStream.write(line.getBytes());
                return byteArrayOutputStream.toByteArray();
            }
            InputStream inputStream = formsService.getMonthlyPfReport(salaryDate);
            if (inputStream == null) {
                //modelMap.addAttribute("error", "No Record Found");
                //  return "html/reportError";
                String line = "No Records Found";
                byteArrayOutputStream.write(line.getBytes());
                return byteArrayOutputStream.toByteArray();
            }

            String pfFileName = "PF_" + new SimpleDateFormat("MMMM_yyyy").format(salaryDate).toUpperCase();


            //org.apache.commons.io.IOUtils.copy(inputStream, byteArrayOutputStream);
            b = IOUtils.toByteArray(inputStream);


        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * @param modelMap
     * @param salaryDate
     * @return
     */
    @GetMapping("/esiReport")
    public String esiReport(ModelMap modelMap, @RequestParam Date salaryDate) {
       FormsResultData formsResultData = new FormsResultData();
        try {

            if (salaryDate == null) {
                modelMap.addAttribute("error", "Please enter Date");
                return "html/reportError";
            }
            FormESIC formESIC = formsService.getMonthlyESIReport(salaryDate);
            if (formESIC != null) {
                formsResultData.setFormESIC(formESIC);
            } else {
                modelMap.addAttribute("error", "ESI Employess are not available ");
                return "html/reportError";
            }
        } catch (RuntimeException e) {
            modelMap.addAttribute("error", "problem occured due to technical error");
        }
        modelMap.addAttribute("formsResultData", formsResultData);
        return "html/esic";
    }

   //TODO:: MOVE THIS METHOD TO SALARY CONTROLLER LATER
    @GetMapping("/bankSalariesReport")
    public String bankSalariesReport(ModelMap modelMap, @RequestParam Date salaryDate) {
        FormsResultData formsResultData = new FormsResultData();
        try {
            if (salaryDate == null) {
                modelMap.addAttribute("error", "Please Enter Date");
            }
       // FormsResultData formsResultData= new FormsResultData();
                formsResultData.setBankSalariesForm(monthlyReportService.getBankSalariesReport(salaryDate));
            if(formsResultData.getBankSalariesForm().getBankSalariesInfoList().size()==0){
               modelMap.addAttribute("error","No Records Found");
            }
            modelMap.addAttribute("formsResultData",formsResultData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "html/bankSalariesReport";
    }
}