package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    @Query("from Department")
    List<Department> getDepartmentList();

    /**
     * To get Employee with employee ID
     *
     * @param empId
     * @return Employee Object
     */

    Employee findByEmpId(String empId);


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
    @Query("select employee.empId from Employee employee")
    List<String> findAllEmpIds();

    /**
     * To find all employees name matched with name
     *
     * @return list of all employees
     */

    @Query("select employee from Employee employee where employee.firstName like %:name% or employee.middleName like %:name% or employee.lastName like %:name%")
    List<Employee> findEmployeeByName(@Param("name") String name);

   List<Employee> findAllByOrderByEmpId();
}
