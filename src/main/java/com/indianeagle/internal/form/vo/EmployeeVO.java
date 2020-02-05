package com.indianeagle.internal.form.vo;

import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.util.Date;

public class EmployeeVO {

	private Long Id;
	private String empId;
	private String firstName;
	private String lastName;
	private String tempAddress;
	private String perAddress;
	private String middleName;
	private Long mobileNo;
	private Date joinDate;
	private Date relieveDate;
	private Date resignDate;
	private String reasonsForResign;
	private String panNo;
	private String pfNo;
	private BigInteger uan;
	private String esiNo;
	private String aadharNo;
	private String bankAc;
	private String designation;
	private String personalEmail;
	private String fullName;
	private String empInfo;
	private String level;
	private String bankName;
	private Date dob;
    private String empStatus;
    private String maritalStatus;
    private String officialEmail;
    private String emergencyContact;
    private String passportNo;
    private String uanNumber;

	private Salary salary;
	private DepartmentVO department;
	private SalaryHistoryVO currentSalary;
    private Leaves leaves;
    private EmployeeSettlement employeeSettlement;
    private Nominee nominee;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		Id = id;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
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
	 * @param lastName
	 *            the lastName to set
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
	 * @param tempAddress
	 *            the tempAddress to set
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
	 * @param perAddress
	 *            the perAddress to set
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
	 * @param middleName
	 *            the middleName to set
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
	 * @param mobileNo
	 *            the mobileNo to set
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
	 * @param panNo
	 *            the panNo to set
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
	 * @param pfNo
	 *            the pfNo to set
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
	 * @param esiNo
	 *            the esiNo to set
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
	 * @param designation
	 *            the designation to set
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
	public DepartmentVO getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(DepartmentVO department) {
		this.department = department;
	}

	/**
	 * @return the salary
	 */
	public Salary getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
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

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public SalaryHistoryVO getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(SalaryHistoryVO currentSalary) {
		this.currentSalary = currentSalary;
	}

	/**
	 * @return the empInfo
	 */
	public String getEmpInfo() {
		return  this.empInfo = this.empId+"_"+this.firstName+" "+this.lastName;
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

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
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
     *
     * @return the leaves
     */
    public Leaves getLeaves() {
        return leaves;
    }

    /**
     *
     * @param leaves the leaves
     */
    public void setLeaves(Leaves leaves) {
        this.leaves = leaves;
    }

    /**
     *
     * @return the empStatus
     */
    public String getEmpStatus() {
        return empStatus;
    }

    /**
     *
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
		return "EmployeeVO{" +
				"Id=" + Id +
				", empId='" + empId + '\'' +
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
				", uan=" + uan +
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
