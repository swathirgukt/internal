/*
 * ---------------------------------------------------------------------------
 * COPYRIGHT NOTICE
 * Copyright (c) 2011 by Yana Software (P) Limited.
 * All rights reserved. These materials are confidential and proprietary to
 * Yana Software (P) Limited.  No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or
 * any information storage or retrieval system), nor may the materials be
 * disclosed to third parties, or used in derivative works without the
 * express written authorization of Yana Software (P) Limited.
 * ---------------------------------------------------------------------------
 */
package com.indianeagle.internal.form;

import java.math.BigDecimal;

/**
 * @author Hari.Pondreti
 * @since Jun 17, 2011
 */
public class EmpSalaryDecider {

    private String empId;
    private String fullName;
    private BigDecimal lopDays;
    private BigDecimal arrearsDays = BigDecimal.ZERO;
    private BigDecimal salaryInAdvance = BigDecimal.ZERO;
    private boolean empExclude;
    private BigDecimal performanceIncentives = BigDecimal.ZERO;


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