package com.indianeagle.internal.dao;

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
public interface EmployeeFinancialYearRepository extends JpaRepository<EmployeeFinancialYear, Long>, EmployeeFinancialYearRepositoryCustom {


    /**
     * To retrieve EmployeeFinancialYear by employee Id
     *
     * @param empId
     * @return list of EmployeeFinancialYear
     */

    @Query("select efy from EmployeeFinancialYear efy left join fetch efy.financialYear left join fetch efy.employeeTaxSections ets left join fetch ets.employeeTaxSectionDeclarations etsd where efy.empId=:empId and ets.active=true and etsd.active=true")
    List<EmployeeFinancialYear> findEmployeeFinancialYearByEmpId(@Param("empId") String empId);

}


