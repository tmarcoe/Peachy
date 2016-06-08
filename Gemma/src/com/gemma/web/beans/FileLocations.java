package com.gemma.web.beans;

public class FileLocations {
	private String imageLoc;
	private String outPath;
	private String transactionPath;
	private String paymentConfig;
	private String emailConfig;

	public String getImageLoc() {
		return imageLoc;
	}

	public void setImageLoc(String imageLoc) {
		this.imageLoc = imageLoc;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	public String getTransactionPath() {
		return transactionPath;
	}

	public void setTransactionPath(String transactionPath) {
		this.transactionPath = transactionPath;
	}

	public String getPaymentConfig() {
		return paymentConfig;
	}

	public void setPaymentConfig(String paymentConfig) {
		this.paymentConfig = paymentConfig;
	}

	public String getEmailConfig() {
		return emailConfig;
	}

	public void setEmailConfig(String emailConfig) {
		this.emailConfig = emailConfig;
	}

}
