package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.EmployeeSettlement;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Jpa Repository for EmployeeSettlement
 * User: Praveen
 * Date: 22/01/2020
 * Time: 5:52 PM
 */
@Repository
public interface EmployeeSettlementRepository extends JpaRepository<EmployeeSettlement, Long>, EmployeeSettlementRepositoryCustom {

    /**
     * method for monthly ESIC report
     *
     * @param formDate
     * @return List
     */
    @Query("select COUNT(es.employee),SUM(es.esi),SUM(es.grossSalary) from EmployeeSettlement es where MONTH (es.settlementDate) = month(:formDate) and year(es.settlementDate) = year(:formDate) and es.esi > 0")
    List<Object> getMonthlyESIReport(@Param("formDate") Date formDate);


    /**
     * Method to get resigned employee settlement report
     *
     * @param empId
     * @return
     */
    @Query("select empSettlement from EmployeeSettlement as  empSettlement join fetch empSettlement.employee as employee where employee.empId=:empId")
    List<EmployeeSettlement> findResignedEmployeeSettlementByEmployeeId(@Param("empId") String empId);

    /**
     * Method to find monthly employee settlement
     *
     * @param date the monthly date
     * @return employee settlement list for the given month
     */
    @Query("select employeeSettlement from EmployeeSettlement employeeSettlement left join fetch employeeSettlement.employee employee WHERE (month(employeeSettlement.settlementDate) = month(:date) AND year(employeeSettlement.settlementDate) = year(:date))")
    List<EmployeeSettlement> findMonthlyEmployeeSettlement(@Param("date") Date date);

}
