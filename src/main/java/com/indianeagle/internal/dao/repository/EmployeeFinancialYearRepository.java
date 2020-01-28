package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.EmployeeFinancialYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao for EmployeeFinancialYear
 * <p>
 * User: Praveen
 * Date: 21/01/2020
 */
@Repository
public interface EmployeeFinancialYearRepository extends JpaRepository<EmployeeFinancialYear, Long> {


    /**
     * To retrieve EmployeeFinancialYear by employee Id
     *
     * @param empId
     * @return list of EmployeeFinancialYear
     */

    @Query("select distinct efy from EmployeeFinancialYear efy left join fetch efy.financialYear left join fetch efy.employeeTaxSections ets left join fetch ets.employeeTaxSectionDeclarations etsd where efy.empId=:empId and ets.active=true and etsd.active=true")
    List<EmployeeFinancialYear> findEmployeeFinancialYearByEmpId(@Param("empId") String empId);

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
    @Query("select distinct efy from EmployeeFinancialYear efy left join fetch efy.financialYear fy left join fetch efy.employeeTaxSections ets left join fetch ets.employeeTaxSectionDeclarations etsd where efy.empId=:empId and fy.fromMonth=:fromMonth and fy.fromYear=:fromYear and fy.toMonth=:toMonth and fy.toYear=:toYear and ets.active=true and etsd.active=true")
    List<EmployeeFinancialYear> findEmployeeFinancialYearWithEmpIdAndMonthAndYear(@Param("empId") String empId, @Param("fromMonth") String fromMonth, @Param("fromYear") String fromYear, @Param("toMonth") String toMonth, @Param("toYear") String toYear);
}


