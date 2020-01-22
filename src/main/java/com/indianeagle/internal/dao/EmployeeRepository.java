package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Employee Repository
 *
 * @author Praveen
 * @Date 22/01/2020
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

    /**
     * To get department names
     *
     * @return
     */
    @Query("select department from department")
    List<Department> findByDepartment();


    /**
     * Find employees
     *
     * @return List<Employee>
     */
    List<Employee> findAll();

    /**
     * To get the Employees whose relieve date is null and
     * order by empid
     */
    List<Employee> findByRelieveDateIsNullOrderByEmpId();

    /**
     * To get all employee id's
     *
     * @return list of employee id's
     */
    @Query("select e.id from EMPLOYEE e")
    List<String> findAllEmpIds();

    /**
     * This method to find the list of employees based on date of birth
     *
     * @return list of employees
     */
    @Query("select e from FROM Employee e WHERE DAY(e.dob) = DAY(CURRENT_DATE)+1 ")
    List<Employee> findByDob();

    /**
     * This method to find the employees based on date of joining
     *
     * @return
     */
    @Query("select e FROM Employee e WHERE DAY(e.joinDate) = DAY(CURRENT_DATE) AND MONTH(e.joinDate) = MONTH(CURRENT_DATE)+1")
    List<Employee> findByJoinDate(Date joinDate);


    /**
     * To find all employees name matched with name
     *
     * @return list of all employees
     */

    @Query("select employee from Employee employee where employee.firstName like %:name% or employee.middleName like %:name% or employee.lastName like %:name%")
    List<Employee> findEmployeeByName(@Param("name") String name);


}
