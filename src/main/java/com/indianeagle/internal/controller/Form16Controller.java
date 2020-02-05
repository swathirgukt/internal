package com.indianeagle.internal.controller;

import com.indianeagle.internal.dao.repository.FinancialYearRepository;
import com.indianeagle.internal.dao.repository.SalaryHistoryRepository;
import com.indianeagle.internal.dto.*;
import com.indianeagle.internal.form.*;
import com.indianeagle.internal.service.DepartmentService;
import com.indianeagle.internal.util.Form16Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.indianeagle.internal.util.Form16Utils.*;

@Controller
public class Form16Controller {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    FinancialYearRepository financialYearRepository;
    @Autowired
    SalaryHistoryRepository salaryHistoryRepository;
    List<SalaryHistory> salaryHistories;

    @ModelAttribute
    public String prepare(ModelMap modelMap) {
        EmployeeFinancialYearForm employeeFinancialYearForm = new EmployeeFinancialYearForm();
        modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
        return "html/employeeSavings";
    }

    @ModelAttribute
    public String prepareForm16(ModelMap modelMap) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        //Employee employee= new Employee();
        modelMap.addAttribute("financialYearForm", financialYearForm);
        //  modelMap.addAttribute("employee",employee);

        return "html/form16Generation";
    }

    /*@PostMapping("/itSections")
    public String itSections(ModelMap modelMap) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        List<FinancialYear> financialYears = departmentService.findAllFinancialYearSections();
        if (financialYears != null && !financialYears.isEmpty()) {
            FinancialYear financialYear = financialYears.get(financialYears.size() - 1);
            financialYearForm = Form16Utils.prepareFinancialYearForm(financialYear);
            modelMap.addAttribute("financialYearForm", financialYearForm);

        }
        return "html/itSections";
    }
   *//*@PostMapping("/employeeSavings")
    public String employeeSavings(){


   }*//*
     */

    /**
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/form16Generation")
    public String Form16Generation(ModelMap modelMap) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        EmployeeFinancialYearForm employeeFinancialYearForm = new EmployeeFinancialYearForm();
        /*List<FinancialYear> financialYears = departmentService.findAllFinancialYearSections();
        if (financialYears != null && !financialYears.isEmpty()) {
            FinancialYear financialYear = financialYears.get(financialYears.size() - 1);
            employeeFinancialYearForm.setFinancialYear(financialYear);
        }*/
        modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
        modelMap.addAttribute("financialYearForm", financialYearForm);
        return "html/form16Generation";
    }

    /**
     * search employee by name
     *
     * @param modelMap
     * @param employeeFinancialYearForm
     * @param empName
     * @return
     */
    @RequestMapping("/searchEmployeeByNameInForm16Generation")
    public String searchEmployeeByNameInForm16Generation(ModelMap modelMap, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm, @RequestParam String empName) {
        List<Employee> employees = null;
        System.out.println(empName);
        //if(employeeFinancialYearForm.getFinancialYear()!=null
        // FinancialYearForm financialYearForm=Form16Utils.prepareFinancialYearForm(employeeFinancialYearForm.getFinancialYear());
        if (empName != null && (!empName.equals("")) && empName.length() >= 3) {

            employees = departmentService.findEmployeeByName(empName);
            System.out.println(employees.size());
        } else {
            modelMap.addAttribute("retrieveNameError", "Please enter at least three letters to search or there is no employee with the entered name");


            return "html/Form16Generation";
        }
        if (employees == null || employees.isEmpty()) {
            modelMap.addAttribute("nameError", "enter a valid name");


        }

        modelMap.addAttribute("employsList", employees);

        return "html/Form16Generation";

    }

    /**
     * retrieve employee info
     *
     * @param modelMap
     * @param employeeFinancialYearForm
     * @return
     */
    /*@PostMapping("/retrieveEmployeeInfoInForm16")
    public String retrieveEmployeeInfo(ModelMap modelMap,@ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm){
       FinancialYearForm financialYearForm = new FinancialYearForm();
        Employee employee=null;
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        String empId = employeeFinancialYearForm.getEmpId();
        employeeFinancialYearForm = null;
        financialYearForm.setFromMonth(financialYear.getFromMonth());
        financialYearForm.setFromYear(financialYear.getFromYear());
        financialYearForm.setToMonth(financialYear.getToMonth());
        financialYearForm.setToYear(financialYear.getToYear());
        employee = departmentService.findEmployeeByEmployeeId(empId);
        employeeFinancialYearForm = new EmployeeFinancialYearForm();
        employeeFinancialYearForm.setFinancialYear(financialYear);
        if (employee == null) {
            modelMap.addAttribute("nameError","enter a valid name");
            return "html/form16Generation";
            *//*employee = new Employee();
            employee.setEmpId(empId);*//*
        }
       */
    public String retrieveSalaryInfoForForm16(ModelMap modelMap, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm) {
        TotalSalaryHistoryForm totalSalaryHistoryForm = loadRequiredData(employeeFinancialYearForm);
        BigDecimal grossSalary;
        Form16GenerationForm form16GenerationForm = null;
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        String empId = employeeFinancialYearForm.getEmpId();
        Employee employee = departmentService.findEmployeeByEmployeeId(empId);
        if (employee == null) {
            modelMap.addAttribute("employeeerror", "Noe employee Found");
            //todo:: why we need to create a new employee object when method retruns no object
            /*employee = new Employee();
            employee.setEmpId(empId);*/
            return "html/form16Genaration";
        }
        List<FinancialYear> financialYears = financialYearRepository.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (financialYears == null || financialYears.isEmpty()) {
            modelMap.addAttribute("financialerror", "Noe Records Found");
            return "html/form16Genaration";
        }
        if (totalSalaryHistoryForm != null) {
            grossSalary = totalSalaryHistoryForm.getTotalGrossSalary();
        }
        List<EmployeeFinancialYear> employeeFinancialYears = departmentService.findEmployeeFinancialYearWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (employeeFinancialYears != null && !employeeFinancialYears.isEmpty()) {
            employeeFinancialYearForm = Form16Utils.prepareEmployeeFinancialYearForm(employeeFinancialYears.get(0));
        }
        employeeFinancialYearForm = updateSectionLimit(employeeFinancialYearForm);
        List<EmployeeIncomeTax> employeeIncomeTaxes = departmentService.findEmployeeIncomeTaxWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (employeeIncomeTaxes != null && !employeeIncomeTaxes.isEmpty()) {
            form16GenerationForm = Form16Utils.prepareForm16GenerationForm(employeeIncomeTaxes.get(0));
            Set<Rebate> rebates = financialYears.get(0).getRebates();
            if (rebates != null) {
                form16GenerationForm.setRebates(new ArrayList<Rebate>(rebates));
            }
        }
        form16GenerationForm.setBasic40per(calculateBasic40per(salaryHistories, financialYear));
        form16GenerationForm.setTotalActualHRA(calculateActualHRA(salaryHistories, financialYear));
        if (employeeFinancialYears != null && !employeeFinancialYears.isEmpty()) {
            form16GenerationForm.setHraWithBasic10per(calculateHraWithBasic10per(salaryHistories, financialYear, employeeFinancialYears.get(0)));
        }
        form16GenerationForm.setHra(calculateHRA(form16GenerationForm.getTotalActualHRA(), form16GenerationForm.getHraWithBasic10per(), form16GenerationForm.getBasic40per()));
        return "html/form16Generation";
    }

    private TotalSalaryHistoryForm loadRequiredData(EmployeeFinancialYearForm employeeFinancialYearForm) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        FinancialYear financialYear;

        List<Integer> remainedMonths;
        String empId = employeeFinancialYearForm.getEmpId();
        Employee employee = departmentService.findEmployeeByEmployeeId(empId);
        financialYear = employeeFinancialYearForm.getFinancialYear();
        Date fromDate = Form16Utils.prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
        Date toDate = Form16Utils.prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());
        salaryHistories = salaryHistoryRepository.findSalaryHistoryByEmpId(empId, fromDate, toDate);
        TotalSalaryHistoryForm totalSalaryHistoryForm = new TotalSalaryHistoryForm();
        totalSalaryHistoryForm = Form16Utils.prepareTotalSalary(salaryHistories, financialYear);
        if (salaryHistories != null && !salaryHistories.isEmpty()) {
            int remainingMonths = Form16Utils.calculateUnSalariedMonths(financialYear, salaryHistories.get(salaryHistories.size() - 1).getSalaryDate());
            remainedMonths = new ArrayList();
            for (int i = 0; i <= remainingMonths; i++) {
                remainedMonths.add(i);
            }
        }
        return totalSalaryHistoryForm;
    }

    private EmployeeFinancialYearForm updateSectionLimit(EmployeeFinancialYearForm employeeFinancialYearForm) {
        if (employeeFinancialYearForm.getEmployeeTaxSectionForms() != null) {
            for (EmployeeTaxSectionForm employeeTaxSectionForm : employeeFinancialYearForm.getEmployeeTaxSectionForms()) {
                BigDecimal saveAmount = BigDecimal.ZERO;
                if (employeeTaxSectionForm.getEmployeeTaxSectionDeclarations() != null) {
                    for (EmployeeTaxSectionDeclaration employeeTaxSectionDeclaration : employeeTaxSectionForm.getEmployeeTaxSectionDeclarations()) {
                        if (!employeeTaxSectionDeclaration.getSubSectionName().trim().equalsIgnoreCase("HRA")) {
                            saveAmount = saveAmount.add(employeeTaxSectionDeclaration.getSaveAmount());
                        }
                    }
                }
                if (saveAmount.compareTo(employeeTaxSectionForm.getSectionLimit()) == -1) {
                    employeeTaxSectionForm.setSectionLimit(saveAmount);
                }
            }
        }
        return employeeFinancialYearForm;
    }

}