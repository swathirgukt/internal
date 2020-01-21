package com.indianeagle.internal.facade;

import com.yana.internal.dto.SalaryHistory;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author SVK
 */
public interface SalaryHistoryService {

    List<SalaryHistory> searchSalaryHistory(String empId, Date periodDate);

    /**
     * Method to find the salary history for an employee
     *
     * @param employeeId the employee Id
     * @return salaryHistory list
     */
    List<SalaryHistory> searchSalaryHistoryByEmployeeId(String employeeId);

    List<SalaryHistory> salaryReport(Date salaryDate);

    List<SalaryHistory> salaryReport(Date salaryDate, Date salaryEndDate);

    SalaryHistory sendPaySlip(Long id, String contextPath);

    InputStream printPaySlip(Long id, String contextPath);

    /**
     * Method to find the salary historty based on emplyee id and given date
     *
     * @param empId
     * @param salaryDate
     * @return
     */
    List<SalaryHistory> findSalaryHistoryByEmpId(String empId, Date salaryDate);

    List<SalaryHistory> findSalaryHistoryByEmpId(String empId, Date salaryDate, Date salaryEndDate);
}
