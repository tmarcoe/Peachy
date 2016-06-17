package com.gemma.web.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Coupons {

	
	@Id
	@NotBlank
	private String couponID;
	private String name;
	private String description;
	private Date expires;
	private boolean active;
	private boolean exclusive;
	private String ruleName;
	private int useage;
	
	
	public String getCouponID() {
		return couponID;
	}
	public void setCouponID(String couponNumber) {
		this.couponID = couponNumber;
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
	public boolean isExclusive() {
		return exclusive;
	}
	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public int getUseage() {
		return useage;
	}
	public void setUseage(int useage) {
		this.useage = useage;
	}

}
