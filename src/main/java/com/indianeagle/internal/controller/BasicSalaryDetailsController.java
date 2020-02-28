package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.enums.EmployeeStatusEnum;
//import com.indianeagle.internal.facade.BasicSalaryDetailsService;
import com.indianeagle.internal.service.BasicSalaryDetailsService;
//import com.indianeagle.internal.util.EmployeeStatusEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller to  render the BasicSalaryDetails Report
 *
 * @author CH.Srinath
 */
@Controller
public class BasicSalaryDetailsController {
    public Map<String, String> empStatusMap;
    @Autowired
    public BasicSalaryDetailsService basicSalaryDetailsService;

    @ModelAttribute(name = "empStatusList")
    public String employeeStatus(ModelMap model) {
        empStatusMap = new LinkedHashMap<String, String>();
        empStatusMap.put(EmployeeStatusEnum.ALL.name(), "All");
        empStatusMap.put(EmployeeStatusEnum.RELIEVED.name(), "Relieved");
        empStatusMap.put(EmployeeStatusEnum.RESIGNED.name(), "Resigned");
        empStatusMap.put(EmployeeStatusEnum.WORKING.name(), "Working");
        model.addAttribute("empStatusMap", empStatusMap);
        return "html/basicSalaryDetails";
    }

    /**
     * shows the BasicSalaryReport form
     *
     * @return String
     */
    @GetMapping("/basicSalaryDetailsForm")
    public String defaultBasicSalaryReport() {
        return "html/basicSalaryDetails";
    }

    /**
     * gives the BasicSalaryReport
     *
     * @return String
     */
    @PostMapping("/basicSalaryDetailsReport")
    public String basicSalaryReport(@RequestParam String empStatus, ModelMap model) {
        try {
            List<Employee> employeesList = basicSalaryDetailsService.getBasicSalaryDetails(empStatus);
            model.addAttribute("employeesList", employeesList);
        } catch (Exception e) {
            model.addAttribute("exceptionMessage", "service problem");
            return "html/basicSalaryDetails";
        }
        return "html/fragment/basicSalaryResult";
    }

}
