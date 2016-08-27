package com.peachy.web.beans;

public class Audit {
	Double debitColumn;
	Double creditColumn;
 
	public Audit() {
		super();
	}

	public Audit(Double debitColumn, Double creditColumn) {
		super();
		this.debitColumn = debitColumn;
		this.creditColumn = creditColumn;
	}


	public Double getDebitColumn() {
		return debitColumn;
	}
	public void setDebitColumn(Double debitColumn) {
		this.debitColumn = debitColumn;
	}
	public Double getCreditColumn() {
		return creditColumn;
	}
	public void setCreditColumn(Double creditColumn) {
		this.creditColumn = creditColumn;
	}

}
