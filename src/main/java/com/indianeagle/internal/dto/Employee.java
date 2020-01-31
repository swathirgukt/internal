package com.indianeagle.internal.dto;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseDto{
    @Column(name = "EMP_ID")
    private String empId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "TEM_ADDRESS")
    private String tempAddress;
    @Column(name = "PER_ADDRESS")
    private String perAddress;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "MOBILE_NO")
    private Long mobileNo;
    @Temporal(TemporalType.DATE)
    @Column(name = "JOIN_DATE")
    private Date joinDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "RELIEVE_DATE")
    private Date relieveDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "RESIGN_DATE")
    private Date resignDate;
    @Column(name = "REASON_FOR_RESIGN")
    private String reasonsForResign;
    @Column(name = "PAN_NO")
    private String panNo;
    @Column(name = "PF_NO")
    private String pfNo;
    @Column(name = "ESI_NO")
    private String esiNo;
    @Column(name = "AADHAR_NUMBER")
    private String aadharNo;
    @Column(name = "BANK_AC")
    private String bankAc;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "PERSONAL_EMAIL")
    private String personalEmail;
    //@Column(name = "ID")
    private String fullName;
    //@Column(name = "ID")
    private String empInfo;
    @Column(name = "LEVEL")
    private String level;
    @Column(name = "BANK_NAME")
    private String bankName;
    @Temporal(TemporalType.DATE)
    @Column(name = "DOB")
    private Date dob;
    @Column(name = "EMP_STATUS")
    private String empStatus;
    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;
    @Column(name = "OFFICIAL_EMAIL")
    private String officialEmail;
    @Column(name = "EMERGENCY_CONTACT")
    private String emergencyContact;
    @Column(name = "PASSPORT_NO")
    private String passportNo;
    @Column(name = "UAN_NUMBER")
    private String uanNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "SAL_REF", unique = true)
    private Salary salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPT_REF")
    private Department department;


    private SalaryHistory currentSalary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private Leaves leaves;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private EmployeeSettlement employeeSettlement;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "employee")
    private Nominee nominee;

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the tempAddress
     */
    public String getTempAddress() {
        return tempAddress;
    }

    /**
     * @param tempAddress the tempAddress to set
     */
    public void setTempAddress(String tempAddress) {
        this.tempAddress = tempAddress;
    }

    /**
     * @return the perAddress
     */
    public String getPerAddress() {
        return perAddress;
    }

    /**
     * @param perAddress the perAddress to set
     */
    public void setPerAddress(String perAddress) {
        this.perAddress = perAddress;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the mobileNo
     */
    public Long getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the panNo
     */
    public String getPanNo() {
        return panNo;
    }

    /**
     * @param panNo the panNo to set
     */
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    /**
     * @return the pfNo
     */
    public String getPfNo() {
        return pfNo;
    }

    /**
     * @param pfNo the pfNo to set
     */
    public void setPfNo(String pfNo) {
        this.pfNo = pfNo;
    }

    /**
     * @return the esiNo
     */
    public String getEsiNo() {
        return esiNo;
    }

    /**
     * @param esiNo the esiNo to set
     */
    public void setEsiNo(String esiNo) {
        this.esiNo = esiNo;
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
     * @return the personalEmail
     */
    public String getPersonalEmail() {
        return personalEmail;
    }

    /**
     * @param personalEmail the email to set
     */
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * @return the salary
     */
    public Salary getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        StringBuffer name = new StringBuffer();
        name.append(StringUtils.capitalize(getFirstName()));
        if (StringUtils.isNotBlank(getMiddleName()))
            name.append(" ").append(getMiddleName());
        return name.append(" ").append(StringUtils.capitalize(getLastName())).toString();
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public SalaryHistory getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(SalaryHistory currentSalary) {
        this.currentSalary = currentSalary;
    }

    /**
     * @return the empInfo
     */
    public String getEmpInfo() {
        return this.empInfo = this.empId + "_" + this.firstName + " " + this.lastName;
    }

    public void setEmpInfo(String empInfo) {
        this.empInfo = empInfo;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getRelieveDate() {
        return relieveDate;
    }

    public void setRelieveDate(Date relieveDate) {
        this.relieveDate = relieveDate;
    }

    public Date getResignDate() {
        return resignDate;
    }

    public void setResignDate(Date resignDate) {
        this.resignDate = resignDate;
    }

    public String getReasonsForResign() {
        return reasonsForResign;
    }

    public void setReasonsForResign(String reasonsForResign) {
        this.reasonsForResign = reasonsForResign;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return the official email
     */
    public String getOfficialEmail() {
        return officialEmail;
    }

    /**
     * @param officialEmail the official email to set
     */
    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    /**
     * @return the emergency contact no
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * @param emergencyContact the emergencyContact to set
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    /**
     * @return the passportNo
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * @param passportNo the passport no to set
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @return the leaves
     */
    public Leaves getLeaves() {
        return leaves;
    }

    /**
     * @param leaves the leaves
     */
    public void setLeaves(Leaves leaves) {
        this.leaves = leaves;
    }

    /**
     * @return the empStatus
     */
    public String getEmpStatus() {
        return empStatus;
    }

    /**
     * @param empStatus the empStatus
     */
    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }


    public EmployeeSettlement getEmployeeSettlement() {
        return employeeSettlement;
    }

    public void setEmployeeSettlement(EmployeeSettlement employeeSettlement) {
        this.employeeSettlement = employeeSettlement;
    }

    public Nominee getNominee() {
        return nominee;
    }

    public void setNominee(Nominee nominee) {
        this.nominee = nominee;
    }


    public String getUanNumber() {
        return uanNumber;
    }

    public void setUanNumber(String uanNumber) {
        this.uanNumber = uanNumber;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tempAddress='" + tempAddress + '\'' +
                ", perAddress='" + perAddress + '\'' +
                ", middleName='" + middleName + '\'' +
                ", mobileNo=" + mobileNo +
                ", joinDate=" + joinDate +
                ", relieveDate=" + relieveDate +
                ", resignDate=" + resignDate +
                ", reasonsForResign='" + reasonsForResign + '\'' +
                ", panNo='" + panNo + '\'' +
                ", pfNo='" + pfNo + '\'' +
                ", esiNo='" + esiNo + '\'' +
                ", aadharNo='" + aadharNo + '\'' +
                ", bankAc='" + bankAc + '\'' +
                ", designation='" + designation + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", fullName='" + fullName + '\'' +
                ", empInfo='" + empInfo + '\'' +
                ", level='" + level + '\'' +
                ", bankName='" + bankName + '\'' +
                ", dob=" + dob +
                ", empStatus='" + empStatus + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", officialEmail='" + officialEmail + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", passportNo='" + passportNo + '\'' +
                ", uanNumber='" + uanNumber + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                ", currentSalary=" + currentSalary +
                ", leaves=" + leaves +
                ", employeeSettlement=" + employeeSettlement +
                ", nominee=" + nominee +
                '}';
    }
}
