/*
 * Copyright (c) 2020 by Indianeagle (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Indianeagle (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Indianeagle (P) Limited.
 */

package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Praveen
 * Date: 5/29/12
 * Time: 3:10 PM
 */
@Entity
@Table(name = "LEAVES")
public class Leaves  extends BaseDto {

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", unique = true, nullable = false)
    private Employee employee;

    @Column(name = "CASUAL_LEAVES")
    private Double casualLeaves = 0d;

    @Column(name = "SICK_LEAVES")
    private Double sinkLeaves = 0d;

    @Column(name = "COMPENSATORY_LEAVES")
    private Double compensatoryLeaves = 0d;

    @Column(name = "PREVIOUS_YEAR_LEAVES")
    private Double previousYearLeaves = 0d;

    @Column(name = "CURRENT_LEAVE_YEAR")
    private Date currentLeaveYear;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getCasualLeaves() {
        return casualLeaves;
    }

    public void setCasualLeaves(Double casualLeaves) {
        this.casualLeaves = casualLeaves;
    }

    public Double getSinkLeaves() {
        return sinkLeaves;
    }

    public void setSinkLeaves(Double sinkLeaves) {
        this.sinkLeaves = sinkLeaves;
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
