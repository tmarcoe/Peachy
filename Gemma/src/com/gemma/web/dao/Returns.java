package com.gemma.web.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Returns {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int rmaId;
	private Integer invoiceNum;
	private String skuNum;
	private Double purchasePrice;
	private Double purchaseTax;
	private Integer amtReturned;
	private Date datePurchased;
	private Date dateReturned;
	private Date dateProcessed;
	private String reason;
	private boolean returnToStock;
		
	public int getRmaId() {
		return rmaId;
	}

	public void setRmaId(int rmaId) {
		this.rmaId = rmaId;
	}

	public Integer getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(Integer invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public String getSkuNum() {
		return skuNum;
	}

	public void setSkuNum(String skuNum) {
		this.skuNum = skuNum;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getPurchaseTax() {
		return purchaseTax;
	}

	public void setPurchaseTax(Double purchaseTax) {
		this.purchaseTax = purchaseTax;
	}

	public Integer getAmtReturned() {
		return amtReturned;
	}

	public void setAmtReturned(Integer amtReturned) {
		this.amtReturned = amtReturned;
	}

	public Date getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	public Date getDateProcessed() {
		return dateProcessed;
	}

	public void setDateProcessed(Date dateProcessed) {
		this.dateProcessed = dateProcessed;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isReturnToStock() {
		return returnToStock;
	}

	public void setReturnToStock(boolean returnToStock) {
		this.returnToStock = returnToStock;
	}
}
