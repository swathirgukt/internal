package com.indianeagle.internal.form;

import com.indianeagle.internal.util.SimpleUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author hari.pondreti
 * used to represent Bank Salaries Report
 */
public class BankSalariesForm {

	private Date salaryDate;
	private List<BankSalariesInfo> bankSalariesInfoList;
	private BigDecimal totalSal;

	public String getTotalSalInWords() {
		return SimpleUtils.numberToWord(getTotalSal());
	}

	/**
	 * @return the totalSal
	 */
	public BigDecimal getTotalSal() {
		return totalSal;
	}

	/**
	 * @param totalSal the totalSal to set
	 */
	public void setTotalSal(BigDecimal totalSal) {
		this.totalSal = totalSal;
	}

	/**
	 * @return the bankSalariesInfoList
	 */
	public List<BankSalariesInfo> getBankSalariesInfoList() {
		return bankSalariesInfoList;
	}

	/**
	 * @param bankSalariesInfoList the bankSalariesInfoList to set
	 */
	public void setBankSalariesInfoList(List<BankSalariesInfo> bankSalariesInfoList) {
		this.bankSalariesInfoList = bankSalariesInfoList;
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

	public class BankSalariesInfo {
		private int slNo;
		private String empFullName;
		private String bankAcNo;
		private BigDecimal salary;

		/**
		 * @return the slNo
		 */
		public int getSlNo() {
			return slNo;
		}

		/**
		 * @param slNo the slNo to set
		 */
		public void setSlNo(int slNo) {
			this.slNo = slNo;
		}

		/**
		 * @return the empFullName
		 */
		public String getEmpFullName() {
			return empFullName;
		}

		/**
		 * @param empFullName the empFullName to set
		 */
		public void setEmpFullName(String empFullName) {
			this.empFullName = empFullName;
		}

		/**
		 * @return the bankAcNo
		 */
		public String getBankAcNo() {
			return bankAcNo;
		}

		/**
		 * @param bankAcNo the bankAcNo to set
		 */
		public void setBankAcNo(String bankAcNo) {
			this.bankAcNo = bankAcNo;
		}

		/**
		 * @return the salary
		 */
		public BigDecimal getSalary() {
			return salary;
		}

		/**
		 * @param salary the salary to set
		 */
		public void setSalary(BigDecimal salary) {
			this.salary = salary;
		}
	}

}
