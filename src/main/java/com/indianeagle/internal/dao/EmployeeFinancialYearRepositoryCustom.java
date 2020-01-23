package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.EmployeeFinancialYear;

import java.util.List;

public interface EmployeeFinancialYearRepositoryCustom {
    /**
     * To retrieve EmployeeFinancialYear with employee Id and FinancialYear
     *
     * @param empId
     * @param fromMonth
     * @param fromYear
     * @param toMonth
     * @param toYear
     * @return
     */


    List<EmployeeFinancialYear> findEmployeeFinancialYearWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear);


}
