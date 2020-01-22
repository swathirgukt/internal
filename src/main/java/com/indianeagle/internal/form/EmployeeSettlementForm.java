package com.indianeagle.internal.form;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: praveen
 * Date: 1/17/13
 * Time: 6:17 PM
 */
public class EmployeeSettlementForm {

    private String employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String empStatus;
    private Date resignationDate;
    private Date relievingDate;
    private Date settlementDate;
    private String reasonRelieving;
    private BigDecimal otherSettlementDeductions = BigDecimal.ZERO;
    private BigDecimal otherSettlementEarnings = BigDecimal.ZERO;
    private BigDecimal previousArrearsEarnings = BigDecimal.ZERO;
    private BigDecimal adminCharges = BigDecimal.ZERO;

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the  employeeDesignation
     */
    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    /**
     * @param employeeDesignation the employeeDesignation to set
     */
    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

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
     * @return the otherSettlementDeductions
     */
    public BigDecimal getOtherSettlementDeductions() {
        return otherSettlementDeductions;
    }

    /**
     * @param otherSettlementDeductions the otherSettlementDeductions to set
     */
    public void setOtherSettlementDeductions(BigDecimal otherSettlementDeductions) {
        this.otherSettlementDeductions = otherSettlementDeductions;
    }

    /**
     * @return the otherSettlementEarnings
     */
    public BigDecimal getOtherSettlementEarnings() {
        return otherSettlementEarnings;
    }

    /**
     * @param otherSettlementEarnings the otherSettlementEarnings to set
     */
    public void setOtherSettlementEarnings(BigDecimal otherSettlementEarnings) {
        this.otherSettlementEarnings = otherSettlementEarnings;
    }

    /**
     * @return the  previousArrearsEarnings
     */
    public BigDecimal getPreviousArrearsEarnings() {
        return previousArrearsEarnings;
    }

    /**
     * @param previousArrearsEarnings the previousArrearsEarnings to set
     */
    public void setPreviousArrearsEarnings(BigDecimal previousArrearsEarnings) {
        this.previousArrearsEarnings = previousArrearsEarnings;
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
     * @return the empStatus
     */
    public String getEmpStatus() {
        return empStatus;
    }

    /**
     * @param empStatus the empStatus to set
     */
    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    /**
     * @return the resignationDate
     */
    public Date getResignationDate() {
        return resignationDate;
    }

    /**
     * @param resignationDate the resignationDate to set
     */
    public void setResignationDate(Date resignationDate) {
        this.resignationDate = resignationDate;
    }

    /**
     * @return the  relievingDate
     */
    public Date getRelievingDate() {
        return relievingDate;
    }

    /**
     * @param relievingDate the relievingDate to set
     */
    public void setRelievingDate(Date relievingDate) {
        this.relievingDate = relievingDate;
    }

    /**
     * @return the reasonRelieving
     */
    public String getReasonRelieving() {
        return reasonRelieving;
    }

    /**
     * @param reasonRelieving the reasonRelieving to set
     */
    public void setReasonRelieving(String reasonRelieving) {
        this.reasonRelieving = reasonRelieving;
    }

}
