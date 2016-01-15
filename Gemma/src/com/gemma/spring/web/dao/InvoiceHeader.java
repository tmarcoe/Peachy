/**
 * 
 */
package com.gemma.spring.web.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Timothy Marcoe
 *
 */
@Entity
public class InvoiceHeader {
	
	@Id
	private long invoiceNum;
	private int userID;
	private Date dateShipped;
	private float shippingCost;
	public long getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(long invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	
	

}
