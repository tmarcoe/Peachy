package com.gemma.web.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Coupons {

	
	@Id
	@NotBlank
	private String couponNumber;
	private String name;
	private String description;
	private Date expires;
	private boolean active;
	private String ruleName;
	
	
	public String getCouponNumber() {
		return couponNumber;
	}
	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

}
