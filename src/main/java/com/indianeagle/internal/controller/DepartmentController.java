package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.indianeagle.internal.constants.StringConstants.DEPARTMENTS_LIST;

/**
 * Controller to handle leave report requests
 * Author: Taymur Shaikh
 * Since: 20/01/2020
 */

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public String department(Model model) {
        model.addAttribute("department", new Department());
        return "html/departmentDetails";
    }

    @PostMapping("/save")
    public String saveOrUpdate(ModelMap modelMap, Department department, BindingResult bindingResult) {
        departmentService.saveOrUpdate(department);
        modelMap.addAttribute("success", "Save or Update department Successfull");
        modelMap.addAttribute("department", new Department());
        return "html/departmentDetails";
    }

    @RequestMapping("/search")
    public String search(ModelMap modelMap, @ModelAttribute Department department) {
        List<Department> departments = departmentService.findDepartments(department);
        modelMap.addAttribute(DEPARTMENTS_LIST, departments);
        return "html/fragment/departmentResult";
    }

    @GetMapping("/edit/{deptId}")
    public String edit(ModelMap modelMap, @PathVariable("deptId") Long deptId) {
        Department department = departmentService.findById(deptId);
        modelMap.addAttribute("department", department);
        return "html/departmentDetails";
    }

}
