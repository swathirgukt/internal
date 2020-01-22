package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.EmployeeFinancialYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Dao for EmployeeFinancialYear
 * <p>
 * User: Praveen
 * Date: 21/01/2020
 */
public interface EmployeeFinancialYearRepository extends JpaRepository<EmployeeFinancialYear, Long> {


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
    /*
    if possible later try to change it to Spring data jpa
     */
    // List<EmployeeFinancialYear> findEmployeeFinancialYearWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear);
    @Query("select EmployeeFinancialYear from EmployeeFinancialYear efy left join fetch efy.financialYear fy left join fetch efy.employeeTaxSections ets left join fetch ets.employeeTaxSectionDeclarations etsd where efy.empId=:empId and fy.fromMonth=:fromMonth and fy.fromYear=fromYear and fy.toMonth=toMonth and fy.toYear=toYear and ets.active=true and etsd.active=true")
    List<EmployeeFinancialYear> findEmployeeFinancialYearWithEmpIdAndFinancialYear(@Param("EMPiD") String empId, @Param("fromMonth") String fromMonth, @Param("fromYear") String fromYear, @Param("toMonth") String toMonth, @Param("toYear") String toYear);

    /**
     * To retrieve EmployeeFinancialYear by employee Id
     *
     * @param empId
     * @return list of EmployeeFinancialYear
     */
    @Query("from EmployeeFinancialYear efy left join fetch efy.financialYear left join fetch efy.employeeTaxSections ets left join fetch ets.employeeTaxSectionDeclarations etsd where efy.empId=:empId and ets.active=true and etsd.active=true")
    List<EmployeeFinancialYear> findEmployeeFinancialYearByEmpId(@Param("empId") String empId);

}


