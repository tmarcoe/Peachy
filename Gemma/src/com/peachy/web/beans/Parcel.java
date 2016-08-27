package com.peachy.web.beans;

public class Parcel {
	private double length;
	private double width;
	private double height;
	private double weight;
	private String unit;
	private String serviceType;
	private String dropoffType;
	private String packagingType;
	private String transactionID;
	
	//Defaults
	private final String defServiceType = "FEDEX_FREIGHT_ECONOMY";
	private final String defDropoffType = "REGULAR_PICKUP";
	private final String defPackagingType = "FEDEX_BOX";
	
	public Parcel() {
		super();
		serviceType = defServiceType;
		dropoffType = defDropoffType;
		packagingType = defPackagingType;
	}

	public double getLength() {
		return length;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public String getDropoffType() {
		return dropoffType;
	}
	
	public void setDropoffType(String dropoffType) {
		this.dropoffType = dropoffType;
	}
	
	public String getPackagingType() {
		return packagingType;
	}
	
	public void setPackagingType(String packagingType) {
		this.packagingType = packagingType;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

}
