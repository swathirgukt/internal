package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.EmpSalaryDecider;
import com.indianeagle.internal.form.GenerateAllSalariesForm;
import com.indianeagle.internal.form.SalaryForm;
import com.indianeagle.internal.service.DepartmentService;
import com.indianeagle.internal.service.SalaryHistoryService;
import com.indianeagle.internal.service.SalaryService;
import com.indianeagle.internal.util.DateUtils;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * Controller to get salary details of all employees
 *
 * @author
 */

@Controller
public class SalaryController {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SalaryController.class);
    /*@Autowired
    private SalaryForm salaryForm;*/
    @Autowired
    private SalaryHistoryService salaryHistoryService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private DepartmentService departmentService;
    /* @Autowired
     private GenerateAllSalariesForm generateAllSalariesForm;*/

    List<SalaryHistory> listOfChangedSalaries;
    //@Autowired
    private HttpServletRequest servletRequest;

    private List<SalaryHistory> currentSalaryList;

    private String department;
    private int newDept;
    private int allSalariesFormActionInd = 1;

    /**
     * Method to load  before request comes
     *
     * @return
     */

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ModelAttribute(name = "salarieslist")
    public void salaryList(ModelMap model) {
        SalaryForm salaryForm = new SalaryForm();
        model.addAttribute("salaryForm", salaryForm);
        List departmentList = departmentService.loadAllDepartments();
        List employeeList = salaryService.loadActiveEmployees();
        // this.
        salaryForm.setEmployeeList(employeeList);
        //this.
        salaryForm.setDepartmentList(departmentList);
        GenerateAllSalariesForm generateAllSalariesForm = new GenerateAllSalariesForm();
        model.addAttribute("generateAllSalariesForm", generateAllSalariesForm);
    }

    @GetMapping("/eSalary")
    public String salaryByIndividual() throws Exception {
        return "html/salaryByIndividual";
    }


    /**
     * Method to generate salary of an employee
     */
    @PostMapping("/generateSalary")
    public String generateSalary(ModelMap model, @Valid @ModelAttribute("salaryForm") SalaryForm salaryForm, BindingResult bindingResult) {
        System.out.println("SalaryController.generateSalary");
        log.warn((Object) "##########33generateSalary");
        if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            return "html/salaryByIndividual";
        }

        try {
            Employee employee = getEmployee(salaryForm.getEmployeeId(), salaryForm);
            List<SalaryHistory> salaryHistories = salaryHistoryService.findSalaryHistoryByEmpId(employee.getEmpId(), salaryForm.getSalaryRule().getSalaryDate(), salaryForm.getSalaryRule().getSalaryEndDate());
            if (salaryHistories != null && salaryHistories.size() > 0) {
                model.addAttribute("salaryAlreadyCredited", "Salary already generated for this employee up to " + ((SalaryHistory) salaryHistories.get(0)).getSalaryEndDate());
                //  bindingResult.reject("Salary already generated for this employee up to " + ((SalaryHistory) salaryHistories.get(0)).getSalaryEndDate());
                return "html/salaryByIndividual";
            }
            if (DateUtils.differenceInDays((Date) employee.getJoinDate(), (Date) salaryForm.getSalaryRule().getSalaryEndDate()) <= 0) {


                //bindingResult.reject("Salary is not applicable in this dates");
                model.addAttribute("invalidDates", "Salary is not applicable in this dates");
                return "html/salaryByIndividual";
            }
            salaryForm.setEmployee(employee);
            SalaryHistory currentSalary = salaryService.generateSalary(employee, salaryForm.getSalaryRule());
            salaryForm.setCurrentSalary(currentSalary);
            model.addAttribute("salaryform", salaryForm);
            log.warn((Object) ("******** salary end date is:" + salaryForm.getSalaryRule().getSalaryEndDate()));

            //model.addAttribute("message", "success");
            return "html/netSalary";

        } catch (Exception e) {
            log.warn((Object) "##########33generateSalary exception", (Throwable) e);
            // bindingResult.reject("Error occured while generating salary");
            model.addAttribute("exceptionMessage", "Error occured while generating salary");
            e.printStackTrace();
            return "html/salaryByIndividual";
        }

    }

    @GetMapping("/confirmAndSendMail")
    public String confirmAndSendMail(ModelMap model, HttpServletRequest request) {
        try {
            salaryService.confirmAndSendMail(SimpleUtils.getContextPath(request));
        } catch (Exception e) {
            //this.addActionError("error occured due to technical problem");
            model.addAttribute("technicalerrormessage", "error occured due to technical problem");

            e.printStackTrace();
            //return "netSalary";
            return "html/salaryByIndividual";
        }
        return "html/mail";
    }

    @GetMapping("/produceAllSalariesForm")
    public String produceAllSalariesForm(ModelMap model, GenerateAllSalariesForm generateAllSalariesForm) {
        try {
            generateAllSalariesForm = this.salaryService.fillProduceAllSalariesForm(generateAllSalariesForm);
            System.out.println(generateAllSalariesForm.getEmpSalaryDeciderList());
            //model.addAttribute("empSalaryDeciderList", generateAllSalariesForm.getEmpSalaryDeciderList());

        } catch (Exception e) {
            // this.addActionError("error occured due to technical problem");
            model.addAttribute("technicalerrormessage", "error occured due to technical problem");

            e.printStackTrace();
            return "html/eSalary";
        }
        return "html/eSalary";
    }

    @GetMapping("/produceAllSalaries")
    public String produceAllSalaries(ModelMap model, GenerateAllSalariesForm generateAllSalariesForm) {
        log.warn((Object) "Vijay produceAllSalaries method is called");
        this.listOfChangedSalaries = new ArrayList<SalaryHistory>();
        ArrayList<String> empIdsOfDeductedSalaries = new ArrayList<String>();
        try {
            if (!SimpleUtils.reqParamExist((Enumeration) this.servletRequest.getParameterNames())) {
                for (EmpSalaryDecider empSalaryDecider : generateAllSalariesForm.getEmpSalaryDeciderList()) {
                    if ((empSalaryDecider.getLopDays() == null || empSalaryDecider.getLopDays().equals(BigDecimal.ZERO)) && empSalaryDecider.getSalaryInAdvance().equals(BigDecimal.ZERO) && empSalaryDecider.getArrearsDays().equals(BigDecimal.ZERO))
                        continue;
                    empIdsOfDeductedSalaries.add(empSalaryDecider.getEmpId().trim());
                }
                this.currentSalaryList = this.salaryService.produceAllSalaries(generateAllSalariesForm);
                if (null != empIdsOfDeductedSalaries && !empIdsOfDeductedSalaries.isEmpty()) {
                    for (SalaryHistory salaryHistory : this.currentSalaryList) {
                        if (!empIdsOfDeductedSalaries.contains(salaryHistory.getEmpId().trim())) continue;
                        this.listOfChangedSalaries.add(salaryHistory);
                    }
                }
                model.addAttribute("currentSalaryList", this.currentSalaryList);
                model.addAttribute("editedSalaryList", this.listOfChangedSalaries);
                //this.session.put("currentSalaryList", this.currentSalaryList);
                //  this.session.put("editedSalaryList", this.listOfChangedSalaries);
            } else {
                this.currentSalaryList = (List<SalaryHistory>) model.getAttribute("currentSalaryList");
                this.listOfChangedSalaries = (List<SalaryHistory>) model.getAttribute("editedSalaryList");


                //this.currentSalaryList = (List)this.session.get("currentSalaryList");
                //this.listOfChangedSalaries = (List)this.session.get("editedSalaryList\t");
            }
        } catch (Exception e) {
            //this.addActionError("error occured due to technical problem");
            model.addAttribute("exceptionMessage", "error occured due to technical problem");
            e.printStackTrace();
            return "generateAllSalaries";
        }
        log.warn("Vijay produceAllSalaries method is end");
        return "generateAllSalaries";
    }

    @GetMapping("/generateAllSalaries")
    public String generateAllSalaries(ModelMap model, @ModelAttribute("salaryForm") SalaryForm salaryForm, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // model.addAttribute("message", "Validate error");
                bindingResult.reject("Validate error");
                return "generateSalary";
            }

            if (!SimpleUtils.reqParamExist((Enumeration) this.servletRequest.getParameterNames())) {

                this.currentSalaryList = this.salaryService.generateSalaries(this.department, salaryForm.getSalaryRule());
                model.addAttribute("currentSalaryList", this.currentSalaryList);
                // this.session.put("currentSalaryList", this.currentSalaryList);
            } else {
                this.currentSalaryList = (List) model.getAttribute("currentSalaryList");

                // this.currentSalaryList = (List)this.session.get("currentSalaryList");
            }
        } catch (Exception e) {
            //this.addActionError("error occured due to technical problem");
            model.addAttribute("exceptionMessage", "error occured due to technical problem");
            e.printStackTrace();
            return "generateSalary";
        }
        return "generateSalary";
    }

    @GetMapping("/confirmAndSendPaySlipMails")
    public String confirmAndSendPaySlipMails(ModelMap model, GenerateAllSalariesForm generateAllSalariesForm) {
        try {
            this.salaryService.sendAllPaySlipMails(SimpleUtils.getContextPath((HttpServletRequest) this.servletRequest));
            // this.addActionMessage("Saved And Send Mails Successfully");
            model.addAttribute("mailMessage", "Saved And Send Mails Successfully");
            this.produceAllSalariesForm(model, generateAllSalariesForm);
            return "generateAllSalaries";
        } catch (Exception e) {
            // this.addActionError(e.getMessage());
            model.addAttribute("exceptionMessage", "e.getMessage()");
            e.printStackTrace();
            return "generateAllSalaries";
        }
    }


    @GetMapping("/populateEmployees")
    public String populateEmployees(ModelMap model, SalaryForm salaryForm) {
        List deptEmployeeList = this.salaryService.loadEmployeesByDepartment(this.department);
        if (this.newDept != 1) {
            salaryForm.setDeptEmployeeList(SimpleUtils.removeEmpsFromExcludedEmps((List) deptEmployeeList, (String[]) salaryForm.getSalaryRule().getDeptExcludedEmpList()));
        } else {
            salaryForm.setDeptEmployeeList(deptEmployeeList);
            salaryForm.getSalaryRule().setDeptExcludedEmpList(null);
        }
        return "populateEmployees";
    }


    //TODO: remove this method after testing
    @PostMapping("/saveSalaryTest")
    public String test(@ModelAttribute @Valid SalaryForm sf, BindingResult bindingResult) {
        System.out.println(
                "##SalaryForm:  id:" + sf.getEmployeeId() +
                        " d1:" + sf.getSalaryRule().getSalaryDate() +
                        " d2:" + sf.getSalaryRule().getSalaryEndDate() +
                        " lop:" + sf.getSalaryRule().getLopDays() +
                        " arr:" + sf.getSalaryRule().getArrearsDays() +
                        " inc:" + sf.getSalaryRule().getPerformanceIncentives() +
                        " misc:" + sf.getSalaryRule().getMiscDeductions() +
                        " adv:" + sf.getSalaryRule().getSalaryInAdvance());

        if (bindingResult.hasErrors())
            return "html/salaryByIndividual";
        return "html/salaryByIndividual";
    }

   /* public boolean validate(SalaryForm salaryForm) {
        boolean status = false;
        if (SimpleUtils.isEmpty((Date)salaryForm.getSalaryRule().getSalaryDate())) {
            this.addActionError("Salary Date is required");
        }
        if (SimpleUtils.isEmpty((Date)salaryForm.getSalaryRule().getSalaryEndDate())) {
            this.addActionError("Salary End Date is required");
        }
        if (this.hasActionErrors()) {
            status = true;
        }
        return status;
    }*/

    private Employee getEmployee(String employeeId, SalaryForm salaryForm) {
        List<Employee> employeeList = salaryForm.getEmployeeList();
        for (Employee employee : employeeList) {
            if (employee == null || !employee.getId().equals(employeeId)) continue;
            return employee;
        }
        return null;
    }

    /*public SalaryForm getSalaryForm() {
        return salaryForm;
    }

    public void setSalaryForm(SalaryForm salaryForm) {
        this.salaryForm = salaryForm;
    }*/

    public SalaryService getSalaryService() {
        return this.salaryService;
    }

    public void setSalaryService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public List<SalaryHistory> getCurrentSalaryList() {
        return this.currentSalaryList;
    }

    public void setCurrentSalaryList(List<SalaryHistory> currentSalaryList) {
        this.currentSalaryList = currentSalaryList;
    }

   /* public Map getSession() {
        return this.session;
    }

    public void setSession(Map session) {
        this.session = session;
    }*/

    public DepartmentService getDepartmentService() {
        return this.departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNewDept() {
        return this.newDept;
    }

    public void setNewDept(int newDept) {
        this.newDept = newDept;
    }

    /*public GenerateAllSalariesForm getGenerateAllSalariesForm() {
        return this.generateAllSalariesForm;
    }

    public void setGenerateAllSalariesForm(GenerateAllSalariesForm generateAllSalariesForm) {
        this.generateAllSalariesForm = generateAllSalariesForm;
    }*/

    public int getAllSalariesFormActionInd() {
        return this.allSalariesFormActionInd;
    }

    public void setAllSalariesFormActionInd(int allSalariesFormActionInd) {
        this.allSalariesFormActionInd = allSalariesFormActionInd;
    }

    public List<SalaryHistory> getListOfChangedSalaries() {
        return this.listOfChangedSalaries;
    }

    public SalaryHistoryService getSalaryHistoryService() {
        return this.salaryHistoryService;
    }

    public void setSalaryHistoryService(SalaryHistoryService salaryHistoryService) {
        this.salaryHistoryService = salaryHistoryService;
    }
}
