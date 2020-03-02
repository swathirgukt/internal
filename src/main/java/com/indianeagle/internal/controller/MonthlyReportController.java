package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.MonthlySalaryReport;
//import com.indianeagle.internal.facade.MonthlyReportService;
import com.indianeagle.internal.service.MonthlyReportService;
import com.indianeagle.internal.util.SimpleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller to monthly salary report of all employees
 *
 * @author CH.Srinath
 */
@Controller
public class MonthlyReportController {
    private static final Logger LOG = LogManager.getLogger(MonthlyReportController.class);
    @Autowired
    private MonthlyReportService monthlyReportService;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/salaryReportHome")
    public String salaryReportHome() {
        return "html/monthlySalaryReport";
    }

    /**
     * Method to get monthly wise salary report
     *
     * @return
     */
    @PostMapping("/monthlySalaryReport")
    public String getmonthlySalaryReport(ModelMap model, @RequestParam @DateTimeFormat(pattern = "YYYY-MM-dd") Date salaryDate) {
        try {

            List<MonthlySalaryReport>   monthlySalaryList = monthlyReportService.getMonthlySalaryReport(salaryDate);

            if (monthlySalaryList.isEmpty()) {
                model.addAttribute("recordMessage", "No Records Found");
                return "html/monthlySalaryReport";
            }
            model.addAttribute("monthlySalaryList", monthlySalaryList);
            return "html/fragment/monthlySalaryReportResult";
           // return "html/exceltest";
        } catch (Exception e) {
            model.addAttribute("Problem occured due to technical problem");
            LOG.error("Problem occured due to technical problem", e);
            return "html/monthlySalaryReport";
        }
    }
}
