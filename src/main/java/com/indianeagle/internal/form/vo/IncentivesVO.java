package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO class for Incentives
 * @author kiran.paluvadi
 *
 */
public class IncentivesVO {
	
	private Long id;
	private BigDecimal incentiveAmount;
	private Date incentiveDate;
	private EmployeeVO employeeVO;
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
	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}
	/**
	 * @param employeeVO the employee to set
	 */
	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
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
