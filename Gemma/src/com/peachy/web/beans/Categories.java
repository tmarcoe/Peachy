package com.peachy.web.beans;

import java.io.Serializable;


public class Categories implements Serializable {
	private static final long serialVersionUID = 1L;
	private String category;
	private String subCategory;
	
	public Categories() {
		this.category = "";
		this.subCategory = "";
	}
	
	public Categories(String category) {

		this.category = category;
		this.subCategory = "";
	}

	public Categories(String category, String subCategory) {

		this.category = category;
		this.subCategory = subCategory;
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

}
