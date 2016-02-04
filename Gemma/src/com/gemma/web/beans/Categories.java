package com.gemma.web.beans;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Categories {
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
