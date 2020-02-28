package com.indianeagle.internal.controller;

import com.indianeagle.internal.dao.repository.FinancialYearRepository;
import com.indianeagle.internal.dao.repository.SalaryHistoryRepository;
import com.indianeagle.internal.dto.*;
import com.indianeagle.internal.form.*;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.service.DepartmentService;
import com.indianeagle.internal.service.MonthlyReportService;
import com.indianeagle.internal.util.Form16Utils;
import com.indianeagle.internal.util.SimpleUtils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;


import java.text.Normalizer;
import java.util.*;

import static com.indianeagle.internal.util.Form16Utils.*;

@Controller
public class Form16Controller {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    FinancialYearRepository financialYearRepository;
    @Autowired
    SalaryHistoryRepository salaryHistoryRepository;
    @Autowired
    private MonthlyReportService monthlyReportService;

    private BigDecimal grossSalary;
    private Form16GenerationForm form16GenerationForm;
    private List<EmployeeFinancialYear> employeeFinancialYears;



    private static final BigDecimal EDU_CESS = new BigDecimal(3);
    private static final BigDecimal HUNDRED = new BigDecimal(100);
    private Employee employee;

    private BigDecimal totalSavings = BigDecimal.ZERO;


   /* @ModelAttribute
    public String prepare(ModelMap modelMap) {
        EmployeeFinancialYearForm employeeFinancialYearForm = new EmployeeFinancialYearForm();
        modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
        return "html/employeeSavings";
    }*/

    /**
     * FinancialYear Form is created and sent to front end and
     * available for every request
     *
     * @param modelMap
     * @return
     */
    @ModelAttribute
    public String prepareForm16(ModelMap modelMap) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        //Employee employee= new Employee();
        modelMap.addAttribute("financialYearForm", financialYearForm);
        //  modelMap.addAttribute("employee",employee);

        return "html/form16Generation";
    }


    /**
     * Creates And send EmployeeFinancialYear Form Object for binding values in thymeleaf
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/form16Generation")
    public String Form16Generation(ModelMap modelMap) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        EmployeeFinancialYearForm employeeFinancialYearForm = new EmployeeFinancialYearForm();
        modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
        //modelMap.addAttribute("financialYearForm", financialYearForm);

        return "html/Form16Generation";
    }

    /**
     * search employee by name
     * returns a new html page which is get attached to the form16generation page
     *
     * @param modelMap
     * @param employeeFinancialYearForm
     * @param empName
     * @return
     */
    @RequestMapping("/searchEmployeeByNameInForm16Generation")
    public String searchEmployeeByNameInForm16Generation(ModelMap modelMap, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm, @RequestParam String empName) {
        List<Employee> employees = null;

        //if(employeeFinancialYearForm.getFinancialYear()!=null
        // FinancialYearForm financialYearForm=Form16Utils.prepareFinancialYearForm(employeeFinancialYearForm.getFinancialYear());
        if (empName != null && (!empName.equals("")) && empName.length() >= 3) {

            employees = departmentService.findEmployeeByName(empName);

        } else {
            modelMap.addAttribute("retrieveNameError", "Please enter at least three letters to search or there is no employee with the entered name");


            return "html/searchEmployeeForm16Results";
        }
        if (employees == null || employees.isEmpty()) {
            modelMap.addAttribute("nameError", "enter a valid name");


        }

        modelMap.addAttribute("employsList", employees);

        return "html/searchEmployeeForm16Results";

    }


    /**
     * retrieve employee info based on the empid and financial year
     * provided in the form16generation form Employee Details section
     *
     * @param modelMap
     * @param employeeFinancialYearForm
     * @return
     */
    @PostMapping("/retrieveEmployeeInfoInForm16")
    public String retrieveSalaryInfoForForm16(ModelMap modelMap, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm, HttpSession httpSession) {

        TotalSalaryHistoryForm totalSalaryHistoryForm = new TotalSalaryHistoryForm();
        List<SalaryHistory> salaryHistories = null;
        List<EmployeeIncomeTax> employeeIncomeTaxes=null;
        Employee employee=null;
        List<Integer> remainedMonths=new ArrayList();
        Form16GenerationForm form16GenerationForm = new Form16GenerationForm();
        BigDecimal grossSalary=BigDecimal.ZERO;
        BigDecimal totalSavings=BigDecimal.ZERO;

        if (employeeFinancialYearForm.getEmpId() != null && !employeeFinancialYearForm.getEmpId().equals("")) {
            FinancialYearForm financialYearForm = new FinancialYearForm();
            FinancialYear financialYear;


            String empId = employeeFinancialYearForm.getEmpId();
            employee = departmentService.findEmployeeByEmployeeId(empId);
            financialYear = employeeFinancialYearForm.getFinancialYear();


            Date fromDate = Form16Utils.prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
            Date toDate = Form16Utils.prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());

            //this.employeeIncomeTaxes = employeeIncomeTaxes;
           // salaryHistories = salaryHistoryRepository.findByEmpIdAndSalaryEndDateBetween(empId, fromDate, toDate);
           // this.salaryHistories = salaryHistories;
            List list=loadRequiredData(employeeFinancialYearForm);
            remainedMonths= (List<Integer>) list.get(0);
            totalSalaryHistoryForm= (TotalSalaryHistoryForm) list.get(1);
            salaryHistories= (List<SalaryHistory>) list.get(2);
            if (salaryHistories == null || salaryHistories.size() == 0) {
                modelMap.addAttribute("noSalary", "No Salary Records Found");
                modelMap.addAttribute("employee", employee);
                // System.out.println(form16GenerationForm.getId());
                // System.out.println("#####employeeIncomeTaxId:  "+employeeIncomeTaxes.get(0).getId());
                return "html/Form16Generation";
            }


            modelMap.addAttribute("remainedMonths", remainedMonths);
            System.out.println("remained months size"+remainedMonths.size());
            modelMap.addAttribute("salaryHistories", salaryHistories);
            modelMap.addAttribute("totalSalaryHistoryForm", totalSalaryHistoryForm);
            System.out.println("###totalSalaryHistryform"+totalSalaryHistoryForm.toString());
            modelMap.addAttribute("lastSalary", salaryHistories.get(salaryHistories.size() - 1));


        } else {
            modelMap.addAttribute("employeeId", "Please Provide Employee Id");
            return "html/Form16Generation";

        }


        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        String empId = employeeFinancialYearForm.getEmpId();
        employee = departmentService.findEmployeeByEmployeeId(empId);
        modelMap.addAttribute("employee", employee);
        if (employee == null) {
            modelMap.addAttribute("employeeerror", "No employee Found");
            return "html/Form16Generation";
        }

        List<FinancialYear> financialYears = financialYearRepository.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (financialYears.size() > 0) {

        }
         //TODO:CHANGE TO ONE METHOD
            grossSalary=setGrossSalary(totalSalaryHistoryForm);
            modelMap.addAttribute("grossSalary", grossSalary);

        //todo:change to one method
        List<EmployeeFinancialYear> employeeFinancialYears = departmentService.findEmployeeFinancialYearWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
       /* if (employeeFinancialYears != null && !employeeFinancialYears.isEmpty()) {
            employeeFinancialYearForm = Form16Utils.prepareEmployeeFinancialYearForm(employeeFinancialYears.get(0));
        }
*/
        try {
            employeeFinancialYearForm = generateEmployeeFinancialYearForm(empId,financialYear,employeeFinancialYearForm);
            totalSavings=updateSectionLimit(employeeFinancialYearForm);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("financialerror", "No Records Found");
            return "html/Form16Generation";
        }

        form16GenerationForm=updateHraForm16(empId,salaryHistories, financialYear, employeeFinancialYears,financialYears);
        modelMap.addAttribute("salaryHistories", salaryHistories);
        modelMap.addAttribute("totalSalaryHistoryForm", totalSalaryHistoryForm);

        modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);

        modelMap.addAttribute("form16GenerationForm", form16GenerationForm);
        modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
       // System.out.println(employeeFinancialYearForm.getEmployeeTaxSectionForms().get(0).getEmployeeTaxSectionDeclarations().get(0).getSubSectionName());
      //  System.out.println(totalSavings);
        return "html/Form16Generation";
    }

    /**
     * sets gross salary
     * @param totalSalaryHistoryForm
     * @return
     */
    private BigDecimal setGrossSalary(TotalSalaryHistoryForm totalSalaryHistoryForm) {
        return totalSalaryHistoryForm!=null?totalSalaryHistoryForm.getTotalGrossSalary():BigDecimal.ZERO;

    }


    private BigDecimal updateSectionLimit(EmployeeFinancialYearForm employeeFinancialYearForm) {
        BigDecimal toatalSaveAmountFromTaxSections = BigDecimal.ZERO;
        if (employeeFinancialYearForm.getEmployeeTaxSectionForms() != null) {

            for (EmployeeTaxSectionForm employeeTaxSectionForm : employeeFinancialYearForm.getEmployeeTaxSectionForms()) {
                BigDecimal saveAmount = BigDecimal.ZERO;
                if (employeeTaxSectionForm.getEmployeeTaxSectionDeclarations() != null) {
                    for (EmployeeTaxSectionDeclaration employeeTaxSectionDeclaration : employeeTaxSectionForm.getEmployeeTaxSectionDeclarations()) {
                        if (!employeeTaxSectionDeclaration.getSubSectionName().trim().equalsIgnoreCase("HRA")) {

                            if (employeeTaxSectionDeclaration.getSaveAmount() != null) {
                                saveAmount = saveAmount.add(employeeTaxSectionDeclaration.getSaveAmount());
                            }
                        }
                    }
                }
                if (saveAmount.compareTo(employeeTaxSectionForm.getSectionLimit()) == -1) {

                    employeeTaxSectionForm.setSectionLimit(saveAmount);
                    toatalSaveAmountFromTaxSections = toatalSaveAmountFromTaxSections.add(saveAmount);

                }
            }


        }
        return toatalSaveAmountFromTaxSections;
    }

    /**
     * Tax is calculated for the employee's income
     * generates new html page and result is attached to the form16GeneartionForm
     * calculate tax section
     *
     * @param modelMap
     * @param httpSession
     * @param employeeFinancialYearForm
     * @param incomeEarnedByEmployee
     * @return
     */
    @RequestMapping("/calculateTax")
    public String calculateTax(ModelMap modelMap, HttpSession httpSession, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm, @RequestParam(name = "incomeEarnedByEmployee") String incomeEarnedByEmployee,
                               @RequestParam(name = "incentives") String incentives, @RequestParam String plb, @RequestParam String reimbursement, @RequestParam String bonus, @RequestParam String others, @RequestParam String previousCompanyIncome) {

        String empId=employeeFinancialYearForm.getEmpId();
        FinancialYear financialYear=employeeFinancialYearForm.getFinancialYear();
        TotalSalaryHistoryForm totalSalaryHistoryForm=null;
        List<SalaryHistory> salaryHistories=null;
        Form16GenerationForm form16GenerationForm=null;
        List<Integer> remainedMonths=new ArrayList<>();
        List<EmployeeFinancialYear> employeeFinancialYears=null;
        BigDecimal grossSalary=BigDecimal.ZERO;
        BigDecimal totalSavings=BigDecimal.ZERO;
        BigDecimal totalTdcDeducted=BigDecimal.ZERO;
        Date lastSalaryDate=null;

        List list=loadRequiredData(employeeFinancialYearForm);

        remainedMonths= (List<Integer>) list.get(0);
        totalSalaryHistoryForm= (TotalSalaryHistoryForm) list.get(1);
        salaryHistories= (List<SalaryHistory>) list.get(2);
        grossSalary=setGrossSalary(totalSalaryHistoryForm);

        if (totalSalaryHistoryForm == null || employeeFinancialYearForm == null || salaryHistories == null || employeeFinancialYearForm.getEmpId().equals("")) {
            modelMap.addAttribute("valuesError", "InSufficient Data So Tax can't be calculated");
            return "html/calculateTaxResults";
        }
        totalTdcDeducted=totalSalaryHistoryForm.getTotalTdsDeducted();
        lastSalaryDate=salaryHistories.get(salaryHistories.size()-1).getSalaryDate();
        employeeFinancialYears=departmentService.findEmployeeFinancialYearWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        List<FinancialYear> financialYears = financialYearRepository.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        form16GenerationForm=updateHraForm16(empId,salaryHistories,financialYear,employeeFinancialYears,financialYears);
        employeeFinancialYearForm= generateEmployeeFinancialYearForm(empId,financialYear,employeeFinancialYearForm);
        totalSavings=updateSectionLimit(employeeFinancialYearForm);
        setPropertiesToForm16(form16GenerationForm, incentives, plb, bonus, reimbursement, previousCompanyIncome, others);
        form16GenerationForm=form16CalculateTax(form16GenerationForm,incomeEarnedByEmployee, employeeFinancialYearForm,grossSalary,totalSavings,financialYears,lastSalaryDate,totalTdcDeducted);

        modelMap.addAttribute("form16GenerationForm", form16GenerationForm);
        System.out.println(this.form16GenerationForm);
        return "html/calculateTaxResults";
    }


    @RequestMapping("/saveAsPdf")

    public ResponseEntity<byte[]> saveForm16PDF(ModelMap modelMap, HttpServletRequest httpServletRequest, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm) {
        HttpHeaders response = new HttpHeaders();
        byte[] bytes = null;
        InputStream inputStream;
        String fileName;
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        Date fromDate = prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
        Date toDate = prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());
        List<SalaryHistory> salaryHistories;
        salaryHistories = departmentService.findSalaryHistoriesWithInFinancialYear(employeeFinancialYearForm.getEmpId(), fromDate, toDate);
        List<FinancialYear> financialYears = departmentService.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        List<EmployeeIncomeTax> employeeIncomeTaxes = departmentService.findEmployeeIncomeTaxWithEmpIdAndFinancialYear(employeeFinancialYearForm.getEmpId(), financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());


        try {

            inputStream = departmentService.saveForm16PDF(employeeFinancialYearForm.getEmpId(), financialYears.get(0), salaryHistories, employeeIncomeTaxes.get(0), employeeFinancialYears.get(0), SimpleUtils.getContextPath(httpServletRequest));

            fileName = employeeFinancialYearForm.getEmpId().concat("-(").concat(employeeFinancialYearForm.getFinancialYear().getFromYear()).concat("-").concat(employeeFinancialYearForm.getFinancialYear().getToYear()).concat(")");
            response.setContentType(MediaType.APPLICATION_PDF);
            response.add("Content-Disposition", "inline; filename=" + fileName + ".pdf");
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);


        } catch (Exception ex) {
            try {
                fileName = employeeFinancialYearForm.getEmpId().concat("-(").concat(employeeFinancialYearForm.getFinancialYear().getFromYear()).concat("-").concat(employeeFinancialYearForm.getFinancialYear().getToYear()).concat(")");
                response.setContentType(MediaType.APPLICATION_PDF);
                response.add("Content-Disposition", "inline; filename=" + fileName + ".pdf");

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        ResponseEntity<byte[]> responseEntity = new ResponseEntity(bytes, response, HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping("saveAsExcel")
    public ResponseEntity<byte[]> saveForm16EXCEL(@ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm) {
        ResponseEntity<byte[]> responseEntity = null;
        InputStream inputStream;
        String fileName;
        HttpHeaders httpHeaders = new HttpHeaders();
        byte[] bytes = null;
        List<SalaryHistory> salaryHistories;
        List<Integer> remainedMonths;
        TotalSalaryHistoryForm totalSalaryHistoryForm;
        List list=loadRequiredData(employeeFinancialYearForm);
        remainedMonths= (List<Integer>) list.get(0);
        totalSalaryHistoryForm= (TotalSalaryHistoryForm) list.get(1);
        salaryHistories= (List<SalaryHistory>) list.get(2);

        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        Date fromDate = prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
        Date toDate = prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());
        salaryHistories = departmentService.findSalaryHistoriesWithInFinancialYear(employeeFinancialYearForm.getEmpId(), fromDate, toDate);
        totalSalaryHistoryForm = prepareTotalSalary(salaryHistories, financialYear);
        try {
            if (salaryHistories != null && !salaryHistories.isEmpty()) {
                int remainingMonths = calculateUnSalariedMonths(financialYear, salaryHistories.get(salaryHistories.size() - 1).getSalaryDate());
                remainedMonths = new ArrayList();
                for (int i = 0; i <= remainingMonths; i++) {
                    remainedMonths.add(i);
                }
            }


            HSSFWorkbook hssfWorkbook = genarateForm16Excel(salaryHistories, totalSalaryHistoryForm, remainedMonths);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            hssfWorkbook.write(byteArrayOutputStream);
            inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            fileName = employeeFinancialYearForm.getEmpId().concat("-(").concat(employeeFinancialYearForm.getFinancialYear().getFromYear()).concat("-").concat(employeeFinancialYearForm.getFinancialYear().getToYear()).concat(")");
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);


            //  response.setContentType("application/vnd.ms-excel");
            //httpHeaders.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.add("Content-Disposition", "inline; filename=" + fileName + ".xls");
            // org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
            // response.getOutputStream().flush();
            responseEntity = new ResponseEntity(bytes, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return responseEntity;
    }

    /**
     * Mail will be triggered if only if all the required data present
     * returns result page which is get attached to the form16generation html
     *
     * @param modelMap
     * @param employeeFinancialYearForm
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/sendMail")
    public String sendMailEmployeeIncomeTax(ModelMap modelMap, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm, HttpServletRequest httpServletRequest) {
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        String empId = employeeFinancialYearForm.getEmpId();
        List<SalaryHistory> salaryHistories;

        Date fromDate = prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
        Date toDate = prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());
        salaryHistories = departmentService.findSalaryHistoriesWithInFinancialYear(empId, fromDate, toDate);
        List<FinancialYear> financialYears = departmentService.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        List<EmployeeIncomeTax> employeeIncomeTaxes = departmentService.findEmployeeIncomeTaxWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (employeeIncomeTaxes == null || employeeIncomeTaxes.isEmpty()) {
            modelMap.addAttribute("employeeTax", "employeTax is Emplty");
            return "html/form16EmailResult";
        }
        List<EmployeeFinancialYear> employeeFinancialYears = departmentService.findEmployeeFinancialYearWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (employeeFinancialYears == null || employeeFinancialYears.isEmpty()) {
            modelMap.addAttribute("employeeFinanciaalYear", "employeeFinanciaalYear is Emplty");
            return "html/form16EmailResult";
        }
        departmentService.sendMailEmployeeIncomeTax(empId, financialYears.get(0), salaryHistories, employeeIncomeTaxes.get(0), employeeFinancialYears.get(0), SimpleUtils.getContextPath(httpServletRequest));
        //todo:: why do we need to calculate tax here?
        // calculateTax();
        // emailSent = true;
        modelMap.addAttribute("emailSent", "email sent successfully");
        return "html/form16EmailResult";
    }


    @PostMapping("/saveEmployeeIncomeTax")
    public String saveEmployeeIncomeTax(ModelMap modelMap, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm, @RequestParam("employee.designation") String designation, @RequestParam("employee.panNo") String panNo, @RequestParam("employee.address") String address,
                                        @RequestParam String incentives, @RequestParam String plb, @RequestParam String reimbursement, @RequestParam String bonus, @RequestParam String others, @RequestParam String previousCompanyIncome, @RequestParam("incomeEarnedByEmployee") String incomeEarnedByEmployee) {
        try {
        EmployeeIncomeTax employeeIncomeTax = new EmployeeIncomeTax();
        EmployeeVO employeeVO = new EmployeeVO();
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        List<SalaryHistory> salaryHistories = null;
        TotalSalaryHistoryForm totalSalaryHistoryForm = null;
        List<Integer> remainedMonths = new ArrayList<>();
        BigDecimal grossSalary = BigDecimal.ZERO;
        Form16GenerationForm form16GenerationForm = null;
        String empId = employeeFinancialYearForm.getEmpId();
        List<EmployeeFinancialYear> employeeFinancialYears;
        BigDecimal totalSavings = BigDecimal.ZERO;
        Employee employee = null;
        Date lastSalaryDate = null;
        BigDecimal totalTdcDeducted = BigDecimal.ZERO;


           List list = loadRequiredData(employeeFinancialYearForm);
           remainedMonths = (List<Integer>) list.get(0);
           totalSalaryHistoryForm = (TotalSalaryHistoryForm) list.get(1);
           salaryHistories = (List<SalaryHistory>) list.get(2);
           grossSalary = setGrossSalary(totalSalaryHistoryForm);
           employee = departmentService.findEmployeeByEmployeeId(empId);
           if (salaryHistories != null && !salaryHistories.isEmpty()) {
               lastSalaryDate = salaryHistories.get(salaryHistories.size() - 1).getSalaryDate();
           }
           if (totalSalaryHistoryForm != null) {
               totalSavings = totalSalaryHistoryForm.getTotalTdsDeducted();
           }

           employeeFinancialYears = departmentService.findEmployeeFinancialYearWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
           List<FinancialYear> financialYears = financialYearRepository.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
           form16GenerationForm = updateHraForm16(empId, salaryHistories, financialYear, employeeFinancialYears, financialYears);
           employeeFinancialYearForm = generateEmployeeFinancialYearForm(empId, financialYear, employeeFinancialYearForm);
           totalSavings = updateSectionLimit(employeeFinancialYearForm);
           if (form16GenerationForm != null) {
               setPropertiesToForm16(form16GenerationForm, incentives, plb, bonus, reimbursement, previousCompanyIncome, others);
               form16GenerationForm = form16CalculateTax(form16GenerationForm, incomeEarnedByEmployee, employeeFinancialYearForm, grossSalary, totalSavings, financialYears, lastSalaryDate, totalTdcDeducted);

       }

         if (totalSalaryHistoryForm == null || form16GenerationForm == null || salaryHistories == null || employeeFinancialYearForm.getEmpId().equals("")) {
               // System.out.println(form16GenerationForm);
                System.out.println(totalSalaryHistoryForm);
                System.out.println(salaryHistories);
                /*modelMap.addAttribute("form16GenerationForm",form16GenerationForm);
                modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
                modelMap.addAttribute("totalSalaryHistoryForm", totalSalaryHistoryForm);
                if (salaryHistories != null && !salaryHistories.isEmpty()) {
                    modelMap.addAttribute("salaryHistories", salaryHistories);
                    modelMap.addAttribute("grossSalary", grossSalary);
                    modelMap.addAttribute("remainedMonths", remainedMonths);
                    modelMap.addAttribute("lastSalary", salaryHistories.get(salaryHistories.size() - 1));
                }
                modelMap.addAttribute("technicalError", "Unable to save Employee Details");
                modelMap.addAttribute("employee", employee);*/
                //  modelMap.addAttribute("valuesError", "Please Provide  Required values");
             modelMap.addAttribute("employeSaveError","unable to save Employee");
                return "html/form16SaveResults";
            }
            //set values to EmployeeVo object
            employeeVO.setPerAddress(address);
            employeeVO.setPanNo(panNo);
            employeeVO.setDesignation(designation);
            //save employee to data base with modified values
            saveEmployee(employeeVO,employeeFinancialYearForm);
            System.out.println("etax" + employeeIncomeTax);
            System.out.println("form16" + form16GenerationForm);
            //System.out.println("before" + employeeIncomeTax.getFinancialYear().getId());
            //saves employeeIncomeTax to DataBase
            copyPropertiesToEmployeeIncomeTax(form16GenerationForm,employeeIncomeTax,employeeFinancialYearForm.getFinancialYear(),empId);
            departmentService.saveOrUpdateEmployeeIncomeTax(employeeIncomeTax);
            System.out.println(employeeIncomeTax);
           // System.out.println("after" + employeeIncomeTax.getFinancialYear().getId());
            System.out.println("=====================================");
            //executes if any exception raises
        } catch (Exception e) {
            System.out.println("exception");
            e.printStackTrace();
            /*modelMap.addAttribute("technicalError", "Unable to save Employee Details");
            modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
            modelMap.addAttribute("form16GenerationForm", form16GenerationForm);
            modelMap.addAttribute("totalSalaryHistoryForm", totalSalaryHistoryForm);
            if (salaryHistories != null && !salaryHistories.isEmpty()) {
                modelMap.addAttribute("salaryHistories", salaryHistories);
                modelMap.addAttribute("grossSalary", grossSalary);
                modelMap.addAttribute("remainedMonths", remainedMonths);
                modelMap.addAttribute("lastSalary", salaryHistories.get(salaryHistories.size() - 1));
            }

            modelMap.addAttribute("technicalError", "Unable to save Employee Details");
            modelMap.addAttribute("employee", employee);
            modelMap.addAttribute("employeSaveError","unable to save Employee");*/
            modelMap.addAttribute("employeSaveError","unable to save Employee");
            return "html/form16SaveResults";
        }
        System.out.println("saved");
        modelMap.addAttribute("employeeSaveSuccess", "Saved Successfully");
        /*modelMap.addAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
        modelMap.addAttribute("form16GenerationForm", form16GenerationForm);
        modelMap.addAttribute("totalSalaryHistoryForm", totalSalaryHistoryForm);
        if (salaryHistories != null && !salaryHistories.isEmpty()) {
            modelMap.addAttribute("salaryHistories", salaryHistories);
            modelMap.addAttribute("grossSalary", grossSalary);
            modelMap.addAttribute("remainedMonths", remainedMonths);
            modelMap.addAttribute("lastSalary", salaryHistories.get(salaryHistories.size() - 1));
        }
*/
        //modelMap.addAttribute("employee", employee);
        return "html/form16SaveResults";
    }


    /**
     * copy properties to employeetax from form16GenerationForm
     *
     * @param form16GenerationForm
     * @param employeeIncomeTax
     * @param financialYear
     * @param empId
     */
    private void copyPropertiesToEmployeeIncomeTax(Form16GenerationForm form16GenerationForm, EmployeeIncomeTax employeeIncomeTax, FinancialYear financialYear, String empId) {
        employeeIncomeTax.setGrossSalary(form16GenerationForm.getGrossSalary());
        employeeIncomeTax.setBonus(form16GenerationForm.getBonus());
        employeeIncomeTax.setFinancialYear(financialYear);
        employeeIncomeTax.setEmpId(empId);
        employeeIncomeTax.setEduCess(form16GenerationForm.getEduCess());
        employeeIncomeTax.setIncentives(form16GenerationForm.getIncentives());
        employeeIncomeTax.setIncomeEarnedByEmployee(form16GenerationForm.getIncomeEarnedByEmployee());
        employeeIncomeTax.setOthers(form16GenerationForm.getOthers());
        employeeIncomeTax.setPerMonthTax(form16GenerationForm.getPerMonthTax());
        employeeIncomeTax.setPlb(form16GenerationForm.getPlb());
        employeeIncomeTax.setPreviousCompanyIncome(form16GenerationForm.getPreviousCompanyIncome());
        employeeIncomeTax.setReimbursement(form16GenerationForm.getReimbursement());
        employeeIncomeTax.setTaxableIncome(form16GenerationForm.getTaxableIncome());
        employeeIncomeTax.setTaxOnIncome(form16GenerationForm.getTaxOnIncome());
        employeeIncomeTax.setTaxTobeDeducted(form16GenerationForm.getTaxTobeDeducted());
        employeeIncomeTax.setTdsDeducted(form16GenerationForm.getTdsDeducted());
        employeeIncomeTax.setTotalTaxOnIncome(form16GenerationForm.getTotalTaxOnIncome());
        if (form16GenerationForm.getId() != null) {
            employeeIncomeTax.setId(form16GenerationForm.getId());
        }


    }

    /**
     * validates EmployeeFinancial Year Form
     *
     * @param employeeFinancialYearForm
     * @return
     */
    private boolean validate(EmployeeFinancialYearForm employeeFinancialYearForm) {
        return employeeFinancialYearForm.getEmpId() != null && !employeeFinancialYearForm.getEmpId().equals("");
    }


    /**
     * Creates form16GenerationForm
     *
     *
     * @param salaryHistories
     * @param financialYear
     * @param employeeFinancialYears
     */
    Form16GenerationForm updateHraForm16(String empId, List<SalaryHistory> salaryHistories, FinancialYear financialYear, List<EmployeeFinancialYear> employeeFinancialYears,List<FinancialYear> financialYears) {
        Form16GenerationForm form16GenerationForm=new Form16GenerationForm();

        List<EmployeeIncomeTax> employeeIncomeTaxes=null;
        employeeIncomeTaxes=employeeIncomeTaxes = departmentService.findEmployeeIncomeTaxWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (employeeIncomeTaxes != null && !employeeIncomeTaxes.isEmpty()) {
            form16GenerationForm = prepareForm16GenerationForm(employeeIncomeTaxes.get(0));
            System.out.println("employeeIncomeTaxes.get(0)   " + employeeIncomeTaxes.get(0));
            System.out.println(" form16GenerationForm   " + form16GenerationForm);
            if (!financialYears.isEmpty()) {
                Set<Rebate> rebates = financialYears.get(0).getRebates();
                if (rebates != null) {
                    form16GenerationForm.setRebates(new ArrayList<Rebate>(rebates));
                }
            }
        }
        form16GenerationForm.setBasic40per(calculateBasic40per(salaryHistories, financialYear));
        form16GenerationForm.setTotalActualHRA(calculateActualHRA(salaryHistories, financialYear));
        if (employeeFinancialYears != null && !employeeFinancialYears.isEmpty()) {
            form16GenerationForm.setHraWithBasic10per(calculateHraWithBasic10per(salaryHistories, financialYear, employeeFinancialYears.get(0)));
        }
        form16GenerationForm.setHra(calculateHRA(form16GenerationForm.getTotalActualHRA(), form16GenerationForm.getHraWithBasic10per(), form16GenerationForm.getBasic40per()));
        System.out.println(form16GenerationForm.getIncomeEarnedByEmployee());
        return form16GenerationForm;
    }

    /**
     * calculate taxes and set taxes to form16GenerationForm
     *
     * @param incomeEarnedByEmployee
     * @param employeeFinancialYearForm
     */
    Form16GenerationForm form16CalculateTax(Form16GenerationForm form16GenerationForm,String incomeEarnedByEmployee, EmployeeFinancialYearForm employeeFinancialYearForm,BigDecimal grossSalary,BigDecimal totalSavings,List<FinancialYear> financialYears,Date lastSalaryDate,BigDecimal totalTdsDeducted) {

        form16GenerationForm.setIncomeEarnedByEmployee(new BigDecimal(incomeEarnedByEmployee));
        form16GenerationForm.setGrossSalary(grossSalary);
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        //tax sections of employee
        BigDecimal saveAmount = BigDecimal.ZERO;
        List<TaxSectionForm> taxSectionForms = form16GenerationForm.getTaxSectionForms();
        if (taxSectionForms != null) {
            for (TaxSectionForm taxSectionForm : taxSectionForms) {
                saveAmount = saveAmount.add(taxSectionForm.getSectionLimit());
            }
        }
        saveAmount = saveAmount.add(totalSavings);
        //*Taxable Income = income earned by employee - (applied sections amount + HRA Exempted)*//*
        if (form16GenerationForm.getHra() != null) {
            saveAmount = saveAmount.add(form16GenerationForm.getHra());
        }
       form16GenerationForm.setTaxableIncome(form16GenerationForm.getIncomeEarnedByEmployee().subtract(saveAmount).setScale(0, BigDecimal.ROUND_CEILING));
        BigDecimal taxableIncome = form16GenerationForm.getTaxableIncome();
        BigDecimal taxOnIncome = BigDecimal.ZERO;
        if (financialYears != null && !financialYears.isEmpty()) {
            FinancialYear financialYearFromDB = financialYears.get(0);
            if (financialYearFromDB.getRebates() != null) {
                List<Rebate> rebates = new ArrayList<Rebate>(financialYearFromDB.getRebates());
                form16GenerationForm.setRebates(rebates);
            }
            //*tax on income = taxable income - every tax slab percentages *//*
            List<IncomeTaxSlab> incomeTaxSlabs = new ArrayList<IncomeTaxSlab>(financialYearFromDB.getIncomeTaxSlabs());
            Collections.sort(incomeTaxSlabs, Collections.<IncomeTaxSlab>reverseOrder());
            for (IncomeTaxSlab incomeTaxSlab : incomeTaxSlabs) {
                if (incomeTaxSlab.getMaxIncome() == null) {
                    if (taxableIncome.compareTo(incomeTaxSlab.getMinIncome()) == 1) {
                        if (incomeTaxSlab.getTaxRate().compareTo(BigDecimal.ZERO) != 0) {
                            taxOnIncome = taxOnIncome.add(taxableIncome.subtract(incomeTaxSlab.getMinIncome()).multiply(incomeTaxSlab.getTaxRate()).divide(HUNDRED, BigDecimal.ROUND_UP));
                            taxableIncome = taxableIncome.subtract(taxableIncome.subtract(incomeTaxSlab.getMinIncome()));
                        }
                    }
                } else {
                    if (taxableIncome.compareTo(incomeTaxSlab.getMinIncome()) == 1) {
                        if (incomeTaxSlab.getTaxRate().compareTo(BigDecimal.ZERO) != 0) {
                            taxOnIncome = taxOnIncome.add(taxableIncome.subtract(incomeTaxSlab.getMinIncome()).multiply(incomeTaxSlab.getTaxRate()).divide(HUNDRED, BigDecimal.ROUND_UP));
                            taxableIncome = taxableIncome.subtract(taxableIncome.subtract(incomeTaxSlab.getMinIncome()));
                        }
                    }
                }
            }
            form16GenerationForm.setTaxOnIncome(taxOnIncome.setScale(0, BigDecimal.ROUND_CEILING));
            List<Rebate> rebates = form16GenerationForm.getRebates();
            BigDecimal rebateAmount = BigDecimal.ZERO;
            if (rebates != null) {
                for (Rebate rebate : rebates) {
                    if (form16GenerationForm.getTaxableIncome().compareTo(rebate.getRebateApplySalary()) <= 0) {
                        rebateAmount = rebateAmount.add(rebate.getRebateAmount());
                    }
                }
            }

            //*If the rebate applicable then tax on income = taxOnIncome - rebateAmount*//*
            taxOnIncome = taxOnIncome.subtract(rebateAmount);
            form16GenerationForm.setEduCess(taxOnIncome.multiply(EDU_CESS).divide(HUNDRED, BigDecimal.ROUND_CEILING).setScale(0, BigDecimal.ROUND_CEILING));
        }

        if (form16GenerationForm.getEduCess() != null) {
            form16GenerationForm.setTotalTaxOnIncome(taxOnIncome.add(form16GenerationForm.getEduCess()).setScale(0, BigDecimal.ROUND_CEILING));
        }
        form16GenerationForm.setTdsDeducted(totalTdsDeducted.setScale(0, BigDecimal.ROUND_CEILING));
        if (form16GenerationForm.getTotalTaxOnIncome() != null) {
            form16GenerationForm.setTaxTobeDeducted(form16GenerationForm.getTotalTaxOnIncome().subtract(form16GenerationForm.getTdsDeducted()).setScale(0, BigDecimal.ROUND_CEILING));
        }

        if (form16GenerationForm.getTaxTobeDeducted() != null) {
            form16GenerationForm.setPerMonthTax(form16GenerationForm.getTaxTobeDeducted().divide(new BigDecimal(calculateUnSalariedMonths(financialYear, lastSalaryDate) + 1), BigDecimal.ROUND_CEILING).setScale(0, BigDecimal.ROUND_CEILING));
        }
        if (form16GenerationForm.getTaxOnIncome().compareTo(BigDecimal.ZERO) == -1) {
            form16GenerationForm.setTaxOnIncome(BigDecimal.ZERO);
        }
        if (form16GenerationForm.getEduCess().compareTo(BigDecimal.ZERO) == -1) {
            form16GenerationForm.setEduCess(BigDecimal.ZERO);
        }
        if (form16GenerationForm.getTotalTaxOnIncome().compareTo(BigDecimal.ZERO) == -1) {
            form16GenerationForm.setTotalTaxOnIncome(BigDecimal.ZERO);
        }
        if (form16GenerationForm.getTaxTobeDeducted().compareTo(BigDecimal.ZERO) == -1) {
            form16GenerationForm.setTaxTobeDeducted(BigDecimal.ZERO);
        }
        if (form16GenerationForm.getPerMonthTax().compareTo(BigDecimal.ZERO) == -1) {
            form16GenerationForm.setPerMonthTax(BigDecimal.ZERO);
        }
        return form16GenerationForm;

    }

    /**
     * updates EmployeFinancialYear Form with tax sections data
     *
     * @param empId
     * @param financialYear
     * @return
     */
    EmployeeFinancialYearForm generateEmployeeFinancialYearForm(String empId, FinancialYear financialYear,EmployeeFinancialYearForm employeeFinancialYearForm) {
        BigDecimal totalSavings=BigDecimal.ZERO;
        List<EmployeeFinancialYear> employeeFinancialYears = departmentService.findEmployeeFinancialYearWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (employeeFinancialYears != null && !employeeFinancialYears.isEmpty()) {
            employeeFinancialYearForm = prepareEmployeeFinancialYearForm(employeeFinancialYears.get(0));
        }

        return employeeFinancialYearForm;
    }

    private boolean checkEmployeeData(String designation, String panNo, String address) {
        return SimpleUtils.isEmpty(designation) || SimpleUtils.isEmpty(panNo) || SimpleUtils.isEmpty(address);
    }

    private void saveEmployee(EmployeeVO employeeVo,EmployeeFinancialYearForm employeeFinancialYearForm) throws Exception {
        Employee employeeFromDB = departmentService.findEmployeeByEmployeeId(employeeFinancialYearForm.getEmpId());
        employeeFromDB.setDesignation(employeeVo.getDesignation());
        employeeFromDB.setPanNo(employeeVo.getPanNo());
        employeeFromDB.setPerAddress(employeeVo.getPerAddress());
        departmentService.saveOrUpdateEmployee(employeeFromDB);
    }

    /**
     * set front end values to form16
     *
     * @param form16GenerationForm
     * @param incentives
     * @param plb
     * @param bonus
     * @param reimbursement
     * @param previousCompanyIncome
     * @param others
     */
    private void setPropertiesToForm16(Form16GenerationForm form16GenerationForm, String incentives, String plb, String bonus, String reimbursement, String previousCompanyIncome, String others) {

        switch (plb) {
            case "":
                form16GenerationForm.setPlb(BigDecimal.ZERO);
                break;
            default:
                form16GenerationForm.setPlb(new BigDecimal(Double.parseDouble(plb)));
        }

        switch (bonus) {
            case "":
                form16GenerationForm.setPlb(BigDecimal.ZERO);
                break;
            default:
                form16GenerationForm.setBonus(new BigDecimal(Double.parseDouble(bonus)));
        }

        switch (others) {
            case "":
                form16GenerationForm.setOthers(BigDecimal.ZERO);
                break;
            default:
                form16GenerationForm.setOthers(new BigDecimal(Double.parseDouble(others)));
        }

        switch (previousCompanyIncome) {
            case "":
                form16GenerationForm.setPreviousCompanyIncome(BigDecimal.ZERO);
                break;
            default:
                form16GenerationForm.setPreviousCompanyIncome(new BigDecimal(Double.parseDouble(previousCompanyIncome)));
        }

        switch (incentives) {
            case "":
                form16GenerationForm.setIncentives(BigDecimal.ZERO);
                break;
            default:
                form16GenerationForm.setIncentives(new BigDecimal(Double.parseDouble(incentives)));
        }

        switch (reimbursement) {
            case "":
                form16GenerationForm.setReimbursement(BigDecimal.ZERO);
                break;
            default:
                form16GenerationForm.setReimbursement(new BigDecimal(Double.parseDouble(reimbursement)));
        }
        System.out.println("PLB" + form16GenerationForm.getPlb());
        System.out.println("OTHERS" + form16GenerationForm.getOthers());
        System.out.println("INCENT" + form16GenerationForm.getIncentives());
        System.out.println("REIB" + form16GenerationForm.getReimbursement());
        System.out.println("PREVIOUS" + form16GenerationForm.getPreviousCompanyIncome());
    }

    private List loadRequiredData(EmployeeFinancialYearForm employeeFinancialYearForm) {
        List<SalaryHistory> salaryHistories=null;
        TotalSalaryHistoryForm totalSalaryHistoryForm=null;
        List<Integer> remainedMonths=new ArrayList<>();
        FinancialYearForm financialYearForm = new FinancialYearForm();
        String empId = employeeFinancialYearForm.getEmpId();
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        Date fromDate = prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
        Date toDate = prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());
        salaryHistories = departmentService.findSalaryHistoriesWithInFinancialYear(empId, fromDate, toDate);
        //System.out.println("departmentAction salryHistories:  "+salaryHistories);
        System.out.println(salaryHistories.size());
        totalSalaryHistoryForm = prepareTotalSalary(salaryHistories, financialYear);
        //System.out.println("department ACTION TOTAL SALRY HISTORY FORM:   "+totalSalaryHistoryForm);
        if (salaryHistories != null && !salaryHistories.isEmpty()) {
            int remainingMonths = calculateUnSalariedMonths(financialYear, salaryHistories.get(salaryHistories.size() - 1).getSalaryDate());
            remainedMonths = new ArrayList();
            for (int i = 0; i <= remainingMonths; i++) {
                remainedMonths.add(i);
            }
        }
        List list=new ArrayList();
        list.add(remainedMonths);
        list.add(totalSalaryHistoryForm);
        list.add(salaryHistories);
        return list;
    }



}
