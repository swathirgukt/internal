package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.form.UserForm;
import com.indianeagle.internal.mail.MailingEngine;
import com.indianeagle.internal.service.UsersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller to handle forget password requests
 * Author: Taymur Shaikh
 * Since: 27/01/2020
 */

@Controller
public class ForgotPasswordController {
    public static final Logger log = Logger.getLogger(ForgotPasswordController.class);
    @Autowired
    private UsersService usersservice;
    @Autowired
    private MailingEngine mailingEngine;

    @GetMapping("/forgetPassword")
    public String forgetPasswor() {
        return "html/forgetPassword";
    }

    @PostMapping("/forgetPassword")
    public String mailUserPassword(ModelMap modelMap, @ModelAttribute UserForm userForm) {
        try {
            User userByName = usersservice.findByUserName(userForm.getUserName());
            if (userByName != null && userByName.getEmail().equalsIgnoreCase(userForm.getEmail())) {
                String newPassword = usersservice.resetPassword(userByName);
                mailingEngine.mailUserPassword(userByName, newPassword);
                modelMap.addAttribute("success", "New Password is sent to mail");
            } else {
                modelMap.addAttribute("credentialError", "Invalid User Or Mail");
            }
        } catch (Exception e) {
            log.error("Error occurred  due to sending forgotpassword details :" + e.getMessage(), e);
            modelMap.addAttribute("mailError", "Unable to process the request");
            return "html/forgetPassword";
        }
        return "html/forgetPassword";
    }
}
