package com.indianeagle.internal.service;

import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.form.EmployeeForm;
import java.util.List;

public interface EmployeeService {

    /**
     * To get department names
     *
     * @return
     */
    List<Department> getDepartmentList();

    /**
     * To save an Employee
     *
     * @param employeeForm
     */
    void createEmployee(EmployeeForm employeeForm);

    /**
     * To update the employee
     *
     * @param employeeForm the employeeForm
     */
    void updateEmployee(EmployeeForm employeeForm);

    /**
     * To search for employees
     *
     * @param employeeForm
     * @return
     */
    List<Employee> searchEmployees(EmployeeForm employeeForm);

    /**
     * To get employee from buffer
     *
     * @param id
     */
    Employee findEmpFromBuffer(String id);

    /**
     * gives the list of working employees
     *
     * @return List<Employee>
     */
    List<Employee> getEmployeeList();

    /**
     * To get employee based on status
     */
    List<Employee> searchBasedOnEmpStatus(EmployeeForm employeeForm);

    /**
     * To find the employee based on employee id
     *
     * @param empId the employee id to set
     * @return employee the employee
     */
    Employee findEmployeeByEmpId(String empId);

    /**
     * This method to update the employee leaves
     *
     * @param employee
     */
    void updateEmployeeLeaves(Employee employee);
}
