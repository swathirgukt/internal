package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.form.EmployeeForm;

import java.util.List;

/**
 * Custom Employee Repository
 *
 * @author Praveen
 * @Date 22/01/2020
 */
public interface EmployeeRepositoryCustom {

    /**
     * To search for employees
     *
     * @param employeeForm
     * @return
     */
    List<Employee> searchEmployees(EmployeeForm employeeForm);

    /**
     * gives the list of employees based on their status
     *
     * @param empStatus
     * @return List<Employee>
     */
    public List<Employee> getBasicSalaryDetails(String empStatus);

    /**
     * To get the Employees based on Status
     *
     * @param employeeForm
     * @return List<Employee>
     */
    @SuppressWarnings("unchecked")
    public List<Employee> searchBasedOnEmpStatus(EmployeeForm employeeForm);

}
