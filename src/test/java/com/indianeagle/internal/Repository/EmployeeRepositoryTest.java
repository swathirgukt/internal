package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.EmployeeRepository;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.enums.EmployeeStatusEnum;
import com.indianeagle.internal.form.EmployeeForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void getEmpids() {
        List<String> stringList = employeeRepository.findAllEmpIds();
        for (String s : stringList
        ) {
            System.out.println(s);
        }
        System.out.println(stringList.size());
    }

    @Test
    public void findEmployeeByName() {
        List<Employee> employees = employeeRepository.findEmployeeByName("sr");
        System.out.println(employees.size());

        for (Employee e : employees
        ) {
            System.out.println(e.getFullName());
        }
    }

    @Test
    public void getBasicSalarybyEmpStatus() {

        List<Employee> employees = employeeRepository.getBasicSalaryDetails(EmployeeStatusEnum.RELIEVED.name());

    }

    @Test
    public void searchEmployee() {
        EmployeeForm employeeForm = new EmployeeForm();
        //Employee e=employeeRepository.findByEmpId("YSPL1006");
        /*Employee employee=new Employee();
        employee.setEmpId("YSPL1006");
        try {
            employee.setJoinDate(DateUtilForTest.getDate("2009/06/01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Department department=new Department();
        department.setDeptNo("2");
        employee.setDepartment(department);
       // employeeForm.setEmployee();
        List<Employee> employees=employeeRepository.searchEmployees(employeeForm);
        System.out.println(employees.size());*/
        // System.out.println(e);
        employeeForm.setDepartment("D002");
        List<Employee> employees = employeeRepository.searchEmployees(employeeForm);
        System.out.println(employees.size());
        for (Employee e1 : employees
        ) {
            System.out.println(e1.getFullName() + "  "/*e.getDepartment().getDepartment()*/);
        }
    }

    @Test
    public void findByJoinDaTE() {
        List<Employee> employees = employeeRepository.findByJoinDate();
        System.out.println(employees.size());
    }

    @Test
    public void findByDob() {
        List<Employee> employees = employeeRepository.findByDob();
        System.out.println(employees.size());
        for (Employee e : employees
        ) {
            System.out.println(e.getFullName() + "    " +
                    "" + e.getDob());
        }
    }

    public void getByStatus() {
        List<Employee> employees = employeeRepository.findByDob();
        System.out.println(employees.size());
    }
    @Test
    public void editEmployee(){
        Employee employee=employeeRepository.findByEmpId("YSPL1034");
        employee.setOfficialEmail("praveen.k@indianeagle.com");
        employeeRepository.save(employee);
    }
    @Test
    public void getEmployes(){
        List<Employee> employees=employeeRepository.findAllByOrderByEmpId();
        System.out.println("listSize"+employees.size());
        employees.forEach(employee -> {
            System.out.println(employee.getEmpId());
        });
    }
}
