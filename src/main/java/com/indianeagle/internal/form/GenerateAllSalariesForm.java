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
import java.util.Date;
import java.util.List;

/**
 * @author Hari.Pondreti
 * @since Jun 15, 2011
 */
public class GenerateAllSalariesForm {

    private Date salaryDate;
    private Date salaryEndDate;
    private BigDecimal totalWorkingDays;
    private List<EmpSalaryDecider> empSalaryDeciderList;

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
     * @return the totalWorkingDays
     */
    public BigDecimal getTotalWorkingDays() {
        return totalWorkingDays;
    }

    /**
     * @param totalWorkingDays the totalWorkingDays to set
     */
    public void setTotalWorkingDays(BigDecimal totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    /**
     * @return the empSalaryDeciderList
     */
    public List<EmpSalaryDecider> getEmpSalaryDeciderList() {
        return empSalaryDeciderList;
    }

    /**
     * @param empSalaryDeciderList the empSalaryDeciderList to set
     */
    public void setEmpSalaryDeciderList(List<EmpSalaryDecider> empSalaryDeciderList) {
        this.empSalaryDeciderList = empSalaryDeciderList;
    }

    public Date getSalaryEndDate() {
        return salaryEndDate;
    }

    public void setSalaryEndDate(Date salaryEndDate) {
        this.salaryEndDate = salaryEndDate;
    }
}
