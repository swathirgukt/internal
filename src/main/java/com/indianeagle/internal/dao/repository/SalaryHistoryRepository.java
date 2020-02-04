package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Praveen
 */
@Repository
public interface SalaryHistoryRepository extends JpaRepository<SalaryHistory, Long>, SalaryHistoryRepositoryCustom {


    /**
     * Searches salaryHistory on the basis of employee id and salaryDate
     *
     * @param empId
     * @param salaryDate
     * @return collection of SalaryHistory objects
     */

    //@Query("SELECT s from  SalaryHistory s where s.empId =:empId and s.salaryDate <=:date")
    List<SalaryHistory> findByEmpIdAndSalaryDateLessThanEqual(String empId, Date salaryDate);

    /**
     * Method to search salary history based on employee id
     *
     * @param empId the employee id
     * @return list of salaryHistory
     */
    List<SalaryHistory> findByEmpId(String empId);

    /**
     * Salary Report for the Month of given
     *
     * @param salaryDate
     * @return
     */
    @Query("SELECT  s from SalaryHistory s where MONTH(s.salaryDate) =  MONTH(:salaryDate) AND YEAR(s.salaryDate) = YEAR(:salaryDate)")
    List<SalaryHistory> salaryReport(@Param("salaryDate") Date salaryDate);

    /**
     * Salary Report between two dates
     *
     * @param
     * @return collection of SalaryHistory Objects
     */
    @Query("SELECT s from SalaryHistory s where (DATE(s.salaryDate) BETWEEN DATE(:salaryDate)AND DATE(:salaryEndDate)) OR (DATE(s.salaryEndDate) BETWEEN DATE(:salaryDate)AND DATE(:salaryEndDate)) AND (YEAR(s.salaryDate) = YEAR(salaryDate) OR  YEAR(s.salaryEndDate) = YEAR(:salaryEndDate))ORDER BY s.salaryEndDate DESC")
    List<SalaryHistory> salaryReport(@Param("salaryDate") Date salaryDate, @Param("salaryEndDate") Date salaryEndDate);


    @Query("select e from Employee e where e.empId =:empId")
    List<Employee> findEmployeeByEmpId(@Param("empId") String empId);


    /**
     * method for monthly ESIC report
     *
     * @param formDate
     * @return List
     */
    @Query("SELECT COUNT(sh.empId),SUM(sh.esi),SUM(sh.grossSalary) from SalaryHistory sh  where  MONTH(sh.salaryDate) = MONTH(:formDate) AND YEAR(sh.salaryDate) = YEAR(:formDate) and sh.empId in (select empId from Employee e where e.salary.esiEligible = true )")
    List<Object> getMonthlyESIReport(@Param("formDate") Date formDate);


    /**
     * Method to find the salary historty based on emplyee id and given date
     *
     * @param empId
     * @param salaryDate
     * @return
     */
    @Query("  from SalaryHistory s where s.empId =:empId AND MONTH(s.salaryDate) =  MONTH(:salaryDate) AND YEAR(s.salaryDate) = YEAR(:salaryDate)")
    List<SalaryHistory> findSalaryHistoryByEmpId(@Param("empId") String empId, @Param("salaryDate") Date salaryDate);

    /**
     * Method to find the salary historty based on emplyee id and given dates
     *
     * @param empId
     * @param salaryDate,salaryEndDate
     * @return
     */
    @Query(" from SalaryHistory s where s.empId =:empId AND MONTH(s.salaryDate) =  MONTH(salaryDate) AND YEAR(s.salaryDate) = YEAR(:salaryDate)AND MONTH(s.salaryDate) =  MONTH(:salaryEndDate) AND YEAR(s.salaryDate) = YEAR(:salaryEndDate)")
    List<SalaryHistory> findSalaryHistoryByEmpId(@Param("empId") String empId, @Param("salaryDate") Date salaryDate, @Param("salaryEndDate") Date salaryEndDate);

    /**
     * To find the salary history of an employee within two dates
     *
     * @param empId
     * @param startDate
     * @param endDate
     * @return
     */


    //List<SalaryHistory> findSalaryHistoriesWithInFinancialYear(String empId, Date fromDate, Date toDate);
    // @Query("SELECT sh from SalaryHistory sh where sh.empId=:empId and sh.salaryEndDate between DATE(:startDate) and DATE(:endDate)")
    List<SalaryHistory> findByEmpIdAndSalaryEndDateBetween(@Param("empId") String empId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
