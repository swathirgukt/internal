package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.SalaryHistoryForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.form.vo.SalaryHistoryVO;
import com.indianeagle.internal.service.ApplicationSession;
import com.indianeagle.internal.service.SalaryHistoryService;
import com.indianeagle.internal.service.SalaryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Satya.Neelam
 */

@Controller
@RequestMapping("salaryHistory")
public class EmpSalaryHistoryController {
    private static Logger LOGGER = LogManager.getLogger(EmpSalaryHistoryController.class);

    @Autowired
    private SalaryService salaryService;
    private SalaryHistoryForm salaryHistoryForm;
    @Autowired
    private SalaryHistoryService salHistoryService;
    @Autowired
    private ApplicationSession applicationSession;

    @PostConstruct
    public void loadData() {
        salaryHistoryForm = new SalaryHistoryForm();
        List<EmployeeVO> employeeVOList = new ArrayList<>();
       /* if (applicationSession.isAdminRoleUser()) {

        } else {
            String loginUser = applicationSession.loginUserName();
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(salaryService.loadEmployee(loginUser), employeeVO);
            employeeVOList.add(employeeVO);
        }*/

        for (Employee employee : salaryService.loadAllEmployees()) {
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(employee, employeeVO);
            employeeVOList.add(employeeVO);
        }
        salaryHistoryForm.setEmployeeVOList(employeeVOList);
    }

    @ModelAttribute
    public void prepare(Model model){
        model.addAttribute("salaryHistoryForm", salaryHistoryForm);
    }

    @GetMapping
    public String salaryHistory() {
        return "html/salaryHistory";
    }

    @GetMapping("/search")
    public String getSalaryHistory(ModelMap modelMap, @RequestParam String employeeId) {
        List<SalaryHistory> salaryHistories = salHistoryService.searchSalaryHistoryByEmployeeId(employeeId);
        List<SalaryHistoryVO> salaryHistoryVOS = new ArrayList<>();
        for (SalaryHistory salaryHistory : salaryHistories){
            SalaryHistoryVO salaryHistoryVO = new SalaryHistoryVO();
            BeanUtils.copyProperties(salaryHistory, salaryHistoryVO);
            salaryHistoryVOS.add(salaryHistoryVO);
        }
        modelMap.addAttribute("salaryHistories", salaryHistoryVOS);
        return "html/fragment/employeeSalaryHistoryResult";
    }

    @GetMapping("/mail")
    @ResponseBody
    public String sendPaySlip(@RequestParam Long id, HttpSession session) {
        LOGGER.warn("######### IN sendPaySlip()");
        salHistoryService.sendPaySlip(id, "");
        LOGGER.warn("######### IN sendPaySlip() DONE..");
        return "Payslip mail sent successfully";
    }


    @GetMapping("/print")
    @ResponseBody
    public ResponseEntity<byte[]> printPaySlip(@RequestParam Long id) {
        InputStream inputStream= salHistoryService.printPaySlip(id,"");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "Payslip.pdf";
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = null;
        try {
            byte[] bytes=new byte[inputStream.available()];
            inputStream.read(bytes);
            response = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
