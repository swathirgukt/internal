package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Praveen
 * @date 22/01/2020
 */
@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("select emp from Employee emp ORDER BY emp.empId")
    List<Employee> loadAllEmployees();

    /**
     * method to load employee based on empID i.e
     *
     * @param
     * @return List<Employee>
     */
    @Query("from Employee emp where emp.empId=:empId")
    List<Employee> loadEmployee(@Param("empId") String empID);

    /**
     * method to load Employees based on Department
     *
     * @param department
     * @return List
     */
    @Query("select emp from Employee emp where emp.relieveDate IS  NULL AND emp.department.department=:department")
    List<Employee> loadEmployeesByDepartment(@Param("department") String department);

    /**
     * list of employees whose relieve date is null
     * and ordered by employee id
     *
     * @return List<Employee>
     */
    @Query("SELECT emp from Employee emp where emp.relieveDate IS  NULL ORDER BY emp.empId")
    List<Employee> loadActiveEmployees();

}
