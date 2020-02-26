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
    private Date salaryDate;
    private TotalSalaryHistoryForm totalSalaryHistoryForm;
    private BigDecimal grossSalary;
    private List<SalaryHistory> salaryHistories;
    private EmployeeFinancialYearForm employeeFinancialYearForm;
    private Form16GenerationForm form16GenerationForm;
    private List<EmployeeFinancialYear> employeeFinancialYears;
    private List<EmployeeIncomeTax> employeeIncomeTaxes;
    private List<FinancialYear> financialYears;
    List<Integer> remainedMonths;
    private static final BigDecimal EDU_CESS = new BigDecimal(3);
    private static final BigDecimal HUNDRED = new BigDecimal(100);
    private Employee employee;

    private BigDecimal totalSavings=BigDecimal.ZERO;


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
        if (employeeFinancialYearForm.getEmpId() != null && !employeeFinancialYearForm.getEmpId().equals("")) {
            FinancialYearForm financialYearForm = new FinancialYearForm();
            FinancialYear financialYear;


            String empId = employeeFinancialYearForm.getEmpId();
             employee = departmentService.findEmployeeByEmployeeId(empId);
            financialYear = employeeFinancialYearForm.getFinancialYear();

            int x = Integer.parseInt(financialYear.getFromYear());

            Date fromDate = Form16Utils.prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
            Date toDate = Form16Utils.prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());
            List<EmployeeIncomeTax> employeeIncomeTaxes = departmentService.findEmployeeIncomeTaxWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
            this.employeeIncomeTaxes=employeeIncomeTaxes;
            salaryHistories =salaryHistoryRepository.findByEmpIdAndSalaryEndDateBetween(empId, fromDate, toDate);

            if(salaryHistories==null||salaryHistories.size()==0){


                modelMap.addAttribute("financialerror", "No Records Found");
                modelMap.addAttribute("employee",employee);

                return "html/Form16Generation";

            }
            totalSalaryHistoryForm = Form16Utils.prepareTotalSalary(salaryHistories, financialYear);
            if (salaryHistories != null && !salaryHistories.isEmpty()) {
                int remainingMonths = Form16Utils.calculateUnSalariedMonths(financialYear, salaryHistories.get(salaryHistories.size() - 1).getSalaryDate());
                remainedMonths = new ArrayList();
                for (int i = 0; i <= remainingMonths; i++) {

                    remainedMonths.add(i);
                }
            }

            modelMap.addAttribute("remainedMonths", remainedMonths);

        }else{
            modelMap.addAttribute("employeeId","Please Provide Employee Id");
            return "html/Form16Generation";

        }
       // BigDecimal grossSalary = null;
        Form16GenerationForm form16GenerationForm = new Form16GenerationForm();
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        String empId = employeeFinancialYearForm.getEmpId();
         employee = departmentService.findEmployeeByEmployeeId(empId);
        modelMap.addAttribute("employee", employee);
        if (employee == null) {

            modelMap.addAttribute("employeeerror", "No employee Found");
            //todo:: why we need to create a new employee object when method retruns no object
            /*employee = new Employee();
            employee.setEmpId(empId);*/
            return "html/Form16Generation";
        }

        List<FinancialYear> financialYears = financialYearRepository.findFinancialYearSectionsByFinancialYear(financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if(financialYears.size()>0) {

        }
        if (financialYears == null || financialYears.isEmpty()) {

            modelMap.addAttribute("financialerror", "No Records related to financial Year  Found");

            return "html/Form16Generation";
        }
        if (totalSalaryHistoryForm != null) {

            grossSalary = totalSalaryHistoryForm.getTotalGrossSalary();
            modelMap.addAttribute("grossSalary", grossSalary);
        }

        List<EmployeeFinancialYear> employeeFinancialYears = departmentService.findEmployeeFinancialYearWithEmpIdAndFinancialYear(empId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
        if (employeeFinancialYears != null && !employeeFinancialYears.isEmpty()) {
            employeeFinancialYearForm = Form16Utils.prepareEmployeeFinancialYearForm(employeeFinancialYears.get(0));
        }


        try {
            totalSavings = updateSectionLimit(employeeFinancialYearForm);
        }catch (Exception e){
            e.printStackTrace();
            modelMap.addAttribute("financialerror", "No Records Found");
            return "html/Form16Generation";
        }


        if (employeeIncomeTaxes != null && !employeeIncomeTaxes.isEmpty()) {

            form16GenerationForm = Form16Utils.prepareForm16GenerationForm(employeeIncomeTaxes.get(0));
            Set<Rebate> rebates = financialYears.get(0).getRebates();
            if (rebates != null) {
                form16GenerationForm.setRebates(new ArrayList<Rebate>(rebates));
            }
        }



        form16GenerationForm.setBasic40per(calculateBasic40per(salaryHistories, financialYear));
        form16GenerationForm.setTotalActualHRA(calculateActualHRA(salaryHistories, financialYear));
        if (employeeFinancialYears != null && !employeeFinancialYears.isEmpty()) {
            form16GenerationForm.setHraWithBasic10per(calculateHraWithBasic10per(salaryHistories, financialYear, employeeFinancialYears.get(0)));
        }
        form16GenerationForm.setHra(calculateHRA(form16GenerationForm.getTotalActualHRA(), form16GenerationForm.getHraWithBasic10per(), form16GenerationForm.getBasic40per()));
        modelMap.addAttribute("form16GenerationForm", form16GenerationForm);


        Date fromDate = Form16Utils.prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
        Date toDate = Form16Utils.prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());



        modelMap.addAttribute("salaryHistories", salaryHistories);


        modelMap.addAttribute("totalSalaryHistoryForm", totalSalaryHistoryForm);


        // to get lastsalary object
        if (salaryHistories.size() != 0 && !salaryHistories.isEmpty()) {
            modelMap.addAttribute("lastSalary", salaryHistories.get(salaryHistories.size() - 1));
            // httpSession.setAttribute("totalSalaryHistoryForm", totalSalaryHistoryForm);
            this.totalSalaryHistoryForm = totalSalaryHistoryForm;
            //  httpSession.setAttribute("employeeFinancialYearForm", employeeFinancialYearForm);
          this.employeeFinancialYearForm = employeeFinancialYearForm;
            //httpSession.setAttribute("grossSalary", grossSalary);
            this.grossSalary = grossSalary;
            // httpSession.setAttribute("form16GenerationForm", form16GenerationForm);
            this.form16GenerationForm = form16GenerationForm;
            // httpSession.setAttribute("employeeFinancialYears", employeeFinancialYears);
            this.employeeFinancialYears = employeeFinancialYears;
            // httpSession.setAttribute("salaryHistories", salaryHistories);
            this.salaryHistories = salaryHistories;
            this.employeeIncomeTaxes = employeeIncomeTaxes;
            this.financialYears = financialYears;
        }

        modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
        return "html/Form16Generation";
    }




    private BigDecimal updateSectionLimit(EmployeeFinancialYearForm employeeFinancialYearForm) {
        BigDecimal toatalSaveAmountFromTaxSections=BigDecimal.ZERO;
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
                if ( saveAmount.compareTo(employeeTaxSectionForm.getSectionLimit()) == -1) {

                    employeeTaxSectionForm.setSectionLimit(saveAmount);
                    toatalSaveAmountFromTaxSections=toatalSaveAmountFromTaxSections.add(saveAmount);

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
     * @param employeeFinancialYearForm1
     * @param incomeEarnedByEmployee
     * @return
     */
    @RequestMapping("/calculateTax")
    public String calculateTax(ModelMap modelMap, HttpSession httpSession, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm1, @RequestParam(name = "incomeEarnedByEmployee") String incomeEarnedByEmployee) {

        if (totalSalaryHistoryForm == null || employeeFinancialYearForm == null || form16GenerationForm == null || salaryHistories == null) {
            modelMap.addAttribute("valuesError", "InSufficient Data So Tax can't be calculated");
            return "html/calculateTaxResults";
        }
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


        saveAmount=saveAmount.add(totalSavings);


        /*Taxable Income = income earned by employee - (applied sections amount + HRA Exempted)*/
        saveAmount = saveAmount.add(form16GenerationForm.getHra());

        form16GenerationForm.setIncomeEarnedByEmployee(new BigDecimal(incomeEarnedByEmployee));

        form16GenerationForm.setTaxableIncome(form16GenerationForm.getIncomeEarnedByEmployee().subtract(saveAmount).setScale(0, BigDecimal.ROUND_CEILING));


        BigDecimal taxableIncome = form16GenerationForm.getTaxableIncome();
        BigDecimal taxOnIncome = BigDecimal.ZERO;
        if (financialYears != null && !financialYears.isEmpty()) {
            FinancialYear financialYearFromDB = financialYears.get(0);

            if (financialYearFromDB.getRebates() != null) {

                List<Rebate> rebates = new ArrayList<Rebate>(financialYearFromDB.getRebates());
                form16GenerationForm.setRebates(rebates);

            }


            /*tax on income = taxable income - every tax slab percentages */
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

            /*If the rebate applicable then tax on income = taxOnIncome - rebateAmount*/
            taxOnIncome = taxOnIncome.subtract(rebateAmount);
            form16GenerationForm.setEduCess(taxOnIncome.multiply(EDU_CESS).divide(HUNDRED, BigDecimal.ROUND_CEILING).setScale(0, BigDecimal.ROUND_CEILING));
        }


        form16GenerationForm.setTotalTaxOnIncome(taxOnIncome.add(form16GenerationForm.getEduCess()).setScale(0, BigDecimal.ROUND_CEILING));
        form16GenerationForm.setTdsDeducted(totalSalaryHistoryForm.getTotalTdsDeducted().setScale(0, BigDecimal.ROUND_CEILING));
        form16GenerationForm.setTaxTobeDeducted(form16GenerationForm.getTotalTaxOnIncome().subtract(form16GenerationForm.getTdsDeducted()).setScale(0, BigDecimal.ROUND_CEILING));
        Date lastSalaryDate = salaryHistories.get(salaryHistories.size() - 1).getSalaryDate();
        form16GenerationForm.setPerMonthTax(form16GenerationForm.getTaxTobeDeducted().divide(new BigDecimal(calculateUnSalariedMonths(financialYear, lastSalaryDate) + 1), BigDecimal.ROUND_CEILING).setScale(0, BigDecimal.ROUND_CEILING));
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
        modelMap.addAttribute("form16GenerationForm", form16GenerationForm);
        return "html/calculateTaxResults";
    }

    protected boolean isValid() {
        if (salaryDate != null)
            return true;
        else
            return false;
    }



    /**
     * @return
     */

    @RequestMapping("/saveAsPdf")

    public ResponseEntity<byte[]> saveForm16PDF(ModelMap modelMap, HttpServletRequest httpServletRequest, @ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm) {
        HttpHeaders response = new HttpHeaders();
        byte[] bytes=null;
        InputStream inputStream;
        String fileName;
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        Date fromDate = prepareDateWithMonthAndYear(financialYear.getFromMonth(), financialYear.getFromYear());
        Date toDate = prepareDateWithMonthAndYear(financialYear.getToMonth(), financialYear.getToYear());
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

    ResponseEntity<byte[]> responseEntity= new ResponseEntity (bytes,response,HttpStatus.OK);
      return  responseEntity;
    }




    @RequestMapping("saveAsExcel")
    public ResponseEntity<byte[]> saveForm16EXCEL(@ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm) {
        ResponseEntity <byte[]> responseEntity=null;
        InputStream inputStream;
        String fileName;
        HttpHeaders httpHeaders=new HttpHeaders();
        byte[] bytes=null;

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
            responseEntity= new ResponseEntity(bytes,httpHeaders,HttpStatus.OK);

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


    private boolean checkEmployeeData(String designation, String panNo, String address) {
        return SimpleUtils.isEmpty(designation) && SimpleUtils.isEmpty(panNo) && SimpleUtils.isEmpty(address);
    }

    private void saveEmployee(EmployeeVO employeeVo) throws Exception {
        Employee employeeFromDB = departmentService.findEmployeeByEmployeeId(employeeFinancialYearForm.getEmpId());
        employeeFromDB.setDesignation(employeeVo.getDesignation());
        employeeFromDB.setPanNo(employeeVo.getPanNo());
        employeeFromDB.setPerAddress(employeeVo.getPerAddress());
        departmentService.saveOrUpdateEmployee(employeeFromDB);
    }

    @PostMapping("/saveEmployeeIncomeTax")
    public String saveEmployeeIncomeTax(ModelMap modelMap,@ModelAttribute EmployeeFinancialYearForm employeeFinancialYearForm, @RequestParam("employee.designation") String designation, @RequestParam("employee.panNo") String panNo, @RequestParam("employee.address") String address) {
        if(this.employeeFinancialYearForm!=null){
            employeeFinancialYearForm.setEmployeeTaxSectionForms(this.employeeFinancialYearForm.getEmployeeTaxSectionForms());
        }

         if (checkEmployeeData(designation, panNo, address)) {


             modelMap.addAttribute("form16GenerationForm",form16GenerationForm);
             modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
             modelMap.addAttribute("totalSalaryHistoryForm",totalSalaryHistoryForm);
             modelMap.addAttribute("salaryHistories",salaryHistories);
             modelMap.addAttribute("grossSalary",grossSalary);
             modelMap.addAttribute("remainedMonths",remainedMonths);
             modelMap.addAttribute("employee",employee );
            modelMap.addAttribute("employeeDetails", "please provide employee details");
            return "html/Form16Generation";
        }
        FinancialYearForm financialYearForm = new FinancialYearForm();
       /* if (validation(employeeFinancialYearForm)) {
            return INPUT;
        }*/

        if (totalSalaryHistoryForm == null || form16GenerationForm == null || salaryHistories == null) {

            modelMap.addAttribute("form16GenerationForm",form16GenerationForm);
            modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
            modelMap.addAttribute("totalSalaryHistoryForm",totalSalaryHistoryForm);
            modelMap.addAttribute("salaryHistories",salaryHistories);
            modelMap.addAttribute("grossSalary",grossSalary);
            modelMap.addAttribute("remainedMonths",remainedMonths);
            modelMap.addAttribute("technicalError", "Unable to save Employee Details");
            modelMap.addAttribute("employee",employee );
          //  modelMap.addAttribute("valuesError", "Please Provide  Required values");
            return "html/Form16Generation";
        }
        //create employee vo object and assign values
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setPerAddress(address);
        employeeVO.setPanNo(panNo);
        employeeVO.setDesignation(designation);
        FinancialYear financialYear = employeeFinancialYearForm.getFinancialYear();
        try {
            saveEmployee(employeeVO);
            EmployeeIncomeTax employeeIncomeTax = prepareEmployeeIncomeTax(form16GenerationForm, financialYear, employeeFinancialYearForm.getEmpId());
            departmentService.saveOrUpdateEmployeeIncomeTax(employeeIncomeTax);
        } catch (Exception e) {

            e.printStackTrace();
            modelMap.addAttribute("employeeSaved", "Saved Successfully");
            modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
            modelMap.addAttribute("form16GenerationForm",form16GenerationForm);
            modelMap.addAttribute("totalSalaryHistoryForm",totalSalaryHistoryForm);
            modelMap.addAttribute("salaryHistories",salaryHistories);
            modelMap.addAttribute("grossSalary",grossSalary);
            modelMap.addAttribute("remainedMonths",remainedMonths);
            modelMap.addAttribute("technicalError", "Unable to save Employee Details");
            modelMap.addAttribute("employee",employee );
            return "html/Form16Generation";
        }


        // calculateTax();
        /*
        set EmpId and financial Year to this.emploYeeFinanacialYearFrom so that
         */

        modelMap.addAttribute("employeeSaved", "Saved Successfully");
        modelMap.addAttribute("employeeFinancialYearForm",employeeFinancialYearForm);
        modelMap.addAttribute("form16GenerationForm",form16GenerationForm);
        modelMap.addAttribute("totalSalaryHistoryForm",totalSalaryHistoryForm);
        modelMap.addAttribute("salaryHistories",salaryHistories);
        modelMap.addAttribute("grossSalary",grossSalary);
        modelMap.addAttribute("remainedMonths",remainedMonths);
        modelMap.addAttribute("employee",employee );
        return "html/Form16Generation";
    }

    private boolean validate(EmployeeFinancialYearForm employeeFinancialYearForm) {
          return employeeFinancialYearForm.getEmpId()!=null&&!employeeFinancialYearForm.getEmpId().equals("");
    }
}
