package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.FinancialYear;
import com.indianeagle.internal.form.EmployeeFinancialYearForm;
import com.indianeagle.internal.form.FinancialYearForm;
import com.indianeagle.internal.service.DepartmentService;
import com.indianeagle.internal.util.Form16Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Form16Controller {
    @Autowired
    DepartmentService departmentService;

    @ModelAttribute
    public String prepare(ModelMap modelMap) {
        EmployeeFinancialYearForm employeeFinancialYearForm= new EmployeeFinancialYearForm();
        modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
        return "html/employeeSavings";
    }

    @PostMapping("/itSections")
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
   @PostMapping("/employeeSavings")
    public String employeeSavings(){


   }
}
