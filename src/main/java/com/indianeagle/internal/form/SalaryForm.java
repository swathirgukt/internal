package com.indianeagle.internal.form;

import com.indianeagle.internal.form.vo.DepartmentVO;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.form.vo.SalaryHistoryVO;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Durga Prasad M
 * @date Jan 26, 2010
 */
public class SalaryForm {
    private List<EmployeeVO> employeeList;
    private List<DepartmentVO> departmentList;
    private List<EmployeeVO> deptEmployeeList;
    private String employeeId;
    private EmployeeVO employee;
    private SalaryRule salaryRule;
    private SalaryHistoryVO currentSalary;
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

    public List<EmployeeVO> getEmployeeList() {
        return this.employeeList;
    }

    public void setEmployeeList(List<EmployeeVO> employeeList) {
        this.employeeList = employeeList;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeVO getEmployee() {
        return this.employee;
    }

    public void setEmployee(EmployeeVO employee) {
        this.employee = employee;
    }

    public SalaryRule getSalaryRule() {
        return this.salaryRule;
    }

    public void setSalaryRule(SalaryRule salaryRule) {
        this.salaryRule = salaryRule;
    }

    public SalaryHistoryVO getCurrentSalary() {
        return this.currentSalary;
    }

    public void setCurrentSalary(SalaryHistoryVO currentSalary) {
        this.currentSalary = currentSalary;
    }

    public List<DepartmentVO> getDepartmentList() {
        return this.departmentList;
    }

    public void setDepartmentList(List<DepartmentVO> departmentList) {
        this.departmentList = departmentList;
    }

    public List<EmployeeVO> getDeptEmployeeList() {
        return this.deptEmployeeList;
    }

    public void setDeptEmployeeList(List<EmployeeVO> deptEmployeeList) {
        this.deptEmployeeList = deptEmployeeList;
    }

}