package com.gemma.web.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InventoryInfo {
	
	@Id
	private String skuNum;
	private String color;
	private String size;
	private String version;
	/************************
	 * deliveryMethod:
	 * 'M' = mail
	 * 'D' = download
	 * 'P' = print
	 ************************/
	private char deliveryMethod;
	
	
	public String getSkuNum() {
		return skuNum;
	}
	public void setSkuNum(String skuNum) {
		this.skuNum = skuNum;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public char getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(char deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	} 
}
