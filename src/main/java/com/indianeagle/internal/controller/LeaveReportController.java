package com.indianeagle.internal.controller;


import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.form.LeaveApproveForm;
import com.indianeagle.internal.form.LeavesForm;
import com.indianeagle.internal.service.ApprovedLeaveService;
import com.indianeagle.internal.service.EmployeeService;
import com.indianeagle.internal.service.LeaveDetailsService;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller to handle leave report requests
 * Author: Taymur Shaikh
 * Since: 22/01/2020
 */


@Controller
public class LeaveReportController {
    @Autowired
    private LeaveDetailsService leaveDetailsService;
    @Autowired
    private ApprovedLeaveService approvedLeaveService;
    @Autowired
    private EmployeeService employeeService;

    List<String> employeeIds;

    @PostConstruct
    public void loadData(){
        employeeIds = leaveDetailsService.findAllEmployeeIds();
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/employeeLeaveReport")
    public String leaveReportView(ModelMap modelMap) {
        List<String> employeeIds = leaveDetailsService.findAllEmployeeIds();
        modelMap.addAttribute("employeeIds", employeeIds);
        return "html/employeeLeaveReport";
    }


    @PostMapping("/employeeLeaveReport")
    public String leaveReport(ModelMap modelMap, @RequestParam String empId, @RequestParam Date fromDate, @RequestParam Date toDate){
        List<LeaveApproveForm> leaveApproveFormList = approvedLeaveService.getApprovedLeaves(empId, fromDate, toDate);
        modelMap.addAttribute("leaveApproveFormList", leaveApproveFormList);
        modelMap.addAttribute("isAllEmployeeReport", false);
        return "html/fragment/LeaveReportResult";
    }

    @GetMapping("/allEmployeeLeaveReport")
    public String allEmpLeaveReportView() {
        return "html/allEmployeesLeaveReport";
    }

    //Todo : chnage return view for all employee report instead for one employee
    @PostMapping("/allEmployeeLeaveReport")
    public String allEmpLeaveReport(ModelMap modelMap, @RequestParam Date fromDate, @RequestParam Date toDate) {
        List<LeaveApproveForm> leaveApproveFormList = approvedLeaveService.getApprovedLeaves(fromDate, toDate);
        modelMap.addAttribute("leaveApproveFormList", leaveApproveFormList);
        modelMap.addAttribute("isAllEmployeeReport", true);
        return "html/fragment/leaveReportResult";
    }

    @GetMapping("/addEditLeaves")
    public String addEditleaveView(ModelMap modelMap) {
        modelMap.addAttribute("employeeIds", employeeIds);
        Employee employee = employeeService.findEmployeeByEmpId(employeeIds.get(0));
        LeavesForm leavesForm = new LeavesForm();
        if (employee.getLeaves() != null) {
            BeanUtils.copyProperties(employee.getLeaves(), leavesForm);
        }
        modelMap.addAttribute("leavesForm", leavesForm);
        return "html/addEditLeave";
    }

    @PostMapping("/findEmployeeLeaves")
    public String findEmployeeLeaves(ModelMap modelMap, @RequestParam String employeeId) {
        Employee employee = employeeService.findEmployeeByEmpId(employeeId);
        LeavesForm leavesForm = new LeavesForm();
        if (employee.getLeaves() != null) {
            BeanUtils.copyProperties(employee.getLeaves(), leavesForm);
        }
        modelMap.addAttribute("leavesForm", leavesForm);
        return "html/fragment/leavesResult";
    }


    @PostMapping("/updateLeaves")
    public String updateLeaves(ModelMap modelMap, @RequestParam String employeeId, LeavesForm leavesForm) {
        if(SimpleUtils.isEmpty(employeeId)) {
            return "html/addEditLeave";
        }
        Employee employee = employeeService.findEmployeeByEmpId(employeeId);
        if (employee.getLeaves()==null){
            return "html/addEditLeave";
        }

        employee.getLeaves().setCasualLeaves(leavesForm.getCasualLeaves());
        employee.getLeaves().setSickLeaves(leavesForm.getSickLeaves());
        employee.getLeaves().setCompensatoryLeaves(leavesForm.getCompensatoryLeaves());
        employee.getLeaves().setPreviousYearLeaves(leavesForm.getPreviousYearLeaves());
        employeeService.updateEmployeeLeaves(employee);

        modelMap.addAttribute("employeeIds", employeeIds);
        modelMap.addAttribute("leavesForm", new LeavesForm());
        modelMap.addAttribute("success","Add or Edit leave is successfull");
        return "html/addEditLeave";
    }

}
