package com.gemma.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Invoice implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer invoiceNum;
	private Integer itemNum;
	
	public Integer getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(Integer invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public Integer getItemNum() {
		return itemNum;
	}
	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

}
