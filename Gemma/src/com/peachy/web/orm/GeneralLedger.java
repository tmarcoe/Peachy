/**
 * 
 */
package com.peachy.web.orm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GeneralLedger {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long entryNum;
	private String accountNum;
	private int userID;
	private Date entryDate;
	private String description;
	private float debitAmt;
	private float creditAmt;
	
	public GeneralLedger(String accountNum, int userID, Date entryDate,
			String description, float debitAmt, float creditAmt) {
		super();
		this.accountNum = accountNum;
		this.userID = userID;
		this.entryDate = entryDate;
		this.description = description;
		this.debitAmt = debitAmt;
		this.creditAmt = creditAmt;
	}
	
	public GeneralLedger() {
		super();
	}

	public long getEntryNum() {
		return entryNum;
	}
	public void setEntryNum(long entryNum) {
		this.entryNum = entryNum;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getDebitAmt() {
		return debitAmt;
	}
	public void setDebitAmt(float debitAmt) {
		this.debitAmt = debitAmt;
	}
	public float getCreditAmt() {
		return creditAmt;
	}
	public void setCreditAmt(float creditAmt) {
		this.creditAmt = creditAmt;
	}
	
	
	

}
