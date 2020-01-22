package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.EmployeeSettlement;

import java.util.Date;
import java.util.List;

/**
 * Custom  Repository for EmployeeSettlement
 * User: Praveen
 * Date: 22/01/2020
 * Time: 5:52 PM
 */
public interface EmployeeSettlementRepositoryCustom {
    /**
     * Method to find the count of employee's list for specified range to calculate PT
     *
     * @param date the specified date
     * @return list containing no of employees by PT range
     */
    public List<Long> findMonthlySettlementReport(Date date);

    /**
     * Method to find the EmployeeSettlement list by given settlement date
     *
     * @param date EmployeeSettlement date
     * @return EmployeeSettlement list
     */
    public List<EmployeeSettlement> findEmployeeSettlementByDate(Date date);

    /*
     * method to get monthly ESI report
     *
    public List<Object> getMonthlyESIReport(Date formDate);*/


}
