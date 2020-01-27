package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.util.Date;

/**
 * Peripheral entity.
 *
 * @author SVK
 */
@Entity
@Table(name = "PERIPHERAL")
public class Peripheral  extends BaseDto{

    @Column(name = "PERIPHERAL")
    private String peripheralName;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "PURCHASE_DATE")
    private Date yearOfPurchase;
    @Temporal(TemporalType.DATE)
    @Column(name = "WARRANTY_DATE")
    private Date warrantyDate;
    @Column(name = "USERS")
    private String users;
    @Column(name = "STATUS")
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getYearOfPurchase() {
        return yearOfPurchase;
    }

    public void setYearOfPurchase(Date yearOfPurchase) {
        this.yearOfPurchase = yearOfPurchase;
    }

    public Date getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(Date warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the peripheralName
     */
    public String getPeripheralName() {
        return peripheralName;
    }

    /**
     * @param peripheralName the peripheralName to set
     */
    public void setPeripheralName(String peripheralName) {
        this.peripheralName = peripheralName;
    }

}
