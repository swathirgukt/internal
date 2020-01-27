/**
 * 
 */
package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.EmployeeRepository;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.service.BasicSalaryDetailsService;
import java.util.List;

/**
 * @author hari.pondreti
 * Service for BasicSalaryDetails of employees
 */
public class BasicSalaryDetailsServiceImpl implements BasicSalaryDetailsService {
	private EmployeeRepository employeeRepository;
	
	/* (non-Javadoc)
	 * @see com.yana.internal.facade.BasicSalaryDetailsService#getBasicSalaryDetails(java.lang.String)
	 */
	public List<Employee> getBasicSalaryDetails(String empStatus) {
		return employeeRepository.getBasicSalaryDetails(empStatus);
	}
	/**
	 * @return the employeeDAO
	 */
	public EmployeeRepository getEmployeeDAO() {
		return employeeRepository;
	}
	/**
	 * @param employeeRepository the employeeDAO to set
	 */
	public void setEmployeeDAO(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}
}
