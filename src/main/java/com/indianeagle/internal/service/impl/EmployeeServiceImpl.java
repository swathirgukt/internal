package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.EmployeeRepository;
import com.indianeagle.internal.dao.repository.UsersRepository;
import com.indianeagle.internal.dto.*;
import com.indianeagle.internal.form.EmployeeForm;
import com.indianeagle.internal.form.vo.NomineeVO;
import com.indianeagle.internal.mail.MailingEngine;
import com.indianeagle.internal.service.EmployeeService;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UsersRepository usersRepository;

    private List<Employee> bufferedEmployees;
    @Autowired
    private MailingEngine mailingEngine;

    public List<Employee> searchEmployees(EmployeeForm employeeForm) {
        this.bufferedEmployees = this.employeeRepository.searchEmployees(employeeForm);
        return this.bufferedEmployees;
    }

    public List<Department> getDepartmentList() {
        return employeeRepository.getDepartmentList();
    }


    public void createEmployee(EmployeeForm employeeForm) {
        Employee employee = this.employeeRepository.findByEmpId(employeeForm.getEmpId());
        if (employee != null) {
            employeeForm.getEmployeeVO().setId(employee.getId());
            employeeForm.getEmployeeVO().setEmpId(employee.getEmpId());
            BeanUtils.copyProperties(employeeForm.getEmployeeVO(), employee);
            this.employeeRepository.save(employee);
        } else {
            employee = new Employee();
            BeanUtils.copyProperties(employeeForm.getEmployeeVO(), employee);

            Nominee nominee = new Nominee();
            BeanUtils.copyProperties(employeeForm.getEmployeeVO().getNomineeVO(), nominee);
            employee.setNominee(nominee);
            employee.getNominee().setEmployee(employee);

            Salary salary = new Salary();
            BeanUtils.copyProperties(employeeForm.getEmployeeVO().getSalaryVO(),salary);
            employee.setSalary(salary);

            Leaves leaves = new Leaves();
            leaves.setEmployee(employee);
            employee.setLeaves(leaves);
            this.employeeRepository.save(employee);
        }
        // this.mailingEngine.mailAccountDetails(employeeForm.getEmpId(), employeeForm.getEmployeeVO().getOfficialEmail());
    }

    public void updateEmployee(EmployeeForm employeeForm) {
        Employee employee = this.employeeRepository.findByEmpId(employeeForm.getEmployeeVO().getEmpId());
        User user = this.usersRepository.findByUserName(employeeForm.getEmployeeVO().getEmpId());
        if(user!=null) {
            user.setFirstName(employeeForm.getEmployeeVO().getFirstName());
            user.setLastName(employeeForm.getEmployeeVO().getLastName());
            user.setEmail(employeeForm.getEmployeeVO().getOfficialEmail());
            user.setPassword(employeeForm.getUser().getPassword());
            if (!SimpleUtils.isEmpty((Date) employeeForm.getEmployeeVO().getRelieveDate())) {
                user.setStatus(false);
            } else {
                user.setStatus(true);
            }
            this.usersRepository.save(user);
        }
        if (employee.getSalary() != null) {
            employeeForm.getEmployeeVO().getSalaryVO().setId(employee.getSalary().getId());
        }
        employeeForm.getEmployeeVO().setId(employee.getId());
        if (employee.getNominee() != null) {
            employeeForm.getEmployeeVO().getNomineeVO().setId(employee.getNominee().getId());
        }
        BeanUtils.copyProperties( employeeForm.getEmployeeVO(),  employee);
        employee.getNominee().setEmployee(employee);
        Salary salary=new Salary();
        BeanUtils.copyProperties(employeeForm.getEmployeeVO().getSalaryVO(),salary);
        employee.setSalary(salary);
        this.employeeRepository.save(employee);
        this.mailingEngine.mailAccountDetails(employee.getEmpId(), employee.getOfficialEmail());
    }

    public void updateEmployeeLeaves(Employee employee) {
        this.employeeRepository.save(employee);
    }

    public Employee findEmpFromBuffer(String id) {
        for (Employee employee : this.bufferedEmployees) {
            if (!employee.getId().toString().equals(id)) continue;
            return employee;
        }
        return null;
    }

    public List<Employee> searchBasedOnEmpStatus(EmployeeForm employeeForm) {

        return this.employeeRepository.searchBasedOnEmpStatus(employeeForm);
    }

    public Employee findEmployeeByEmpId(String empId) {
        return this.employeeRepository.findByEmpId(empId);
    }


    public List<Employee> getEmployeeList() {
        return this.employeeRepository.findAllByOrderByEmpId();
    }

    public List<Employee> getBufferedEmployees() {
        return this.bufferedEmployees;
    }

    public void setBufferedEmployees(List<Employee> bufferedEmployees) {
        this.bufferedEmployees = bufferedEmployees;
    }

    public EmployeeRepository getEmployeeDAO() {
        return this.employeeRepository;
    }

    public void setEmployeeDAO(EmployeeRepository employeeDAO) {
        this.employeeRepository = employeeDAO;
    }

    public UsersRepository getUsersDAO() {
        return this.usersRepository;
    }

    public void setUsersDAO(UsersRepository usersDAO) {
        this.usersRepository = usersDAO;
    }

    public MailingEngine getMailingEngine() {
        return this.mailingEngine;
    }

    public void setMailingEngine(MailingEngine mailingEngine) {
        this.mailingEngine = mailingEngine;
    }
}