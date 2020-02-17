package com.indianeagle.internal.controller;

import com.indianeagle.internal.dao.repository.UsersRepository;
import com.indianeagle.internal.dto.*;
import com.indianeagle.internal.form.EmployeeForm;
import com.indianeagle.internal.form.EmployeeSettlementForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.service.EmployeeService;
import com.indianeagle.internal.service.SalaryService;
import com.indianeagle.internal.service.UserRolesService;
import com.indianeagle.internal.service.UsersService;
import com.indianeagle.internal.util.SimpleUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeSettlementController {
    @Autowired
    private UserRolesService userRolesService;
    private UserDetails userDetails;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UsersService usersService;
    Employee employee;
    Salary salary;
    EmployeeSettlement employeeSettlement;
    @Autowired
    private SalaryService salaryService;


    /**
     * Execute method
     */
    @ModelAttribute
    public String execute(Model model) {
        EmployeeForm employeeForm = new EmployeeForm();
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        model.addAttribute("employeeForm", employeeForm);
        return "html/fSettlement";
    }

    @GetMapping("/employeeSettlement")
    public String employee() {
        EmployeeForm employeeForm=new EmployeeForm();
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        employeeForm.setEmployee(employee);
        return "html/fSettlement";
    }

    /**
     *  Method to search employees
     */
    @PostMapping("/searchEmployeeSettlement")
    public String searchEmployee(@ModelAttribute("employeeForm") EmployeeForm employeeForm, ModelMap model) {
        employeeForm.setEmployeeList(employeeService.searchEmployees(employeeForm));
        model.addAttribute("employeeList", employeeForm.getEmployeeList());
        return "html/fragment/fSettlementResult";
    }

    @GetMapping("/goUrl")
    public String goEmployee(ModelMap modelMap, @RequestParam("employeeName") String employeeName, @RequestParam("employeeDesignation") String employeeDesignation, @RequestParam("employeeId") String employeeId){

        EmployeeSettlementForm employeeSettlementForm = new EmployeeSettlementForm();
        employeeSettlementForm.setEmployeeId(employeeId);
        employeeSettlementForm.setEmployeeName(employeeName);
        employeeSettlementForm.setEmployeeDesignation(employeeDesignation);
        modelMap.addAttribute("employeeSettlementForm", employeeSettlementForm);
        return "html/fSettlementDetails";
    }
    /**
     * Method to copy form properties
     *
     * @param employeeSettlementForm
     */
    private void copyEmployeeSettlementProperties(EmployeeSettlementForm employeeSettlementForm) {
        employee.setEmpStatus(employeeSettlementForm.getEmpStatus());
        employee.setResignDate(employeeSettlementForm.getResignationDate());
        employee.setRelieveDate(employeeSettlementForm.getRelievingDate());
        if (StringUtils.isNotBlank(employeeSettlementForm.getReasonRelieving())) {
            employee.setReasonsForResign(employeeSettlementForm.getReasonRelieving());
        }
    }


    /**
     * Method to calculate employee settlement details
     */
    @PostMapping("/viewSettlement")
    public String calculateEmployeeSettlement(ModelMap modelMap, EmployeeSettlementForm employeeSettlementForm){
        System.out.println("EmployeeSettlementController.calculateEmployeeSettlement");
        employee = employeeService.findEmployeeByEmpId(employeeSettlementForm.getEmployeeId());
            salary = employee.getSalary();
            copyEmployeeSettlementProperties(employeeSettlementForm);
        try {
            employeeSettlement = salaryService.generateSalarySettlement(employee, employeeSettlementForm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        modelMap.addAttribute("employee",employee );
        modelMap.addAttribute("salary",salary );
        modelMap.addAttribute("employeeSettlementForm", employeeSettlementForm);
        modelMap.addAttribute("employeeSettlement", employeeSettlement);
            return "html/viewEmployeeSettlement";

    }


    /**
     * This Method saves employee settlement details
     *
     * @return
     */
    @PostMapping("/saveSettlement")
    public String saveEmployeeSettlement(ModelMap modelMap,EmployeeSettlementForm employeeSettlementForm) {
            employee = employeeService.findEmployeeByEmpId(employeeSettlementForm.getEmployeeId());
            copyEmployeeSettlementProperties(employeeSettlementForm);
            try {
                salaryService.generateSalarySettlement(employee, employeeSettlementForm);
            } catch (Exception e) {
                e.printStackTrace();
            }
            salaryService.confirmEmployeeSettlement();
            modelMap.addAttribute("save", "Employee Settlement is successfully saved");
            return "html/fSettlementDetails";
        }

   /* *//**
     *  Method to validate form properties
     * @param employeeSettlementForm
     * @return
     *//*
    public boolean validate(ModelMap modelMap,EmployeeSettlementForm employeeSettlementForm ) {
        boolean status = false;
        if (SimpleUtils.isEmpty(employeeSettlementForm.getSettlementDate())) {
            modelMap.addAttribute("settlement","Date of Settlement is Required");
        }
        if (employeeSettlementForm.getEmpStatus().equals("Resignation") && SimpleUtils.isEmpty(employeeSettlementForm.getResignationDate())) {
            modelMap.addAttribute("resignation","Date of Resignation is Required ");
        }
        if (SimpleUtils.isEmpty(employeeSettlementForm.getRelievingDate())) {
            modelMap.addAttribute("relieving","Date of Relieving is Required ");
        }
        else {
            if (employeeSettlementForm.getEmpStatus().equals("Resigned")) {
                if (employeeSettlementForm.getResignationDate().after(employeeSettlementForm.getRelievingDate())) {
                    modelMap.addAttribute("resignationLess","Date of Resignation should be less than Relieving date");
                    status = true;
                }
            }
            if (employeeSettlementForm.getRelievingDate().after(employeeSettlementForm.getSettlementDate())) {
                modelMap.addAttribute("relieveLess","Date of Relieving should be less than Settlement date");
                status = true;
            }
        }
        return Boolean.parseBoolean(String.valueOf(status));
    }
*/


}


