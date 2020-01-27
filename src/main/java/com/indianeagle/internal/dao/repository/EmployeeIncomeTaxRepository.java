
package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.EmployeeIncomeTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * JpaRepository for Employee Income Tax
 * <p>
 * User: Praveen
 * Date: 22/01/2020
 */

@Repository
public interface EmployeeIncomeTaxRepository extends JpaRepository<EmployeeIncomeTax, Long> {



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
    @Query("select et from EmployeeIncomeTax et left join fetch et.financialYear fy where et.empId=:empId and fy.fromMonth=:fromMonth and fy.fromYear=:fromYear and fy.toMonth=:toMonth and fy.toYear=:toYear")
    List<EmployeeIncomeTax> findEmployeeIncomeTaxWithEmpIdAndFinancialYear(@Param("empId") String empId, @Param("fromMonth") String fromMonth, @Param("fromYear") String fromYear, @Param("toMonth") String toMonth, @Param("toYear") String toYear);


    /**
     * To find all EmployeeIncomeTax for an employee
     *
     * @param empId
     * @return list of EmployeeIncomeTax
     */

    @Query("select et from EmployeeIncomeTax et left join fetch et.financialYear where et.empId=:empId")
    List<EmployeeIncomeTax> findByEmpId(String empId);
}

