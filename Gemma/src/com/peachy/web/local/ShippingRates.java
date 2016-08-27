package com.peachy.web.local;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.peachy.web.beans.BeansHelper;
import com.peachy.web.beans.LocalValues;
import com.peachy.web.beans.Parcel;
import com.peachy.web.beans.ShippingAddress;

public class ShippingRates {
	private LocalValues localValues;
	
	public ShippingRates() {
		super();
		localValues = (LocalValues) new BeansHelper().getBean("config-context.xml", "localValues");
	}

	public double getShippingRate(ShippingAddress from, ShippingAddress to, Parcel parcel) throws SOAPException, IOException {
		double shippingCost = 0;
		final String fileLoc = "C:\\Users\\Timothy Marcoe\\shippinglibs\\RateReply.xml";
		
		SOAPConnection conn = getSOAPConnection();
		
        String url = localValues.getMailRateURL();
        SOAPMessage soapResponse = conn.call(getMessage(from, to, parcel), url);
        
       
        System.out.println();
        if (hasErrors(soapResponse) ==  true ){
        	soapResponse = stringToSOAP(fileLoc);
        	soapResponse.writeTo(System.out);
        	System.out.println();
        }

        shippingCost = parseRate(soapResponse);
        conn.close();
        
       return shippingCost; 
	}
	
	public SOAPConnection getSOAPConnection() throws SOAPException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        
        return soapConnection;
	}
	
	private SOAPMessage getMessage(ShippingAddress from, ShippingAddress to, Parcel parcel) throws SOAPException, IOException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage msg = messageFactory.createMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        sdf.setTimeZone(TimeZone.getTimeZone(localValues.getLocalTZ()));

		SOAPPart soapPart = msg.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addAttribute(new QName("xmlns"), localValues.getNs());
		envelope.addNamespaceDeclaration("SOAP-ENC", localValues.getSoapEncNs());
		envelope.addNamespaceDeclaration("xsd", localValues.getXsdNs());
		envelope.addNamespaceDeclaration("xsi", localValues.getXsiNs());

		//Root Node
		SOAPBody soapBody = envelope.getBody();
		SOAPElement rateRequest = soapBody.addChildElement("RateRequest", "V20", localValues.getNs());

		//Authentication
		SOAPElement webAuthenticationDetail = rateRequest.addChildElement("WebAuthenticationDetail");
		SOAPElement userCredential = webAuthenticationDetail.addChildElement("UserCredential");
		SOAPElement key = userCredential.addChildElement("key");
		key.addTextNode(localValues.getShippingKey());
		SOAPElement password = userCredential.addChildElement("Password");
		password.addTextNode(localValues.getShippingPassword());
		
		//Account Information
		SOAPElement clientDetail = rateRequest.addChildElement("ClientDetail");
		SOAPElement accountNumber = clientDetail.addChildElement("AccountNumber");
		accountNumber.addTextNode(localValues.getAccountNumber());
		SOAPElement meterNumber = clientDetail.addChildElement("MeterNumber");
		meterNumber.addTextNode(localValues.getMeterNumber());
		
		//Transaction Information
		SOAPElement transactionDetail = rateRequest.addChildElement("TransactionDetail");
		SOAPElement customerTransactionId = transactionDetail.addChildElement("CustomerTransactionId");
		customerTransactionId.addTextNode(parcel.getTransactionID());
		SOAPElement version = rateRequest.addChildElement("Version");
		SOAPElement serviceId = version.addChildElement("ServiceId");
		serviceId.addTextNode("crs");
		SOAPElement major = version.addChildElement("Major");
		major.addTextNode("20");
		SOAPElement intermediate = version.addChildElement("Intermediate");
		intermediate.addTextNode("0");
		SOAPElement minor = version.addChildElement("Minor");
		minor.addTextNode("0");
		
		//Shipment Information
		SOAPElement requestShipment = rateRequest.addChildElement("RequestShipment");
		SOAPElement shipTimestamp = requestShipment.addChildElement("ShipTimestamp");
		shipTimestamp.addTextNode(sdf.format(new Date()));
		SOAPElement preferredCurrency = requestShipment.addChildElement("PreferredCurrency");
		preferredCurrency.addTextNode(localValues.getDefaultCurrency());
		
		//Shipper Information
		SOAPElement shipper = requestShipment.addChildElement("Shipper");
		SOAPElement contact = shipper.addChildElement("Contact");
		SOAPElement companyName = contact.addChildElement("CompanyName");
		companyName.addTextNode(localValues.getCompanyName());
		SOAPElement phoneNumber = contact.addChildElement("PhoneNumber");
		phoneNumber.addTextNode(from.getPhone());
		
		//From Address
		SOAPElement address = shipper.addChildElement("Address");
		SOAPElement streetLines1 = address.addChildElement("StreetLines");
		streetLines1.addTextNode(from.getAddress1());
		SOAPElement streetLines2 = address.addChildElement("StreetLines");
		streetLines2.addTextNode(from.getAddress2());
		SOAPElement city = address.addChildElement("City");
		city.addTextNode(from.getCity());
		SOAPElement stateOrProvinceCode = address.addChildElement("StateOrProvinceCode");
		stateOrProvinceCode.addTextNode(from.getRegion());
		SOAPElement postalCode = address.addChildElement("PostalCode");
		postalCode.addTextNode(from.getPostalCode());
		SOAPElement countryCode = address.addChildElement("CountryCode");
		countryCode.addTextNode(from.getCountry());
		
		//To Address
		SOAPElement recipient = requestShipment.addChildElement("Recipient");
		contact = recipient.addChildElement("Contact");
		address = recipient.addChildElement("Address");
		streetLines1 = address.addChildElement("StreetLines");
		streetLines1.addTextNode(to.getAddress1());
		streetLines2 = address.addChildElement("StreetLines");
		streetLines2.addTextNode(to.getAddress2());
		city = address.addChildElement("City");
		city.addTextNode(to.getCity());
		stateOrProvinceCode = address.addChildElement("StateOrProvinceCode");
		stateOrProvinceCode.addTextNode(to.getRegion());
		postalCode = address.addChildElement("PostalCode");
		postalCode.addTextNode(to.getPostalCode());
		countryCode = address.addChildElement("CountryCode");
		countryCode.addTextNode(to.getCountry());
		
		return msg;
        
	}
	
	public boolean hasErrors(SOAPMessage soapResponse) throws SOAPException{
		boolean result = false;
		
		SOAPBody soapBody = soapResponse.getSOAPBody();
		NodeList nl = soapBody.getElementsByTagName("v20:Severity");
		if (nl.getLength() > 0) {
			Node n = nl.item(0);
			String t = n.getTextContent();
			if ("ERROR".compareTo(t) == 0) {
				result = true;
			}
		}
		
		return result;
	}
	
	public SOAPMessage stringToSOAP(String fileLoc) throws IOException, SOAPException {
		InputStream is = new FileInputStream(fileLoc);
		SOAPMessage result = MessageFactory.newInstance().createMessage(null, is);
		
		return result;
	}
	
	public double parseRate(SOAPMessage soapResponse) throws SOAPException {
		
		SOAPBody soapBody = soapResponse.getSOAPBody();
		
		Double baseCharge = totalAmount("TotalBaseCharge", soapBody);
		Double discount = totalAmount("TotalFreightDiscounts", soapBody);
		Double surcharge = totalAmount("TotalSurcharges", soapBody);
		Double tax = totalAmount("TotalTaxes", soapBody);
		Double rebate = totalAmount("TotalRebates", soapBody);
		Double ancillaryFees = totalAmount("TotalAncillaryFeesAndTaxes", soapBody);
		Double duty = totalAmount("TotalDutiesTaxesAndFees", soapBody);
				
		return (baseCharge + surcharge + tax + ancillaryFees + duty) - (discount + rebate);
	}
	
	private Double totalAmount(String parentNode, SOAPBody soapBody) {
		Double total = 0.0;
		NodeList nl = soapBody.getElementsByTagName(parentNode);
		Node item;
		Node itm;

		item = nl.item(0);
		NodeList n = item.getChildNodes();
		for (int j=0; j < n.getLength();j++) {
			itm = n.item(j);
			if (itm.getNodeName().compareTo("Amount") == 0) {
				total += Double.valueOf(itm.getTextContent());
			}

		}
		
		return total;
	}

}
