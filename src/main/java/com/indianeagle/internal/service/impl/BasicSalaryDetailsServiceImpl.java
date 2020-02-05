/**
 * 
 */
package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.EmployeeRepository;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.service.BasicSalaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Unskrishna
  * Service for BasicSalaryDetails of employees
 */
@Service
public class BasicSalaryDetailsServiceImpl implements BasicSalaryDetailsService {
	@Autowired
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
