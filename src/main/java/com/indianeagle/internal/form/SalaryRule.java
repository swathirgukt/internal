package com.indianeagle.internal.form;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Durga Prasad M
 * @date Jan 29, 2010
 */
public class SalaryRule {

    private Date salaryDate;
    private Date salaryEndDate;
    private BigDecimal arrearsDays;
    private BigDecimal lopDays;
    private BigDecimal totalDays;

    private BigDecimal salaryInAdvance = new BigDecimal(0.00);
    private BigDecimal miscDeductions = new BigDecimal(0.00);
    private BigDecimal performanceIncentives = new BigDecimal(0.00);

    private String[] deptExcludedEmpList;

    /**
     * @return the salaryDate
     */
    public Date getSalaryDate() {
        return salaryDate;
    }

    /**
     * @param salaryDate the salaryDate to set
     */
    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
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
     * @return the arrearsDays
     */
    public BigDecimal getArrearsDays() {
        return arrearsDays;
    }

    /**
     * @param arrearsDays the arrearsDays to set
     */
    public void setArrearsDays(BigDecimal arrearsDays) {
        this.arrearsDays = arrearsDays;
    }

    /**
     * @return the lopDays
     */
    public BigDecimal getLopDays() {
        return lopDays;
    }

    /**
     * @param lopDays the lopDays to set
     */
    public void setLopDays(BigDecimal lopDays) {
        this.lopDays = lopDays;
    }

    /**
     * @return the salaryInAdvance
     */
    public BigDecimal getSalaryInAdvance() {
        return salaryInAdvance;
    }

    /**
     * @param salaryInAdvance the salaryInAdvance to set
     */
    public void setSalaryInAdvance(BigDecimal salaryInAdvance) {
        this.salaryInAdvance = salaryInAdvance;
    }

    /**
     * @return the miscDeductions
     */
    public BigDecimal getMiscDeductions() {
        return miscDeductions;
    }

    /**
     * @param miscDeductions the miscDeductions to set
     */
    public void setMiscDeductions(BigDecimal miscDeductions) {
        this.miscDeductions = miscDeductions;
    }

    /**
     * @return the deptExcludedEmpList
     */
    public String[] getDeptExcludedEmpList() {
        return deptExcludedEmpList;
    }

    /**
     * @param deptExcludedEmpList the deptExcludedEmpList to set
     */
    public void setDeptExcludedEmpList(String[] deptExcludedEmpList) {
        this.deptExcludedEmpList = deptExcludedEmpList;
    }

    /**
     * @return the performanceIncentives
     */
    public BigDecimal getPerformanceIncentives() {
        return performanceIncentives;
    }

    /**
     * @param performanceIncentives the performanceIncentives to set
     */
    public void setPerformanceIncentives(BigDecimal performanceIncentives) {
        this.performanceIncentives = performanceIncentives;
    }


    public Date getSalaryEndDate() {
        return salaryEndDate;
    }

    public void setSalaryEndDate(Date salaryEndDate) {
        this.salaryEndDate = salaryEndDate;
    }
}
