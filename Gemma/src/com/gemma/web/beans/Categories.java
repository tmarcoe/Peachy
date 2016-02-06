package com.gemma.web.beans;

import java.io.Serializable;


public class Categories implements Serializable {
	private static final long serialVersionUID = 1L;
	private String category;
	private String subCategory;
	
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
