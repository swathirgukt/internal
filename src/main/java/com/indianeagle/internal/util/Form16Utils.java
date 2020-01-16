package com.indianeagle.internal.util;

import com.indianeagle.internal.dto.*;
import com.indianeagle.internal.form.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * This class represents utility methods for Form 16 Generation
 *
 * User: kalesha
 * Date: 8/17/2017
 */
public class Form16Utils {

    private static final BigDecimal HUNDRED = new BigDecimal(100);
    private static List<String> SALARY_COLUMNS = Arrays.asList("MONTH", "BASIC", "HRA", "CON", "MED", "SPL", "GRA", "BONUS", "PER", "N.A", "T.A", "GROSS SALARY", "PF", "ESI", "PT", "MED.IND", "SAL.ADV", "TDS", "TOT.D", "NET SALERY");

    /**
     * To prepare a FinancialYear DTO object
     *
     * @param financialYearForm
     * @return Financial Year
     */
    public static FinancialYear prepareFinancialYear(FinancialYearForm financialYearForm) {
        FinancialYear financialYear = new FinancialYear();
        financialYear.setId(financialYearForm.getId());
        financialYear.setFromMonth(financialYearForm.getFromMonth());
        financialYear.setFromYear(financialYearForm.getFromYear());
        financialYear.setToMonth(financialYearForm.getToMonth());
        financialYear.setToYear(financialYearForm.getToYear());
        if(financialYearForm.getIncomeTaxSlabs() != null && !financialYearForm.getIncomeTaxSlabs().isEmpty()) {
            Collections.sort(financialYearForm.getIncomeTaxSlabs());
            financialYear.setIncomeTaxSlabs(new TreeSet<IncomeTaxSlab>(financialYearForm.getIncomeTaxSlabs()));
        }
        Set<TaxSection> taxSections = new HashSet<TaxSection>();
        if (financialYearForm.getTaxSectionForms() != null) {
            for (TaxSectionForm taxSectionForm : financialYearForm.getTaxSectionForms()) {
                TaxSection taxSection = new TaxSection();
                taxSection.setId(taxSectionForm.getId());
                taxSection.setSectionName(taxSectionForm.getSectionName());
                taxSection.setSectionLimit(taxSectionForm.getSectionLimit());
                taxSection.setActive(taxSectionForm.isActive());
                taxSection.setTaxSectionDeclarations(new HashSet<TaxSectionDeclaration>(taxSectionForm.getTaxSectionDeclarations()));
                taxSections.add(taxSection);
            }
        }
        financialYear.setTaxSections(taxSections);
        if(financialYearForm.getRebates() != null && !financialYearForm.getRebates().isEmpty()) {
            financialYear.setRebates(new HashSet<Rebate>(financialYearForm.getRebates()));
        }
        return financialYear;
    }

    /**
     * To prepare Financial Year Form object
     *
     * @param financialYear
     * @return Financial Year Form
     */
    public static FinancialYearForm prepareFinancialYearForm(FinancialYear financialYear) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        financialYearForm.setId(financialYear.getId());
        financialYearForm.setFromMonth(financialYear.getFromMonth());
        financialYearForm.setFromYear(financialYear.getFromYear());
        financialYearForm.setToMonth(financialYear.getToMonth());
        financialYearForm.setToYear(financialYear.getToYear());
        if (financialYear.getIncomeTaxSlabs() != null && !financialYear.getIncomeTaxSlabs().isEmpty()) {
            financialYearForm.setIncomeTaxSlabs(new ArrayList<IncomeTaxSlab>(financialYear.getIncomeTaxSlabs()));
            Collections.sort(financialYearForm.getIncomeTaxSlabs());
        }
        List<TaxSectionForm> taxSectionForms = new ArrayList<TaxSectionForm>();
        if (financialYear.getTaxSections() != null) {
            List<TaxSection> taxSections = new ArrayList<TaxSection>(financialYear.getTaxSections());
            Collections.sort(taxSections);
            for (TaxSection taxSection : taxSections) {
                TaxSectionForm taxSectionForm = new TaxSectionForm();
                taxSectionForm.setId(taxSection.getId());
                taxSectionForm.setSectionName(taxSection.getSectionName());
                taxSectionForm.setSectionLimit(taxSection.getSectionLimit());
                taxSectionForm.setActive(taxSection.isActive());
                taxSectionForm.setTaxSectionDeclarations(new ArrayList<TaxSectionDeclaration>(taxSection.getTaxSectionDeclarations()));
                taxSectionForms.add(taxSectionForm);
            }
        }
        financialYearForm.setTaxSectionForms(taxSectionForms);
        if (financialYear.getRebates() != null && !financialYear.getRebates().isEmpty()) {
            financialYearForm.setRebates(new ArrayList<Rebate>(financialYear.getRebates()));
        }
        return financialYearForm;
    }

    /**
     * To prepare Employee Financial Year Form object
     *
     * @param employeeFinancialYear
     * @return Employee Financial Year Form
     */
    public static EmployeeFinancialYearForm prepareEmployeeFinancialYearForm(EmployeeFinancialYear employeeFinancialYear) {
        EmployeeFinancialYearForm employeeFinancialYearForm = new EmployeeFinancialYearForm();
        employeeFinancialYearForm.setId(employeeFinancialYear.getId());
        employeeFinancialYearForm.setEmpId(employeeFinancialYear.getEmpId());
        employeeFinancialYearForm.setFinancialYear(employeeFinancialYear.getFinancialYear());
        List<EmployeeTaxSectionForm> employeeTaxSectionForms = new ArrayList<EmployeeTaxSectionForm>();
        if (employeeFinancialYear.getEmployeeTaxSections() != null) {
            List<EmployeeTaxSection> employeeTaxSections = new ArrayList<EmployeeTaxSection>(employeeFinancialYear.getEmployeeTaxSections());
            Collections.sort(employeeTaxSections);
            for (EmployeeTaxSection employeeTaxSection : employeeTaxSections) {
                EmployeeTaxSectionForm employeeTaxSectionForm = new EmployeeTaxSectionForm();
                employeeTaxSectionForm.setId(employeeTaxSection.getId());
                employeeTaxSectionForm.setSectionName(employeeTaxSection.getSectionName());
                employeeTaxSectionForm.setSectionLimit(employeeTaxSection.getSectionLimit());
                employeeTaxSectionForm.setActive(employeeTaxSection.isActive());
                if (employeeTaxSection.getEmployeeTaxSectionDeclarations() != null) {
                    employeeTaxSectionForm.setEmployeeTaxSectionDeclarations(new ArrayList<EmployeeTaxSectionDeclaration>(employeeTaxSection.getEmployeeTaxSectionDeclarations()));
                }
                employeeTaxSectionForms.add(employeeTaxSectionForm);
            }
        }
        employeeFinancialYearForm.setEmployeeTaxSectionForms(employeeTaxSectionForms);
        return employeeFinancialYearForm;
    }

    /**
     * To prepare Employee Financial Year DTO object
     *
     * @param employeeFinancialYearForm
     * @return Employee Financial Year
     */
    public static EmployeeFinancialYear prepareEmployeeFinancialYear(EmployeeFinancialYearForm employeeFinancialYearForm) {
        EmployeeFinancialYear employeeFinancialYear = new EmployeeFinancialYear();
        employeeFinancialYear.setId(employeeFinancialYearForm.getId());
        employeeFinancialYear.setEmpId(employeeFinancialYearForm.getEmpId());
        employeeFinancialYear.setFinancialYear(employeeFinancialYearForm.getFinancialYear());
        Set<EmployeeTaxSection> employeeTaxSections = new HashSet<EmployeeTaxSection>();
        if (employeeFinancialYearForm.getEmployeeTaxSectionForms() != null) {
            for (EmployeeTaxSectionForm employeeTaxSectionForm : employeeFinancialYearForm.getEmployeeTaxSectionForms()) {
                EmployeeTaxSection employeeTaxSection = new EmployeeTaxSection();
                employeeTaxSection.setId(employeeTaxSectionForm.getId());
                employeeTaxSection.setSectionName(employeeTaxSectionForm.getSectionName());
                employeeTaxSection.setSectionLimit(employeeTaxSectionForm.getSectionLimit());
                employeeTaxSection.setActive(employeeTaxSectionForm.isActive());
                if (employeeTaxSectionForm.getEmployeeTaxSectionDeclarations() != null) {
                    employeeTaxSection.setEmployeeTaxSectionDeclarations(new HashSet<EmployeeTaxSectionDeclaration>(employeeTaxSectionForm.getEmployeeTaxSectionDeclarations()));
                }
                employeeTaxSections.add(employeeTaxSection);
            }
        }
        employeeFinancialYear.setEmployeeTaxSections(employeeTaxSections);
        return employeeFinancialYear;
    }


    /**
     * To Prepare a date by using financial Year month and year
     *
     * @param month financial Year start month or end month
     * @param year financial Year
     * @return date
     */
    public static Date prepareDateWithMonthAndYear(String month, String year) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        List<String> monthList = Arrays.asList(financialYearForm.getMonths());
        int monthIndex = monthList.indexOf(month);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, monthIndex);
        calendar.set(Calendar.YEAR, Integer.valueOf(year));
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        return date;
    }

    public static String nextMonth(Date date, int index) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, index + 1);
        Date d = calendar.getTime();
        return DateFormatter.getMonth(d);
    }

    /**
     * To calculate totals of salaryHistory fields
     *
     * @param salaryHistories list of Salary History
     * @return Total Salary History Form
     */
    public static TotalSalaryHistoryForm prepareTotalSalary(List<SalaryHistory> salaryHistories, FinancialYear financialYear) {
        if (salaryHistories != null && !salaryHistories.isEmpty()) {
            TotalSalaryHistoryForm totalSalaryHistoryForm = new TotalSalaryHistoryForm();
            BigDecimal totalBasic = BigDecimal.ZERO;
            BigDecimal totalHRA = BigDecimal.ZERO;
            BigDecimal totalConveyence = BigDecimal.ZERO;
            BigDecimal totalMedicalAllowance = BigDecimal.ZERO;
            BigDecimal totalSpecialAllowance = BigDecimal.ZERO;
            BigDecimal totalTelephoneAllowance = BigDecimal.ZERO;
            BigDecimal totalBonus = BigDecimal.ZERO;
            BigDecimal totalPerformanceLinkedPay = BigDecimal.ZERO;
            BigDecimal totalInternetAllowance = BigDecimal.ZERO;
            BigDecimal totalUniformAllowance = BigDecimal.ZERO;
            BigDecimal totalGrossSalary = BigDecimal.ZERO;
            BigDecimal totalPfEmp = BigDecimal.ZERO;
            BigDecimal totalEsi = BigDecimal.ZERO;
            BigDecimal totalPtax = BigDecimal.ZERO;
            BigDecimal totalMedicalInsurance = BigDecimal.ZERO;
            BigDecimal totalSalaryInAdvance = BigDecimal.ZERO;
            BigDecimal totalTdsDeducted = BigDecimal.ZERO;
            BigDecimal totalDeductions = BigDecimal.ZERO;
            BigDecimal totalNetSalary = BigDecimal.ZERO;
            for (SalaryHistory salaryHistory : salaryHistories) {
                totalBasic = totalBasic.add(salaryHistory.getBasic());
                totalHRA = totalHRA.add(salaryHistory.getHra());
                totalConveyence = totalConveyence.add(salaryHistory.getConveyence());
                totalMedicalAllowance = totalMedicalAllowance.add(salaryHistory.getMedicalAllowance());
                totalSpecialAllowance = totalSpecialAllowance.add(salaryHistory.getSpecialAllowance());
                totalTelephoneAllowance = totalTelephoneAllowance.add(salaryHistory.getTelephoneAllowance());
                totalBonus = totalBonus.add(salaryHistory.getBonus());
                totalPerformanceLinkedPay = totalPerformanceLinkedPay.add(salaryHistory.getPerformanceLinkedPay());
                totalInternetAllowance = totalInternetAllowance.add(salaryHistory.getInternetAllowance());
                totalUniformAllowance = totalUniformAllowance.add(salaryHistory.getUniformAllowance());
                totalGrossSalary = totalGrossSalary.add(salaryHistory.getGrossSalary());
                totalPfEmp = totalPfEmp.add(salaryHistory.getPfEmp());
                totalEsi = totalEsi.add(salaryHistory.getEsi());
                totalPtax = totalPtax.add(salaryHistory.getPTax());
                totalMedicalInsurance = totalMedicalInsurance.add(salaryHistory.getMedicalInsurance());
                totalSalaryInAdvance = totalSalaryInAdvance.add(salaryHistory.getSalaryInAdvance());
                totalTdsDeducted = totalTdsDeducted.add(salaryHistory.getIncomeTax());
                totalDeductions = totalDeductions.add(salaryHistory.getTotalDeductions());
                totalNetSalary = totalNetSalary.add(salaryHistory.getNetSalary());
            }
            SalaryHistory lastSalary = salaryHistories.get(salaryHistories.size()-1);
            int remainingMonths = calculateUnSalariedMonths(financialYear, lastSalary.getSalaryDate());
            for(int i=0; i<= remainingMonths; i++) {
                totalBasic = totalBasic.add(lastSalary.getActlBasic());
                totalHRA = totalHRA.add(lastSalary.getActlHra());
                totalConveyence = totalConveyence.add(lastSalary.getActlConveyence());
                totalMedicalAllowance = totalMedicalAllowance.add(lastSalary.getActlMedicalAllowance());
                totalSpecialAllowance = totalSpecialAllowance.add(lastSalary.getActlSpecialAllowance());
                totalTelephoneAllowance = totalTelephoneAllowance.add(lastSalary.getTelephoneAllowance());
                totalBonus = totalBonus.add(lastSalary.getBonus());
                totalPerformanceLinkedPay = totalPerformanceLinkedPay.add(lastSalary.getPerformanceLinkedPay());
                totalInternetAllowance = totalInternetAllowance.add(lastSalary.getInternetAllowance());
                totalUniformAllowance = totalUniformAllowance.add(lastSalary.getUniformAllowance());
                totalGrossSalary = totalGrossSalary.add(lastSalary.getActlGross());
                totalPfEmp = totalPfEmp.add(lastSalary.getPfEmp());
                totalEsi = totalEsi.add(lastSalary.getEsi());
                totalPtax = totalPtax.add(lastSalary.getPTax());
                totalMedicalInsurance = totalMedicalInsurance.add(lastSalary.getMedicalInsurance());
                totalSalaryInAdvance = totalSalaryInAdvance.add(lastSalary.getSalaryInAdvance());
                totalTdsDeducted = totalTdsDeducted.add(lastSalary.getIncomeTax());
                totalDeductions = totalDeductions.add(lastSalary.getTotalDeductions());
                totalNetSalary = totalNetSalary.add(lastSalary.getNetSalary());
            }
            totalSalaryHistoryForm.setTotalBasic(totalBasic);
            totalSalaryHistoryForm.setTotalHRA(totalHRA);
            totalSalaryHistoryForm.setTotalConveyence(totalConveyence);
            totalSalaryHistoryForm.setTotalMedicalAllowance(totalMedicalAllowance);
            totalSalaryHistoryForm.setTotalSpecialAllowance(totalSpecialAllowance);
            totalSalaryHistoryForm.setTotalTelephoneAllowance(totalTelephoneAllowance);
            totalSalaryHistoryForm.setTotalBonus(totalBonus);
            totalSalaryHistoryForm.setTotalPerformanceLinkedPay(totalPerformanceLinkedPay);
            totalSalaryHistoryForm.setTotalInternetAllowance(totalInternetAllowance);
            totalSalaryHistoryForm.setTotalUniformAllowance(totalUniformAllowance);
            totalSalaryHistoryForm.setTotalGrossSalary(totalGrossSalary);
            totalSalaryHistoryForm.setTotalPfEmp(totalPfEmp);
            totalSalaryHistoryForm.setTotalEsi(totalEsi);
            totalSalaryHistoryForm.setTotalPtax(totalPtax);
            totalSalaryHistoryForm.setTotalMedicalInsurance(totalMedicalInsurance);
            totalSalaryHistoryForm.setTotalSalaryInAdvance(totalSalaryInAdvance);
            totalSalaryHistoryForm.setTotalTdsDeducted(totalTdsDeducted);
            totalSalaryHistoryForm.setTotalDeductions(totalDeductions);
            totalSalaryHistoryForm.setTotalNetSalary(totalNetSalary);
            return totalSalaryHistoryForm;
        }
        return null;
    }

    /**
     * To prepare Employee Income Tax DTO object
     *
     * @param form16GenerationForm
     * @param financialYear
     * @param empId
     * @return Employee Income Tax
     */
    public static EmployeeIncomeTax prepareEmployeeIncomeTax(Form16GenerationForm form16GenerationForm, FinancialYear financialYear, String empId){
        EmployeeIncomeTax employeeIncomeTax = new EmployeeIncomeTax();
        try {
            BeanUtils.copyProperties(employeeIncomeTax, form16GenerationForm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        employeeIncomeTax.setEmpId(empId);
        employeeIncomeTax.setFinancialYear(financialYear);
        if(employeeIncomeTax.getId() == 0L){
            employeeIncomeTax.setId(null);
        }
        return employeeIncomeTax;
    }

    /**
     * To prepare Form16 Generation Form object
     *
     * @param employeeIncomeTax
     * @return Form 16 Generation Form
     */
    public static Form16GenerationForm prepareForm16GenerationForm(EmployeeIncomeTax employeeIncomeTax) {
        Form16GenerationForm form16GenerationForm = new Form16GenerationForm();
        try {
            BeanUtils.copyProperties(form16GenerationForm, employeeIncomeTax);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return form16GenerationForm;
    }

    /**
     * To prepare section save amount
     *
     * @param employeeTaxSection
     * @param calculatedHRA
     * @return
     */
    public static EmployeeTaxSection prepareSectionSaveAmount(EmployeeTaxSection employeeTaxSection, BigDecimal calculatedHRA) {
        BigDecimal saveAmount = BigDecimal.ZERO;
        if (employeeTaxSection.getEmployeeTaxSectionDeclarations() != null) {
            for (EmployeeTaxSectionDeclaration employeeTaxSectionDeclaration : employeeTaxSection.getEmployeeTaxSectionDeclarations()) {
                if (employeeTaxSectionDeclaration.getSubSectionName().equalsIgnoreCase("HRA")) {
                    saveAmount = saveAmount.add(calculatedHRA);
                } else {
                    saveAmount = saveAmount.add(employeeTaxSectionDeclaration.getSaveAmount());
                }
            }
            if (saveAmount.compareTo(employeeTaxSection.getSectionLimit()) <= 0) {
                employeeTaxSection.setSectionLimit(saveAmount);
            }
        }
        return employeeTaxSection;
    }

    /**
     * To calculate total exemption amount
     *
     * @param employeeFinancialYear
     * @param calculatedHRA
     * @return
     */
    public static BigDecimal totalExemptionAmount(EmployeeFinancialYear employeeFinancialYear, BigDecimal calculatedHRA) {
        BigDecimal totalExemptionAmount = BigDecimal.ZERO;
        Set<EmployeeTaxSection> employeeTaxSections = employeeFinancialYear.getEmployeeTaxSections();
        if (employeeTaxSections != null) {
            BigDecimal exemptionAmount = BigDecimal.ZERO;
            for (EmployeeTaxSection employeeTaxSection : employeeTaxSections) {
                if (employeeTaxSection.getEmployeeTaxSectionDeclarations() != null) {
                    for (EmployeeTaxSectionDeclaration employeeTaxSectionDeclaration : employeeTaxSection.getEmployeeTaxSectionDeclarations()) {
                        if (employeeTaxSectionDeclaration.getSubSectionName().equalsIgnoreCase("HRA")) {
                            exemptionAmount = exemptionAmount.add(calculatedHRA);
                        } else {
                            exemptionAmount = exemptionAmount.add(employeeTaxSectionDeclaration.getSaveAmount());
                        }
                    }
                    if (exemptionAmount.compareTo(employeeTaxSection.getSectionLimit()) > 0) {
                        exemptionAmount = employeeTaxSection.getSectionLimit();
                    }
                }
                totalExemptionAmount = totalExemptionAmount.add(exemptionAmount);
            }
        }
        return totalExemptionAmount;
    }

    public static boolean isRebateEligible(Rebate rebate, BigDecimal salary) {
        return (salary.compareTo(rebate.getRebateApplySalary()) <= 0);
    }

    /**
     * To calculate the how many remaining months the salary is need to pay in the financial year
     *
     * @param financialYear
     * @param lastSalaryDate
     * @return the remaining months
     */
    public static int calculateUnSalariedMonths(FinancialYear financialYear, Date lastSalaryDate) {
        FinancialYearForm financialYearForm = new FinancialYearForm();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastSalaryDate);
        int lastSalaryMonthIndex = calendar.get(Calendar.MONTH);
        int remainingMonths = 0;
        List<String> monthList = Arrays.asList(financialYearForm.getMonths());
        if (Integer.valueOf(financialYear.getFromYear()) < Integer.valueOf(financialYear.getToYear())) {
            int toMonthIndex = monthList.indexOf(financialYear.getToMonth());
            if (toMonthIndex >= lastSalaryMonthIndex) {
                remainingMonths = toMonthIndex - lastSalaryMonthIndex;
            } else {
                remainingMonths = toMonthIndex + (11 - lastSalaryMonthIndex);
            }
        }
        return remainingMonths;
    }

    /**
     * To calculate HRA for a financial Year
     *
     * @param totalActualHRA
     * @param hraWithBasic10per
     * @param basic40per
     * @return minimum of ActualHRA , 40% of Basic and (hra - 10% of basic)
     */
    public static BigDecimal calculateHRA(BigDecimal totalActualHRA, BigDecimal hraWithBasic10per, BigDecimal basic40per) {
        return (totalActualHRA.min(hraWithBasic10per).min(basic40per)).setScale(2,BigDecimal.ROUND_CEILING);
    }

    /**
     * To calculate total basic
     *
     * @param salaryHistories
     * @param financialYear
     * @return total basic HRA
     */
    public static BigDecimal calculateBasicHRA(List<SalaryHistory> salaryHistories, FinancialYear financialYear) {
        BigDecimal basicHRA = BigDecimal.ZERO;
        if (salaryHistories != null && !salaryHistories.isEmpty()) {
            for (SalaryHistory salaryHistory : salaryHistories) {
                basicHRA = basicHRA.add(salaryHistory.getBasic());
            }
            int remainingMonths = calculateUnSalariedMonths(financialYear, salaryHistories.get(salaryHistories.size() - 1).getSalaryEndDate());
            for (int i = 0; i <= remainingMonths; i++) {
                basicHRA = basicHRA.add(salaryHistories.get(0).getActlBasic());
            }
        }
        return basicHRA;
    }

    /**
     * To calculate total hra exclude 10% of basic
     *
     * @param salaryHistories
     * @param financialYear
     * @param employeeFinancialYear
     * @return employee given HRA - 10% of basic
     */
    public static BigDecimal calculateHraWithBasic10per(List<SalaryHistory> salaryHistories, FinancialYear financialYear, EmployeeFinancialYear employeeFinancialYear) {
        BigDecimal hraWithBasic10per = BigDecimal.ZERO;
        BigDecimal basic10per = calculateBasicHRA(salaryHistories, financialYear);
        EmployeeTaxSectionDeclaration hraTaxSectionDeclaration = null;
        if (employeeFinancialYear.getEmployeeTaxSections() != null) {
            for (EmployeeTaxSection employeeTaxSection : employeeFinancialYear.getEmployeeTaxSections()) {
                if (employeeTaxSection.getEmployeeTaxSectionDeclarations() != null) {
                    for (EmployeeTaxSectionDeclaration employeeTaxSectionDeclaration : employeeTaxSection.getEmployeeTaxSectionDeclarations()) {
                        if (employeeTaxSectionDeclaration.getSubSectionName().equalsIgnoreCase("HRA")) {
                            hraTaxSectionDeclaration = employeeTaxSectionDeclaration;
                            break;
                        }
                    }
                }
                if (hraTaxSectionDeclaration != null) {
                    break;
                }
            }
        }
        if (basic10per.compareTo(BigDecimal.ZERO) != 0) {
            basic10per = basic10per.multiply(BigDecimal.TEN).divide(HUNDRED, BigDecimal.ROUND_UP);
        }
        if (hraTaxSectionDeclaration != null) {
            hraWithBasic10per = hraTaxSectionDeclaration.getSaveAmount().multiply(new BigDecimal(12)).subtract(basic10per);
        }
        return hraWithBasic10per.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * To calculate basic 40 percent
     *
     * @param salaryHistories
     * @param financialYear
     * @return basic 40 percent
     */
    public static BigDecimal calculateBasic40per(List<SalaryHistory> salaryHistories, FinancialYear financialYear) {
        BigDecimal basic40per = calculateBasicHRA(salaryHistories, financialYear);
        if (basic40per.compareTo(BigDecimal.ZERO) != 0) {
            basic40per = basic40per.multiply(new BigDecimal(40)).divide(HUNDRED, BigDecimal.ROUND_UP);
        }
        return basic40per.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * To calculate total actual HRA
     *
     * @param salaryHistories
     * @param financialYear
     * @return total actual HRA
     */
    public static BigDecimal calculateActualHRA(List<SalaryHistory> salaryHistories, FinancialYear financialYear) {
        BigDecimal totalActualHRA = BigDecimal.ZERO;
        if (salaryHistories != null && !salaryHistories.isEmpty()) {
            totalActualHRA = salaryHistories.get(0).getActlHra().multiply(new BigDecimal(12));
        }
        return totalActualHRA.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    public static List<TaxSectionDeclaration> taxSectionDeclarationList(TaxSectionForm taxSectionForm) {
        return taxSectionForm.getTaxSectionDeclarations();
    }

    /**
     * In this method we genarate the excel sheet and build the employee salary details.
     *
     * @param salaryHistories paid months salaries in the selected financial year.
     * @param totalSalaryHistoryForm total salary for an employee in the selected financial year.
     * @param remainedMonths remained months salary in the selected financial year.
     * @return HSSFWorkbook
     */
    public static HSSFWorkbook genarateForm16Excel(List<SalaryHistory> salaryHistories, TotalSalaryHistoryForm totalSalaryHistoryForm, List remainedMonths) {
        HSSFWorkbook workbook = null;
        int rowCount = 0;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            HSSFRow rowHeading = sheet.createRow(rowCount);
            HSSFCellStyle headerCellStyle = workbook.createCellStyle();
            HSSFFont boldFont = workbook.createFont();
            boldFont.setBold(true);
            headerCellStyle.setFont(boldFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            for (int headingCell = 0; headingCell < SALARY_COLUMNS.size(); headingCell++) {
                rowHeading.createCell(headingCell).setCellValue(new HSSFRichTextString(SALARY_COLUMNS.get(headingCell)));
                rowHeading.getCell(headingCell).setCellStyle(headerCellStyle);
            }
            for (SalaryHistory salaryHistory : salaryHistories) {
                HSSFRow row = sheet.createRow(++rowCount);
                row.createCell(0).setCellValue(new HSSFRichTextString(DateFormatter.getMonth(salaryHistory.getSalaryDate()).toUpperCase()));
                row.getCell(0).setCellStyle(headerCellStyle);
                updateSalaryRow(row, salaryHistory, false);
            }
            for (int months = 0; months < remainedMonths.size(); months++) {
                HSSFRow row = sheet.createRow(++rowCount);
                row.createCell(0).setCellValue(new HSSFRichTextString(nextMonth(salaryHistories.get(salaryHistories.size() - 1).getSalaryDate(), months).toUpperCase()));
                row.getCell(0).setCellStyle(headerCellStyle);
                updateSalaryRow(row, salaryHistories.get(salaryHistories.size() - 1), true);
            }
            HSSFRow row = sheet.createRow(++rowCount);
            int cellReference = 0;
            row.createCell(cellReference).setCellValue(new HSSFRichTextString("TOTAL"));
            row.getCell(cellReference).setCellStyle(headerCellStyle);
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalBasic().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalHRA().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalConveyence().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalMedicalAllowance().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalSpecialAllowance().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalTelephoneAllowance().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalBonus().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalPerformanceLinkedPay().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalInternetAllowance().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalUniformAllowance().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalGrossSalary().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalPfEmp().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalEsi().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalPtax().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalMedicalInsurance().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalSalaryInAdvance().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalTdsDeducted().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalDeductions().doubleValue());
            row.createCell(++cellReference).setCellValue(totalSalaryHistoryForm.getTotalNetSalary().doubleValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * In this method we updated the employee salary details to excel rows.
     *
     * @param row
     * @param salaryHistory
     * @param isActualSalary
     */
    private static void updateSalaryRow(HSSFRow row, SalaryHistory salaryHistory, boolean isActualSalary) {
        int cellReference = 0;
        if (isActualSalary) {
            row.createCell(++cellReference).setCellValue(new HSSFRichTextString(salaryHistory.getActlBasic().toString()));
            row.createCell(++cellReference).setCellValue(salaryHistory.getActlHra().doubleValue());
            row.createCell(++cellReference).setCellValue(salaryHistory.getActlConveyence().doubleValue());
            row.createCell(++cellReference).setCellValue(salaryHistory.getActlMedicalAllowance().doubleValue());
            row.createCell(++cellReference).setCellValue(salaryHistory.getActlSpecialAllowance().doubleValue());
        } else {
            row.createCell(++cellReference).setCellValue(salaryHistory.getBasic().doubleValue());
            row.createCell(++cellReference).setCellValue(salaryHistory.getHra().doubleValue());
            row.createCell(++cellReference).setCellValue(salaryHistory.getConveyence().doubleValue());
            row.createCell(++cellReference).setCellValue(salaryHistory.getMedicalAllowance().doubleValue());
            row.createCell(++cellReference).setCellValue(salaryHistory.getSpecialAllowance().doubleValue());
        }
        row.createCell(++cellReference).setCellValue(salaryHistory.getTelephoneAllowance().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getBonus().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getPerformanceLinkedPay().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getInternetAllowance().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getUniformAllowance().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getGrossSalary().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getPfEmp().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getEsi().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getPTax().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getMedicalInsurance().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getSalaryInAdvance().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getIncomeTax().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getTotalDeductions().doubleValue());
        row.createCell(++cellReference).setCellValue(salaryHistory.getNetSalary().doubleValue());
    }
}
