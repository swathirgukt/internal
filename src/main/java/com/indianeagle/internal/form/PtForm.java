/**
 *
 */
package com.indianeagle.internal.form;

import com.indianeagle.internal.util.SimpleUtils;

import java.util.Date;
import java.util.List;

/**
 * @author hari.pondreti
 * represents the FormV's result form
 */
public class PtForm {

    private Date returnPayMonth;
    private String employerName;
    private String orgAddress;
    private String regiCertificateNo;
    private String authorizedPerson;
    private String place;
    private Long totalAmountPaid;
    private List<PtCalculator> ptCalculatorList;

    /**
     * calculate the employees count
     * @return Long
     */
    public Long getEmpsCount() {
        Long empsCount = 0l;
        if (ptCalculatorList != null) {
            for (PtCalculator ptCalculator : ptCalculatorList) {
                empsCount += ptCalculator.getNoOfEmps();
            }
        }
        return empsCount;
    }

    public String getTotalAmountPaidInWords() {
        return SimpleUtils.numberToWord(getTotalAmountPaid().intValue());
    }

    /**
     * @return the returnPayMonth
     */
    public Date getReturnPayMonth() {
        return returnPayMonth;
    }

    /**
     * @param returnPayMonth the returnPayMonth to set
     */
    public void setReturnPayMonth(Date returnPayMonth) {
        this.returnPayMonth = returnPayMonth;
    }

    /**
     * @return the employerName
     */
    public String getEmployerName() {
        return employerName;
    }

    /**
     * @param employerName the employerName to set
     */
    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    /**
     * @return the orgAddress
     */
    public String getOrgAddress() {
        return orgAddress;
    }

    /**
     * @param orgAddress the orgAddress to set
     */
    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    /**
     * @return the regiCertificateNo
     */
    public String getRegiCertificateNo() {
        return regiCertificateNo;
    }

    /**
     * @param regiCertificateNo the regiCertificateNo to set
     */
    public void setRegiCertificateNo(String regiCertificateNo) {
        this.regiCertificateNo = regiCertificateNo;
    }

    /**
     * @return the authorizedPerson
     */
    public String getAuthorizedPerson() {
        return authorizedPerson;
    }

    /**
     * @param authorizedPerson the authorizedPerson to set
     */
    public void setAuthorizedPerson(String authorizedPerson) {
        this.authorizedPerson = authorizedPerson;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return the ptCalculatorList
     */
    public List<PtCalculator> getPtCalculatorList() {
        return ptCalculatorList;
    }

    /**
     * @param ptCalculatorList the ptCalculatorList to set
     */
    public void setPtCalculatorList(List<PtCalculator> ptCalculatorList) {
        this.ptCalculatorList = ptCalculatorList;
    }

    /**
     * @return the totalAmountPaid
     */
    public Long getTotalAmountPaid() {
        return totalAmountPaid;
    }

    /**
     * @param totalAmountPaid the totalAmountPaid to set
     */
    public void setTotalAmountPaid(Long totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public class PtCalculator {
        private String salaryRange;
        private Long noOfEmps;
        private Integer taxPerMonth;
        private Long deductedTax;

        /**
         * @return the salaryRange
         */
        public String getSalaryRange() {
            return salaryRange;
        }

        /**
         * @param salaryRange the salaryRange to set
         */
        public void setSalaryRange(String salaryRange) {
            this.salaryRange = salaryRange;
        }

        /**
         * @return the noofEmps
         */
        public Long getNoOfEmps() {
            return noOfEmps;
        }

        /**
         * @param noofEmps the noofEmps to set
         */
        public void setNoofEmps(Long noofEmps) {
            this.noOfEmps = noofEmps;
        }

        /**
         * @return the taxPerMonth
         */
        public Integer getTaxPerMonth() {
            return taxPerMonth;
        }

        /**
         * @param taxPerMonth the taxPerMonth to set
         */
        public void setTaxPerMonth(Integer taxPerMonth) {
            this.taxPerMonth = taxPerMonth;
        }

        /**
         * @return the deductedTax
         */
        public Long getDeductedTax() {
            return getNoOfEmps() * getTaxPerMonth();
        }

        /**
         * @param deductedTax the deductedTax to set
         */
        public void setDeductedTax(Long deductedTax) {
            this.deductedTax = deductedTax;
        }
    }
}
