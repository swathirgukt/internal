package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.ChequeDetails;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author irfan
 * form object for Cheque Details
 */
public class ChequeDetailsForm {

    private ChequeDetails chequeDetails;
    private Date fromDate;
    private Date toDate;
    private BigDecimal amount;
    private List<ChequeDetails> chequeDetailsList;

    /**
     * @return the chequeDetails
     */
    public ChequeDetails getChequeDetails() {
        return chequeDetails;
    }

    /**
     * @param chequeDetails the chequeDetails to set
     */
    public void setChequeDetails(ChequeDetails chequeDetails) {
        this.chequeDetails = chequeDetails;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the chequeDetailsList
     */
    public List<ChequeDetails> getChequeDetailsList() {
        return chequeDetailsList;
    }

    /**
     * @param chequeDetailsList the chequeDetailsList to set
     */
    public void setChequeDetailsList(List<ChequeDetails> chequeDetailsList) {
        this.chequeDetailsList = chequeDetailsList;
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

}
