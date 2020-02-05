package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.ChequeDetails;
import com.indianeagle.internal.form.ChequeDetailsForm;
import com.indianeagle.internal.service.ChequeDetailsService;
import com.indianeagle.internal.util.SimpleUtils;
import com.indianeagle.internal.validator.ChequeDetailsFormValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author controller to perform operations on Cheque Details
 */
@Controller
public class ChequeDetailsController {
    @Autowired
    private ChequeDetailsFormValidator chequeDetailsFormValidator;
    @Autowired
    private ChequeDetailsService service;

    @InitBinder("chequeDetailsForm")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(chequeDetailsFormValidator);
    }

    @ModelAttribute(name = "chequeDetailsFormObject")
    public String chequeDetailsFormObject(ModelMap model) {
        ChequeDetailsForm chequeDetailsForm = new ChequeDetailsForm();
        model.addAttribute("chequeDetailsForm", chequeDetailsForm);
        return "html/chequeDetails";
    }


    /**
     * shows cheque details
     *
     * @return String
     */
    @GetMapping("/chequeDetailsController")
    public String chequeDetails() {
        return "html/chequeDetails";
    }


    /**
     * To save or update the cheque details in database
     *
     * @return
     */
    @PostMapping("/saveChequeDetails")
    public String saveOrUpdate(ModelMap model, @Valid @ModelAttribute("chequeDetailsForm") ChequeDetailsForm chequeDetailsForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            return "html/chequeDetails";
        }
        if (chequeDetailsForm.getChequeDetails().getId() == null) {
            model.addAttribute("saveMessage", "Saved Successfully");
        } else {
            model.addAttribute("updateMessage", "Updated Successfully");
        }
        service.saveOrUpdateCheque(chequeDetailsForm.getChequeDetails());
        System.out.println("##CheqDtls: "+chequeDetailsForm.getChequeDetails());

        chequeDetailsForm.setChequeDetails(null);
        return "html/chequeDetails";
    }

    /**
     * Retrieve the cheque details fromDate is mandatory
     *
     * @return
     */
    @PostMapping("/searchChequeDetails")
    public String search(ModelMap model, @ModelAttribute("chequeDetailsForm") ChequeDetailsForm chequeDetailsForm, BindingResult bindingResult) {
        if (chequeDetailsForm.getFromDate() == null || chequeDetailsForm.getToDate() == null||chequeDetailsForm.getAmount()==null){
            return "chequeDetailsResult";
        }

        List<ChequeDetails> chequeDetailsList = service.searchChequeDetails(chequeDetailsForm);
        System.out.println(chequeDetailsList);

        BigDecimal total = BigDecimal.ZERO;
        for (ChequeDetails chequeDetails:chequeDetailsList) {
            total = total.add(chequeDetails.getAmount());
        }

        System.out.println(total);
        ChequeDetails chequeDetails = new ChequeDetails();
        chequeDetails.setAmount(total);
        chequeDetails.setNameOfPay("Total Amount");
        chequeDetailsList.add(chequeDetails);
        chequeDetailsForm.setChequeDetailsList(chequeDetailsList);
        model.addAttribute("chequeDetailsForm",chequeDetailsForm);
        return "html/fragment/chequeDetailsResult";
    }

    @GetMapping("/editChequeDetails/{chequeId}")
    public String edit(ModelMap model, ChequeDetailsForm chequeDetailsForm,@PathVariable("chequeId") Long chequeId) {

       chequeDetailsForm.setChequeDetails(service.findBy(chequeId));
       model.addAttribute("chequeDetailsForm",chequeDetailsForm);
        return "html/chequeDetails";
    }


}
