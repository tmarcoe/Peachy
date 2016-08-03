package com.gemma.web.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

public class CurrencyExchange {
	private String currencyRecord = null;
	
	public CurrencyExchange() throws IOException {
		super();
		currencyRecord = getRecord("PHP");
	}

	public double getRate(String base, String target) throws ClientProtocolException, IOException, URISyntaxException {
		double rate;
		
		if (base.compareTo(target) == 0) {
			return 1.0;
		}
		if (currencyRecord == null) {
			currencyRecord = getRecord(base);
		}
		JSONObject record = new JSONObject(currencyRecord);
       
        JSONObject rates = record.getJSONObject("rates");
        rate = rates.getDouble(target);
        
		return rate;
	}
	
	public String getRecord(String base) throws IOException {
		URL currency = new URL("http://api.fixer.io/latest?base=" + base);
        
        BufferedReader in = new BufferedReader(
        new InputStreamReader(currency.openStream()));

        String inputLine;
        String buff = "";
        while ((inputLine = in.readLine()) != null)
        	buff = buff + inputLine;
        in.close();
        
        return buff;
	}
	
	
	public double convert(double amount, String target) throws ClientProtocolException, IOException, URISyntaxException {
		return amount * getRate("PHP", target);
	}
}
