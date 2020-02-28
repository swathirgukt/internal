package com.indianeagle.internal.controller;


import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.EmployeeIncomeTax;
import com.indianeagle.internal.service.DepartmentService;
import com.indianeagle.internal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author unskrishna
 * Date : 2/17/2020
 * Time : 3:28 PM
 */
@Controller
public class SavedFormsController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private DepartmentService service;
    @RequestMapping("/savedForms")
    public String SavedForms() {
        return "html/SavedForms";
    }

    @PostMapping("/searchEmployeeDetails")
    public String searchEmployeeDetails(@RequestParam String employeeId, @RequestParam String employeeName, ModelMap modelMap) {
        System.out.println("======searchEmployee======" + employeeId);
        System.out.println( "SavedForms.searchEmployeeDetails" +employeeId);
         List<Employee> employees;
        Employee employee;
         List<EmployeeIncomeTax> employeeIncomeTaxes;

        if (employeeId != null && !employeeId.trim().equals( "" )) {
            employee = service.findEmployeeByEmployeeId( employeeId );
            System.out.println( "@@@employee  :" + employee );
            modelMap.addAttribute( "employee", employee );

        } else {
            if (employeeName != null && !employeeName.trim().equals( "" ) && employeeName.length() >= 3) {
                employees = service.findEmployeeByName( employeeName );
                System.out.println( "SavedForms.searchEmployeeDetails if" );
                modelMap.addAttribute( "EmployeeName", employees );
                System.out.println( "employyees=====" );
                return "html/EmployeeResults";
            } else {
                System.out.println( "SavedForms.searchEmployeeDetails" );
                modelMap.addAttribute( "error", "PLEASE ENTER ATLEAST 3 CHARACTERS OR EMPLOYEE NOT AVAILABLE" );
                return "html/EmployeeResults";
               // return   "html/SavedForms";
            }
        }

            employeeIncomeTaxes = service.findEmployeeIncomeTaxWithEmpId(employeeId);
        System.out.println("=imcometax="+employeeIncomeTaxes);
            modelMap.addAttribute( "employeeIncomeTaxes",  employeeIncomeTaxes);

        return "html/fragment/employeeSaveResults";

    }


}