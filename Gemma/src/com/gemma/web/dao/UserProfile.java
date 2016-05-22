/**
 * 
 */
package com.gemma.web.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author Timothy Marcoe
 *
 */
@Entity
public class UserProfile {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	int userID;
	
	@NotBlank
	@Size(min=2, max=15)
	private String firstname;
	@NotBlank
	@Size(min=2, max=15)
	private String lastname;
	private String maleFemale;
	@NotBlank
	private String address1;
	private String address2;
	@NotBlank
	private String city;
	@NotBlank
	private String region;
	@NotBlank
	private String postalCode;
	@NotBlank
	private String country;
	
	private String homePhone;
	private String cellPhone;
	@NotBlank
	@Email 
	private String username;
	@NotBlank
	@Size(min = 6, max = 15)
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
