package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author irfan
 * dto for Cheque Details
 */
public class ChequeDetails {
	
	private Long id;
	private Date chequeDate;
	private BigDecimal amount;
	private Long chequeNo;
	private String status;
	private String bank;
	private String nameOfPay;
	private String comments;
	
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
	 * @return the chequeDate
	 */
	public Date getChequeDate() {
		return chequeDate;
	}
	/**
	 * @param chequeDate the chequeDate to set
	 */
	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the chequeNo
	 */
	public Long getChequeNo() {
		return chequeNo;
	}
	/**
	 * @param chequeNo the chequeNo to set
	 */
	public void setChequeNo(Long chequeNo) {
		this.chequeNo = chequeNo;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * @return the nameOfPay
	 */
	public String getNameOfPay() {
		return nameOfPay;
	}
	/**
	 * @param nameOfPay the nameOfPay to set
	 */
	public void setNameOfPay(String nameOfPay) {
		this.nameOfPay = nameOfPay;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
