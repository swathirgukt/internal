/*
 * Copyright (c) 2012 by Yana Software (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Yana Software (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Yana Software (P) Limited.
 */

package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Leaves;
import com.indianeagle.internal.form.LeaveApproveForm;
import com.indianeagle.internal.service.LeaveDetailsService;
import com.indianeagle.internal.service.LeavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller to handle leave  requests
 * Author: Taymur Shaikh
 * Since: 28/01/2020
 */

@Controller
public class ApproveLeaveController {
    @Autowired
    private LeaveDetailsService leaveDetailsService;
    @Autowired
    private LeavesService leavesService;
    private List<String> employeeIds;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostConstruct
    public void loadData(){
        employeeIds = leaveDetailsService.findAllEmployeeIds();
    }

    @GetMapping("/approveLeave")
    public String approveLeaveView(ModelMap modelMap) {
        modelMap.addAttribute("employeeIds", employeeIds);
        modelMap.addAttribute("leaveApproveForm", new LeaveApproveForm());
        return "html/approveLeave";
    }

    @PostMapping("/approveLeave")
    public String approveLeave(ModelMap modelMap, @ModelAttribute LeaveApproveForm leaveApproveForm) {
        leaveDetailsService.approveLeave(leaveApproveForm);
        modelMap.addAttribute("success", "Leave approved successfully for employee " + leaveApproveForm.getEmpId());
        modelMap.addAttribute("employeeIds", employeeIds);
        modelMap.addAttribute("leaveApproveForm", new LeaveApproveForm());
        return "html/approveLeave";
    }

    @PostMapping("/findLeaveBalance")
    public String findBalanceLeaves(ModelMap modelMap,@ModelAttribute LeaveApproveForm leaveApproveForm) {
        Leaves leaves = leavesService.findLeaveByEmployeeId(leaveApproveForm.getEmpId());
        if (leaves!=null) {
            leaveApproveForm = new LeaveApproveForm();
            leaveApproveForm.setRemainingCL(leaves.getCasualLeaves() + leaves.getPreviousYearLeaves());
            leaveApproveForm.setRemainingCompOff(leaves.getCompensatoryLeaves());
            leaveApproveForm.setRemainingSL(leaves.getSinkLeaves());
            modelMap.addAttribute("leaveApproveForm", leaveApproveForm);
            return "html/fragment/leaveBalanceResult";
        }
        return "html/fragment/leaveBalanceResult";
    }
}
