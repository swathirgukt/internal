package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: mahesh
 * Date: 1/17/13
 * Time: 3:56 PM
 */
@Entity
@Table(name = "EMPLOYEE_SETTLEMENT")
public class EmployeeSettlement extends BaseDto{

    @Column(name = "SETTLEMENT_DATE")
    private Date settlementDate;
    @Column(name = "BASIC")
    private BigDecimal basic = BigDecimal.ZERO;
    @Column(name = "HRA")
    private BigDecimal hra = BigDecimal.ZERO;
    @Column(name = "CONVEYENCE")
    private BigDecimal conveyence = BigDecimal.ZERO;
    @Column(name = "PF_EMP")
    private BigDecimal pfEmp = BigDecimal.ZERO;
    @Column(name = "PF_COMP")
    private BigDecimal pfCom = BigDecimal.ZERO;
    @Column(name = "ESI")
    private BigDecimal esi = BigDecimal.ZERO;
    @Column(name = "MEDICAL_ALLOWANCE")
    private BigDecimal medicalAllowance = BigDecimal.ZERO;
    @Column(name = "SPECIAL_ALLOWANCE")
    private BigDecimal specialAllowance = BigDecimal.ZERO;
    @Column(name = "MEDICAL_INSURANCE")
    private BigDecimal medicalInsurance = BigDecimal.ZERO;
    @Column(name = "OTHER_ALLOWANCE")
    private BigDecimal otherAllowance = BigDecimal.ZERO;
    @Column(name = "P_TAX")
    private BigDecimal PTax = BigDecimal.ZERO;
    @Column(name = "INCOME_TAX")
    private BigDecimal incomeTax = BigDecimal.ZERO;
    @Column(name = "TOTAL_DAYS")
    private BigDecimal totalDays;
    @Column(name = "PREVIOUS_ARREARS")
    private BigDecimal previousArrears = BigDecimal.ZERO;
    @Column(name = "ADMIN_CHARGES")
    private BigDecimal adminCharges = BigDecimal.ZERO;
    @Column(name = "TOTAL_EARNINGS")
    private BigDecimal totalEarnings = BigDecimal.ZERO;
    @Column(name = "TOTAL_DEDUCTIONS")
    private BigDecimal totalDeductions = BigDecimal.ZERO;
    @Column(name = "GROSS_SALARY")
    private BigDecimal grossSalary = BigDecimal.ZERO;
    @Column(name = "NET_SALARY")
    private BigDecimal netSalary = BigDecimal.ZERO;
    @Column(name = "OTHER_EARNINGS")
    private BigDecimal otherEarnings = BigDecimal.ZERO;
    @Column(name = "OTHER_DEDUCTIONS")
    private BigDecimal otherDeductions = BigDecimal.ZERO;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)

    @JoinColumn(name = "EMP_ID", unique = true)
    private Employee employee;

    /**
     * @return the settlementDate
     */
    public Date getSettlementDate() {
        return settlementDate;
    }

    /**
     * @param settlementDate the settlementDate to set
     */
    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    /**
     * @return the basic
     */
    public BigDecimal getBasic() {
        return basic;
    }

    /**
     * @param basic the basic to set
     */
    public void setBasic(BigDecimal basic) {
        this.basic = basic;
    }

    /**
     * @return the hra
     */
    public BigDecimal getHra() {
        return hra;
    }

    /**
     * @param hra the hra to set
     */
    public void setHra(BigDecimal hra) {
        this.hra = hra;
    }

    /**
     * @return the pfEmp
     */
    public BigDecimal getPfEmp() {
        return pfEmp;
    }

    /**
     * @param pfEmp the pfEmp to set
     */
    public void setPfEmp(BigDecimal pfEmp) {
        this.pfEmp = pfEmp;
    }

    /**
     * @return the pfCom
     */
    public BigDecimal getPfCom() {
        return pfCom;
    }

    /**
     * @param pfCom the pfCom to set
     */
    public void setPfCom(BigDecimal pfCom) {
        this.pfCom = pfCom;
    }

    /**
     * @return the esi
     */
    public BigDecimal getEsi() {
        return esi;
    }

    /**
     * @param esi the esi to set
     */
    public void setEsi(BigDecimal esi) {
        this.esi = esi;
    }

    /**
     * @return the medicalAllowance
     */
    public BigDecimal getMedicalAllowance() {
        return medicalAllowance;
    }

    /**
     * @param medicalAllowance the medicalAllowance to set
     */
    public void setMedicalAllowance(BigDecimal medicalAllowance) {
        this.medicalAllowance = medicalAllowance;
    }

    /**
     * @return the specialAllowance
     */
    public BigDecimal getSpecialAllowance() {
        return specialAllowance;
    }

    /**
     * @param specialAllowance the specialAllowance to set
     */
    public void setSpecialAllowance(BigDecimal specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    /**
     * @return the medicalInsurance
     */
    public BigDecimal getMedicalInsurance() {
        return medicalInsurance;
    }

    /**
     * @param medicalInsurance the medicalInsurance to set
     */
    public void setMedicalInsurance(BigDecimal medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    /**
     * @return the otherAllowance
     */
    public BigDecimal getOtherAllowance() {
        return otherAllowance;
    }

    /**
     * @param otherAllowance the otherAllowance to set
     */
    public void setOtherAllowance(BigDecimal otherAllowance) {
        this.otherAllowance = otherAllowance;
    }

    /**
     * @return the PTax
     */
    public BigDecimal getPTax() {
        return PTax;
    }

    /**
     * @param PTax the PTax to set
     */
    public void setPTax(BigDecimal PTax) {
        this.PTax = PTax;
    }

    /**
     * @return the incomeTax
     */
    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    /**
     * @param incomeTax the incomeTax to set
     */
    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    /**
     * @return the totalDays
     */
    public BigDecimal getTotalDays() {
        return totalDays;
    }

    /**
     * @param totalDays the totalDays to set
     */
    public void setTotalDays(BigDecimal totalDays) {
        this.totalDays = totalDays;
    }

    /**
     * @return the conveyence
     */
    public BigDecimal getConveyence() {
        return conveyence;
    }

    /**
     * @param conveyence the conveyence to set
     */
    public void setConveyence(BigDecimal conveyence) {
        this.conveyence = conveyence;
    }

    /**
     * @return the previousArrears
     */
    public BigDecimal getPreviousArrears() {
        return previousArrears;
    }

    /**
     * @param previousArrears the previousArrears to set
     */
    public void setPreviousArrears(BigDecimal previousArrears) {
        this.previousArrears = previousArrears;
    }

    /**
     * @return the adminCharges
     */
    public BigDecimal getAdminCharges() {
        return adminCharges;
    }

    /**
     * @param adminCharges the adminCharges to set
     */
    public void setAdminCharges(BigDecimal adminCharges) {
        this.adminCharges = adminCharges;
    }

    /**
     * @return the grossSalary
     */
    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    /**
     * @param grossSalary the grossSalary to set
     */
    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    /**
     * @return the netSalary
     */
    public BigDecimal getNetSalary() {
        return netSalary;
    }

    /**
     * @param netSalary the netSalary to set
     */
    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    /**
     * @return the otherEarnings
     */
    public BigDecimal getOtherEarnings() {
        return otherEarnings;
    }

    /**
     * @param otherEarnings the otherEarnings to set
     */
    public void setOtherEarnings(BigDecimal otherEarnings) {
        this.otherEarnings = otherEarnings;
    }

    /**
     * @return the otherDeductions
     */
    public BigDecimal getOtherDeductions() {
        return otherDeductions;
    }

    /**
     * @param otherDeductions the otherDeductions to set
     */
    public void setOtherDeductions(BigDecimal otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }
}
