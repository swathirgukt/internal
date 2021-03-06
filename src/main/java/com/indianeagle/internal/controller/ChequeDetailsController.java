package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.ChequeDetails;
import com.indianeagle.internal.form.ChequeDetailsForm;
import com.indianeagle.internal.service.ChequeDetailsService;
import com.indianeagle.internal.validator.ChequeDetailsFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author CH.Srinath
 * controller to save or update cheque details
 */

@Controller
public class ChequeDetailsController {
    @Autowired
    private ChequeDetailsFormValidator chequeDetailsFormValidator;
    @Autowired
    private ChequeDetailsService service;

  /*  @InitBinder("chequeDetailsForm")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(chequeDetailsFormValidator);
    }*/

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ModelAttribute(name = "chequeDetailsFormObject")
    public String chequeDetailsFormObject(ModelMap model) {
        ChequeDetailsForm chequeDetailsForm = new ChequeDetailsForm();
        model.addAttribute("chequeDetailsForm", chequeDetailsForm);
        return "html/chequeDetails";
    }

    @GetMapping("/chequeDetails")
    public String chequeDetails() {
        return "html/chequeDetails";
    }

    @PostMapping("/saveChequeDetails")
    public String saveOrUpdate(ModelMap model, @ModelAttribute("chequeDetailsForm") ChequeDetailsForm chequeDetailsForm) {

        if (chequeDetailsForm.getChequeDetails().getId() == null) {
            model.addAttribute("saveMessage", "Saved Successfully");
        } else {
            model.addAttribute("updateMessage", "Updated Successfully");
        }
        service.saveOrUpdateCheque(chequeDetailsForm.getChequeDetails());

        chequeDetailsForm.setChequeDetails(null);
        return "html/chequeDetails";
    }


    @PostMapping("/searchCheques")
    public String search(ModelMap model, @ModelAttribute("chequeDetailsForm") ChequeDetailsForm chequeDetailsForm) {

        if (chequeDetailsForm.getFromDate() == null || chequeDetailsForm.getToDate() == null || chequeDetailsForm.getAmount() == null) {
            return "html/fragment/chequeDetailsResult";
        }

        List<ChequeDetails> chequeDetailsList = service.searchChequeDetails(chequeDetailsForm);

        BigDecimal total = BigDecimal.ZERO;
        for (ChequeDetails chequeDetails : chequeDetailsList) {
            total = total.add(chequeDetails.getAmount());
        }

        ChequeDetails chequeDetails = new ChequeDetails();
        chequeDetails.setAmount(total);
        chequeDetails.setNameOfPay("Total Amount");
        chequeDetailsList.add(chequeDetails);
        chequeDetailsForm.setChequeDetailsList(chequeDetailsList);
        model.addAttribute("chequeDetailsForm", chequeDetailsForm);
        return "html/fragment/chequeDetailsResult";
    }

    @GetMapping("/editChequeDetails/{chequeId}")
    public String edit(ModelMap model, ChequeDetailsForm chequeDetailsForm, @PathVariable("chequeId") Long chequeId) {

        chequeDetailsForm.setChequeDetails(service.findBy(chequeId));
        model.addAttribute("chequeDetailsForm", chequeDetailsForm);
        return "html/chequeDetails";
    }


}
