package com.indianeagle.internal.form;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Class represents the totals of Salary History
 * <p/>
 * User: kalesha
 * Date: 8/17/2017
 */

@Component
public class TotalSalaryHistoryForm {

    private BigDecimal totalBasic = BigDecimal.ZERO;
    private BigDecimal totalHRA = BigDecimal.ZERO;
    private BigDecimal totalConveyence = BigDecimal.ZERO;
    private BigDecimal totalMedicalAllowance = BigDecimal.ZERO;
    private BigDecimal totalSpecialAllowance = BigDecimal.ZERO;
    private BigDecimal totalTelephoneAllowance = BigDecimal.ZERO;
    private BigDecimal totalBonus = BigDecimal.ZERO;
    private BigDecimal totalPerformanceLinkedPay = BigDecimal.ZERO;
    private BigDecimal totalInternetAllowance = BigDecimal.ZERO;
    private BigDecimal totalUniformAllowance = BigDecimal.ZERO;
    private BigDecimal totalGrossSalary = BigDecimal.ZERO;
    private BigDecimal totalPfEmp = BigDecimal.ZERO;
    private BigDecimal totalEsi = BigDecimal.ZERO;
    private BigDecimal totalPtax = BigDecimal.ZERO;
    private BigDecimal totalMedicalInsurance = BigDecimal.ZERO;
    private BigDecimal totalSalaryInAdvance = BigDecimal.ZERO;
    private BigDecimal totalTdsDeducted = BigDecimal.ZERO;
    private BigDecimal totalDeductions = BigDecimal.ZERO;
    private BigDecimal totalNetSalary = BigDecimal.ZERO;

    public BigDecimal getTotalBasic() {
        return totalBasic;
    }

    public void setTotalBasic(BigDecimal totalBasic) {
        this.totalBasic = totalBasic;
    }

    public BigDecimal getTotalHRA() {
        return totalHRA;
    }

    public void setTotalHRA(BigDecimal totalHRA) {
        this.totalHRA = totalHRA;
    }

    public BigDecimal getTotalConveyence() {
        return totalConveyence;
    }

    public void setTotalConveyence(BigDecimal totalConveyence) {
        this.totalConveyence = totalConveyence;
    }

    public BigDecimal getTotalMedicalAllowance() {
        return totalMedicalAllowance;
    }

    public void setTotalMedicalAllowance(BigDecimal totalMedicalAllowance) {
        this.totalMedicalAllowance = totalMedicalAllowance;
    }

    public BigDecimal getTotalSpecialAllowance() {
        return totalSpecialAllowance;
    }

    public void setTotalSpecialAllowance(BigDecimal totalSpecialAllowance) {
        this.totalSpecialAllowance = totalSpecialAllowance;
    }

    public BigDecimal getTotalTelephoneAllowance() {
        return totalTelephoneAllowance;
    }

    public void setTotalTelephoneAllowance(BigDecimal totalTelephoneAllowance) {
        this.totalTelephoneAllowance = totalTelephoneAllowance;
    }

    public BigDecimal getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(BigDecimal totalBonus) {
        this.totalBonus = totalBonus;
    }

    public BigDecimal getTotalPerformanceLinkedPay() {
        return totalPerformanceLinkedPay;
    }

    public void setTotalPerformanceLinkedPay(BigDecimal totalPerformanceLinkedPay) {
        this.totalPerformanceLinkedPay = totalPerformanceLinkedPay;
    }

    public BigDecimal getTotalInternetAllowance() {
        return totalInternetAllowance;
    }

    public void setTotalInternetAllowance(BigDecimal totalInternetAllowance) {
        this.totalInternetAllowance = totalInternetAllowance;
    }

    public BigDecimal getTotalUniformAllowance() {
        return totalUniformAllowance;
    }

    public void setTotalUniformAllowance(BigDecimal totalUniformAllowance) {
        this.totalUniformAllowance = totalUniformAllowance;
    }

    public BigDecimal getTotalGrossSalary() {
        return totalGrossSalary;
    }

    public void setTotalGrossSalary(BigDecimal totalGrossSalary) {
        this.totalGrossSalary = totalGrossSalary;
    }

    public BigDecimal getTotalPfEmp() {
        return totalPfEmp;
    }

    public void setTotalPfEmp(BigDecimal totalPfEmp) {
        this.totalPfEmp = totalPfEmp;
    }

    public BigDecimal getTotalEsi() {
        return totalEsi;
    }

    public void setTotalEsi(BigDecimal totalEsi) {
        this.totalEsi = totalEsi;
    }

    public BigDecimal getTotalPtax() {
        return totalPtax;
    }

    public void setTotalPtax(BigDecimal totalPtax) {
        this.totalPtax = totalPtax;
    }

    public BigDecimal getTotalMedicalInsurance() {
        return totalMedicalInsurance;
    }

    public void setTotalMedicalInsurance(BigDecimal totalMedicalInsurance) {
        this.totalMedicalInsurance = totalMedicalInsurance;
    }

    public BigDecimal getTotalSalaryInAdvance() {
        return totalSalaryInAdvance;
    }

    public void setTotalSalaryInAdvance(BigDecimal totalSalaryInAdvance) {
        this.totalSalaryInAdvance = totalSalaryInAdvance;
    }

    public BigDecimal getTotalTdsDeducted() {
        return totalTdsDeducted;
    }

    public void setTotalTdsDeducted(BigDecimal totalTdsDeducted) {
        this.totalTdsDeducted = totalTdsDeducted;
    }

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public BigDecimal getTotalNetSalary() {
        return totalNetSalary;
    }

    public void setTotalNetSalary(BigDecimal totalNetSalary) {
        this.totalNetSalary = totalNetSalary;
    }
}
