package com.peachy.web.beans;

import java.io.Serializable;

public class FileLocations implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String imageLoc;
	private String imgUploadLoc;
	private String outPath;
	private String transactionPath;
	private String paymentConfig;
	private String emailConfig;
	private String couponPath;
	private String emailPath;
	private String currencyFile;

	public String getImageLoc() {
		return imageLoc;
	}

	public void setImageLoc(String imageLoc) {
		this.imageLoc = imageLoc;
	}

	public String getImgUploadLoc() {
		return imgUploadLoc;
	}

	public void setImgUploadLoc(String imgUploadLoc) {
		this.imgUploadLoc = imgUploadLoc;
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

	public String getCouponPath() {
		return couponPath;
	}

	public void setCouponPath(String couponPath) {
		this.couponPath = couponPath;
	}

	public String getEmailPath() {
		return emailPath;
	}

	public void setEmailPath(String emailPath) {
		this.emailPath = emailPath;
	}

	public String getCurrencyFile() {
		return currencyFile;
	}

	public void setCurrencyFile(String currencyFile) {
		this.currencyFile = currencyFile;
	}

}
