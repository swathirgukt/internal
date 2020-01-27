package com.indianeagle.internal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "Please provide peripheral name")
    private String peripheralName;

    @Column(name = "TYPE")
    @NotBlank(message = "Please provide peripheral type")
    private String type;

    @Column(name = "BRAND")
    @NotBlank(message = "Please provide peripheral brand")
    private String brand;

    @Column(name = "MODEL")
    @NotBlank(message = "Please provide peripheral model")
    private String model;

    @Column(name = "SERIAL_NUMBER")
    @NotBlank(message = "Please provide serial number")
    private String serialNumber;

    @Column(name = "PURCHASE_DATE")
    @NotNull(message = "Please provide purchase date")
    private Date yearOfPurchase;

    @Column(name = "WARRANTY_DATE")
    @NotNull(message = "Please provide warranty date")
    private Date warrantyDate;

    @Column(name = "USERS")
    private String users;

    @Column(name = "STATUS")
    @NotBlank(message = "Please provide peripheral status")
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

    @Override
    public String toString() {
        return "Peripheral{" +
                "peripheralName='" + peripheralName + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", yearOfPurchase=" + yearOfPurchase +
                ", warrantyDate=" + warrantyDate +
                ", users='" + users + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
