/*
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

*/
/**
 * @author
 * controller to perform operations on Cheque Details
 *//*

@Controller
public class ChequeDetailsController {
    //@Autowired
    private ChequeDetailsFormValidator chequeDetailsFormValidator;
   // @Autowired
    private ChequeDetailsService service;

    @InitBinder("chequeDetailsForm")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(chequeDetailsFormValidator);
    }

   @ModelAttribute(name= "chequeDetailsFormObject")
    public void chequeDetailsFormObject(ModelMap model) {
       ChequeDetailsForm chequeDetailsForm = new ChequeDetailsForm();
    }



    */
/**
     * shows cheque details
     * @return String
     *//*

    @GetMapping("/chequeDetailsController")
    public String chequeDetails() {
        return "chequeDetails";
    }


    */
/**
     * To save or update the cheque details in database
     * @return
     *//*

@PostMapping("/saveChequeDetails")
    public String saveOrUpdate(ModelMap model,@Valid @ModelAttribute("chequeDetailsForm")  ChequeDetailsForm chequeDetailsForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        // model.addAttribute("message", "Validate error");
        //bindingResult.reject("Validate error");
        return "chequeDetails";
    }
        if(chequeDetailsForm.getChequeDetails().getId() == null){
            model.addAttribute("saveMessage","Saved Successfully");
        }else{
            model.addAttribute("updateMessage","Updated Successfully");
        }
        service.saveOrUpdateCheque(chequeDetailsForm.getChequeDetails());
        chequeDetailsForm.setChequeDetails(null);
        return "chequeDetails";
    }

    */
/**
     * Retrieve the cheque details fromDate is mandatory
     * @return
     *//*

@PostMapping("/searchChequeDetails")
    public String search(ModelMap model, @ModelAttribute("chequeDetailsForm") @Valid ChequeDetailsForm chequeDetailsForm, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
        // model.addAttribute("message", "Validate error");
        //bindingResult.reject("Validate error");
        return "chequeDetails";
    }

    if(!(chequeDetailsForm.getFromDate()==null||chequeDetailsForm.getToDate()==null)) {
        if ((chequeDetailsForm.getFromDate()).after(chequeDetailsForm.getToDate())) {
            model.addAttribute("dateMessage", "Please Provide Valid Dates");
            // addActionError(getText("Please Provide Valid Dates"));
            return "chequeDetails";

        }
    }

        List<ChequeDetails> chequeDetailsList = service.searchChequeDetails(chequeDetailsForm);
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < chequeDetailsList.size(); i++) {
            total = total.add(chequeDetailsList.get(i).getAmount());
        }
        ChequeDetails chequeDetails = new ChequeDetails();
        chequeDetails.setAmount(total);
        chequeDetails.setNameOfPay("Total Amount");
        chequeDetailsList.add(chequeDetails);
        chequeDetailsForm.setChequeDetailsList(chequeDetailsList);
        return "chequeDetails";
    }
    @GetMapping("/editChequeDetails")
    public String edit(ModelMap model, @RequestParam ChequeDetailsForm chequeDetailsForm,@RequestParam Long chequeId){
        chequeDetailsForm.setChequeDetails(service.findBy(chequeId));
        return "chequeDetails";
    }


}
*/
