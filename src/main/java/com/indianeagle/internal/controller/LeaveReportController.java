package com.indianeagle.internal.controller;


import com.indianeagle.internal.form.LeaveApproveForm;
import com.indianeagle.internal.service.ApprovedLeaveService;
import com.indianeagle.internal.service.LeaveDetailsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LeaveReportController {
    @Autowired
    private LeaveDetailsService leaveDetailsService;
    @Autowired
    private ApprovedLeaveService approvedLeaveService;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/employeeLeaveReport")
    public String leaveReportView(ModelMap modelMap){
        List<String> employeeIds = leaveDetailsService.findAllEmployeeIds();
        modelMap.addAttribute("employeeIds", employeeIds);
        return "html/employeeLeaveReport";
    }

    @PostMapping("/employeeLeaveReport")
    public String leaveReport(ModelMap modelMap, @RequestParam() String empId, @RequestParam Date fromDate, @RequestParam Date toDate){
        System.out.println("#leaveReport  "+empId+fromDate+toDate);
        if (StringUtils.isBlank(empId) || fromDate == null || toDate == null) {
            return "html/fragment/LeaveReportResult";
        }
        List<LeaveApproveForm> leaveApproveFormList = approvedLeaveService.getApprovedLeaves(empId, fromDate, toDate);
        modelMap.addAttribute("leaveApproveFormList",leaveApproveFormList);
        return "html/fragment/LeaveReportResult";
    }

}
