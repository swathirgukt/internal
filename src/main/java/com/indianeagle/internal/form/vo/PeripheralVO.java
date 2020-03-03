package com.indianeagle.internal.form.vo;

import java.util.Date;

/**
 * Peripheral entity.
 * @author SVK
 */
public class PeripheralVO {

	private Long id;
	private String peripheralName;
	private String type;
	private String brand;
	private String model;
	private String serialNumber;
	private Date yearOfPurchase;
	private Date warrantyDate;
	private String users;
	private String status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
		return "PeripheralVO{" +
				"id=" + id +
				", peripheralName='" + peripheralName + '\'' +
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
