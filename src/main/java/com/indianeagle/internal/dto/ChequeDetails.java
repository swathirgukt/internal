package com.indianeagle.internal.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author irfan
 * dto for Cheque Details
 */
public class ChequeDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "CHEQUE_DATE")
    private Date chequeDate;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "CHEQUE_NO")
    private Long chequeNo;
    @Column(name = "STATUS")

    private String status;
    @Column(name = "BANK")
    private String bank;
    @Column(name = "NAME_OF_PAY")
    private String nameOfPay;
    @Column(name = "ID")
    private String comments;

    @Column(name = "COMMENTS")

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
