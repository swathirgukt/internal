package com.indianeagle.internal.service.impl.impl;

import com.indianeagle.internal.dao.repository.EmployeeSettlementRepository;
import com.indianeagle.internal.dao.repository.SalaryHistoryRepository;
import com.indianeagle.internal.dao.repository.SalaryRepository;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.EmployeeSettlement;
import com.indianeagle.internal.dto.Salary;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.enums.TemplateNames;
import com.indianeagle.internal.form.EmpSalaryDecider;
import com.indianeagle.internal.form.EmployeeSettlementForm;
import com.indianeagle.internal.form.GenerateAllSalariesForm;
import com.indianeagle.internal.form.SalaryRule;
import com.indianeagle.internal.service.SalaryHistoryService;
import com.indianeagle.internal.service.SalaryService;
import com.indianeagle.internal.util.CalculationRules;
import com.indianeagle.internal.util.DateUtils;
import com.indianeagle.internal.util.PaySlipPdfUtils;
import com.indianeagle.internal.util.SimpleUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.thymeleaf.TemplateEngine;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

public class SalaryServiceimpl implements SalaryService, MessageSourceAware {
    private static final Logger log = Logger.getLogger(SalaryServiceimpl.class);
    private MessageSource messageSource;
    private SalaryRepository salaryRepository;
    private SalaryHistoryRepository salaryHistoryRepository;
    private Employee employee;
    private SalaryHistory currentSalary;
    private com.indianeagle.internal.mail.MailingEngine mailingEngine;
    private List<Employee> currSalaryEmpList;
    private List<SalaryHistory> currentSalaryList;
    private EmployeeSettlementRepository employeeSettlementRepository;
    private EmployeeSettlement employeeSettlement;
    private SalaryHistoryService salaryHistoryService;
    private TemplateEngine templateEngine;

    public List<Employee> loadAllEmployees() {
        return this.salaryRepository.loadAllEmployees();
    }



    public List<Employee> loadActiveEmployees() {
        return this.salaryRepository.loadActiveEmployees();
    }



    public List<SalaryHistory> generateSalaries(String department, SalaryRule salaryRule) throws Exception {
        log.debug((Object)"Generate Salary for All Employees");
        List<Employee> employeeList = this.loadEmployeesByDepartment(department);
        ArrayList<SalaryHistory> currentSalaryList = new ArrayList<SalaryHistory>();
        ArrayList<Employee> currSalaryEmpList = new ArrayList<Employee>();
        List<String> excludeEmpList = Arrays.asList(salaryRule.getDeptExcludedEmpList());
        for (Employee emp : employeeList) {
            if (excludeEmpList.contains(emp.getEmpId())) continue;
            SalaryHistory currentSalary = this.createSalaryHistory(salaryRule, null, emp);
            this.generateNetSalary(emp.getSalary(), currentSalary, currentSalary.getTotalDays(), currentSalary.getDaysWorked(), emp.getEmpId());
            currentSalaryList.add(currentSalary);
            emp.setCurrentSalary(currentSalary);
            currSalaryEmpList.add(emp);
        }
        this.currSalaryEmpList = currSalaryEmpList;
        this.currentSalaryList = currentSalaryList;
        return currentSalaryList;
    }

    public GenerateAllSalariesForm fillProduceAllSalariesForm(GenerateAllSalariesForm generateAllSalariesForm) {
        Calendar cal = GregorianCalendar.getInstance();
        Calendar calforEndDate = GregorianCalendar.getInstance();
        cal.add(2, -1);
        calforEndDate.add(2, -1);
        cal.set(5, cal.getActualMinimum(5));
        calforEndDate.set(5, calforEndDate.getActualMaximum(5));
        if (generateAllSalariesForm.getSalaryDate() == null) {
            generateAllSalariesForm.setSalaryDate(cal.getTime());
        }
        if (generateAllSalariesForm.getSalaryEndDate() == null) {
            generateAllSalariesForm.setSalaryEndDate(calforEndDate.getTime());
        }
        cal.setTime(generateAllSalariesForm.getSalaryDate());
        List<Employee> allEmpsList = this.salaryRepository.loadActiveEmployees();
        List salaryHistoryList = this.salaryHistoryService.salaryReport(generateAllSalariesForm.getSalaryDate(), generateAllSalariesForm.getSalaryEndDate());
        ArrayList<EmpSalaryDecider> empSalaryDeciderList = new ArrayList<EmpSalaryDecider>();
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        if (allEmpsList != null && !allEmpsList.isEmpty()) {
            for (Employee employee : allEmpsList) {
                if (this.checkEmployeeSalaryGenerated(employee, salaryHistoryList)) continue;
                employeeList.add(employee);
            }
        }
        for (Employee emp : employeeList) {
            EmpSalaryDecider empSalaryDecider = new EmpSalaryDecider();
            empSalaryDecider.setEmpId(emp.getEmpId());
            empSalaryDecider.setFullName(emp.getFullName());
            empSalaryDeciderList.add(empSalaryDecider);
        }
        generateAllSalariesForm.setTotalWorkingDays(new BigDecimal(cal.getActualMaximum(5)));
        generateAllSalariesForm.setEmpSalaryDeciderList(empSalaryDeciderList);
        return generateAllSalariesForm;
    }

    private boolean checkEmployeeSalaryGenerated(Employee employee, List<SalaryHistory> salaryHistoryList) {
        for (SalaryHistory salaryHistory : salaryHistoryList) {
            if (!StringUtils.equalsIgnoreCase((String)employee.getEmpId(), (String)salaryHistory.getEmpId())) continue;
            return true;
        }
        return false;
    }

    public List<SalaryHistory> produceAllSalaries(GenerateAllSalariesForm generateAllSalariesForm) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(generateAllSalariesForm.getSalaryDate());
        Calendar calendarForToDate = Calendar.getInstance();
        calendarForToDate.setTime(generateAllSalariesForm.getSalaryEndDate());
        generateAllSalariesForm.setTotalWorkingDays(new BigDecimal(DateUtils.differenceInDays((Date)calendar.getTime(), (Date)calendarForToDate.getTime())));
        SalaryHistory currentSalary = null;
        EmpSalaryDecider empSalaryDecider = new EmpSalaryDecider();
        List<EmpSalaryDecider> empSalaryDeciderList = generateAllSalariesForm.getEmpSalaryDeciderList();
        ArrayList<SalaryHistory> currentSalaryList = new ArrayList<SalaryHistory>();
        ArrayList<Employee> currSalaryEmpList = new ArrayList<Employee>();
        List<Employee> allEmpsList = this.salaryRepository.loadActiveEmployees();
        List salaryHistoryList = this.salaryHistoryService.salaryReport(generateAllSalariesForm.getSalaryDate(), generateAllSalariesForm.getSalaryEndDate());
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        if (allEmpsList != null && !allEmpsList.isEmpty()) {
            for (Employee employee : allEmpsList) {
                if (this.checkEmployeeSalaryGenerated(employee, salaryHistoryList)) continue;
                employeeList.add(employee);
            }
        }
        for (Employee emp : employeeList) {
            try {
                log.debug("in loop: "+emp.getOfficialEmail()+" empSalaryDeciderList:"+empSalaryDeciderList);
                currentSalary = new SalaryHistory();
                SalaryRule salaryRule = new SalaryRule();
                for (EmpSalaryDecider esd : empSalaryDeciderList) {
                    if (esd.getEmpId() == null || !emp.getEmpId().equals(esd.getEmpId())) continue;
                    empSalaryDecider = esd;
                    break;
                }
                if (empSalaryDecider.getEmpExclude()) continue;
                salaryRule.setArrearsDays(empSalaryDecider.getArrearsDays());
                salaryRule.setLopDays(empSalaryDecider.getLopDays());
                salaryRule.setSalaryInAdvance(empSalaryDecider.getSalaryInAdvance());
                salaryRule.setPerformanceIncentives(empSalaryDecider.getPerformanceIncentives());
                salaryRule.setSalaryDate(generateAllSalariesForm.getSalaryDate());
                salaryRule.setSalaryEndDate(generateAllSalariesForm.getSalaryEndDate());
                salaryRule.setTotalDays(generateAllSalariesForm.getTotalWorkingDays());
                currentSalary = this.createSalaryHistory(salaryRule, null, emp);
                this.generateNetSalary(emp.getSalary(), currentSalary, new BigDecimal(calendar.getActualMaximum(5)), currentSalary.getDaysWorked(), emp.getEmpId());
                currentSalary.setFullName(emp.getFullName());
                currentSalary.setDesignation(emp.getDesignation());
                currentSalary.setJoinDate(emp.getJoinDate());
                currentSalary.setBankAc(emp.getBankAc());
                currentSalary.setBankName(emp.getBankName());
                currentSalaryList.add(currentSalary);
                emp.setCurrentSalary(currentSalary);
                currSalaryEmpList.add(emp);
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        this.currSalaryEmpList = currSalaryEmpList;
        this.currentSalaryList = currentSalaryList;
        return currentSalaryList;
    }



    public void sendAllPaySlipMails(String contextPath) throws Exception {
        this.salaryHistoryRepository.saveAll(this.currentSalaryList);
        int counter = 0;
        for (Employee emp : this.currSalaryEmpList) {
            if (counter == 20) {
                log.debug("::::::::: Hold on for 2 minutes :::::::::");
                counter = 0;
                Thread.sleep(120000);
                log.debug("Thank you for your time! Continue......");
            }
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            PaySlipPdfUtils.generatePaySlipPdf((TemplateEngine) this.templateEngine, (OutputStream) arrayOutputStream, (String) (TemplateNames.MAIL_TEMPLATE_PATH.getPath() + TemplateNames.IE_Payslip.name() + ".vm"), (Object[]) new Object[]{contextPath, emp, emp.getCurrentSalary()});
            this.mailingEngine.sendMail(emp, emp.getCurrentSalary(), (InputStreamSource) new ByteArrayResource(arrayOutputStream.toByteArray()), false);
            counter += 1;
        }
    }

    public SalaryHistory generateSalary(Employee employee, SalaryRule salaryRule) throws Exception {
        log.debug((Object)("Generate Salary for Employee ID:" + employee.getEmpId()));
        Calendar cal = GregorianCalendar.getInstance();
        Calendar cal1 = GregorianCalendar.getInstance();
        cal.setTime(salaryRule.getSalaryDate());
        cal1.setTime(salaryRule.getSalaryEndDate());
        salaryRule.setTotalDays(new BigDecimal(DateUtils.differenceInDays((Date)cal.getTime(), (Date)cal1.getTime())));
        SalaryHistory salaryHistory = this.createSalaryHistory(salaryRule, null, employee);
        this.generateNetSalary(employee.getSalary(), salaryHistory, new BigDecimal(cal.getActualMaximum(5)), salaryHistory.getDaysWorked(), employee.getEmpId());
        this.employee = employee;
        this.currentSalary = salaryHistory;
        return salaryHistory;
    }

    public EmployeeSettlement generateSalarySettlement(Employee employee, EmployeeSettlementForm employeeSettlementForm) throws Exception {
        log.debug((Object)("Generate Salary for Employee ID:" + employee.getEmpId()));
        SalaryHistory salaryHistory = this.createSalaryHistory(null, employeeSettlementForm, employee);
        this.generateNetSalary(employee.getSalary(), salaryHistory, salaryHistory.getTotalDays(), salaryHistory.getDaysWorked(), employee.getEmpId());
        EmployeeSettlement employeeSettlement = this.prepareEmployeeSettlement(salaryHistory);
        employeeSettlement.setSettlementDate(employeeSettlementForm.getSettlementDate());
        employeeSettlement.setEmployee(employee);
        employeeSettlement.setOtherDeductions(employeeSettlementForm.getOtherSettlementDeductions());
        employeeSettlement.setOtherEarnings(employeeSettlementForm.getOtherSettlementEarnings());
        employeeSettlement.setAdminCharges(employeeSettlementForm.getAdminCharges());
        employeeSettlement.setPreviousArrears(employeeSettlementForm.getPreviousArrearsEarnings());
        this.employee = employee;
        this.employeeSettlement = employeeSettlement;
        return employeeSettlement;
    }

    private EmployeeSettlement prepareEmployeeSettlement(SalaryHistory salaryHistory) {
        EmployeeSettlement employeeSettlement = new EmployeeSettlement();
        BeanUtils.copyProperties((Object)salaryHistory, (Object)employeeSettlement);
        employeeSettlement.setTotalDays(salaryHistory.getDaysWorked());
        return employeeSettlement;
    }

    public void confirmAndSendMail(String contextPath) throws Exception {
        this.salaryHistoryRepository.save(this.currentSalary);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        PaySlipPdfUtils.generatePaySlipPdf((TemplateEngine) this.templateEngine, (OutputStream)arrayOutputStream, (String)(TemplateNames.MAIL_TEMPLATE_PATH.getPath() + TemplateNames.IE_Payslip.name() + ".vm"), (Object[])new Object[]{contextPath, this.employee, this.currentSalary});
        this.mailingEngine.sendMail(this.employee, this.currentSalary, (InputStreamSource)new ByteArrayResource(arrayOutputStream.toByteArray()), false);
    }

    public void confirmEmployeeSettlement() {
        this.employeeSettlementRepository.save(this.employeeSettlement);
    }

    private SalaryHistory createSalaryHistory(SalaryRule salaryRule, EmployeeSettlementForm salarySettlementForm, Employee emp) {
        SalaryHistory salaryHistory = new SalaryHistory();
        if (salaryRule != null) {
            BigDecimal salaryForDays = SimpleUtils.isEmptyValue((BigDecimal)salaryRule.getTotalDays()).add(SimpleUtils.isEmptyValue((BigDecimal)salaryRule.getArrearsDays())).subtract(SimpleUtils.isEmptyValue((BigDecimal)salaryRule.getLopDays()));
            salaryHistory.setTotalDays(salaryRule.getTotalDays());
            salaryHistory.setLopDays(salaryRule.getLopDays());
            salaryHistory.setArrearsDays(salaryRule.getArrearsDays());
            salaryHistory.setSalaryDate(salaryRule.getSalaryDate());
            salaryHistory.setSalaryEndDate(salaryRule.getSalaryEndDate());
            salaryHistory.setDaysWorked(salaryForDays);
            salaryHistory.setPerformanceIncentives(salaryRule.getPerformanceIncentives());
            salaryHistory.setSalaryInAdvance(salaryRule.getSalaryInAdvance());
            salaryHistory.setMiscDeductions(salaryRule.getMiscDeductions());
            salaryHistory.setTotalEarnings(BigDecimal.ZERO);
            this.updateSalaryHistoryForNewEmployee(salaryHistory, emp);
        } else if (salarySettlementForm != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(salarySettlementForm.getSettlementDate());
            salaryHistory.setTotalDays(new BigDecimal(calendar.getActualMaximum(5)));
            salaryHistory.setDaysWorked(new BigDecimal(calendar.get(5)));
            salaryHistory.setPerformanceIncentives(BigDecimal.ZERO);
            salaryHistory.setSalaryInAdvance(BigDecimal.ZERO);
            salaryHistory.setMiscDeductions(salarySettlementForm.getOtherSettlementDeductions().add(salarySettlementForm.getAdminCharges()));
            salaryHistory.setTotalEarnings(salarySettlementForm.getOtherSettlementEarnings().add(salarySettlementForm.getPreviousArrearsEarnings()));
        }
        return salaryHistory;
    }

    private void updateSalaryHistoryForNewEmployee(SalaryHistory salaryHistory, Employee emp) {
        Calendar joinDate = Calendar.getInstance();
        if (DateUtils.findMonthsDifference((Date)emp.getJoinDate(), (Date)salaryHistory.getSalaryDate()) == 1 && this.salaryHistoryService.searchSalaryHistoryByEmployeeId(emp.getEmpId()).size() == 0) {
            joinDate.setTime(emp.getJoinDate());
            BigDecimal previousMonthWorkingDays = new BigDecimal(DateUtils.differenceInDays((Date)joinDate.getTime(), (Date)salaryHistory.getSalaryDate()) - 1);
            salaryHistory.setArrearsDays(salaryHistory.getArrearsDays().add(previousMonthWorkingDays));
            salaryHistory.setDaysWorked(salaryHistory.getDaysWorked().add(previousMonthWorkingDays));
        } else if (DateUtils.findMonthsDifference((Date)emp.getJoinDate(), (Date)salaryHistory.getSalaryDate()) == 0) {
            joinDate = Calendar.getInstance();
            joinDate.setTime(emp.getJoinDate());
            int daysDifference = DateUtils.differenceInDays((Date)joinDate.getTime(), (Date)salaryHistory.getSalaryDate()) - 1;
            if (daysDifference > 0) {
                log.warn((Object)"Vijay if block");
                salaryHistory.setArrearsDays(salaryHistory.getArrearsDays().add(new BigDecimal(daysDifference)));
                salaryHistory.setDaysWorked(salaryHistory.getDaysWorked().add(new BigDecimal(daysDifference)));
                log.warn((Object)("******Vijay if block days worked is" + salaryHistory.getDaysWorked()));
            } else if (daysDifference < 0) {
                log.warn((Object)"Vijay else block");
                log.warn((Object)("******Vijay joindate is" + new BigDecimal(joinDate.get(5))));
                salaryHistory.setDaysWorked(salaryHistory.getDaysWorked().subtract(new BigDecimal(DateUtils.differenceInDays((Date)salaryHistory.getSalaryDate(), (Date)joinDate.getTime()) - 1)));
                log.warn((Object)("******Vijay else block days worked is" + salaryHistory.getDaysWorked()));
            }
        } else if (DateUtils.findMonthsDifference((Date)emp.getJoinDate(), (Date)salaryHistory.getSalaryDate()) < 0 && this.salaryHistoryService.searchSalaryHistoryByEmployeeId(emp.getEmpId()).size() == 0) {
            joinDate.setTime(emp.getJoinDate());
            salaryHistory.setDaysWorked(salaryHistory.getDaysWorked().subtract(new BigDecimal(DateUtils.differenceInDays((Date)salaryHistory.getSalaryDate(), (Date)joinDate.getTime()) - 1)));
        }
    }

    private void generateNetSalary(Salary salary, SalaryHistory salaryHistory, BigDecimal totalDays, BigDecimal salaryForDays, String empId) throws Exception {
        log.debug((Object)("Start:Calculating Net Salary for Employee ID:" + empId));
        try {
            salaryHistory.setEmpId(empId);
            boolean calculate = false;
            BigDecimal actlGross = BigDecimal.ZERO;
            BigDecimal totalDeductions = BigDecimal.ZERO;
            BigDecimal grossSalary = BigDecimal.ZERO;
            BigDecimal netSalary = BigDecimal.ZERO;
            BigDecimal esiCalc = BigDecimal.ZERO;
            BigDecimal pfCalc = BigDecimal.ZERO;
            if (salaryForDays.compareTo(totalDays) != 0) {
                calculate = true;
            }
            salaryHistory.setBasic(this.calculateAmount(salary.getBasic(), totalDays, salaryForDays, calculate));
            salaryHistory.setConveyence(this.calculateAmount(salary.getConveyence(), totalDays, salaryForDays, calculate));
            salaryHistory.setHra(this.calculateAmount(salary.getHra(), totalDays, salaryForDays, calculate));
            salaryHistory.setMedicalAllowance(this.calculateAmount(salary.getMedicalAllowance(), totalDays, salaryForDays, calculate));
            salaryHistory.setTelephoneAllowance(this.calculateAmount(salary.getTelephoneAllowance(), totalDays, salaryForDays, calculate));
            salaryHistory.setInternetAllowance(this.calculateAmount(salary.getInternetAllowance(), totalDays, salaryForDays, calculate));
            salaryHistory.setUniformAllowance(this.calculateAmount(salary.getUniformAllowance(), totalDays, salaryForDays, calculate));
            salaryHistory.setSpecialAllowance(this.calculateAmount(salary.getSpecialAllowance(), totalDays, salaryForDays, calculate));
            salaryHistory.setPerformanceLinkedPay(salary.getPerformanceLinkedPay());
            salaryHistory.setBonus(this.calculateAmount(salary.getBonus(), totalDays, salaryForDays, calculate));
            salaryHistory.setOtherAllowance(this.calculateAmount(salary.getOtherAllowance(), totalDays, salaryForDays, calculate));
            salaryHistory.setMedicalInsurance(SimpleUtils.isEmptyValue((BigDecimal)salary.getMedicalInsurance()));
            salaryHistory.setLoanAmount(SimpleUtils.isEmptyValue((BigDecimal)salary.getLoanAmount()));
            BigDecimal pfAmount = BigDecimal.ZERO;
            if (salary.isPfRequired()) {
                pfAmount = CalculationRules.ELIGIBLE_PF_AMOUNT.compareTo(salaryHistory.getBasic()) == 1 ? salaryHistory.getBasic().divide(new BigDecimal(100), 2, 0).multiply(CalculationRules.PER_PF).setScale(0, 5) : CalculationRules.MIN_PF_AMOUNT;
            }
            salaryHistory.setPfEmp(pfAmount.setScale(2));
            salaryHistory.setPfCom(pfAmount.setScale(2));
            actlGross = actlGross.add(salary.getBasic()).add(salary.getHra()).add(salary.getConveyence()).add(salary.getMedicalAllowance()).add(salary.getSpecialAllowance()).add(salary.getOtherAllowance()).add(salary.getBonus()).add(salary.getPerformanceLinkedPay()).add(salary.getUniformAllowance()).add(salary.getTelephoneAllowance()).add(salary.getInternetAllowance());
            salaryHistory.setActlBasic(salary.getBasic());
            salaryHistory.setActlHra(salary.getHra());
            salaryHistory.setActlConveyence(salary.getConveyence());
            salaryHistory.setActlMedicalAllowance(salary.getMedicalAllowance());
            salaryHistory.setActlSpecialAllowance(salary.getSpecialAllowance());
            salaryHistory.setActlOtherAllowance(salary.getOtherAllowance());
            salaryHistory.setActlGross(actlGross);
            grossSalary = grossSalary.add(salaryHistory.getBasic()).add(salaryHistory.getHra()).add(salaryHistory.getConveyence()).add(salaryHistory.getMedicalAllowance()).add(salaryHistory.getSpecialAllowance()).add(salaryHistory.getOtherAllowance()).add(salaryHistory.getTelephoneAllowance()).add(salaryHistory.getInternetAllowance()).add(salaryHistory.getPerformanceLinkedPay()).add(salaryHistory.getUniformAllowance()).add(salaryHistory.getPerformanceIncentives().add(salaryHistory.getTotalEarnings()).add(salaryHistory.getBonus())).setScale(0, 5);
            if (salary.getEsiEligible()) {
                esiCalc = grossSalary.divide(new BigDecimal(100), 2, 0).multiply(CalculationRules.PER_ESI).setScale(0, 2);
                salaryHistory.setEsi(esiCalc.setScale(2));
            } else {
                salaryHistory.setEsi(new BigDecimal(0.0));
            }
            if (grossSalary.intValue() <= CalculationRules.PT_PAID_ON.intValue()) {
                salaryHistory.setPTax(BigDecimal.ZERO);
            } else {
                ArrayList inputList = new ArrayList(CalculationRules.PT_RANGE.keySet());
                for (int index = 0; index < inputList.size() - 1; ++index) {
                    Integer minPT = (Integer)inputList.get(index) + 1;
                    Integer maxPT = (Integer)inputList.get(index + 1);
                    if (grossSalary.intValue() > (Integer)inputList.get(inputList.size() - 1)) {
                        salaryHistory.setPTax(new BigDecimal((Integer)CalculationRules.PT_RANGE.get(inputList.get(inputList.size() - 1))).setScale(2));
                        break;
                    }
                    if (grossSalary.intValue() < minPT || maxPT < grossSalary.intValue()) continue;
                    salaryHistory.setPTax(new BigDecimal((Integer)CalculationRules.PT_RANGE.get(inputList.get(index))).setScale(2));
                    break;
                }
            }
            salaryHistory.setIncomeTax(salary.getIncomeTax());
            totalDeductions = totalDeductions.add(salaryHistory.getPTax()).add(salaryHistory.getIncomeTax()).add(salaryHistory.getPfEmp()).add(salaryHistory.getSalaryInAdvance()).add(salaryHistory.getEsi()).add(salaryHistory.getMiscDeductions()).add(salaryHistory.getMedicalInsurance()).add(salaryHistory.getLoanAmount()).setScale(0, 5);
            netSalary = grossSalary.subtract(totalDeductions);
            log.info((Object)("Employee - Salary Date::" + salaryHistory.getSalaryDate() + " Gross Salary::" + grossSalary + " Total Deductions::" + totalDeductions + " Net Salary::" + netSalary));
            salaryHistory.setTotalEarnings(grossSalary.setScale(2));
            salaryHistory.setTotalDeductions(totalDeductions.setScale(2));
            salaryHistory.setGrossSalary(grossSalary.setScale(2));
            salaryHistory.setNetSalary(netSalary.setScale(2));
            log.debug((Object)("End:Calculating Net Salary for Employee ID:" + empId));
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error((Object)("Error: While Generating Employee ID:" + empId + "Please try again..!"));
            throw new Exception("Error: While Generating Employee ID:" + empId + "Please try again..!");
        }
    }

    private BigDecimal calculateAmount(BigDecimal amount, BigDecimal totalDays, BigDecimal totalWorkingDays, boolean calculate) {
        BigDecimal finalValue = new BigDecimal(0.0);
        finalValue = calculate ? amount.divide(totalDays, 2, 5).multiply(totalWorkingDays) : amount;
        return finalValue;
    }

    public EmployeeSettlement loadResignedEmployeeSettlement(String empId) {
        return this.employeeSettlementRepository.findResignedEmployeeSettlementByEmployeeId(empId);
    }

    public List<Employee> loadEmployee(String empID) {
        return this.salaryRepository.loadEmployee(empID);
    }

    public List<Employee> loadEmployeesByDepartment(String department) {
        return this.salaryRepository.loadEmployeesByDepartment(department);
    }

    public void setSalaryDAO(SalaryRepository salaryDAO) {
        this.salaryRepository = salaryDAO;
    }

    public void setMailingEngine(com.indianeagle.internal.mail.MailingEngine mailingEngine) {
        this.mailingEngine = mailingEngine;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setEmployeeSettlementDAO(EmployeeSettlementRepository employeeSettlementDAO) {
        this.employeeSettlementRepository = employeeSettlementDAO;
    }

    public EmployeeSettlement getEmployeeSettlement() {
        return this.employeeSettlement;
    }

    public void setEmployeeSettlement(EmployeeSettlement employeeSettlement) {
        this.employeeSettlement = employeeSettlement;
    }

    public void setSalaryHistoryService(SalaryHistoryService salaryHistoryService) {
        this.salaryHistoryService = salaryHistoryService;
    }

    public void setVelocityEngine(TemplateEngine velocityEngine) {
        this.templateEngine = velocityEngine;
    }
}