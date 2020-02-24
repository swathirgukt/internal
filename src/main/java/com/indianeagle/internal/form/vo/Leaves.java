/*
 * Copyright (c) 2012 by Yana Software (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Yana Software (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Yana Software (P) Limited.
 */

package com.indianeagle.internal.form.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * User: anish
 * Date: 5/29/12
 * Time: 3:10 PM
 */
public class Leaves implements Serializable{

    private Long id;
	private EmployeeVO employee;
	private Double casualLeaves = new Double(0);
	private Double sickLeaves = new Double(0);
	private Double compensatoryLeaves = new Double(0);
	private Double previousYearLeaves = new Double(0);
	private Date currentLeaveYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeVO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeVO employee) {
        this.employee = employee;
    }

    public Double getCasualLeaves() {
        return casualLeaves;
    }

    public void setCasualLeaves(Double casualLeaves) {
        this.casualLeaves = casualLeaves;
    }

    public Double getSickLeaves() {
        return sickLeaves;
    }

    public void setSickLeaves(Double sickLeaves) {
        this.sickLeaves = sickLeaves;
    }

    public Double getCompensatoryLeaves() {
        return compensatoryLeaves;
    }

    public void setCompensatoryLeaves(Double compensatoryLeaves) {
        this.compensatoryLeaves = compensatoryLeaves;
    }

    public Double getPreviousYearLeaves() {
        return previousYearLeaves;
    }

    public void setPreviousYearLeaves(Double previousYearLeaves) {
        this.previousYearLeaves = previousYearLeaves;
    }

    public Date getCurrentLeaveYear() {
        return currentLeaveYear;
    }

    public void setCurrentLeaveYear(Date currentLeaveYear) {
        this.currentLeaveYear = currentLeaveYear;
    }
}
