package com.gemma.web.dao;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Invoice implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer invoiceNum;
	
	
	private String skuNum;
	
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
	
}
