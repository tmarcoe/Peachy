package com.gemma.web.dao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class InvoiceItem {
	
	@EmbeddedId
	private Invoice invoiceKey;
	
	@Min(1)
	@NotNull 
	private int amount;
	/*
	 * The following is from the Iventory table
	 */
	private String productName;
	private String description;
	private String image;
	private float price;
	private float tax;
	private int amtInStock;
	private float weight;
	private boolean disableCoupons;

	/**
	 * 
	 */
	public InvoiceItem(Inventory inventory) {
		super();
		invoiceKey = new Invoice();
		invoiceKey.setSkuNum(inventory.getSkuNum());
		productName = inventory.getProductName();
		description = inventory.getDescription();
		image = inventory.getImage();
		amtInStock = inventory.getAmtInStock();
		if (inventory.isOnSale() == true){
			price = inventory.getDiscountPrice();
		}else{
			price = inventory.getSalePrice();
		}
		tax = inventory.getTaxAmt();
		weight = inventory.getWeight();
	}
	public Invoice getInvoiceKey() {
		return invoiceKey;
	}
	public void setInvoiceKey(Invoice invoiceKey) {
		this.invoiceKey = invoiceKey;
	}
	/**
	 * 
	 */
	public InvoiceItem() {
		invoiceKey = new Invoice();
	}	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public int getAmtInStock() {
		return amtInStock;
	}
	public void setAmtInStock(int amtInStock) {
		this.amtInStock = amtInStock;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public Integer getInvoiceNum() {
		return invoiceKey.getInvoiceNum();
	}
	public void setInvoiceNum(Integer invoiceNum) {
		this.invoiceKey.setInvoiceNum(invoiceNum);
	}
	public String getSkuNum() {
		return invoiceKey.getSkuNum();
	}
	public void setSkuNum(String itemNum) {
		this.invoiceKey.setSkuNum(itemNum);
	}
	public boolean isDisableCoupons() {
		return disableCoupons;
	}
	public void setDisableCoupons(boolean disableCoupons) {
		this.disableCoupons = disableCoupons;
	}


}
