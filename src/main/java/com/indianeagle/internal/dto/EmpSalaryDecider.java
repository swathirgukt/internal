package com.indianeagle.internal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : Taymur Shaikh
 * @since : 11-02-2020
 */

@Entity
@Table(name = "SALARY_DECIDER")
public class EmpSalaryDecider extends BaseDto {

    @Column(name = "EMPLOYEE_ID")
    private String empId;
    @Transient
    private String fullName;
    @Column(name = "LOP_DAYS")
    private BigDecimal lopDays = BigDecimal.ZERO;
    @Column(name = "ARREAR_DAYS")
    private BigDecimal arrearsDays = BigDecimal.ZERO;
    @Column(name = "SALARY_ADVANCE")
    private BigDecimal salaryInAdvance = BigDecimal.ZERO;
    @Transient
    private Boolean empExclude = Boolean.FALSE;
    @Column(name = "PERFORMANCE_INCETIVE")
    private BigDecimal performanceIncentives = BigDecimal.ZERO;

    @Column(name = "SALARY_DATE")
    private Date salaryDate;
    @Column(name = "SALARY_END_DATE")
    private Date salaryEndDate;


    /**
     * @return the empExclude
     */
    public boolean getEmpExclude() {
        return empExclude;
    }

    /**
     * @param empExclude the empExclude to set
     */
    public void setEmpExclude(boolean empExclude) {
        this.empExclude = empExclude;
    }

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public boolean isEmpExclude() {
        return empExclude;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public Date getSalaryEndDate() {
        return salaryEndDate;
    }

    public void setSalaryEndDate(Date salaryEndDate) {
        this.salaryEndDate = salaryEndDate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((empId == null) ? 0 : empId.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final EmpSalaryDecider other = (EmpSalaryDecider) obj;
        if (empId == null) {
            if (other.empId != null)
                return false;
        } else if (!empId.equals(other.empId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EmpSalaryDecider{" +
                "empId='" + empId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", lopDays=" + lopDays +
                ", arrearsDays=" + arrearsDays +
                ", salaryInAdvance=" + salaryInAdvance +
                ", empExclude=" + empExclude +
                ", performanceIncentives=" + performanceIncentives +
                '}';
    }
}