package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.form.vo.DepartmentVO;
import com.indianeagle.internal.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
        model.addAttribute("department", new DepartmentVO());
        return "html/departmentDetails";
    }

    @PostMapping("/save")
    public String saveOrUpdate(ModelMap modelMap, DepartmentVO departmentVO) {
        Department department= new Department();
        BeanUtils.copyProperties(departmentVO,department);
        departmentService.saveOrUpdate(department);
        modelMap.addAttribute("success", "Save or Update department Successfull");
        modelMap.addAttribute("department", new DepartmentVO());
        return "html/departmentDetails";
    }

    @RequestMapping("/search")
    public String search(ModelMap modelMap, @ModelAttribute DepartmentVO departmentVO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentVO, department);
        List<Department> departments = departmentService.findDepartments(department);
        List<DepartmentVO> departmentVOS = new ArrayList<>();
        for (Department dept : departments){
            DepartmentVO deptVO = new DepartmentVO();
            BeanUtils.copyProperties(dept,deptVO);
            departmentVOS.add(deptVO);
        }
        modelMap.addAttribute(DEPARTMENTS_LIST, departmentVOS);
        return "html/fragment/departmentResult";
    }

    @GetMapping("/edit/{deptId}")
    public String edit(ModelMap modelMap, @PathVariable("deptId") Long deptId) {
        Department department = departmentService.findById(deptId);
        modelMap.addAttribute("department", department);
        return "html/departmentDetails";
    }

}
