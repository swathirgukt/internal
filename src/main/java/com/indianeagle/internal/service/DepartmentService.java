package com.indianeagle.internal.facade;

import com.yana.internal.dto.*;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface DepartmentService {

    void saveOrUpdate(Department department);

    List<Department> findDepartments(Department department);

    List<Department> loadAllDepartments();

    Department findById(long department);

    void saveOrUpdateFinancialYear(FinancialYear financialYear);

    Employee findEmployeeByEmployeeId(String employeeId);

    List<FinancialYear> findFinancialYearSectionsByFinancialYear(String fromMonth, String fromYear, String toMonth, String toYear);

    List<FinancialYear> findAllFinancialYearSections();

    void saveOrUpdateEmployeeFinancialYear(EmployeeFinancialYear employeeFinancialYear);

    List<EmployeeFinancialYear> findEmployeeFinancialYearByEmpId(String empId);

    List<SalaryHistory> findSalaryHistoriesWithInFinancialYear(String empId, Date fromDate, Date toDate);

    List<EmployeeFinancialYear> findEmployeeFinancialYearWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear);

    InputStream saveForm16PDF(String empId, FinancialYear financialYear, List<SalaryHistory> salaryHistories, EmployeeIncomeTax employeeIncomeTax, EmployeeFinancialYear employeeFinancialYear, String contextPath);

    void saveOrUpdateEmployeeIncomeTax(EmployeeIncomeTax employeeIncomeTax);

    List<EmployeeIncomeTax> findEmployeeIncomeTaxWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear);

    void saveOrUpdateEmployee(Employee employee);

    List<Employee> findEmployeeByName(String name);

    List<EmployeeIncomeTax> findEmployeeIncomeTaxWithEmpId(String empId);

    void sendMailEmployeeIncomeTax(String empId, FinancialYear financialYear, List<SalaryHistory> salaryHistories, EmployeeIncomeTax employeeIncomeTax, EmployeeFinancialYear employeeFinancialYear, String contextPath);

    void saveOrUpdateGeneratedForm16(GeneratedForm16 generatedForm16);

    GeneratedForm16 findPdfByEmpIdAndFinancialYear(String emId, FinancialYear financialYear);
}
