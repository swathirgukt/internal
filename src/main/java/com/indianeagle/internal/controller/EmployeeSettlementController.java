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
    Employee employee;
    Salary salary;
    EmployeeSettlement employeeSettlement;
    @Autowired
    private UserRolesService userRolesService;
    private UserDetails userDetails;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UsersService usersService;
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

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/employeeSettlement")
    public String employee() {
        return "html/fSettlement";
    }

    /**
     * Method to search employees
     */
    @PostMapping("/searchEmployeeSettlement")
    public String searchEmployee(@ModelAttribute("employeeForm") EmployeeForm employeeForm, ModelMap model) {
        employeeForm.setEmployeeList(employeeService.searchEmployees(employeeForm));
        model.addAttribute("employeeList", employeeForm.getEmployeeList());
        return "html/fragment/fSettlementResult";
    }

    /**
     * Method to view resigned employee settlement details
     */
    @GetMapping("/viewUrl")
    public String viewResignedEmployeeSettlement(ModelMap modelMap, @RequestParam("employeeId") String employeeId, EmployeeSettlementForm employeeSettlementForm) {
       try{
        employeeSettlement = salaryService.loadResignedEmployeeSettlement(employeeSettlementForm.getEmployeeId());
        employee = employeeSettlement.getEmployee();
        salary = employee.getSalary();
       // employeeSettlementForm = new EmployeeSettlementForm();
        employeeSettlementForm.setEmployeeId(employeeId);
        employeeSettlementForm.setEmployeeName(employee.getFullName());
        employeeSettlementForm.setEmployeeDesignation(employee.getDesignation());
        employeeSettlementForm.setRelievingDate(employee.getRelieveDate());
        employeeSettlementForm.setResignationDate(employee.getResignDate());
        employeeSettlementForm.setSettlementDate(employeeSettlement.getSettlementDate());
        employeeSettlementForm.setEmpStatus(employee.getEmpStatus());
        modelMap.addAttribute("employeeSettlementForm", employeeSettlementForm);
        modelMap.addAttribute("employeeSettlement", employeeSettlement);
        modelMap.addAttribute("employee", employee);
        modelMap.addAttribute("salary", salary);
        return "html/viewEmployeeSettlement";
       }catch (Exception e) {
           e.printStackTrace();
           return "html/viewEmployeeSettlement";
       }

    }

    @GetMapping("/goUrl")
    public String goEmployee(ModelMap modelMap, @RequestParam("employeeName") String employeeName, @RequestParam("employeeDesignation") String employeeDesignation, @RequestParam("employeeId") String employeeId) {
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
    public String calculateEmployeeSettlement(ModelMap modelMap, EmployeeSettlementForm employeeSettlementForm) {
        employee = employeeService.findEmployeeByEmpId(employeeSettlementForm.getEmployeeId());
        salary = employee.getSalary();
        copyEmployeeSettlementProperties(employeeSettlementForm);
        try {
            employeeSettlement = salaryService.generateSalarySettlement(employee, employeeSettlementForm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        modelMap.addAttribute("employee", employee);
        modelMap.addAttribute("salary", salary);
        modelMap.addAttribute("employeeSettlementForm", employeeSettlementForm);
        modelMap.addAttribute("employeeSettlement", employeeSettlement);
        return "html/viewEmployeeSettlement";


    }

    /**
     * Method to save employee settlement details
     *
     * @return
     */
    @PostMapping("/saveSettlement")
    public String saveEmployeeSettlement(ModelMap modelMap, EmployeeSettlementForm employeeSettlementForm) {

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
}


