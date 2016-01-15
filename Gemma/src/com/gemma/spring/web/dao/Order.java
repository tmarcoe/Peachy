/**
 * 
 */
package com.gemma.spring.web.dao;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Timothy Marcoe
 *
 */
@Entity
public class Order {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int orderNum;
	private int skuNum;
	private float orderPrice;
	private String productVendor;
	private String productBuyer;
	private int amtOrdered;
	private int amtReceived;
	private float shippingCost;
	private Date dateOrdered;
	private Date dateRecieved;
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getSkuNum() {
		return skuNum;
	}
	public void setSkuNum(int skuNum) {
		this.skuNum = skuNum;
	}
	public float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getProductVendor() {
		return productVendor;
	}
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	public String getProductBuyer() {
		return productBuyer;
	}
	public void setProductBuyer(String productBuyer) {
		this.productBuyer = productBuyer;
	}
	public int getAmtOrdered() {
		return amtOrdered;
	}
	public void setAmtOrdered(int amtOrdered) {
		this.amtOrdered = amtOrdered;
	}
	public int getAmtReceived() {
		return amtReceived;
	}
	public void setAmtReceived(int amtReceived) {
		this.amtReceived = amtReceived;
	}
	public float getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}
	public Date getDateOrdered() {
		return dateOrdered;
	}
	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	public Date getDateRecieved() {
		return dateRecieved;
	}
	public void setDateRecieved(Date dateRecieved) {
		this.dateRecieved = dateRecieved;
	}

}
