package com.indianeagle.internal.dto;

import javax.persistence.*;

/**
 * DTO for Generated Form16
 * <p>
 * User: kalesha
 * Date: 8/22/2017
 */
//@Entity
@Table(name = "GENERATED_FORM_16")
public class GeneratedForm16 extends BaseDto{

    @Column(name = "EMP_ID")
    private String empId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINANCIAL_YEAR_ID", nullable = false)
    private FinancialYear financialYear;
    @Column(name = "PDF_DATA")
    private byte[] pdfData;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public FinancialYear getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
    }

    public byte[] getPdfData() {
        return pdfData;
    }

    public void setPdfData(byte[] pdfData) {
        this.pdfData = pdfData;
    }
}
