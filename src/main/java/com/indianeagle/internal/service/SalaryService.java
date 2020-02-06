package com.indianeagle.internal.service;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.EmployeeSettlement;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.EmployeeSettlementForm;
import com.indianeagle.internal.form.GenerateAllSalariesForm;
import com.indianeagle.internal.form.SalaryRule;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.EmployeeSettlement;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.EmployeeSettlementForm;
import com.indianeagle.internal.form.GenerateAllSalariesForm;
import com.indianeagle.internal.form.SalaryRule;
*/

/**
 * @author Durga Prasad M
 * @date Jan 26, 2010
 */
public interface SalaryService {

    List<Employee> loadAllEmployees();

    SalaryHistory generateSalary(Employee employee, SalaryRule salaryRule) throws Exception;

    void confirmAndSendMail(Employee employee, SalaryHistory currrentSalary) throws Exception;

    List<SalaryHistory> generateSalaries(String department, SalaryRule salaryRule) throws Exception;

    void sendAllPaySlipMails(HttpSession httpSession) throws Exception;

    /**
     * method to load employee based on empID i.e
     *
     * @param empID
     * @return List<Employee>
     */
    Employee loadEmployee(String empID);

    /**
     * method to load Employees based on Department
     *
     * @param department
     * @return List
     */
    List<Employee> loadEmployeesByDepartment(String department);

    /**
     * load Active Employees
     */
    List<Employee> loadActiveEmployees();

    /**
     * Fills the GenerateAllSalariesForm with the Suitable Values
     *
     * @param generateAllSalariesForm
     */
    GenerateAllSalariesForm fillProduceAllSalariesForm(GenerateAllSalariesForm generateAllSalariesForm) throws Exception;

    /**
     * @param generateAllSalariesForm
     * @return List<SalaryHistory>
     * @throws Exception
     */
    List<SalaryHistory> produceAllSalaries(GenerateAllSalariesForm generateAllSalariesForm, HttpSession httpSession) throws Exception;

    /**
     * Method to calculate the employee settlement
     *
     * @param employee
     * @param employeeSettlementForm
     * @return
     * @throws Exception
     */
    EmployeeSettlement generateSalarySettlement(Employee employee, EmployeeSettlementForm employeeSettlementForm) throws Exception;

    /**
     * Method to save the employee settlement details
     */
    void confirmEmployeeSettlement();

    /**
     * Method to get resigned employee settlement report
     *
     * @param empId
     * @return
     */
    EmployeeSettlement loadResignedEmployeeSettlement(String empId);
}
