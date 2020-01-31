package com.indianeagle.internal.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author irfan
 * dto for Cheque Details
 */

@Component
@Entity
@Table(name = "CHEQUE_DETAILS")
public class ChequeDetails extends BaseDto {
    @Temporal(TemporalType.DATE)
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

    @Column(name = "COMMENTS")
    private String comments;

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

    @Override
    public String toString() {
        return "ChequeDetails{" +
                "chequeDate=" + chequeDate +
                ", amount=" + amount +
                ", chequeNo=" + chequeNo +
                ", status='" + status + '\'' +
                ", bank='" + bank + '\'' +
                ", nameOfPay='" + nameOfPay + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
