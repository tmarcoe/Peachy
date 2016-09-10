/**
 * 
 */
package com.peachy.web.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.peachy.web.validation.FloatMin;

@Entity
public class Inventory {
	@Id
	@NotBlank
	private String skuNum;
	@Size(min=2, max=255)
	private String productName;
	@NotBlank
	private String category;
	@NotBlank
	private String subCategory;
	@NotNull
	private int amtInStock;
	@NotNull
	private int amtCommitted;
	@NotNull
	private int minQuantity;
	@NotNull
	private float salePrice;
	@NotNull
	private float discountPrice;
	@NotNull
	private float taxAmt;
	@FloatMin(.01)
	private double weight;
	private boolean onSale;
	private String image;
	@Size(min=10, max=1000)
	private String description;
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSkuNum() {
		return skuNum;
	}
	public void setSkuNum(String skuNum) {
		this.skuNum = skuNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public int getAmtInStock() {
		return amtInStock;
	}
	public void setAmtInStock(int amtInStock) {
		this.amtInStock = amtInStock;
	}
	public int getAmtCommitted() {
		return amtCommitted;
	}
	public void setAmtCommitted(int amtCommitted) {
		this.amtCommitted = amtCommitted;
	}
	public int getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}
	public float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}
	public float getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}
	public float getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(float taxAmt) {
		this.taxAmt = taxAmt;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public boolean isOnSale() {
		return onSale;
	}
	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}
	
	
		
}
