package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Durga Prasad M
 * @date Jan 26, 2010
 */
public class SalaryForm {
    private List<Employee> employeeList;
    private List<Department> departmentList;
    private List<Employee> deptEmployeeList;
    @NotBlank
    private String employeeId;
    private Employee employee;
    private SalaryRule salaryRule;
    private SalaryHistory currentSalary;
    private String message;
    private boolean saveOrMail;

    public boolean isSaveOrMail() {
        return this.saveOrMail;
    }

    public void setSaveOrMail(boolean saveOrMail) {
        this.saveOrMail = saveOrMail;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public SalaryRule getSalaryRule() {
        return this.salaryRule;
    }

    public void setSalaryRule(SalaryRule salaryRule) {
        this.salaryRule = salaryRule;
    }

    public SalaryHistory getCurrentSalary() {
        return this.currentSalary;
    }

    public void setCurrentSalary(SalaryHistory currentSalary) {
        this.currentSalary = currentSalary;
    }

    public List<Department> getDepartmentList() {
        return this.departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Employee> getDeptEmployeeList() {
        return this.deptEmployeeList;
    }

    public void setDeptEmployeeList(List<Employee> deptEmployeeList) {
        this.deptEmployeeList = deptEmployeeList;
    }
}