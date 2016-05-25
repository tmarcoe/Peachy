package com.gemma.web.payment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import com.gemma.web.beans.BeansHelper;
import com.gemma.web.beans.FileLocations;
import com.gemma.web.dao.InvoiceHeader;

public class Checkout {
	private final String sevenConnectFile = "sevenConnect.properties";

	public boolean sevenConnect(InvoiceHeader header, Payment payment ) throws URISyntaxException, IOException {
		PaymentObject po = new PaymentObject();
		Properties prop = getProperties();
		po.merchantID(prop.getProperty("merchantID"));
		po.merchantRef(prop.getProperty("merchantRef"));
		po.amount(header.getTotal() + header.getTotalTax() + header.getShippingCost() + header.getAddedCharges());
		po.successURL(prop.getProperty("successURL"));
		po.failURL(prop.getProperty("failURL"));
		po.token("628e936f45884030ac1f34bcde9c28efa6ae9c839623b45b8942bd4490e1f05d");
		po.transactionDescription("");
		po.receiptRemarks(buildAddress(payment));
		po.email(payment.getUsername());
		po.returnPaymentDetails(false);
		HttpClient httpClient = HttpClientBuilder.create().build();
		JSONObject pmntObject = po.getPmntObject();
		StringEntity params =new StringEntity(pmntObject.toString());
		HttpPost request = new HttpPost(prop.getProperty("transactURL"));
		request.addHeader("content-type", "application/x-www-form-urlencoded");
		request.setEntity(params);
		@SuppressWarnings("unused")
		HttpResponse response = httpClient.execute(new HttpPost(prop.getProperty("inquireURL")));
		
		return false;
	}
	private String buildAddress(Payment payment) {
		String result = payment.getFirstName() + " " + 
						payment.getLastName() + "\n" +
						payment.getAddress1() + "\n" +
						payment.getAddress2() + "\n" +
						payment.getCity() + ", " + payment.getRegion() + " " + payment.getPostal();
		
		return result;
	}
	
	private Properties getProperties() throws URISyntaxException, IOException {

		FileLocations fl = (FileLocations) new BeansHelper().getBean("file-context.xml", "fileLocations");
		Reader fr = new FileReader(new File(new URI(fl.getPaymentConfig() + sevenConnectFile)));
		Properties prop = new Properties();
		prop.load(fr);
		
		return prop;
	}

}
