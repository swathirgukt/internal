package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.EmployeeIncomeTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * JpaRepository for Employee Income Tax
 * <p>
 * User: Praveen
 * Date: 22/01/2020
 */
public interface EmployeeIncomeTaxDAO extends JpaRepository<EmployeeIncomeTax, Long> {


    /**
     * To retrieve EmployeeIncomeTax by employee Id and financial year
     *
     * @param empId
     * @param fromMonth
     * @param fromYear
     * @param toMonth
     * @param toYear
     * @return EmployeeIncomeTax list
     */
    //used joins here check these. may give wrong results
    @Query("from EmployeeFinancialYear efy left join fetch efy.financialYear fy left join fetch efy.employeeTaxSections ets left join fetch ets.employeeTaxSectionDeclarations etsd where efy.empId=:empId and fy.fromMonth=:fromMonth and fy.fromYear=fromYear and fy.toMonth=:toMonth and fy.toYear=:toYear and ets.active=true and etsd.active=true")
    List<EmployeeIncomeTax> findEmployeeIncomeTaxWithEmpIdAndFinancialYear(@Param("empId") String empId, @Param("fromMonth") String fromMonth, @Param("fromYear") String fromYear, @Param("toMonth") String toMonth, @Param("toYear") String toYear);

    /**
     * To find all EmployeeIncomeTax for an employee
     *
     * @param empId
     * @return list of EmployeeIncomeTax
     */
    @Query("from EmployeeFinancialYear efy left join fetch efy.financialYear left join fetch efy.employeeTaxSections ets left join fetch ets.employeeTaxSectionDeclarations etsd where efy.empId=:empId and ets.active=true and etsd.active=true")
    List<EmployeeIncomeTax> findByEmpId(String empId);
}
