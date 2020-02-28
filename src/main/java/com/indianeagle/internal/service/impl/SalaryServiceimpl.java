package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.EmployeeSettlementRepository;
import com.indianeagle.internal.dao.repository.SalaryDeciderRepository;
import com.indianeagle.internal.dao.repository.SalaryHistoryRepository;
import com.indianeagle.internal.dao.repository.SalaryRepository;
import com.indianeagle.internal.dto.*;
import com.indianeagle.internal.form.EmpSalaryDeciderVO;
import com.indianeagle.internal.form.EmployeeSettlementForm;
import com.indianeagle.internal.form.GenerateAllSalariesForm;
import com.indianeagle.internal.form.SalaryRule;
import com.indianeagle.internal.mail.MailingEngine;
import com.indianeagle.internal.service.SalaryHistoryService;
import com.indianeagle.internal.service.SalaryService;
import com.indianeagle.internal.util.CalculationRules;
import com.indianeagle.internal.util.DateUtils;
import com.indianeagle.internal.util.PaySlipPdfUtils;
import com.indianeagle.internal.util.SimpleUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SalaryServiceimpl implements SalaryService, MessageSourceAware {
    private static final Logger logger = LogManager.getLogger(SalaryServiceimpl.class);
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private SalaryHistoryRepository salaryHistoryRepository;
    @Autowired
    private Employee employee;
    @Autowired
    private MailingEngine mailingEngine;

    @Autowired
    private EmployeeSettlementRepository employeeSettlementRepository;
    @Autowired
    private SalaryDeciderRepository salaryDeciderRepository;
    @Autowired
    private EmployeeSettlement employeeSettlement;
    @Autowired
    private SalaryHistoryService salaryHistoryService;
    @Autowired
    private TemplateEngine templateEngine;

    public List<Employee> loadAllEmployees() {
        return this.salaryRepository.loadAllEmployees();
    }


    public List<Employee> loadActiveEmployees() {
        return this.salaryRepository.loadActiveEmployees();
    }


    public List<SalaryHistory> generateSalaries(String department, SalaryRule salaryRule) throws Exception {
        logger.debug("Generate Salary for All Employees");
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
        return currentSalaryList;
    }

    public GenerateAllSalariesForm fillProduceAllSalariesForm(GenerateAllSalariesForm generateAllSalariesForm) {
        Calendar cal = GregorianCalendar.getInstance();
        Calendar calforEndDate = GregorianCalendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        calforEndDate.add(Calendar.MONTH, -1);
        cal.set(5, cal.getActualMinimum(Calendar.DATE));
        calforEndDate.set(5, calforEndDate.getActualMaximum(Calendar.DATE));
        if (generateAllSalariesForm.getSalaryDate() == null) {
            generateAllSalariesForm.setSalaryDate(cal.getTime());
        }
        if (generateAllSalariesForm.getSalaryEndDate() == null) {
            generateAllSalariesForm.setSalaryEndDate(calforEndDate.getTime());
        }
        cal.setTime(generateAllSalariesForm.getSalaryDate());
        List<Employee> allEmpsList = salaryRepository.loadActiveEmployees();
        List<SalaryHistory> salaryHistoryList = salaryHistoryService.salaryReport(generateAllSalariesForm.getSalaryDate(), generateAllSalariesForm.getSalaryEndDate());
        //List<SalaryHistory> salaryHistoriesOfMailNotSent = salaryHistoryList.stream().filter(salaryHistory -> salaryHistory.getMailSent().equals(Boolean.FALSE)).collect(Collectors.toList());
        List<EmpSalaryDecider> salaryDecidersOfMailNotSent = salaryDeciderRepository.findAllBetween(generateAllSalariesForm.getSalaryDate(), generateAllSalariesForm.getSalaryEndDate());

        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        if (allEmpsList != null && !allEmpsList.isEmpty()) {
            for (Employee employee : allEmpsList) {
                if (checkIfPresent(employee.getEmpId(), salaryDecidersOfMailNotSent)) {
                    employeeList.add(employee);
                    continue;
                }
                if (!checkIfPresent(employee.getEmpId(), salaryHistoryList)) {
                    employeeList.add(employee);
                }
            }
        }


        ArrayList<EmpSalaryDeciderVO> empSalaryDeciderVOList = new ArrayList<EmpSalaryDeciderVO>();
        for (Employee employee : employeeList) {
            EmpSalaryDeciderVO empSalaryDeciderVO = new EmpSalaryDeciderVO();
            empSalaryDeciderVO.setEmpId(employee.getEmpId());
            empSalaryDeciderVO.setFullName(employee.getFullName());

            EmpSalaryDecider decider = getSalaryDeciderIfPresent(employee, salaryDecidersOfMailNotSent);
            if (decider != null) {
                empSalaryDeciderVO.setLopDays(decider.getLopDays());
                empSalaryDeciderVO.setArrearsDays(decider.getArrearsDays());
                empSalaryDeciderVO.setSalaryInAdvance(decider.getSalaryInAdvance());
                empSalaryDeciderVO.setPerformanceIncentives(decider.getPerformanceIncentives());
            }
            empSalaryDeciderVOList.add(empSalaryDeciderVO);
        }
        generateAllSalariesForm.setTotalWorkingDays(new BigDecimal(cal.getActualMaximum(5)));
        generateAllSalariesForm.setEmpSalaryDeciderVOList(empSalaryDeciderVOList);
        return generateAllSalariesForm;
    }

    private boolean checkIfPresent(String employeeId, List list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Object object = list.get(0);
        if (object instanceof SalaryHistory) {
            List<SalaryHistory> salaryList = (List<SalaryHistory>) list;
            for (SalaryHistory salaryHistory : salaryList) {
                if (!StringUtils.equalsIgnoreCase(employeeId, salaryHistory.getEmpId()))
                    continue;
                return true;
            }
        } else if (object instanceof EmpSalaryDecider) {
            List<EmpSalaryDecider> empSalaryDeciders = (List<EmpSalaryDecider>) list;
            for (EmpSalaryDecider decider : empSalaryDeciders) {
                if (!StringUtils.equalsIgnoreCase(employeeId, decider.getEmpId()))
                    continue;
                return true;
            }
        }
        return false;
    }

    public List<SalaryHistory> produceAllSalaries(GenerateAllSalariesForm generateAllSalariesForm, HttpSession httpSession) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(generateAllSalariesForm.getSalaryDate());
        Calendar calendarForToDate = Calendar.getInstance();
        calendarForToDate.setTime(generateAllSalariesForm.getSalaryEndDate());
        generateAllSalariesForm.setTotalWorkingDays(new BigDecimal(DateUtils.differenceInDays((Date) calendar.getTime(), (Date) calendarForToDate.getTime())));
        SalaryHistory currentSalary = null;
        EmpSalaryDeciderVO empSalaryDeciderVO = new EmpSalaryDeciderVO();
        List<EmpSalaryDeciderVO> empSalaryDeciderVOList = generateAllSalariesForm.getEmpSalaryDeciderVOList();
        ArrayList<SalaryHistory> currentSalaryList = new ArrayList<SalaryHistory>();
        ArrayList<Employee> currSalaryEmpList = new ArrayList<Employee>();
        List<Employee> allEmpsList = this.salaryRepository.loadActiveEmployees();
        List<SalaryHistory> salaryHistoryList = this.salaryHistoryService.salaryReport(generateAllSalariesForm.getSalaryDate(), generateAllSalariesForm.getSalaryEndDate());
        //List<SalaryHistory> salaryHistoriesOfMailNotSent = salaryHistoryList.stream().filter(salaryHistory -> salaryHistory.getMailSent().equals(Boolean.FALSE)).collect(Collectors.toList());
        List<EmpSalaryDecider> salaryDecidersOfMailNotSent = salaryDeciderRepository.findAllBetween(generateAllSalariesForm.getSalaryDate(), generateAllSalariesForm.getSalaryEndDate());

        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        if (allEmpsList != null && !allEmpsList.isEmpty()) {
            for (Employee employee : allEmpsList) {
                if (checkIfPresent(employee.getEmpId(), salaryDecidersOfMailNotSent)) {
                    employeeList.add(employee);
                    continue;
                }
                if (!checkIfPresent(employee.getEmpId(), salaryHistoryList)) {
                    employeeList.add(employee);
                }
            }
        }
        for (Employee emp : employeeList) {
            try {
                logger.debug("in loop: " + emp.getOfficialEmail() + " empSalaryDeciderList:" + empSalaryDeciderVOList);
                currentSalary = new SalaryHistory();
                SalaryRule salaryRule = new SalaryRule();
                for (EmpSalaryDeciderVO esd : empSalaryDeciderVOList) {
                    if (esd.getEmpId() == null || !emp.getEmpId().equals(esd.getEmpId())) continue;
                    empSalaryDeciderVO = esd;
                    break;
                }
                if (empSalaryDeciderVO.getEmpExclude()) continue;
                salaryRule.setArrearsDays(empSalaryDeciderVO.getArrearsDays());
                salaryRule.setLopDays(empSalaryDeciderVO.getLopDays());
                salaryRule.setSalaryInAdvance(empSalaryDeciderVO.getSalaryInAdvance());
                salaryRule.setPerformanceIncentives(empSalaryDeciderVO.getPerformanceIncentives());
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
            } catch (Exception e) {
                httpSession.setAttribute("errorEmployeeId"+httpSession.getId(),emp.getEmpId());
                e.printStackTrace();
                throw e;
            }
        }
        httpSession.setAttribute("currSalaryEmpList" + httpSession.getId(), currSalaryEmpList);
        httpSession.setAttribute("currentSalaryList" + httpSession.getId(), currentSalaryList);
        return currentSalaryList;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void sendAllPaySlipMails(HttpSession httpSession) throws Exception {
        List<SalaryHistory> currentSalaryList = (List) httpSession.getAttribute("currentSalaryList" + httpSession.getId());
        List<Employee> currSalaryEmpList = (List) httpSession.getAttribute("currSalaryEmpList" + httpSession.getId());

        List<EmpSalaryDecider> deciderList = salaryDeciderRepository.findAllBetween(currentSalaryList.get(0).getSalaryDate(), currentSalaryList.get(0).getSalaryEndDate());
        for (EmpSalaryDecider salaryDecider: deciderList){
            System.out.println("@@@@Decider:: "+salaryDecider.getEmpId());
            if (checkIfPresent(salaryDecider.getEmpId(),currentSalaryList)) {
                System.out.println("@@@FoundToDelete >> "+salaryDecider.getEmpId());
                salaryDeciderRepository.delete(salaryDecider);
            }
        }

        salaryHistoryRepository.saveAll(currentSalaryList);
        int counter = 0;
        for (Employee emp : currSalaryEmpList) {
            if (counter == 20) {
                logger.debug("::::::::: Hold on for 2 minutes :::::::::");
                counter = 0;
                Thread.sleep(120000);
                logger.debug("Thank you for your time! Continue......");
            }
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            PaySlipPdfUtils.generatePaySlipPdf(templateEngine, arrayOutputStream, "mail/IE_Payslip", (Object[]) new Object[]{emp, emp.getCurrentSalary()});
            this.mailingEngine.sendMail(emp, emp.getCurrentSalary(), (InputStreamSource) new ByteArrayResource(arrayOutputStream.toByteArray()), false);
            counter += 1;
        }
        httpSession.removeAttribute("currentSalaryList" + httpSession.getId());
        httpSession.removeAttribute("currSalaryEmpList" + httpSession.getId());
    }

    public SalaryHistory generateSalary(Employee employee, SalaryRule salaryRule) throws Exception {
        logger.debug((Object) ("Generate Salary for Employee ID:" + employee.getEmpId()));
        Calendar cal = GregorianCalendar.getInstance();
        Calendar cal1 = GregorianCalendar.getInstance();
        cal.setTime(salaryRule.getSalaryDate());
        cal1.setTime(salaryRule.getSalaryEndDate());
        salaryRule.setTotalDays(new BigDecimal(DateUtils.differenceInDays((Date) cal.getTime(), (Date) cal1.getTime())));
        SalaryHistory salaryHistory = this.createSalaryHistory(salaryRule, null, employee);
        this.generateNetSalary(employee.getSalary(), salaryHistory, new BigDecimal(cal.getActualMaximum(5)), salaryHistory.getDaysWorked(), employee.getEmpId());
        return salaryHistory;
    }

    public EmployeeSettlement generateSalarySettlement(Employee employee, EmployeeSettlementForm employeeSettlementForm) throws Exception {
        logger.debug((Object) ("Generate Salary for Employee ID:" + employee.getEmpId()));
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
        BeanUtils.copyProperties((Object) salaryHistory, (Object) employeeSettlement);
        employeeSettlement.setTotalDays(salaryHistory.getDaysWorked());
        return employeeSettlement;
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void confirmAndSendMail(Employee employee, SalaryHistory currentSalary) throws Exception {
        SalaryHistory salaryHistory = salaryHistoryRepository.save(currentSalary);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        PaySlipPdfUtils.generatePaySlipPdf(templateEngine, arrayOutputStream, "mail/IE_Payslip", (Object[]) new Object[]{employee, currentSalary});
        mailingEngine.sendMail(employee, currentSalary, (InputStreamSource) new ByteArrayResource(arrayOutputStream.toByteArray()), false);
    }

    public void confirmEmployeeSettlement() {
        this.employeeSettlementRepository.save(this.employeeSettlement);
    }

    private SalaryHistory createSalaryHistory(SalaryRule salaryRule, EmployeeSettlementForm salarySettlementForm, Employee emp) {
        SalaryHistory salaryHistory = new SalaryHistory();
        if (salaryRule != null) {
            BigDecimal salaryForDays = SimpleUtils.isEmptyValue((BigDecimal) salaryRule.getTotalDays()).add(SimpleUtils.isEmptyValue((BigDecimal) salaryRule.getArrearsDays())).subtract(SimpleUtils.isEmptyValue((BigDecimal) salaryRule.getLopDays()));
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
        if (DateUtils.findMonthsDifference((Date) emp.getJoinDate(), (Date) salaryHistory.getSalaryDate()) == 1 && this.salaryHistoryService.searchSalaryHistoryByEmployeeId(emp.getEmpId()).size() == 0) {
            joinDate.setTime(emp.getJoinDate());
            BigDecimal previousMonthWorkingDays = new BigDecimal(DateUtils.differenceInDays((Date) joinDate.getTime(), (Date) salaryHistory.getSalaryDate()) - 1);
            salaryHistory.setArrearsDays(salaryHistory.getArrearsDays().add(previousMonthWorkingDays));
            salaryHistory.setDaysWorked(salaryHistory.getDaysWorked().add(previousMonthWorkingDays));
        } else if (DateUtils.findMonthsDifference((Date) emp.getJoinDate(), (Date) salaryHistory.getSalaryDate()) == 0) {
            joinDate = Calendar.getInstance();
            joinDate.setTime(emp.getJoinDate());
            int daysDifference = DateUtils.differenceInDays((Date) joinDate.getTime(), (Date) salaryHistory.getSalaryDate()) - 1;
            if (daysDifference > 0) {
                logger.warn((Object) "Vijay if block");
                salaryHistory.setArrearsDays(salaryHistory.getArrearsDays().add(new BigDecimal(daysDifference)));
                salaryHistory.setDaysWorked(salaryHistory.getDaysWorked().add(new BigDecimal(daysDifference)));
                logger.warn((Object) ("******Vijay if block days worked is" + salaryHistory.getDaysWorked()));
            } else if (daysDifference < 0) {
                logger.warn((Object) "Vijay else block");
                logger.warn((Object) ("******Vijay joindate is" + new BigDecimal(joinDate.get(5))));
                salaryHistory.setDaysWorked(salaryHistory.getDaysWorked().subtract(new BigDecimal(DateUtils.differenceInDays((Date) salaryHistory.getSalaryDate(), (Date) joinDate.getTime()) - 1)));
                logger.warn((Object) ("******Vijay else block days worked is" + salaryHistory.getDaysWorked()));
            }
        } else if (DateUtils.findMonthsDifference((Date) emp.getJoinDate(), (Date) salaryHistory.getSalaryDate()) < 0 && this.salaryHistoryService.searchSalaryHistoryByEmployeeId(emp.getEmpId()).size() == 0) {
            joinDate.setTime(emp.getJoinDate());
            salaryHistory.setDaysWorked(salaryHistory.getDaysWorked().subtract(new BigDecimal(DateUtils.differenceInDays((Date) salaryHistory.getSalaryDate(), (Date) joinDate.getTime()) - 1)));
        }
    }

    private void generateNetSalary(Salary salary, SalaryHistory salaryHistory, BigDecimal totalDays, BigDecimal salaryForDays, String empId) throws Exception {
        logger.debug((Object) ("Start:Calculating Net Salary for Employee ID:" + empId));
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
            salaryHistory.setMedicalInsurance(SimpleUtils.isEmptyValue((BigDecimal) salary.getMedicalInsurance()));
            salaryHistory.setLoanAmount(SimpleUtils.isEmptyValue((BigDecimal) salary.getLoanAmount()));
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
                    Integer minPT = (Integer) inputList.get(index) + 1;
                    Integer maxPT = (Integer) inputList.get(index + 1);
                    if (grossSalary.intValue() > (Integer) inputList.get(inputList.size() - 1)) {
                        salaryHistory.setPTax(new BigDecimal((Integer) CalculationRules.PT_RANGE.get(inputList.get(inputList.size() - 1))).setScale(2));
                        break;
                    }
                    if (grossSalary.intValue() < minPT || maxPT < grossSalary.intValue()) continue;
                    salaryHistory.setPTax(new BigDecimal((Integer) CalculationRules.PT_RANGE.get(inputList.get(index))).setScale(2));
                    break;
                }
            }
            salaryHistory.setIncomeTax(salary.getIncomeTax());
            totalDeductions = totalDeductions.add(salaryHistory.getPTax()).add(salaryHistory.getIncomeTax()).add(salaryHistory.getPfEmp()).add(salaryHistory.getSalaryInAdvance()).add(salaryHistory.getEsi()).add(salaryHistory.getMiscDeductions()).add(salaryHistory.getMedicalInsurance()).add(salaryHistory.getLoanAmount()).setScale(0, 5);
            netSalary = grossSalary.subtract(totalDeductions);
            logger.info((Object) ("Employee - Salary Date::" + salaryHistory.getSalaryDate() + " Gross Salary::" + grossSalary + " Total Deductions::" + totalDeductions + " Net Salary::" + netSalary));
            salaryHistory.setTotalEarnings(grossSalary.setScale(2));
            salaryHistory.setTotalDeductions(totalDeductions.setScale(2));
            salaryHistory.setGrossSalary(grossSalary.setScale(2));
            salaryHistory.setNetSalary(netSalary.setScale(2));
            logger.debug((Object) ("End:Calculating Net Salary for Employee ID:" + empId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error((Object) ("Error: While Generating Employee ID:" + empId + "Please try again..!"));
            throw new Exception("Error: While Generating Employee ID:" + empId + "Please try again..!");

        }
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void saveSalaries(HttpSession httpSession) {
        List<SalaryHistory> currentSalaryList = (List) httpSession.getAttribute("currentSalaryList" + httpSession.getId());

        List<EmpSalaryDecider> newDeciderList = new ArrayList<>();

        for (SalaryHistory salary : currentSalaryList) {
            if (salary.getLopDays().equals(BigDecimal.ZERO) && salary.getArrearsDays().equals(BigDecimal.ZERO) && salary.getSalaryInAdvance().equals(BigDecimal.ZERO) && salary.getPerformanceIncentives().equals(BigDecimal.ZERO)) {
                continue;
            }
            EmpSalaryDecider decider = new EmpSalaryDecider();
            decider.setEmpId(salary.getEmpId());
            decider.setSalaryDate(salary.getSalaryDate());
            decider.setSalaryEndDate(salary.getSalaryEndDate());
            decider.setLopDays(salary.getLopDays());
            decider.setArrearsDays(salary.getArrearsDays());
            decider.setPerformanceIncentives(salary.getPerformanceIncentives());
            decider.setSalaryInAdvance(salary.getSalaryInAdvance());
            newDeciderList.add(decider);
        }

        List<EmpSalaryDecider> deciderList = salaryDeciderRepository.findAllBetween(currentSalaryList.get(0).getSalaryDate(), currentSalaryList.get(0).getSalaryEndDate());
        List<EmpSalaryDecider> deciderListToBeSaved = new ArrayList<>();
        for (EmpSalaryDecider empSalaryDecider : newDeciderList) {
            if (!checkIfPresent(empSalaryDecider.getEmpId(), deciderList)) {
                deciderListToBeSaved.add(empSalaryDecider);
                continue;
            }
            saveIfUpdated(empSalaryDecider, deciderList);
        }

        salaryDeciderRepository.saveAll(deciderListToBeSaved);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    private void saveIfUpdated(EmpSalaryDecider salaryDecider, List<EmpSalaryDecider> deciderList) {
        for (EmpSalaryDecider decider : deciderList) {
            if (StringUtils.equalsIgnoreCase(salaryDecider.getEmpId(), decider.getEmpId())) {
                if (salaryDecider.getLopDays() != decider.getLopDays())
                    decider.setLopDays(salaryDecider.getLopDays());

                if (salaryDecider.getArrearsDays() != decider.getArrearsDays())
                    decider.setArrearsDays(salaryDecider.getArrearsDays());

                if (salaryDecider.getPerformanceIncentives() != decider.getPerformanceIncentives())
                    decider.setPerformanceIncentives(salaryDecider.getPerformanceIncentives());

                if (salaryDecider.getSalaryInAdvance() != decider.getSalaryInAdvance())
                    decider.setSalaryInAdvance(salaryDecider.getSalaryInAdvance());
            }
        }
    }

    private BigDecimal calculateAmount(BigDecimal amount, BigDecimal totalDays, BigDecimal totalWorkingDays, boolean calculate) {
        BigDecimal finalValue = new BigDecimal(0.0);
        finalValue = calculate ? amount.divide(totalDays, 2, 5).multiply(totalWorkingDays) : amount;
        return finalValue;
    }

    public EmployeeSettlement loadResignedEmployeeSettlement(String empId) {
           List<EmployeeSettlement> employeeSettlements=     this.employeeSettlementRepository.findResignedEmployeeSettlementByEmployeeId(empId );
          return employeeSettlements.isEmpty()?null:employeeSettlements.get(0);
    }

    public Employee loadEmployee(String empID) {
        List<Employee> employeeList = this.salaryRepository.loadEmployee(empID);
        return employeeList.isEmpty() ? null : employeeList.get(0);
    }

    public List<Employee> loadEmployeesByDepartment(String department) {
        return this.salaryRepository.loadEmployeesByDepartment(department);
    }

    public EmpSalaryDecider getSalaryDeciderIfPresent(Employee employee, List<EmpSalaryDecider> salaryDeciders) {
        for (EmpSalaryDecider salaryDecider : salaryDeciders) {
            if (salaryDecider.getEmpId().equalsIgnoreCase(employee.getEmpId()))
                return salaryDecider;
        }
        return null;
    }

    public EmpSalaryDecider getIf(Employee employee, List<EmpSalaryDecider> salaryDeciders) {
        for (EmpSalaryDecider salaryDecider : salaryDeciders) {
            if (salaryDecider.getEmpId().equalsIgnoreCase(employee.getEmpId()))
                return salaryDecider;
        }
        return null;
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