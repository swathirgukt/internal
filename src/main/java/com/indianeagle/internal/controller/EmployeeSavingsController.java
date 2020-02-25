package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.EmployeeFinancialYear;
import com.indianeagle.internal.dto.FinancialYear;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.EmployeeFinancialYearForm;
import com.indianeagle.internal.form.FinancialYearForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.indianeagle.internal.util.Form16Utils.*;

@Controller
public class EmployeeSavingsController {
    @Autowired
    DepartmentService departmentService;
    private BigDecimal employeePF = BigDecimal.ZERO;
    private BigDecimal pTax = BigDecimal.ZERO;
    private FinancialYearForm financialYearForm;
    private boolean successfulSubmission;
    private EmployeeFinancialYearForm employeeFinancialYearForm;

    @ModelAttribute
    public String employeeFinancialYearForm(ModelMap modelMap)
    {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        EmployeeFinancialYearForm employeeFinancialYearForm=new EmployeeFinancialYearForm();

        EmployeeVO employeeVO=new EmployeeVO();
        List<FinancialYear> financialYears = departmentService.findAllFinancialYearSections();
        if (financialYears != null && !financialYears.isEmpty()) {
            FinancialYear financialYear = financialYears.get(financialYears.size() - 1);
            financialYearForm = prepareFinancialYearForm(financialYear);
            //  return "html/employeeSavings";
        }
        modelMap.addAttribute("financialYearForm",financialYearForm);
        modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
        modelMap.addAttribute("employeeVO",employeeVO);

        return "html/employeeSavings";

    }


    @GetMapping("/employeeSavings")
    public String employeeSavings(ModelMap modelMap) {
        return "html/employeeSavings";
    }

    @PostMapping("/searchEmployeeByName")
    public String searchEmployeeByName(ModelMap modelMap, @ModelAttribute EmployeeVO employeeVO) {
       // System.out.println("=========employeeFinancialYearForm=========="+employeeFinancialYearForm.getFinancialYear());
      // FinancialYearForm financialYearForm = prepareFinancialYearForm(employeeFinancialYearForm.getFinancialYear());
        if (employeeVO.getFirstName() != null && !employeeVO.getFirstName().trim().equals("") && employeeVO.getFirstName().length() >= 3) {
            Employee employee=new Employee();
            BeanUtils.copyProperties(employeeVO,employee);
            List<Employee> employees = departmentService.findEmployeeByName(employee.getFirstName());
            modelMap.addAttribute("employees",employees);

        }
        else{
            modelMap.addAttribute("nameError","Please provide name with minimum three characters");
            return "html/employeeSavings";
        }

        /*else {
            retrieveNameError = true;
            return "ERROR";
        }
        if (employees == null || employees.isEmpty()) {
            retrieveNameError = true;
            return "ERROR";
        }*/
        return "html/fragment/employeeSavingsSearchResult";
    }
@PostMapping("/retrieveFinancialYearDataToEmployee")
    public String retrieveFinancialYearDataToEmployee(ModelMap modelMap,@ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm) {
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        String empId = employeeFinancialYearForm.getEmpId();
        employeeFinancialYearForm = null;
        List<EmployeeFinancialYear> employeeFinancialYears = null;
        List<FinancialYear> financialYears = null;
        if (empId != null && !empId.equals("")) {
          Employee  employee = departmentService.findEmployeeByEmployeeId(empId);
            if (employee != null) {
                System.out.println("==employee======="+employee);
                EmployeeVO employeeVO=new EmployeeVO();
                BeanUtils.copyProperties(employee,employeeVO);
                modelMap.addAttribute("employeeVO",employeeVO);
                employeeFinancialYears = departmentService.findEmployeeFinancialYearWithEmpIdAndFinancialYear(employee.getEmpId(), financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
            }
        }
        if (employeeFinancialYears != null && !employeeFinancialYears.isEmpty()) {
            employeeFinancialYearForm = prepareEmployeeFinancialYearForm(employeeFinancialYears.get(0));
            this.employeeFinancialYearForm=employeeFinancialYearForm;
            System.out.println("======employeeFinancialYearForm===="+employeeFinancialYearForm);
            modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
            financialYearForm = new FinancialYearForm();
            modelMap.addAttribute("financialYearForm",financialYearForm);
        }
        financialYears = departmentService.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (financialYears != null && !financialYears.isEmpty()) {
            financialYearForm = prepareFinancialYearForm(financialYears.get(0));
            modelMap.addAttribute("financialYearForm",financialYearForm);
            Date fromDate = prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
            Date toDate = prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());
           List<SalaryHistory> salaryHistories = departmentService.findSalaryHistoriesWithInFinancialYear(empId, fromDate, toDate);
            if (salaryHistories != null && !salaryHistories.isEmpty()) {
                for (SalaryHistory salaryHistory : salaryHistories) {
                    employeePF = employeePF.add(salaryHistory.getPfEmp());
                    pTax = pTax.add(salaryHistory.getPTax());
                }
                int remainingMonths = calculateUnSalariedMonths(financialYear, salaryHistories.get(salaryHistories.size() - 1).getSalaryEndDate());
                for (int i = 0; i <= remainingMonths; i++) {
                    employeePF = employeePF.add(salaryHistories.get(salaryHistories.size() - 1).getPfEmp());
                    pTax = pTax.add(salaryHistories.get(salaryHistories.size() - 1).getPTax());
                }
            }
        }


    if (employeeFinancialYearForm == null && financialYearForm == null) {
            financialYearForm = new FinancialYearForm();
            financialYearForm.setFromMonth(financialYear.getFromMonth());
            financialYearForm.setFromYear(financialYear.getFromYear());
            financialYearForm.setToMonth(financialYear.getToMonth());
            financialYearForm.setToYear(financialYear.getToYear());
            modelMap.addAttribute("financialYearForm",financialYearForm);
            modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
        System.out.println("=======employeeFinan="+employeeFinancialYearForm);
            //retrieveError = true;
        modelMap.addAttribute("noFinancialYear","There is no data with this financial year");
            return "html/employeeSavings";
        }
    System.out.println("=======employeeFinan2="+employeeFinancialYearForm);
    System.out.println("++++++++++empid+"+employeeFinancialYearForm.getId());
    System.out.println("=======taxformid===="+employeeFinancialYearForm.getEmployeeTaxSectionForms().get(0).getId());
    System.out.println("=======taxdecid===="+employeeFinancialYearForm.getEmployeeTaxSectionForms().get(0).getEmployeeTaxSectionDeclarations().get(0).getId());
    return "html/employeeSavings";
    }

    @PostMapping("/saveOrUpdateEmployeeFinancialYear")
    public String saveOrUpdateEmployeeFinancialYear(@ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm,@ModelAttribute EmployeeVO employeeVO,ModelMap modelMap) {
        financialYearForm = new FinancialYearForm();
        /*if (validation(employeeFinancialYearForm)) {
            return INPUT;
        }*/

       System.out.println("======employeeVOemployeeVO========="+employeeVO);
        System.out.println("============employeeForm============"+employeeFinancialYearForm.getEmployeeTaxSectionForms().get(0).getEmployeeTaxSectionDeclarations().get(0).getSaveAmount());

        System.out.println("==============================");
        System.out.println("============employeeForm============"+employeeFinancialYearForm);
        System.out.println("==============================");
        System.out.println("============employeeForm============"+employeeFinancialYearForm.getEmployeeTaxSectionForms());
        System.out.println("==============================");
        System.out.println("============employeeForm============"+employeeFinancialYearForm.getEmployeeTaxSectionForms().get(0).getEmployeeTaxSectionDeclarations().get(0).getSubSectionName());


        saveEmployee(employeeVO,employeeFinancialYearForm);
       departmentService.saveOrUpdateEmployeeFinancialYear( prepareEmployeeFinancialYear(employeeFinancialYearForm));
               // prepareEmployeeFinancialYear(employeeFinancialYearForm);
        successfulSubmission = true;
        employeeFinancialYearForm = null;
        employeeVO = null;
        employeeFinancialYearForm(modelMap);
        modelMap.addAttribute("success","your form is successfully submitted");
        return "html/employeeSavings";
    }

    private void saveEmployee(EmployeeVO employeeVO,EmployeeFinancialYearForm employeeFinancialYearForm) {
        Employee employeeFromDB = departmentService.findEmployeeByEmployeeId(employeeFinancialYearForm.getEmpId());
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeVO,employee);
        employeeFromDB.setDesignation(employee.getDesignation());
        employeeFromDB.setPanNo(employee.getPanNo());
        employeeFromDB.setPerAddress(employee.getPerAddress());
        departmentService.saveOrUpdateEmployee(employeeFromDB);
    }
}
