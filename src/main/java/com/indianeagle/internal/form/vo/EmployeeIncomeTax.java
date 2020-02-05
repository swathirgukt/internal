package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;

/**
 * EmployeeIncomeTax DTO
 *
 * User: kalesha
 * Date: 8/17/2017
 */
public class EmployeeIncomeTax {

    private Long id;
    private String empId;
    private FinancialYear financialYear;
    private BigDecimal grossSalary;
    private BigDecimal incentives;
    private BigDecimal plb;
    private BigDecimal reimbursement;
    private BigDecimal bonus;
    private BigDecimal others;
    private BigDecimal previousCompanyIncome;
    private BigDecimal incomeEarnedByEmployee;
    private BigDecimal taxableIncome;
    private BigDecimal hra;
    private BigDecimal taxOnIncome;
    private BigDecimal eduCess;
    private BigDecimal totalTaxOnIncome;
    private BigDecimal tdsDeducted;
    private BigDecimal taxTobeDeducted;
    private BigDecimal perMonthTax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public FinancialYear getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public BigDecimal getIncentives() {
        return incentives;
    }

    public void setIncentives(BigDecimal incentives) {
        this.incentives = incentives;
    }

    public BigDecimal getPlb() {
        return plb;
    }

    public void setPlb(BigDecimal plb) {
        this.plb = plb;
    }

    public BigDecimal getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(BigDecimal reimbursement) {
        this.reimbursement = reimbursement;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getOthers() {
        return others;
    }

    public void setOthers(BigDecimal others) {
        this.others = others;
    }

    public BigDecimal getPreviousCompanyIncome() {
        return previousCompanyIncome;
    }

    public void setPreviousCompanyIncome(BigDecimal previousCompanyIncome) {
        this.previousCompanyIncome = previousCompanyIncome;
    }

    public BigDecimal getIncomeEarnedByEmployee() {
        return incomeEarnedByEmployee;
    }

    public void setIncomeEarnedByEmployee(BigDecimal incomeEarnedByEmployee) {
        this.incomeEarnedByEmployee = incomeEarnedByEmployee;
    }

    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public BigDecimal getTaxOnIncome() {
        return taxOnIncome;
    }

    public void setTaxOnIncome(BigDecimal taxOnIncome) {
        this.taxOnIncome = taxOnIncome;
    }

    public BigDecimal getEduCess() {
        return eduCess;
    }

    public void setEduCess(BigDecimal eduCess) {
        this.eduCess = eduCess;
    }

    public BigDecimal getTotalTaxOnIncome() {
        return totalTaxOnIncome;
    }

    public void setTotalTaxOnIncome(BigDecimal totalTaxOnIncome) {
        this.totalTaxOnIncome = totalTaxOnIncome;
    }

    public BigDecimal getTdsDeducted() {
        return tdsDeducted;
    }

    public void setTdsDeducted(BigDecimal tdsDeducted) {
        this.tdsDeducted = tdsDeducted;
    }

    public BigDecimal getTaxTobeDeducted() {
        return taxTobeDeducted;
    }

    public void setTaxTobeDeducted(BigDecimal taxTobeDeducted) {
        this.taxTobeDeducted = taxTobeDeducted;
    }

    public BigDecimal getPerMonthTax() {
        return perMonthTax;
    }

    public void setPerMonthTax(BigDecimal perMonthTax) {
        this.perMonthTax = perMonthTax;
    }

    public BigDecimal getHra() {
        return hra;
    }

    public void setHra(BigDecimal hra) {
        this.hra = hra;
    }
}
