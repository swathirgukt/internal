/*
 * Copyright (c) 2012 by Yana Software (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Yana Software (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Yana Software (P) Limited.
 */

package com.indianeagle.internal.form;

import java.util.Date;
import java.util.List;

/**
 * Form class for approve leave
 * User: Seethayya
 * Date: 5/29/12
 * Time: 11:35 AM
 */
public class LeaveApproveForm {

    private String empId;
    private Date fromDate;
    private Date toDate;
    private double totalNumberOfAbsentDays;
    private double casualLeave;
    private double sickLeave;
    private double compensatoryLeave;
    private double lop;
    private double remainingCL;
    private double remainingSL;
    private double remainingCompOff;
    private String leaveType;
    private String note;
    private List<Integer> absentDates;
    private String leaveApprovalMonth;
    private double leaveBalance;

    /**
     * Method to get the empId
     *
     * @return empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId, to set the empId
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * Method to get the fromDate
     *
     * @return fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate, to set the from date
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Method to get the toDate
     *
     * @return toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate, to set the toDate
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * Method to get the totalNumberOfAbsentDays
     *
     * @return totalNumberOfAbsentDays
     */
    public double getTotalNumberOfAbsentDays() {
        return totalNumberOfAbsentDays;
    }

    /**
     * @param totalNumberOfAbsentDays, to set totalNumberOfAbsentDays
     */
    public void setTotalNumberOfAbsentDays(double totalNumberOfAbsentDays) {
        this.totalNumberOfAbsentDays = totalNumberOfAbsentDays;
    }

    /**
     * Method to get the casualLeave
     *
     * @return casualLeave
     */
    public double getCasualLeave() {
        return casualLeave;
    }

    /**
     * @param casualLeave, to set the casualLeave
     */
    public void setCasualLeave(double casualLeave) {
        this.casualLeave = casualLeave;
    }

    /**
     * Method to get the sickLeave
     *
     * @return sickLeave
     */
    public double getSickLeave() {
        return sickLeave;
    }

    /**
     * @param sickLeave, to set the sickLeave
     */
    public void setSickLeave(double sickLeave) {
        this.sickLeave = sickLeave;
    }

    /**
     * Method to get the compensatoryLeave
     *
     * @return compensatoryLeave
     */
    public double getCompensatoryLeave() {
        return compensatoryLeave;
    }

    /**
     * @param compensatoryLeave, to set compensatoryLeave
     */
    public void setCompensatoryLeave(double compensatoryLeave) {
        this.compensatoryLeave = compensatoryLeave;
    }

    /**
     * Method to get lop
     *
     * @return lop
     */
    public double getLop() {
        return lop;
    }

    /**
     * @param lop to set the lop
     */
    public void setLop(double lop) {
        this.lop = lop;
    }

    /**
     * Method to get the remainingCL
     *
     * @return remainingCL
     */
    public double getRemainingCL() {
        return remainingCL;
    }

    /**
     * @param remainingCL, to set remainingCL
     */
    public void setRemainingCL(double remainingCL) {
        this.remainingCL = remainingCL;
    }

    /**
     * Method to get the remainingSL
     *
     * @return remainingSL
     */
    public double getRemainingSL() {
        return remainingSL;
    }

    /**
     * @param remainingSL, to set the remainingSL
     */
    public void setRemainingSL(double remainingSL) {
        this.remainingSL = remainingSL;
    }

    /**
     * Method to get the remainingCompOff
     *
     * @return remainingCompOff
     */
    public double getRemainingCompOff() {
        return remainingCompOff;
    }

    /**
     * @param remainingCompOff, to set remainingCompOff
     */
    public void setRemainingCompOff(double remainingCompOff) {
        this.remainingCompOff = remainingCompOff;
    }

    /**
     * Method to get leaveType
     *
     * @return leaveType
     */
    public String getLeaveType() {
        return leaveType;
    }

    /**
     * @param leaveType to set the leaveType
     */
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    /**
     * Method to get the note
     *
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note, to set the note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Method to get the absentDates
     *
     * @return absentDates
     */
    public List<Integer> getAbsentDates() {
        return absentDates;
    }

    /**
     * @param absentDates, to set the absentDates
     */
    public void setAbsentDates(List<Integer> absentDates) {
        this.absentDates = absentDates;
    }

    /**
     * Method to get the leaveApprovalMonth
     *
     * @return leaveApprovalMonth
     */
    public String getLeaveApprovalMonth() {
        return leaveApprovalMonth;
    }

    /**
     * @param leaveApprovalMonth, to set the leaveApprovalMonth
     */
    public void setLeaveApprovalMonth(String leaveApprovalMonth) {
        this.leaveApprovalMonth = leaveApprovalMonth;
    }

    /**
     * Method to get the leaveBalance
     *
     * @return leaveBalance
     */
    public Double getLeaveBalance() {
        return leaveBalance;
    }

    /**
     * @param leaveBalance, to set the leaveBalance
     */
    public void setLeaveBalance(Double leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    @Override
    public String toString() {
        return "LeaveApproveForm{" + "empId='" + empId + '\'' + ", fromDate=" + fromDate + ", toDate=" + toDate + ", totalNumberOfAbsentDays=" + totalNumberOfAbsentDays + ", casualLeave=" + casualLeave + ", sickLeave=" + sickLeave + ", compensatoryLeave=" + compensatoryLeave + ", lop=" + lop + ", remainingCL=" + remainingCL + ", remainingSL=" + remainingSL + ", remainingCompOff=" + remainingCompOff + ", leaveType='" + leaveType + '\'' + ", note='" + note + '\'' + ", absentDates='" + absentDates + '\'' + ", leaveApprovalMonth='" + leaveApprovalMonth + '\'' + ", leaveBalance=" + leaveBalance + '}';
    }
}
