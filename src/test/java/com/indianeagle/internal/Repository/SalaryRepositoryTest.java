package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.SalaryRepository;
import com.indianeagle.internal.dto.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SalaryRepositoryTest {
    @Autowired
    SalaryRepository salaryRepository;

    @Test
    public void loadAllEmployee() {
        List<Employee> employees = salaryRepository.loadAllEmployees();
        for (Employee e : employees
        ) {
            System.out.println(e.getEmpId());
        }
    }

    @Test
    public void loadEmployee() {
        List<Employee> employees = salaryRepository.loadEmployee("YSPL1006");
        for (Employee e : employees
        ) {
            System.out.println(e.getEmpId());
        }
    }

    @Test
    public void loadEmployeesByDepartment() {
        List<Employee> employees = salaryRepository.loadEmployeesByDepartment("IT");
        System.out.println(employees.size());
        System.out.println("==========================");
    }

    @Test
    public void loadACtiveEmployee() {
        List<Employee> employees = salaryRepository.loadActiveEmployees();
        System.out.println(employees.size());
    }
}
