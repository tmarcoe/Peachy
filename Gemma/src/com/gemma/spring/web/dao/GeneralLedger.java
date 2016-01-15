/**
 * 
 */
package com.gemma.spring.web.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Timothy Marcoe
 *
 */
@Entity
public class GeneralLedger {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long entryNum;
	private String accountNum;
	private Date entryDate;
	private String description;
	private float debitAmt;
	private float creditAmt;
	
	
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
