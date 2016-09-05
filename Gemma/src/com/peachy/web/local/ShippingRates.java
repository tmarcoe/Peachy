package com.peachy.web.local;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.peachy.web.beans.BeansHelper;
import com.peachy.web.beans.LocalValues;
import com.peachy.web.beans.Parcel;
import com.peachy.web.beans.ShippingAddress;

public class ShippingRates {
	private static Logger logger = Logger.getLogger(ShippingRates.class.getName());
	private LocalValues localValues;
	
	public ShippingRates() {
		super();
		localValues = (LocalValues) new BeansHelper().getBean("config-context.xml", "localValues");
	}

	public double getShippingRate(ShippingAddress from, ShippingAddress to, Parcel parcel) throws SOAPException, IOException, URISyntaxException {
		double shippingCost = 0;
		
		SOAPConnection conn = getSOAPConnection();
		
        String url = localValues.getMailRateURL();
        SOAPMessage soapResponse = conn.call(getMessage(from, to, parcel), url);
        
        if (hasErrors(soapResponse) ==  true ){
        	logger.error("Error sending Ship Request");
        	throw new SOAPException();
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
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
		SOAPElement key = userCredential.addChildElement("Key");
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
		SOAPElement requestedShipment = rateRequest.addChildElement("RequestedShipment");
		SOAPElement shipTimestamp = requestedShipment.addChildElement("ShipTimestamp");
		shipTimestamp.addTextNode(sdf.format(new Date()));
		SOAPElement dropoffType = requestedShipment.addChildElement("DropoffType");
		dropoffType.addTextNode("REGULAR_PICKUP");
		SOAPElement serviceType = requestedShipment.addChildElement("ServiceType");
		if (to.getCountry().compareTo(from.getCountry()) != 0) {
			serviceType.addTextNode("INTERNATIONAL_ECONOMY");
		}else{
			serviceType.addTextNode("FEDEX_FREIGHT_ECONOMY");
		}
		SOAPElement packagingType = requestedShipment.addChildElement("PackagingType");
		packagingType.addTextNode("YOUR_PACKAGING");
		SOAPElement preferredCurrency = requestedShipment.addChildElement("PreferredCurrency");
		preferredCurrency.addTextNode(localValues.getDefaultCurrency());
		
		//Shipper Information
		SOAPElement shipper = requestedShipment.addChildElement("Shipper");
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
		if ("US".compareTo(from.getCountry().substring(0, 2)) == 0) {
			SOAPElement stateOrProvinceCode = address.addChildElement("StateOrProvinceCode");
			stateOrProvinceCode.addTextNode(from.getRegion());
		}
		SOAPElement postalCode = address.addChildElement("PostalCode");
		postalCode.addTextNode(from.getPostalCode());
		SOAPElement countryCode = address.addChildElement("CountryCode");
		countryCode.addTextNode(from.getCountry().substring(0, 2));
		
		//To Address
		SOAPElement recipient = requestedShipment.addChildElement("Recipient");
		contact = recipient.addChildElement("Contact");
		SOAPElement personName = contact.addChildElement("PersonName");
		personName.addTextNode(to.getFirstname() + " " + to.getLastname());
		phoneNumber = contact.addChildElement("PhoneNumber");
		phoneNumber.addTextNode(from.getPhone());
		address = recipient.addChildElement("Address");
		streetLines1 = address.addChildElement("StreetLines");
		streetLines1.addTextNode(to.getAddress1());
		streetLines2 = address.addChildElement("StreetLines");
		streetLines2.addTextNode(to.getAddress2());
		city = address.addChildElement("City");
		city.addTextNode(to.getCity());
		if ("US".compareTo(from.getCountry().substring(0, 2)) == 0) {
			SOAPElement stateOrProvinceCode = address.addChildElement("StateOrProvinceCode");
			stateOrProvinceCode.addTextNode(to.getRegion());
		}
		postalCode = address.addChildElement("PostalCode");
		postalCode.addTextNode(to.getPostalCode());
		countryCode = address.addChildElement("CountryCode");
		countryCode.addTextNode(to.getCountry().substring(0, 2));
		
		//Package Information
		SOAPElement packageCount = requestedShipment.addChildElement("PackageCount");
		packageCount.addTextNode("1");
		SOAPElement requestedPackageLineItems = requestedShipment.addChildElement("RequestedPackageLineItems");
		SOAPElement sequenceNumber = requestedPackageLineItems.addChildElement("SequenceNumber");
		sequenceNumber.addTextNode("1");
		SOAPElement groupNumber = requestedPackageLineItems.addChildElement("GroupNumber");
		groupNumber.addTextNode("1");
		SOAPElement groupPackageCount = requestedPackageLineItems.addChildElement("GroupPackageCount");
		groupPackageCount.addTextNode("1");
		SOAPElement weight = requestedPackageLineItems.addChildElement("Weight");
		SOAPElement units = weight.addChildElement("Units");
		units.addTextNode("KG");
		SOAPElement value = weight.addChildElement("Value");
		value.addTextNode(String.valueOf(parcel.getWeight()));
		
		return msg;
        
	}
	
	public boolean hasErrors(SOAPMessage soapResponse) throws SOAPException{
		boolean result = false;
		
		SOAPBody soapBody = soapResponse.getSOAPBody();
		NodeList nl = soapBody.getElementsByTagName("HighestSeverity");
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
	
	public double parseRate(SOAPMessage soapResponse) throws SOAPException, IOException, URISyntaxException {	
		
		SOAPBody soapBody = soapResponse.getSOAPBody();
	
		CurrencyExchange currency = new CurrencyExchange();
		String shippingCurrency = getShippingCurrency(soapBody);
		double rate = currency.getRate(shippingCurrency);
		rate = divide(1,rate);
		
		Double baseCharge = totalAmount("TotalBaseCharge", soapBody);
		Double discount = totalAmount("TotalFreightDiscounts", soapBody);
		Double surcharge = totalAmount("TotalSurcharges", soapBody);
		Double tax = totalAmount("TotalTaxes", soapBody);
		Double rebate = totalAmount("TotalRebates", soapBody);
		Double ancillaryFees = totalAmount("TotalAncillaryFeesAndTaxes", soapBody);
		Double duty = totalAmount("TotalDutiesTaxesAndFees", soapBody);
		
		return ((baseCharge + surcharge + tax + ancillaryFees + duty) - (discount + rebate)) * rate;
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
	private String getShippingCurrency(SOAPBody parent) {
		String c = "";
		NodeList nl = parent.getElementsByTagName("CurrencyExchangeRate");
		Node item;
		Node itm;
		
		item = nl.item(0);
		NodeList n = item.getChildNodes();
		for (int i = 0; i < n.getLength(); i++ ) {
			itm = n.item(i);
			if (itm.getNodeName().compareTo("IntoCurrency") == 0) {
				c = itm.getTextContent();
			}
		}
		
		return c;
	}
	
	
	private double divide(double num, double dem) {
		if (dem == 0) {
			return 0;
		}
		
		return num / dem;
	}

}
