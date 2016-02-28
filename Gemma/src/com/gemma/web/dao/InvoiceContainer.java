package com.gemma.web.dao;

import java.util.List;

public class InvoiceContainer {
	
	private InvoiceHeader invoiceHeader;
	private List<InvoiceItem> invoiceList;
	
	
	/**
	 * Default constructor (to make it a bean).
	 */
	public InvoiceContainer() {
		super();
	}


	/**
	 * @param invoiceHeader
	 * @param items
	 */
	public InvoiceContainer(InvoiceHeader invoiceHeader, List<InvoiceItem> items) {
		this.invoiceHeader = invoiceHeader;
		this.invoiceList = items;
	}
	
	
	public InvoiceHeader getInvoiceHeader() {
		return invoiceHeader;
	}
	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}


	public List<InvoiceItem> getInvoiceList() {
		return invoiceList;
	}


	public void setInvoiceList(List<InvoiceItem> invoiceList) {
		this.invoiceList = invoiceList;
	}
	

}
