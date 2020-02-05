package com.indianeagle.internal.form.vo;


import com.indianeagle.internal.util.SimpleUtils;

import java.math.BigDecimal;
import java.util.Date;

public class SalaryHistoryVO {

	private Long id;
	private String empId;
	private Date salaryDate;
	private BigDecimal actlBasic;
	private BigDecimal actlHra;
	private BigDecimal actlConveyence;
	private BigDecimal actlMedicalAllowance;
	private BigDecimal actlSpecialAllowance;
	private BigDecimal actlOtherAllowance;
	private BigDecimal actlGross;
	private BigDecimal basic;
	private BigDecimal hra;
	private BigDecimal conveyence;
	private BigDecimal pfEmp;
	private BigDecimal pfCom;
	private BigDecimal esi;
	private BigDecimal medicalAllowance;
	private BigDecimal specialAllowance;
	private BigDecimal otherAllowance;
	private BigDecimal performanceIncentives;
	private BigDecimal salaryInAdvance;
	private BigDecimal PTax;
	private BigDecimal incomeTax;
	private BigDecimal arrearsOtherEarnings;
	private BigDecimal miscDeductions;
	private BigDecimal totalEarnings;
	private BigDecimal totalDeductions;
	private BigDecimal totalDays;
	private BigDecimal daysWorked;
	private BigDecimal arrearsDays;
	private BigDecimal lopDays;
	private BigDecimal grossSalary;
	private BigDecimal netSalary;
	private BigDecimal loanAmount ;
	private BigDecimal medicalInsurance ;
	private String fullName;
	private String bankAc;
	private String bankName;
	private String designation;
	private Date joinDate;
    private BigDecimal telephoneAllowance = BigDecimal.ZERO;
	private BigDecimal internetAllowance  = BigDecimal.ZERO;
	private BigDecimal uniformAllowance = BigDecimal.ZERO;
	private BigDecimal performanceLinkedPay = BigDecimal.ZERO;
	private BigDecimal bonus = BigDecimal.ZERO;
	private Date salaryEndDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the salaryDate
	 */
	public Date getSalaryDate() {
		return salaryDate;
	}

	/**
	 * @param salaryDate
	 *            the salaryDate to set
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
	 * @param basic
	 *            the basic to set
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
	 * @param hra
	 *            the hra to set
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
	 * @param conveyence
	 *            the conveyence to set
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
	 * @param pfEmp
	 *            the pfEmp to set
	 */
	public void setPfEmp(BigDecimal pfEmp) {
		this.pfEmp = pfEmp;
	}

	/**
	 * @return the pfCom
	 */
	public BigDecimal getPfCom() {
		return pfCom;
	}

	/**
	 * @param pfCom
	 *            the pfCom to set
	 */
	public void setPfCom(BigDecimal pfCom) {
		this.pfCom = pfCom;
	}

	/**
	 * @return the esi
	 */
	public BigDecimal getEsi() {
		return esi;
	}

	/**
	 * @param esi
	 *            the esi to set
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
	 * @param medicalAllowance
	 *            the medicalAllowance to set
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
	 * @param specialAllowance
	 *            the specialAllowance to set
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
	 * @param otherAllowance
	 *            the otherAllowance to set
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
	 * @param performanceIncentives
	 *            the performanceIncentives to set
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
	 * @param salaryInAdvance
	 *            the salaryInAdvance to set
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
	 * @param tax
	 *            the pTax to set
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
	 * @param incomeTax
	 *            the incomeTax to set
	 */
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	/**
	 * @return the arrearsOtherEarnings
	 */
	public BigDecimal getArrearsOtherEarnings() {
		return arrearsOtherEarnings;
	}

	/**
	 * @param arrearsOtherEarnings
	 *            the arrearsOtherEarnings to set
	 */
	public void setArrearsOtherEarnings(BigDecimal arrearsOtherEarnings) {
		this.arrearsOtherEarnings = arrearsOtherEarnings;
	}

	/**
	 * @return the miscDeductions
	 */
	public BigDecimal getMiscDeductions() {
		return miscDeductions;
	}

	/**
	 * @param miscDeductions
	 *            the miscDeductions to set
	 */
	public void setMiscDeductions(BigDecimal miscDeductions) {
		this.miscDeductions = miscDeductions;
	}

	/**
	 * @return the totalEarnings
	 */
	public BigDecimal getTotalEarnings() {
		return totalEarnings;
	}

	/**
	 * @param totalEarnings
	 *            the totalEarnings to set
	 */
	public void setTotalEarnings(BigDecimal totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	/**
	 * @return the totalDeductions
	 */
	public BigDecimal getTotalDeductions() {
		return totalDeductions;
	}

	/**
	 * @param totalDeductions
	 *            the totalDeductions to set
	 */
	public void setTotalDeductions(BigDecimal totalDeductions) {
		this.totalDeductions = totalDeductions;
	}

	/**
	 * @return the totalDays
	 */
	public BigDecimal getTotalDays() {
		return totalDays;
	}

	/**
	 * @param totalDays
	 *            the totalDays to set
	 */
	public void setTotalDays(BigDecimal totalDays) {
		this.totalDays = SimpleUtils.isEmptyValue(totalDays);
	}

	/**
	 * @return the arrearsDays
	 */
	public BigDecimal getArrearsDays() {
		return arrearsDays;
	}

	/**
	 * @param arrearsDays
	 *            the arrearsDays to set
	 */
	public void setArrearsDays(BigDecimal arrearsDays) {
		this.arrearsDays = SimpleUtils.isEmptyValue(arrearsDays);
	}

	/**
	 * @return the lopDays
	 */
	public BigDecimal getLopDays() {
		return lopDays;
	}

	/**
	 * @param lopDays
	 *            the lopDays to set
	 */
	public void setLopDays(BigDecimal lopDays) {
		this.lopDays = SimpleUtils.isEmptyValue(lopDays);
	}

	/**
	 * @return the grossSalary
	 */
	public BigDecimal getGrossSalary() {
		return grossSalary;
	}

	/**
	 * @param grossSalary
	 *            the grossSalary to set
	 */
	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}

	/**
	 * @return the netSalary
	 */
	public BigDecimal getNetSalary() {
		return netSalary;
	}

	/**
	 * @param netSalary
	 *            the netSalary to set
	 */
	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
	}

	/**
	 * @return the daysWorked
	 */
	public BigDecimal getDaysWorked() {
		return daysWorked;
	}

	/**
	 * @param daysWorked the daysWorked to set
	 */
	public void setDaysWorked(BigDecimal daysWorked) {
		this.daysWorked = daysWorked;
	}

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
	 * @return the actlBasic
	 */
	public BigDecimal getActlBasic() {
		return actlBasic;
	}

	/**
	 * @param actlBasic the actlBasic to set
	 */
	public void setActlBasic(BigDecimal actlBasic) {
		this.actlBasic = actlBasic;
	}

	/**
	 * @return the actlHra
	 */
	public BigDecimal getActlHra() {
		return actlHra;
	}

	/**
	 * @param actlHra the actlHra to set
	 */
	public void setActlHra(BigDecimal actlHra) {
		this.actlHra = actlHra;
	}

	/**
	 * @return the actlConveyence
	 */
	public BigDecimal getActlConveyence() {
		return actlConveyence;
	}

	/**
	 * @param actlConveyence the actlConveyence to set
	 */
	public void setActlConveyence(BigDecimal actlConveyence) {
		this.actlConveyence = actlConveyence;
	}

	/**
	 * @return the actlMedicalAllowance
	 */
	public BigDecimal getActlMedicalAllowance() {
		return actlMedicalAllowance;
	}

	/**
	 * @param actlMedicalAllowance the actlMedicalAllowance to set
	 */
	public void setActlMedicalAllowance(BigDecimal actlMedicalAllowance) {
		this.actlMedicalAllowance = actlMedicalAllowance;
	}

	/**
	 * @return the actlSpecialAllowance
	 */
	public BigDecimal getActlSpecialAllowance() {
		return actlSpecialAllowance;
	}

	/**
	 * @param actlSpecialAllowance the actlSpecialAllowance to set
	 */
	public void setActlSpecialAllowance(BigDecimal actlSpecialAllowance) {
		this.actlSpecialAllowance = actlSpecialAllowance;
	}

	/**
	 * @return the actlOtherAllowance
	 */
	public BigDecimal getActlOtherAllowance() {
		return actlOtherAllowance;
	}

	/**
	 * @param actlOtherAllowance the actlOtherAllowance to set
	 */
	public void setActlOtherAllowance(BigDecimal actlOtherAllowance) {
		this.actlOtherAllowance = actlOtherAllowance;
	}

	/**
	 * @return the actlGross
	 */
	public BigDecimal getActlGross() {
		return actlGross;
	}

	/**
	 * @param actlGross the actlGross to set
	 */
	public void setActlGross(BigDecimal actlGross) {
		this.actlGross = actlGross;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

    public BigDecimal getTelephoneAllowance() {
        return telephoneAllowance;
    }

    public void setTelephoneAllowance(BigDecimal telephoneAllowance) {
        this.telephoneAllowance = telephoneAllowance;
    }

    public BigDecimal getInternetAllowance() {
        return internetAllowance;
    }

    public void setInternetAllowance(BigDecimal internetAllowance) {
        this.internetAllowance = internetAllowance;
    }

    public BigDecimal getUniformAllowance() {
        return uniformAllowance;
    }

    public void setUniformAllowance(BigDecimal uniformAllowance) {
        this.uniformAllowance = uniformAllowance;
    }

	public BigDecimal getPerformanceLinkedPay() {
		return performanceLinkedPay;
	}

	public void setPerformanceLinkedPay(BigDecimal performanceLinkedPay) {
		this.performanceLinkedPay = performanceLinkedPay;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public Date getSalaryEndDate() {
		return salaryEndDate;
	}

	public void setSalaryEndDate(Date salaryEndDate) {
		this.salaryEndDate = salaryEndDate;
	}

	public String getBankAc() {
		return bankAc;
	}

	public void setBankAc(String bankAc) {
		this.bankAc = bankAc;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "SalaryHistoryVO{" +
				"id=" + id +
				", empId='" + empId + '\'' +
				", salaryDate=" + salaryDate +
				", actlBasic=" + actlBasic +
				", actlHra=" + actlHra +
				", actlConveyence=" + actlConveyence +
				", actlMedicalAllowance=" + actlMedicalAllowance +
				", actlSpecialAllowance=" + actlSpecialAllowance +
				", actlOtherAllowance=" + actlOtherAllowance +
				", actlGross=" + actlGross +
				", basic=" + basic +
				", hra=" + hra +
				", conveyence=" + conveyence +
				", pfEmp=" + pfEmp +
				", pfCom=" + pfCom +
				", esi=" + esi +
				", medicalAllowance=" + medicalAllowance +
				", specialAllowance=" + specialAllowance +
				", otherAllowance=" + otherAllowance +
				", performanceIncentives=" + performanceIncentives +
				", salaryInAdvance=" + salaryInAdvance +
				", PTax=" + PTax +
				", incomeTax=" + incomeTax +
				", arrearsOtherEarnings=" + arrearsOtherEarnings +
				", miscDeductions=" + miscDeductions +
				", totalEarnings=" + totalEarnings +
				", totalDeductions=" + totalDeductions +
				", totalDays=" + totalDays +
				", daysWorked=" + daysWorked +
				", arrearsDays=" + arrearsDays +
				", lopDays=" + lopDays +
				", grossSalary=" + grossSalary +
				", netSalary=" + netSalary +
				", loanAmount=" + loanAmount +
				", medicalInsurance=" + medicalInsurance +
				", fullName='" + fullName + '\'' +
				", bankAc='" + bankAc + '\'' +
				", bankName='" + bankName + '\'' +
				", designation='" + designation + '\'' +
				", joinDate=" + joinDate +
				", telephoneAllowance=" + telephoneAllowance +
				", internetAllowance=" + internetAllowance +
				", uniformAllowance=" + uniformAllowance +
				", performanceLinkedPay=" + performanceLinkedPay +
				", bonus=" + bonus +
				", salaryEndDate=" + salaryEndDate +
				'}';
	}
}
