/**
 * 
 */
package com.peachy.web.orm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvoiceHeader {
	

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer invoiceNum;
	private int userID;
	private double total;
	private double totalTax;
	private double shippingCost;
	private double addedCharges;
	private String paymentType;
	private boolean pod;
	private String paymentNumber;
	private boolean disableCoupons;
	private Date modified;
	private Date processed;
	private Date dateShipped;


	public Integer getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(Integer invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public Date getDateShipped() {
		return dateShipped;
	}
	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public double getAddedCharges() {
		return addedCharges;
	}
	public void setAddedCharges(double addedCharges) {
		this.addedCharges = addedCharges;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public boolean isPod() {
		return pod;
	}
	public void setPod(boolean pod) {
		this.pod = pod;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public boolean isDisableCoupons() {
		return disableCoupons;
	}
	public void setDisableCoupons(boolean disableCoupons) {
		this.disableCoupons = disableCoupons;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getProcessed() {
		return processed;
	}
	public void setProcessed(Date processed) {
		this.processed = processed;
	}
	@Override
	public String toString() {
		return "InvoiceHeader [invoiceNum=" + invoiceNum + ", userID=" + userID
				+ ", total=" + total + ", totalTax=" + totalTax
				+ ", shippingCost=" + shippingCost + ", modified=" + modified
				+ ", processed=" + processed + ", dateShipped=" + dateShipped
				+ "]";
	}

}
