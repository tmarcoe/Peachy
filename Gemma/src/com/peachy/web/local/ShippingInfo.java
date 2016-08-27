package com.peachy.web.local;

import com.peachy.web.beans.BeansHelper;
import com.peachy.web.beans.LocalValues;
import com.peachy.web.beans.Parcel;
import com.peachy.web.beans.ShippingAddress;
import com.peachy.web.dao.UserProfile;

public class ShippingInfo {
	private LocalValues localValues;
	
	public ShippingInfo() {
		super();
		localValues = (LocalValues) new BeansHelper().getBean("config-context.xml", "localValues");	
	}

	public ShippingAddress senderAdress() {
		ShippingAddress sender = new ShippingAddress();

		sender.setAddress1(localValues.getAddress1());
		sender.setAddress2(localValues.getAddress2());
		sender.setCity(localValues.getCity());
		sender.setRegion(localValues.getRegion());
		sender.setPostalCode(localValues.getPostalCode());
		sender.setCountry(localValues.getCountry());
		sender.setPhone(localValues.getPhone());
		
		return sender;
	}
	
	public ShippingAddress receiverAddress(UserProfile user) {
		ShippingAddress receiver = new ShippingAddress();
		
		receiver.setAddress1(user.getaddress1());
		receiver.setAddress2(user.getaddress2());
		receiver.setCity(user.getcity());
		receiver.setRegion(user.getregion());
		receiver.setPostalCode(user.getpostalCode());
		receiver.setCountry(user.getcountry());

		return receiver;
	}

	public Parcel parcelInfo() {
		Parcel parcel = new Parcel();
		
		parcel.setUnit("LBS");
		parcel.setWeight(1);
		parcel.setTransactionID("Ground Pickup");
		
		return parcel;
	}

}
