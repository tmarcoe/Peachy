/**
 * 
 */
package com.gemma.spring.web.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Timothy Marcoe
 *
 */
@Entity
public class InvoiceHeader {
	

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer invoiceNum;
	private int userID;
	private double total;
	private double totalTax;
	private float shippingCost;
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
	public float getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
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
