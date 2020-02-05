/*
 * Copyright (c) 2012 by Yana Software (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Yana Software (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Yana Software (P) Limited.
 */

package com.indianeagle.internal.form.vo;

import java.util.Date;

/**
 * This class represent the Approved Leaves of the employee
 * User: nageswaramma
 * Date: 5/29/12
 * Time: 9:49 AM
 */
public class ApprovedLeaves {

    private Long id;
    private String empId;
    private Date approvedDate;
    private Date fromDate;
    private Date toDate;
    private double totalNumberOfAbsentDays;
    private double casualLeave;
    private double sickLeave;
    private double compensatoryLeave;
    private double lop;
    private double leaveBalance;
    private String leaveType;
    private String note;

    /**
	 * @return the id
	 */
    public Long getId() {
        return id;
    }

    /**
	 * @param id the id to set
	 */
    public void setId(Long id) {
        this.id = id;
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
	 * @return the approvedDate
	 */
    public Date getApprovedDate() {
        return approvedDate;
    }

    /**
	 * @param approvedDate the approvedDate to set
	 */
    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    /**
	 * @return the fromDate
	 */
    public Date getFromDate() {
        return fromDate;
    }

    /**
	 * @param fromDate the fromDate to set
	 */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
	 * @return the toDate
	 */
    public Date getToDate() {
        return toDate;
    }

    /**
	 * @param toDate the toDate to set
	 */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
	 * @return the totalNumberOfAbsentDays
	 */
    public double getTotalNumberOfAbsentDays() {
        return totalNumberOfAbsentDays;
    }

    /**
	 * @param totalNumberOfAbsentDays the totalNumberOfAbsentDays to set
	 */
    public void setTotalNumberOfAbsentDays(double totalNumberOfAbsentDays) {
        this.totalNumberOfAbsentDays = totalNumberOfAbsentDays;
    }

    /**
	 * @return the casualLeave
	 */
    public double getCasualLeave() {
        return casualLeave;
    }

    /**
	 * @param casualLeave the casualLeave to set
	 */
    public void setCasualLeave(double casualLeave) {
        this.casualLeave = casualLeave;
    }

    /**
	 * @return the sickLeave
	 */
    public double getSickLeave() {
        return sickLeave;
    }

    /**
	 * @param sickLeave the sickLeave to set
	 */
    public void setSickLeave(double sickLeave) {
        this.sickLeave = sickLeave;
    }

    /**
	 * @return the compensatoryLeave
	 */
    public double getCompensatoryLeave() {
        return compensatoryLeave;
    }

    /**
	 * @param compensatoryLeave the compensatoryLeave to set
	 */
    public void setCompensatoryLeave(double compensatoryLeave) {
        this.compensatoryLeave = compensatoryLeave;
    }

    /**
	 * @return the lop
	 */
    public double getLop() {
        return lop;
    }

    /**
	 * @param lop the lop to set
	 */
    public void setLop(double lop) {
        this.lop = lop;
    }

    /**
	 * @return the leaveBalance
	 */
    public double getLeaveBalance() {
        return leaveBalance;
    }

    /**
	 * @param leaveBalance the leaveBalance to set
	 */
    public void setLeaveBalance(double leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    /**
	 * @return the leaveType
	 */
    public String getLeaveType() {
        return leaveType;
    }

    /**
	 * @param leaveType the leaveType to set
	 */
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    /**
	 * @return the note
	 */
    public String getNote() {
        return note;
    }

    /**
	 * @param note the note to set
	 */
    public void setNote(String note) {
        this.note = note;
    }
}
