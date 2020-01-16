package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * EmployeeIncomeTax DTO
 * <p>
 * User: kalesha
 * Date: 8/17/2017
 */
@Entity
@Table(name = "EMPLOYEE_INCOME_TAX")
public class EmployeeIncomeTax {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "EMP_ID")
    private String empId;
    @ManyToOne
    @JoinColumn(name = "FINANCIAL_YEAR_ID", nullable = false)
    private FinancialYear financialYear;
    @Column(name = "GROSS_SALARY")
    private BigDecimal grossSalary;
    @Column(name = "INCENTIVES")
    private BigDecimal incentives;
    @Column(name = "PLB")
    private BigDecimal plb;
    @Column(name = "REIMBURSEMENT")
    private BigDecimal reimbursement;
    @Column(name = "BONUS")
    private BigDecimal bonus;
    @Column(name = "OTHERS")
    private BigDecimal others;
    @Column(name = "PREVIOUS_COMPANY_INCOME")
    private BigDecimal previousCompanyIncome;
    @Column(name = "INCOME_EARNED_BY_EMPLOYEE")
    private BigDecimal incomeEarnedByEmployee;
    @Column(name = "TAXABLE_INCOME")
    private BigDecimal taxableIncome;
    @Column(name = "HRA")
    private BigDecimal hra;
    @Column(name = "TAX_ON_INCOME")
    private BigDecimal taxOnIncome;
    @Column(name = "EDU_CESS")
    private BigDecimal eduCess;
    @Column(name = "TOTAL_TAX_ON_INCOME")
    private BigDecimal totalTaxOnIncome;
    @Column(name = "TDS_DEDUCTED")
    private BigDecimal tdsDeducted;
    @Column(name = "TAX_TO_BE_DEDUCTED")
    private BigDecimal taxTobeDeducted;
    @Column(name = "PER_MONTH_TAX")
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
