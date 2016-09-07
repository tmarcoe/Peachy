package com.peachy.web.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsedCoupons {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long entry;
	private int userID;
	private String couponID;
	
	public long getEntry() {
		return entry;
	}
	public void setEntry(long entry) {
		this.entry = entry;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getCouponID() {
		return couponID;
	}
	public void setCouponID(String couponID) {
		this.couponID = couponID;
	}
	
}
