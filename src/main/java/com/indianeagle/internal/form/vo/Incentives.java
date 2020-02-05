package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO class for Incentives
 * @author kiran.paluvadi
 *
 */
public class Incentives {
	
	private Long id;
	private BigDecimal incentiveAmount;
	private Date incentiveDate;
	private EmployeeVO employee;
	private String empName;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the incentiveAmount
	 */
	public BigDecimal getIncentiveAmount() {
		return incentiveAmount;
	}
	/**
	 * @param incentiveAmount the incentiveAmount to set
	 */
	public void setIncentiveAmount(BigDecimal incentiveAmount) {
		this.incentiveAmount = incentiveAmount;
	}
	/**
	 * @return the incentiveDate
	 */
	public Date getIncentiveDate() {
		return incentiveDate;
	}
	/**
	 * @param incentiveDate the incentiveDate to set
	 */
	public void setIncentiveDate(Date incentiveDate) {
		this.incentiveDate = incentiveDate;
	}
	/**
	 * @return the employee
	 */
	public EmployeeVO getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
