package com.gemma.spring.web.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePicker {
	private Date start;
	private Date end;
	private SimpleDateFormat sf;
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public SimpleDateFormat getSf() {
		return sf;
	}
	public void setSf(SimpleDateFormat sf) {
		this.sf = sf;
	}
	

}
