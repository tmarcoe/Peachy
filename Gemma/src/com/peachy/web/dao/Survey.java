package com.peachy.web.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Survey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	int key;
	int userID;
	
	//Did you find the item you wanted?
	private String question1;
	//What product were you looking for?
	private String question2;
	//On a scale of 1 to 10 (10 = very likely, 1 = not likely at all), how likely would you recommend this website?
	private int question3;
	//What is the reason for this rating?
	private String question4;
	//On a scale of 1 to 10 (10 = very easy, 1 = very difficult), how difficult was it to navigate the store?
	private int question5;
	//Any suggestions on how we could improve website navigation?
	private String question6;
	//On a scale of 1 to 10 (10 = very good, 1 = very bad), how did you like our prices?
	private int question7;
	//Any comments or suggestions on how we could improve our website?
	private String question8;
	//The Date that the Survey was filled out.
	private Date filledOut;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getQuestion1() {
		return question1;
	}
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	public String getQuestion2() {
		return question2;
	}
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}
	public int getQuestion3() {
		return question3;
	}
	public void setQuestion3(int question3) {
		this.question3 = question3;
	}
	public String getQuestion4() {
		return question4;
	}
	public void setQuestion4(String question4) {
		this.question4 = question4;
	}
	public int getQuestion5() {
		return question5;
	}
	public void setQuestion5(int question5) {
		this.question5 = question5;
	}
	public String getQuestion6() {
		return question6;
	}
	public void setQuestion6(String question6) {
		this.question6 = question6;
	}
	public int getQuestion7() {
		return question7;
	}
	public void setQuestion7(int question7) {
		this.question7 = question7;
	}
	public String getQuestion8() {
		return question8;
	}
	public void setQuestion8(String question8) {
		this.question8 = question8;
	}
	public Date getFilledOut() {
		return filledOut;
	}
	public void setFilledOut(Date filledOut) {
		this.filledOut = filledOut;
	}

	
}
