package com.indianeagle.internal.form.vo;

import com.indianeagle.internal.util.SimpleUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 'POJO'for CRUD operations on view_monthly_salary_report 
 * @author appala.sambangi
 *
 */
public class MonthlySalaryReport {
	private long id;
	private String employeeId;
	private String employeeName;
	private String designation;
	private String department;
	private Date doj;
	private Date salaryDate;
	private BigDecimal basic;
	private BigDecimal hra;
	private BigDecimal conveyence;
	private BigDecimal pfEmp;
	private BigDecimal esi;
	private BigDecimal medicalAllowance;
	private BigDecimal specialAllowance;
	private BigDecimal otherAllowance;
	private BigDecimal performanceIncentives = BigDecimal.ZERO;
	private BigDecimal salaryInAdvance = BigDecimal.ZERO;
	private BigDecimal PTax;
	private BigDecimal incomeTax;
	private BigDecimal totalDeductions;
	private BigDecimal netSalary;
	private BigDecimal grossSalary;
	private String bankAc;
	private BigDecimal totalDays;
	private BigDecimal arrearsDays = BigDecimal.ZERO;
	private BigDecimal lopDays = BigDecimal.ZERO;
	private BigDecimal miscDeductions = BigDecimal.ZERO;
	private BigDecimal loanAmount = BigDecimal.ZERO;
	private BigDecimal medicalInsurance = BigDecimal.ZERO;
	
	/**
	 * @return the loanAmount
	 */
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	/**
	 * @return the medicalInsurance
	 */
	public BigDecimal getMedicalInsurance() {
		return medicalInsurance;
	}
	/**
	 * @param medicalInsurance the medicalInsurance to set
	 */
	public void setMedicalInsurance(BigDecimal medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the doj
	 */
	public Date getDoj() {
		return doj;
	}
	/**
	 * @param doj the doj to set
	 */
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	/**
	 * @return the salaryDate
	 */
	public Date getSalaryDate() {
		return salaryDate;
	}
	/**
	 * @param salaryDate the salaryDate to set
	 */
	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}
	/**
	 * @return the basic
	 */
	public BigDecimal getBasic() {
		return basic;
	}
	/**
	 * @param basic the basic to set
	 */
	public void setBasic(BigDecimal basic) {
		this.basic = basic;
	}
	/**
	 * @return the hra
	 */
	public BigDecimal getHra() {
		return hra;
	}
	/**
	 * @param hra the hra to set
	 */
	public void setHra(BigDecimal hra) {
		this.hra = hra;
	}
	/**
	 * @return the conveyence
	 */
	public BigDecimal getConveyence() {
		return conveyence;
	}
	/**
	 * @param conveyence the conveyence to set
	 */
	public void setConveyence(BigDecimal conveyence) {
		this.conveyence = conveyence;
	}
	/**
	 * @return the pfEmp
	 */
	public BigDecimal getPfEmp() {
		return pfEmp;
	}
	/**
	 * @param pfEmp the pfEmp to set
	 */
	public void setPfEmp(BigDecimal pfEmp) {
		this.pfEmp = pfEmp;
	}
	/**
	 * @return the esi
	 */
	public BigDecimal getEsi() {
		return esi;
	}
	/**
	 * @param esi the esi to set
	 */
	public void setEsi(BigDecimal esi) {
		this.esi = esi;
	}
	/**
	 * @return the medicalAllowance
	 */
	public BigDecimal getMedicalAllowance() {
		return medicalAllowance;
	}
	/**
	 * @param medicalAllowance the medicalAllowance to set
	 */
	public void setMedicalAllowance(BigDecimal medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}
	/**
	 * @return the specialAllowance
	 */
	public BigDecimal getSpecialAllowance() {
		return specialAllowance;
	}
	/**
	 * @param specialAllowance the specialAllowance to set
	 */
	public void setSpecialAllowance(BigDecimal specialAllowance) {
		this.specialAllowance = specialAllowance;
	}
	/**
	 * @return the otherAllowance
	 */
	public BigDecimal getOtherAllowance() {
		return otherAllowance;
	}
	/**
	 * @param otherAllowance the otherAllowance to set
	 */
	public void setOtherAllowance(BigDecimal otherAllowance) {
		this.otherAllowance = otherAllowance;
	}
	/**
	 * @return the performanceIncentives
	 */
	public BigDecimal getPerformanceIncentives() {
		return performanceIncentives;
	}
	/**
	 * @param performanceIncentives the performanceIncentives to set
	 */
	public void setPerformanceIncentives(BigDecimal performanceIncentives) {
		this.performanceIncentives = performanceIncentives;
	}
	/**
	 * @return the salaryInAdvance
	 */
	public BigDecimal getSalaryInAdvance() {
		return salaryInAdvance;
	}
	/**
	 * @param salaryInAdvance the salaryInAdvance to set
	 */
	public void setSalaryInAdvance(BigDecimal salaryInAdvance) {
		this.salaryInAdvance = salaryInAdvance;
	}
	/**
	 * @return the pTax
	 */
	public BigDecimal getPTax() {
		return PTax;
	}
	/**
	 * @param tax the pTax to set
	 */
	public void setPTax(BigDecimal tax) {
		PTax = tax;
	}
	/**
	 * @return the incomeTax
	 */
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}
	/**
	 * @param incomeTax the incomeTax to set
	 */
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}
	/**
	 * @return the totalDeductions
	 */
	public BigDecimal getTotalDeductions() {
		return totalDeductions;
	}
	/**
	 * @param totalDeductions the totalDeductions to set
	 */
	public void setTotalDeductions(BigDecimal totalDeductions) {
		this.totalDeductions = totalDeductions;
	}
	/**
	 * @return the netSalary
	 */
	public BigDecimal getNetSalary() {
		return netSalary;
	}
	/**
	 * @param netSalary the netSalary to set
	 */
	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
	}
	/**
	 * @return the grossSalary
	 */
	public BigDecimal getGrossSalary() {
		return grossSalary;
	}
	/**
	 * @param grossSalary the grossSalary to set
	 */
	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}
	/**
	 * @return the bankAc
	 */
	public String getBankAc() {
		return bankAc;
	}
	/**
	 * @param bankAc the bankAc to set
	 */
	public void setBankAc(String bankAc) {
		this.bankAc = bankAc;
	}
	public String getFormateDoj(){
		return SimpleUtils.dateToString(this.doj);
	}
	public String getFormateSalaryDate(){
		return SimpleUtils.dateToString(this.salaryDate);
	}
	/**
	 * @return the totalDays
	 */
	public BigDecimal getTotalDays() {
		return totalDays;
	}
	/**
	 * @param totalDays the totalDays to set
	 */
	public void setTotalDays(BigDecimal totalDays) {
		this.totalDays = totalDays;
	}
	/**
	 * @return the arrearsDays
	 */
	public BigDecimal getArrearsDays() {
		return arrearsDays;
	}
	/**
	 * @param arrearsDays the arrearsDays to set
	 */
	public void setArrearsDays(BigDecimal arrearsDays) {
		this.arrearsDays = arrearsDays;
	}
	/**
	 * @return the lopDays
	 */
	public BigDecimal getLopDays() {
		return lopDays;
	}
	
	/**
	 * @param lopDays the lopDays to set
	 */
	public void setLopDays(BigDecimal lopDays) {
		this.lopDays = lopDays;
	}
	/**
	 * @return the miscDeductions
	 */
	public BigDecimal getMiscDeductions() {
		return miscDeductions;
	}
	/**
	 * @param miscDeductions the miscDeductions to set
	 */
	public void setMiscDeductions(BigDecimal miscDeductions) {
		this.miscDeductions = miscDeductions;
	}
	
	public void setTodefault(){
		BigDecimal defaultValue = BigDecimal.ZERO ;
		this.grossSalary =  defaultValue;
		this.PTax = defaultValue;
		this.pfEmp = defaultValue;
		this.esi = defaultValue;
		this.incomeTax = defaultValue;
		this.salaryInAdvance = defaultValue;
		this.netSalary = defaultValue;
		this.totalDeductions = defaultValue;
		this.otherAllowance = defaultValue;
	}

}
