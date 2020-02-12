package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Incentives;
import com.indianeagle.internal.form.IncentiveForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.form.vo.IncentivesVO;
import com.indianeagle.internal.service.EmployeeService;
import com.indianeagle.internal.service.IncentiveService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import java.util.*;

/**
 * Action class for adding incentives for employees
 *
 * @author kiran.paluvadi
 */
@Controller
@RequestMapping("/addIncentive")
public class IncentivesController {
    Map<String, EmployeeVO> employeeMap;

    @Autowired
    EmployeeService employeeService;
    @Autowired
    private IncentiveService incentiveService;

    @PostConstruct
    public void loadData() {
        employeeMap = new HashMap<>();
        for (Employee employee : employeeService.getEmployeeList()) {
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(employee,employeeVO);
            employeeMap.put(employee.getEmpId(), employeeVO);
        }
    }

    @GetMapping
    public String addIncentiveView(Model model) {
        IncentiveForm incentiveForm = new IncentiveForm();
        incentiveForm.setEmployeeVOList(new ArrayList(employeeMap.values()));
        model.addAttribute("incentiveForm", incentiveForm);
        return "html/addIncentive";
    }

    @PostMapping("/save")
    public String saveIncentives(ModelMap modelMap, IncentiveForm incentiveForm) {
        System.out.println("##Incetive: " + incentiveForm.getIncentiveDate());
        System.out.println("##Incetive: " + incentiveForm.getIncentivesVOList().get(0).getEmployeeVO().getId());
        System.out.println("##Incetive: " + incentiveForm.getIncentivesVOList().get(0).getIncentiveAmount());
        for (IncentivesVO incentivesVO:incentiveForm.getIncentivesVOList()){
            incentivesVO.setEmployeeVO(employeeMap.get(incentivesVO.getEmployeeVO().getEmpId()));
        }
        incentiveService.saveIncentives(incentiveForm);
        modelMap.addAttribute("success", "Save incentive Successfull");
        modelMap.addAttribute("incentiveForm", new IncentiveForm());
        return "html/addIncentive";

    }

    @RequestMapping("/search")
    public String searchIncentives(ModelMap modelMap, @ModelAttribute IncentiveForm incentiveForm) {
        List<Incentives> incentivesList = incentiveService.searchIncentives(incentiveForm.getIncentiveDate());
        modelMap.addAttribute("incentiveForm", incentiveForm);
        modelMap.addAttribute("incentivesList", incentivesList);
        return "html/addIncentive";
    }


}
