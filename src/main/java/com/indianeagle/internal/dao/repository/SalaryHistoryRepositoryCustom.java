package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.SalaryHistory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface SalaryHistoryRepositoryCustom {
    /**
     * gives the count of employees's list for specified range to calculate PT
     *
     * @param date
     * @return List<Integer>
     */
    public List<Long> getMonthlyRtReport(Date date);

    /**
     * get Monthly PF Report
     *
     * @param date
     * @param range
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<Object> getMonthlyPfReport(Date date, BigDecimal range);
    public List<SalaryHistory> findSalaryHistoryByEmpId(String empId, Date salaryDate, Date salaryEndDate);

    /**
     *  To get salaryHistories of employee within a financial year
     * @param empId
     * @param fromDate
     * @param toDate
     * @return
     */
    List<SalaryHistory> findSalaryHistoriesWithInFinancialYear(String empId, Date fromDate, Date toDate);

}
