package com.peachy.web.payment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONObject;

public class PaymentObject {
	private JSONObject pmntObject = new JSONObject();

	
	public JSONObject merchantID(String id) {
		
		return pmntObject.put("merchantID", id);
	}
	
	public JSONObject merchantRef(String ref) {
		return pmntObject.put("merchantRef", ref);
	}

	public JSONObject amount(Double amt) {
		return pmntObject.put("amount", amt);
	}
	
	public JSONObject successURL(String url) {
		return pmntObject.put("successURL", url);
	}
	
	public JSONObject failURL(String url) {
		return pmntObject.put("failURL", url);
	}
	
	public JSONObject token(String tkn) {
		return pmntObject.put("token", tkn);
	}
	
	public JSONObject transactionDescription(String desc) {
		return pmntObject.put("transactionDescription", desc);
	}
	
	public JSONObject receiptRemarks(String remarks) {
		return pmntObject.put("receiptRemarks", remarks);
	}
	
	public JSONObject email(String eml) {
		return pmntObject.put("email", eml);
	}
	
	public JSONObject returnPaymentDetails(boolean details) {
		
		if (details == true ) {
			return pmntObject.put("returnPaymentDetails", "Y");
		}else{
			return pmntObject.put("returnPaymentDetails", "N");
		}
	}

	
	public String toSHA1(byte[] convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return new String(md.digest(convertme));
	}

	public JSONObject getPmntObject() {
		return pmntObject;
	}

	
}
