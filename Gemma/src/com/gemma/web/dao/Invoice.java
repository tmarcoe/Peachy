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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((invoiceNum == null) ? 0 : invoiceNum.hashCode());
		result = prime * result + ((skuNum == null) ? 0 : skuNum.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (invoiceNum == null) {
			if (other.invoiceNum != null)
				return false;
		} else if (!invoiceNum.equals(other.invoiceNum))
			return false;
		if (skuNum == null) {
			if (other.skuNum != null)
				return false;
		} else if (!skuNum.equals(other.skuNum))
			return false;
		return true;
	}
	
	
}
