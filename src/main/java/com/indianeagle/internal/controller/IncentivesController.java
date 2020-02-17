package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Incentives;
import com.indianeagle.internal.form.IncentiveForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.service.EmployeeService;
import com.indianeagle.internal.service.IncentiveService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.jws.WebParam;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Action class for adding incentives for employees
 *
 * @author kiran.paluvadi
 */
@Controller
@RequestMapping("/incentive")
public class IncentivesController {
    private IncentiveForm incentiveForm;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private IncentiveService incentiveService;

    @PostConstruct
    public void loadData() {
        List<EmployeeVO> employeeVOList = new ArrayList<>();
        for (Employee employee : employeeService.getEmployeeList()) {
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(employee,employeeVO);
            employeeVOList.add(employeeVO);
        }
        incentiveForm = new IncentiveForm();
        incentiveForm.setEmployeeVOList(employeeVOList);

    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public String addIncentiveView(Model model) {
        model.addAttribute("incentiveForm", incentiveForm);
        return "html/addIncentive";
    }

    @PostMapping("/save")
    public String saveIncentives(ModelMap modelMap,@ModelAttribute IncentiveForm incentiveForm) {
        incentiveService.saveIncentives(incentiveForm);
        modelMap.addAttribute("success", "Save incentive Successfull");
        modelMap.addAttribute("incentiveForm", this.incentiveForm);
        return "html/addIncentive";

    }

    @GetMapping("/report")
    public String searchIncentiveView(Model model){
        incentiveForm.setIncentiveDate(new Date());
        model.addAttribute("incentiveForm",incentiveForm);
        return "html/incentiveReports";
    }

    @GetMapping("/search")
    public String searchIncentives(ModelMap modelMap, @RequestParam Date incentiveDate) {
        System.out.println("##date "+incentiveDate);
        List<Incentives> incentivesList = incentiveService.searchIncentives(incentiveDate);

        modelMap.addAttribute("incentivesList", incentivesList);
        modelMap.addAttribute("incentiveForm",incentiveForm);
        return "html/incentiveReports";
    }


}
