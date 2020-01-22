package com.indianeagle.internal.form;

import java.math.BigDecimal;

/**
 * Result Class for PT form of an employee
 * User: Seethayya
 * Date: 1/18/13
 * Time: 12:58 PM
 */
public class PFResultData {

    private static final String PF_DATA_SEPARATOR = "#~#";
    private String memberId;
    private String name;
    private BigDecimal epf = BigDecimal.ZERO;
    private BigDecimal eps = BigDecimal.ZERO;
    private BigDecimal epfDue = BigDecimal.ZERO;
    private BigDecimal epfRemitted = BigDecimal.ZERO;
    private BigDecimal epsDue = BigDecimal.ZERO;
    private BigDecimal epsRemitted = BigDecimal.ZERO;
    private BigDecimal epfEpsDueDifference = BigDecimal.ZERO;
    private BigDecimal epfEpsRemittedDifference = BigDecimal.ZERO;
    private Long lop = 0l;
    private BigDecimal refundOfAdvances = BigDecimal.ZERO;
    private BigDecimal arrearEpf = BigDecimal.ZERO;
    private BigDecimal arrearEpfEEShare = BigDecimal.ZERO;
    private BigDecimal arrearEpfERShare = BigDecimal.ZERO;
    private BigDecimal arrearEpsShare = BigDecimal.ZERO;
    private String fatherOrHusbandName = "";
    private String relationWithMember = "";
    private String dateOfBirth = "";
    private String gender = "";
    private String dateOfJoiningEpf = "";
    private String dateOfJoiningEps = "";
    private String dateOfExitEpf = "";
    private String dateOfExitEps = "";
    private String reasonForLeaving = "";

    /**
     * Override method to prepare PF form result data
     *
     * @return string in the required format for pf
     */
    @Override
    public String toString() {
        return memberId + PF_DATA_SEPARATOR + name + PF_DATA_SEPARATOR + epf + PF_DATA_SEPARATOR + eps + PF_DATA_SEPARATOR + epfDue + PF_DATA_SEPARATOR + epfRemitted + PF_DATA_SEPARATOR + epsDue + PF_DATA_SEPARATOR + epsRemitted + PF_DATA_SEPARATOR + epfEpsDueDifference + PF_DATA_SEPARATOR + epfEpsRemittedDifference + PF_DATA_SEPARATOR + lop + PF_DATA_SEPARATOR + refundOfAdvances + PF_DATA_SEPARATOR + arrearEpf + PF_DATA_SEPARATOR + arrearEpfEEShare + PF_DATA_SEPARATOR + arrearEpfERShare + PF_DATA_SEPARATOR + arrearEpsShare + PF_DATA_SEPARATOR + fatherOrHusbandName + PF_DATA_SEPARATOR + relationWithMember + PF_DATA_SEPARATOR + dateOfBirth + PF_DATA_SEPARATOR + gender + PF_DATA_SEPARATOR + dateOfJoiningEpf + PF_DATA_SEPARATOR + dateOfJoiningEps + PF_DATA_SEPARATOR + dateOfExitEpf + PF_DATA_SEPARATOR + dateOfExitEps + PF_DATA_SEPARATOR + reasonForLeaving;
    }

    /**
     * @return memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return epf
     */
    public BigDecimal getEpf() {
        return epf;
    }

    /**
     * @param epf to set
     */
    public void setEpf(BigDecimal epf) {
        this.epf = epf;
    }

    /**
     * @return eps
     */
    public BigDecimal getEps() {
        return eps;
    }

    /**
     * @param eps to set
     */
    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    /**
     * @return epfDue
     */
    public BigDecimal getEpfDue() {
        return epfDue;
    }

    /**
     * @param epfDue to set
     */
    public void setEpfDue(BigDecimal epfDue) {
        this.epfDue = epfDue;
    }

    /**
     * @return epfRemitted
     */
    public BigDecimal getEpfRemitted() {
        return epfRemitted;
    }

    /**
     * @param epfRemitted to set
     */
    public void setEpfRemitted(BigDecimal epfRemitted) {
        this.epfRemitted = epfRemitted;
    }

    /**
     * @return epsDue
     */
    public BigDecimal getEpsDue() {
        return epsDue;
    }

    /**
     * @param epsDue to set
     */
    public void setEpsDue(BigDecimal epsDue) {
        this.epsDue = epsDue;
    }

    /**
     * @return epsRemitted
     */
    public BigDecimal getEpsRemitted() {
        return epsRemitted;
    }

    /**
     * @param epsRemitted to set
     */
    public void setEpsRemitted(BigDecimal epsRemitted) {
        this.epsRemitted = epsRemitted;
    }

    /**
     * @return epfEpsDueDifference
     */
    public BigDecimal getEpfEpsDueDifference() {
        return epfEpsDueDifference;
    }

    /**
     * @param epfEpsDueDifference to set
     */
    public void setEpfEpsDueDifference(BigDecimal epfEpsDueDifference) {
        this.epfEpsDueDifference = epfEpsDueDifference;
    }

    /**
     * @return epfEpsRemittedDifference
     */
    public BigDecimal getEpfEpsRemittedDifference() {
        return epfEpsRemittedDifference;
    }

    /**
     * @param epfEpsRemittedDifference to set
     */
    public void setEpfEpsRemittedDifference(BigDecimal epfEpsRemittedDifference) {
        this.epfEpsRemittedDifference = epfEpsRemittedDifference;
    }

    /**
     * @return lop
     */
    public Long getLop() {
        return lop;
    }

    /**
     * @param lop to set
     */
    public void setLop(Long lop) {
        this.lop = lop;
    }

    /**
     * @return refundOfAdvances
     */
    public BigDecimal getRefundOfAdvances() {
        return refundOfAdvances;
    }

    /**
     * @param refundOfAdvances to set
     */
    public void setRefundOfAdvances(BigDecimal refundOfAdvances) {
        this.refundOfAdvances = refundOfAdvances;
    }

    /**
     * @return arrearEpf
     */
    public BigDecimal getArrearEpf() {
        return arrearEpf;
    }

    /**
     * @param arrearEpf to set
     */
    public void setArrearEpf(BigDecimal arrearEpf) {
        this.arrearEpf = arrearEpf;
    }

    /**
     * @return arrearEpfEEShare
     */
    public BigDecimal getArrearEpfEEShare() {
        return arrearEpfEEShare;
    }

    /**
     * @param arrearEpfEEShare to set
     */
    public void setArrearEpfEEShare(BigDecimal arrearEpfEEShare) {
        this.arrearEpfEEShare = arrearEpfEEShare;
    }

    /**
     * @return arrearEpfERShare
     */
    public BigDecimal getArrearEpfERShare() {
        return arrearEpfERShare;
    }

    /**
     * @param arrearEpfERShare to set
     */
    public void setArrearEpfERShare(BigDecimal arrearEpfERShare) {
        this.arrearEpfERShare = arrearEpfERShare;
    }

    /**
     * @return arrearEpsShare
     */
    public BigDecimal getArrearEpsShare() {
        return arrearEpsShare;
    }

    /**
     * @param arrearEpsShare to set
     */
    public void setArrearEpsShare(BigDecimal arrearEpsShare) {
        this.arrearEpsShare = arrearEpsShare;
    }

    /**
     * @return fatherOrHusbandName
     */
    public String getFatherOrHusbandName() {
        return fatherOrHusbandName;
    }

    /**
     * @param fatherOrHusbandName to set
     */
    public void setFatherOrHusbandName(String fatherOrHusbandName) {
        this.fatherOrHusbandName = fatherOrHusbandName;
    }

    /**
     * @return relationWithMember
     */
    public String getRelationWithMember() {
        return relationWithMember;
    }

    /**
     * @param relationWithMember to set
     */
    public void setRelationWithMember(String relationWithMember) {
        this.relationWithMember = relationWithMember;
    }

    /**
     * @return dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return dateOfJoiningEpf
     */
    public String getDateOfJoiningEpf() {
        return dateOfJoiningEpf;
    }

    /**
     * @param dateOfJoiningEpf to set
     */
    public void setDateOfJoiningEpf(String dateOfJoiningEpf) {
        this.dateOfJoiningEpf = dateOfJoiningEpf;
    }

    /**
     * @return dateOfJoiningEps
     */
    public String getDateOfJoiningEps() {
        return dateOfJoiningEps;
    }

    /**
     * @param dateOfJoiningEps to set
     */
    public void setDateOfJoiningEps(String dateOfJoiningEps) {
        this.dateOfJoiningEps = dateOfJoiningEps;
    }

    /**
     * @return dateOfExitEpf
     */
    public String getDateOfExitEpf() {
        return dateOfExitEpf;
    }

    /**
     * @param dateOfExitEpf to set
     */
    public void setDateOfExitEpf(String dateOfExitEpf) {
        this.dateOfExitEpf = dateOfExitEpf;
    }

    /**
     * @return dateOfExitEps
     */
    public String getDateOfExitEps() {
        return dateOfExitEps;
    }

    /**
     * @param dateOfExitEps to set
     */
    public void setDateOfExitEps(String dateOfExitEps) {
        this.dateOfExitEps = dateOfExitEps;
    }

    /**
     * @return reasonForLeaving
     */
    public String getReasonForLeaving() {
        return reasonForLeaving;
    }

    /**
     * @param reasonForLeaving to set
     */
    public void setReasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
    }
}
