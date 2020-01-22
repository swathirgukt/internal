package com.indianeagle.internal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseDto {

    @Column(name = "DEPT_NO")
    @NotBlank(message = "Please provide department number")
    private String deptNo;

    @Column(name = "DEPARTMENT")
    @NotBlank(message = "Please provide department name")
    private String department;

    @Column(name = "MNGR_NO")
    @NotNull(message = "Please provide manager number")
    private Long mngrNo;

    @Column(name = "LOCATION")
    @NotBlank(message = "Please provide department location")
    private String location;

    @Column(name = "PHONE_NO")
    @NotNull(message = "Please provide phone number")
    private Long phoneNo;

    /**
     * @return the deptNo
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * @param deptNo the deptNo to set
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the mngrNo
     */
    public Long getMngrNo() {
        return mngrNo;
    }

    /**
     * @param mngrNo the mngrNo to set
     */
    public void setMngrNo(Long mngrNo) {
        this.mngrNo = mngrNo;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the phoneNo
     */
    public Long getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }
}
