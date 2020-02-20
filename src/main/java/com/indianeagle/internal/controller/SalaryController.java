package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.GenerateAllSalariesForm;
import com.indianeagle.internal.form.SalaryForm;
import com.indianeagle.internal.form.vo.DepartmentVO;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.form.vo.SalaryHistoryVO;
import com.indianeagle.internal.service.DepartmentService;
import com.indianeagle.internal.service.SalaryHistoryService;
import com.indianeagle.internal.service.SalaryService;
import com.indianeagle.internal.util.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller to get salary details of all employees
 *
 * @Author : Taymur Shaikh
 * @Since  : 2020-02-11
 */

@Controller
public class SalaryController {
    private static final Logger log = LogManager.getLogger(SalaryController.class);
    @Autowired
    private SalaryHistoryService salaryHistoryService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private DepartmentService departmentService;

    private SalaryForm salaryForm;


    @PostConstruct
    public void loadData() {
        salaryForm = new SalaryForm();

        List departmentList = departmentService.loadAllDepartments();
        List<DepartmentVO> departmentVOList = new ArrayList<>();
        departmentList.stream().forEach(employee -> {
            DepartmentVO departmentVO = new DepartmentVO();
            BeanUtils.copyProperties(employee, departmentVO);
            departmentVOList.add(departmentVO);
        });

        List<Employee> employeeList = salaryService.loadActiveEmployees();
        List<EmployeeVO> employeeVOList = new ArrayList<>();
        employeeList.stream().forEach(employee -> {
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(employee, employeeVO);
            employeeVOList.add(employeeVO);
        });

        salaryForm.setEmployeeList(employeeVOList);
        salaryForm.setDepartmentList(departmentVOList);
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ModelAttribute
    public void model(ModelMap modelMap) {
        modelMap.addAttribute("salaryform", salaryForm);
        modelMap.addAttribute("generateAllSalariesForm", new GenerateAllSalariesForm());
    }

    @GetMapping("/salaryByIndividual")
    public String salaryByIndividual(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("salaryForm", salaryForm);
        return "html/salaryByIndividual";
    }


    @PostMapping("/generateSalary")
    public String generateSalary(ModelMap modelMap, HttpSession httpSession, @ModelAttribute("salaryForm") SalaryForm salaryForm) {
        log.warn((Object) "##########33generateSalary");
        try {
            Employee employee = salaryService.loadEmployee(salaryForm.getEmployeeId());
            List<SalaryHistory> salaryHistories = salaryHistoryService.findSalaryHistoryByEmpId(employee.getEmpId(), salaryForm.getSalaryRule().getSalaryDate(), salaryForm.getSalaryRule().getSalaryEndDate());

            salaryForm.setEmployeeList(this.salaryForm.getEmployeeList());
            if (salaryHistories != null && salaryHistories.size() > 0) {
                modelMap.addAttribute("error", "Salary already generated for this employee up to " + ((SalaryHistory) salaryHistories.get(0)).getSalaryEndDate());
                return "html/salaryByIndividual";
            }
            if (DateUtils.differenceInDays((Date) employee.getJoinDate(), (Date) salaryForm.getSalaryRule().getSalaryEndDate()) <= 0) {
                modelMap.addAttribute("error", "Salary is not applicable in this dates");
                return "html/salaryByIndividual";
            }
            SalaryHistory currentSalary = salaryService.generateSalary(employee, salaryForm.getSalaryRule());

            httpSession.setAttribute("currentSalary" + employee.getEmpId(), currentSalary);
            httpSession.setAttribute("employee" + employee.getEmpId(), employee);

            SalaryHistoryVO salaryHistoryVO = new SalaryHistoryVO();
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(currentSalary, salaryHistoryVO);
            BeanUtils.copyProperties(employee, employeeVO);
            salaryForm.setEmployee(employeeVO);
            salaryForm.setCurrentSalary(salaryHistoryVO);

            log.warn((Object) ("******** salary end date is:" + salaryForm.getSalaryRule().getSalaryEndDate()));
            return "html/netSalary";
        } catch (Exception e) {
            log.warn((Object) "##########33generateSalary exception", (Throwable) e);
            modelMap.addAttribute("error", "Error occured while generating salary");
            e.printStackTrace();
            return "html/salaryByIndividual";
        }
    }

    @GetMapping("/confirmAndSendMail")
    public String confirmAndSendMail(ModelMap model, HttpSession httpSession, @RequestParam String empId) {
        try {
            model.addAttribute("salaryForm", this.salaryForm);
            Employee employee = (Employee) httpSession.getAttribute("employee" + empId);
            SalaryHistory currentSalary = (SalaryHistory) httpSession.getAttribute("currentSalary" + empId);
            if (employee != null && currentSalary != null) {
                salaryService.confirmAndSendMail(employee, currentSalary);
                model.addAttribute("mailSent", "Saved And Send Mails Successfully");
            } else {
                model.addAttribute("error", "Something went wrong.Try again!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error occured due to technical problem");
            e.printStackTrace();
            return "html/salaryByIndividual";
        }
        return "html/salaryByIndividual";

    }

    @GetMapping("/eSalary")
    public String produceAllSalariesForm(ModelMap model, @ModelAttribute GenerateAllSalariesForm generateAllSalariesForm) {
        try {
            generateAllSalariesForm = salaryService.fillProduceAllSalariesForm(generateAllSalariesForm);
            model.addAttribute("generateAllSalariesForm", generateAllSalariesForm);
        } catch (Exception e) {
            model.addAttribute("error", "error occured due to technical problem");
            e.printStackTrace();
            return "html/eSalary";
        }
        return "html/eSalary";
    }

    @PostMapping("/searchAllESalaryEmployee")
    public String searchAllESalaryEmployee(ModelMap model, @ModelAttribute GenerateAllSalariesForm generateAllSalariesForm) {
        try {
            generateAllSalariesForm = salaryService.fillProduceAllSalariesForm(generateAllSalariesForm);
            model.addAttribute("generateAllSalariesForm", generateAllSalariesForm);
        } catch (Exception e) {
            model.addAttribute("error", "error occured due to technical problem");
            e.printStackTrace();
            return "html/fragment/eSalaryResult";
        }
        return "html/fragment/eSalaryResult";
    }


    @PostMapping("/produceAllSalaries")
    public String produce(ModelMap modelMap, HttpServletRequest servletRequest, HttpSession httpSession, @ModelAttribute GenerateAllSalariesForm generateAllSalariesForm) {
        log.warn((Object) "###produceAllSalaries started");
        List<SalaryHistory> currentSalaryList;
        try {
            currentSalaryList = salaryService.produceAllSalaries(generateAllSalariesForm, httpSession);
            modelMap.addAttribute("currentSalaryList", currentSalaryList);
        } catch (Exception e) {
            modelMap.addAttribute("error", "error occured due to technical problem");
            e.printStackTrace();
            return "html/eSalary";
        }
        log.warn((Object) "###produceAllSalaries end");
        return "html/eSalaryReport";
    }

   @GetMapping("/saveSalaries")
   @ResponseBody
   public String saveSalaries(ModelMap modelMap,HttpSession httpSession){
       try {
           this.salaryService.saveSalaries(httpSession);
           return "Saved salaries for later modification successfully.";
       } catch (Exception e) {
           e.printStackTrace();
           return "Error occured due to technical error.";
       }
   }


    @GetMapping("/confirmAndSendPaySlipMails")
    public String confirmAndSendPaySlipMails(ModelMap model, HttpSession httpSession, GenerateAllSalariesForm generateAllSalariesForm) {
        try {
            this.salaryService.sendAllPaySlipMails(httpSession);
            model.addAttribute("mailSent", "Saved And Send Mails Successfully");
            return produceAllSalariesForm(model, generateAllSalariesForm);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "eSalary";
        }
    }


    /*@GetMapping("/populateEmployees")
    public String populateEmployees(ModelMap model, SalaryForm salaryForm) {
        List deptEmployeeList = this.salaryService.loadEmployeesByDepartment(this.department);
        if (this.newDept != 1) {
            salaryForm.setDeptEmployeeList(SimpleUtils.removeEmpsFromExcludedEmps((List) deptEmployeeList, (String[]) salaryForm.getSalaryRule().getDeptExcludedEmpList()));
        } else {
            salaryForm.setDeptEmployeeList(deptEmployeeList);
            salaryForm.getSalaryRule().setDeptExcludedEmpList(null);
        }
        return "populateEmployees";
    }*/

    private EmployeeVO getEmployee(String employeeId, SalaryForm salaryForm) {
        List<EmployeeVO> employeeList = salaryForm.getEmployeeList();
        for (EmployeeVO employee : employeeList) {
            if (employee.getEmpId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }
}
