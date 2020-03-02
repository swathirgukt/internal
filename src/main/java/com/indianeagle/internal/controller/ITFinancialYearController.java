package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.FinancialYear;
import com.indianeagle.internal.form.FinancialYearForm;
import com.indianeagle.internal.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.indianeagle.internal.util.Form16Utils.prepareFinancialYear;
import static com.indianeagle.internal.util.Form16Utils.prepareFinancialYearForm;


/**
 * Controller to handle IT FinancialYear requests
 * <p>
 * Author : Taymur
 * Date: 02/20/2020
 */

@Controller
@RequestMapping("/incomeTaxSections")
public class ITFinancialYearController {
    private static final Logger logger = LogManager.getLogger(ITFinancialYearController.class);
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String ITSections(ModelMap modelMap) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        List<FinancialYear> financialYears = departmentService.findAllFinancialYearSections();
        System.out.println("##YEARS :: "+financialYears);
        if (!financialYears.isEmpty()) {
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            currentYear += currentMonth < Calendar.APRIL ? -1 : 0;

            FinancialYear financialYear = null;
            for (FinancialYear fYear : financialYears) {
                if (Integer.parseInt(fYear.getFromYear()) == currentYear)
                    financialYear = fYear;
            }

            financialYear = financialYear == null ? financialYears.get(financialYears.size() - 1) : financialYear;
            financialYearForm = prepareFinancialYearForm(financialYear);
        }else {
            financialYearForm.setIncomeTaxSlabVOS(Collections.EMPTY_LIST);
            financialYearForm.setRebateVOS(Collections.EMPTY_LIST);
            financialYearForm.setTaxSectionForms(Collections.EMPTY_LIST);
        }

        modelMap.addAttribute("financialYearForm", financialYearForm);
        return "html/itSections";
    }

    @PostMapping("/retrieveFinancialYear")
    public String retrieve(ModelMap modelMap, @ModelAttribute FinancialYearForm financialYearForm) {

        logger.debug("@@RETRIEVE@@FORM_DATA>>> " + financialYearForm);

        List<FinancialYear> financialYears = departmentService.findFinancialYearSectionsByFinancialYear(financialYearForm.getFromMonth(), financialYearForm.getFromYear(), financialYearForm.getToMonth(), financialYearForm.getToYear());
        financialYearForm.setIncomeTaxSlabVOS(Collections.EMPTY_LIST);
        financialYearForm.setRebateVOS(Collections.EMPTY_LIST);
        financialYearForm.setTaxSectionForms(Collections.EMPTY_LIST);
        if (!financialYears.isEmpty()) {
            FinancialYear financialYear = financialYears.get(financialYears.size() - 1);
            financialYearForm = prepareFinancialYearForm(financialYear);
        } else {
            modelMap.addAttribute("noDataError", "There is no data with this financial year.");
        }
        modelMap.addAttribute("financialYearForm", financialYearForm);
        return "html/fragment/iTSectionsBody";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateFinancialYear(ModelMap modelMap, @ModelAttribute FinancialYearForm financialYearForm) {

        logger.debug("##SAVE##FORM_DATA>>> " + financialYearForm);

        FinancialYear financialYear = prepareFinancialYear(financialYearForm);
        FinancialYear financialYearFromDB = null;
        if (financialYear.getId() == null) {
            List<FinancialYear> financialYears = departmentService.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
            if (financialYears != null && !financialYears.isEmpty()) {
                financialYearForm = prepareFinancialYearForm(financialYears.get(0));
                modelMap.addAttribute("duplicateError", "This Financial Year data is available in Data Base. Please retrieve and update or else change the financial year.");
                return "html/iTSections";
            }
        }
        departmentService.saveOrUpdateFinancialYear(financialYear);
        modelMap.addAttribute("success", "Your Data is successfully submitted.");
        return "html/iTSections";
    }

}
