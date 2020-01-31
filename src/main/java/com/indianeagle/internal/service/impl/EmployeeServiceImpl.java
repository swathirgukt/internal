package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.EmployeeRepository;
import com.indianeagle.internal.dao.repository.UsersRepository;
import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Leaves;
import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.form.EmployeeForm;
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
    private MailingEngine mailingEngine;

    public List<Employee> searchEmployees(EmployeeForm employeeForm) {
        this.bufferedEmployees=this.employeeRepository.searchEmployees(employeeForm);
        return this.bufferedEmployees;
    }

    public List<Department> getDepartmentList() {
return  employeeRepository.getDepartmentList();
    }


    public void createEmployee(EmployeeForm employeeForm) {
        Employee employee = this.employeeRepository.findByEmpId(employeeForm.getEmpId());
        if (employee != null) {
            employeeForm.getEmployee().setId(employee.getId());
            employeeForm.getEmployee().setEmpId(employee.getEmpId());
            BeanUtils.copyProperties((Object)employeeForm.getEmployee(), (Object)employee);
            this.employeeRepository.save(employee);
        } else {
            employee = employeeForm.getEmployee();
            employee.getNominee().setEmployee(employee);
            employee.setEmpId(employee.getEmpId());
            Leaves leaves = new Leaves();
            leaves.setEmployee(employee);
            employee.setLeaves(leaves);
            this.employeeRepository.save(employee);
        }
        this.mailingEngine.mailAccountDetails(employeeForm.getEmpId(), employeeForm.getEmployee().getOfficialEmail());
    }

    public void updateEmployee(EmployeeForm employeeForm) {
        Employee employee = this.employeeRepository.findByEmpId(employeeForm.getEmployee().getEmpId());
        User user = this.usersRepository.findByUserName(employeeForm.getEmployee().getEmpId());
        user.setFirstName(employeeForm.getEmployee().getFirstName());
        user.setLastName(employeeForm.getEmployee().getLastName());
        user.setEmail(employeeForm.getEmployee().getOfficialEmail());
        user.setPassword(employeeForm.getUser().getPassword());
        if (!SimpleUtils.isEmpty((Date)employeeForm.getEmployee().getRelieveDate())) {
            user.setStatus(false);
        } else {
            user.setStatus(true);
        }
        this.usersRepository.save(user);
        if (employee.getSalary() != null) {
            employeeForm.getEmployee().getSalary().setId(employee.getSalary().getId());
        }
        employeeForm.getEmployee().setId(employee.getId());
        if (employee.getNominee() != null) {
            employeeForm.getEmployee().getNominee().setId(employee.getNominee().getId());
        }
        BeanUtils.copyProperties((Object)employeeForm.getEmployee(), (Object)employee);
        employee.getNominee().setEmployee(employee);
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
        return this.employeeRepository.findAll();
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