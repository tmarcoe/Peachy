package com.gemma.web.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
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
	
	public String getRecord(String base) throws IOException, JSONException {
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
	
	public String getSymbol(String currency) {
		String symbol = "";
		
		switch (currency) {
		case "AUD":
			symbol = "$";
			break;
		case "BGN":
			symbol = "лв";
			break;
		case "BRL":
			symbol = "R$";
			break;
		case "CAD":
			symbol = "$";
			break;
		case "CHF":
			symbol = "CHF";
			break;
		case "CNY":
			symbol = "¥";
			break;
		case "CZK":
			symbol = "Kč";
			break;
		case "DKK":
			symbol = "kr";
			break;
		case "GBP":
			symbol = "£";
			break;
		case "HKD":
			symbol = "$";
			break;
		case "HRK":
			symbol = "kn";
			break;
		case "HUF":
			symbol = "Ft";
			break;
		case "IDR":
			symbol = "Rp";
			break;
		case "ILS":
			symbol = "₪";
			break;
		case "INR":
			symbol = "﷼";
			break;
		case "JPY":
			symbol = "¥";
			break;
		case "KRW":
			symbol = "₩";
			break;
		case "MXN":
			symbol = "$";
			break;
		case "MYR":
			symbol = "RM";
			break;
		case "NOK":
			symbol = "kr";
			break;
		case "NZD":
			symbol = "$";
			break;
		case "PHP":
			symbol = "₱";
			break;
		case "PLN":
			symbol = "zł";
			break;
		case "RON":
			symbol = "lei";
			break;
		case "RUB":
			symbol = "руб";
			break;
		case "SEK":
			symbol = "kr";
			break;
		case "SGD":
			symbol = "$";
			break;
		case "THB":
			symbol = "฿";
			break;
		case "TRY":
			symbol = "t";
			break;
		case "USD":
			symbol = "$";
			break;
		case "ZAR":
			symbol = "R";
			break;
		default:
			symbol = "$";
			break;				
		}
		
		return symbol;
	}
	
	public double convert(double amount, String target) throws ClientProtocolException, IOException, URISyntaxException {
		return amount * getRate("PHP", target);
	}
}
