/**
 * 
 */
package com.peachy.web.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.peachy.web.validation.Password;
import com.peachy.web.validation.ValidEmail;

@Entity
public class UserProfile implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	int userID;

	@Size(min = 2, max = 30)
	private String firstname;
	@Size(min = 2, max = 30)
	private String lastname;
	@NotNull
	private String maleFemale;
	@NotBlank
	private String address1;
	private String address2;
	@NotBlank
	private String city;
	private String region;
	private String postalCode;
	@Size(min = 3, max = 3)
	private String country;
	@NotBlank
	private String currency;
	private String homePhone;
	private String cellPhone;
	@ValidEmail
	private String username;
	@Size(min = 8, max = 20)
	@Password
	private String password;
	private String shippingInfo;
	private boolean monthlyMailing;
	private String authority;
	private boolean enabled;
	private boolean dailySpecials;
	private Date dateAdded;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMaleFemale() {
		return maleFemale;
	}
	public void setMaleFemale(String maleFemale) {
		this.maleFemale = maleFemale;
	}
	public String getaddress1() {
		return address1;
	}
	public void setaddress1(String address1) {
		this.address1 = address1;
	}
	public String getaddress2() {
		return address2;
	}
	public void setaddress2(String address2) {
		this.address2 = address2;
	}
	public String getcity() {
		return city;
	}
	public void setcity(String city) {
		this.city = city;
	}
	public String getregion() {
		return region;
	}
	public void setregion(String region) {
		this.region = region;
	}
	public String getpostalCode() {
		return postalCode;
	}
	public void setpostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getcountry() {
		return country;
	}
	public void setcountry(String country) {
		this.country = country;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getShippingInfo() {
		return shippingInfo;
	}
	public void setShippingInfo(String shippingInfo) {
		this.shippingInfo = shippingInfo;
	}
	public boolean isMonthlyMailing() {
		return monthlyMailing;
	}
	public void setMonthlyMailing(boolean monthlyMailing) {
		this.monthlyMailing = monthlyMailing;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isDailySpecials() {
		return dailySpecials;
	}
	public void setDailySpecials(boolean dailySpecials) {
		this.dailySpecials = dailySpecials;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
}