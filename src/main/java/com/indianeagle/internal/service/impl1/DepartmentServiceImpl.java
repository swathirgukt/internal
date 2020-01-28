package com.indianeagle.internal.service.impl1;

import com.indianeagle.internal.dto.*;
import com.indianeagle.internal.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    List<Department> departmentList;

    @PostConstruct
    public void prepareList() {
        departmentList = new ArrayList<>();

        for (int i=1;i<25;i++) {
            Department department = new Department();
            department.setDepartment("IT"+i);
            department.setDeptNo("101"+i);
            department.setLocation("Hyderabad"+i);
            department.setMngrNo(i+123456L);
            department.setPhoneNo(i+123456L);
            department.setId(i+101L);
            departmentList.add(department);
        }
    }


    @Override
    public void saveOrUpdate(Department department) {
        ListIterator<Department> iterator =departmentList.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(department.getId())) {
                iterator.remove();
                iterator.add(department);
                return;
            }
        }
        departmentList.add(department);
    }

    @Override
    public List<Department> findDepartments(Department department) {
        List<Department> departments = new ArrayList<>();
        if (department.getDepartment() != null && !department.getDepartment().isEmpty()) {
            for (Department department1 : departmentList)
                if (department1.getDepartment().equals(department.getDepartment())) {
                    departments.add(department1);
                    return departments;
                }
        } else
            return departmentList;
        return null;
    }

    @Override
    public List<Department> loadAllDepartments() {
        return departmentList;
    }

    @Override
    public Department findById(long department) {
            for (Department department1 : departmentList)
                if (department1.getId().equals(department)){
                    return department1;
                }
        return null;
    }

    @Override
    public void saveOrUpdateFinancialYear(FinancialYear financialYear) {

    }

    @Override
    public Employee findEmployeeByEmployeeId(String employeeId) {
        return null;
    }

    @Override
    public List<FinancialYear> findFinancialYearSectionsByFinancialYear(String fromMonth, String fromYear, String toMonth, String toYear) {
        return null;
    }

    @Override
    public List<FinancialYear> findAllFinancialYearSections() {
        return null;
    }

    @Override
    public void saveOrUpdateEmployeeFinancialYear(EmployeeFinancialYear employeeFinancialYear) {

    }

    @Override
    public List<EmployeeFinancialYear> findEmployeeFinancialYearByEmpId(String empId) {
        return null;
    }

    @Override
    public List<SalaryHistory> findSalaryHistoriesWithInFinancialYear(String empId, Date fromDate, Date toDate) {
        return null;
    }

    @Override
    public List<EmployeeFinancialYear> findEmployeeFinancialYearWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear) {
        return null;
    }

    @Override
    public InputStream saveForm16PDF(String empId, FinancialYear financialYear, List<SalaryHistory> salaryHistories, EmployeeIncomeTax employeeIncomeTax, EmployeeFinancialYear employeeFinancialYear, String contextPath) {
        return null;
    }

    @Override
    public void saveOrUpdateEmployeeIncomeTax(EmployeeIncomeTax employeeIncomeTax) {

    }

    @Override
    public List<EmployeeIncomeTax> findEmployeeIncomeTaxWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear) {
        return null;
    }

    @Override
    public void saveOrUpdateEmployee(Employee employee) {

    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return null;
    }

    @Override
    public List<EmployeeIncomeTax> findEmployeeIncomeTaxWithEmpId(String empId) {
        return null;
    }

    @Override
    public void sendMailEmployeeIncomeTax(String empId, FinancialYear financialYear, List<SalaryHistory> salaryHistories, EmployeeIncomeTax employeeIncomeTax, EmployeeFinancialYear employeeFinancialYear, String contextPath) {

    }

    @Override
    public void saveOrUpdateGeneratedForm16(GeneratedForm16 generatedForm16) {

    }

    @Override
    public GeneratedForm16 findPdfByEmpIdAndFinancialYear(String emId, FinancialYear financialYear) {
        return null;
    }
}
