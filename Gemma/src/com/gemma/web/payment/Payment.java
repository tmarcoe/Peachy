package com.gemma.web.payment;

public class Payment {
	

	private Integer userId;
	private String	firstName;
	private String	lastName;
	private String	address1;
	private String	address2;
	private String	city;
	private String	region;
	private String	postal;
	private String	country;
	private String	paymentMethod;
	private String	paymentNum;
	private String	expiresMonth;
	private String	expiresYear;
	private String	cvc;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentNum() {
		return paymentNum;
	}
	public void setPaymentNum(String paymentNum) {
		this.paymentNum = paymentNum;
	}
	public String getExpiresMonth() {
		return expiresMonth;
	}
	public void setExpiresMonth(String expiresMonth) {
		this.expiresMonth = expiresMonth;
	}
	public String getExpiresYear() {
		return expiresYear;
	}
	public void setExpiresYear(String expiresYear) {
		this.expiresYear = expiresYear;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

}
