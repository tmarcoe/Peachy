package com.peachy.web.service;

import java.io.IOException;
import java.util.List;

import javax.xml.soap.SOAPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.peachy.web.beans.Parcel;
import com.peachy.web.beans.ShippingAddress;
import com.peachy.web.dao.InvoiceHeader;
import com.peachy.web.dao.InvoiceHeaderDao;
import com.peachy.web.dao.InvoiceItem;
import com.peachy.web.dao.InvoiceItemDao;
import com.peachy.web.dao.UserProfile;
import com.peachy.web.dao.UserProfileDao;
import com.peachy.web.local.ShippingInfo;
import com.peachy.web.local.ShippingRates;

@Service("invoiceHeaderService")
public class InvoiceHeaderService {
	
	@Autowired
	private InvoiceHeaderDao invoiceHeaderDao;
	
	@Autowired
	private InvoiceItemDao invoiceItemDao;
	
	@Autowired
	private UserProfileDao userProfileDao;
	
	@Autowired
	private InvoiceService invoiceService;
	
	public InvoiceHeader createHeader(InvoiceHeader header) {
		return invoiceHeaderDao.createHeader(header);
	}

	public InvoiceHeader getOpenOrder(int userID) {
		
		return invoiceHeaderDao.getOpenOrder(userID);
	}
	public PagedListHolder<InvoiceHeader> getHistory(int userID) {
		return new PagedListHolder<InvoiceHeader>(invoiceHeaderDao.getHistory(userID));
	}

	public InvoiceHeader getInvoiceHeader(int invoiceNum) {
		return invoiceHeaderDao.getInvoiceHeader(invoiceNum);
	}

	public PagedListHolder<InvoiceHeader> getProcessedInvoices() {

		return new PagedListHolder<InvoiceHeader>(invoiceHeaderDao.getProcessedInvoices());
	}

	public void updateHeader(InvoiceHeader header) {
		invoiceHeaderDao.updateHeader(header);
	}

	
	public List<InvoiceHeader> getAllInvoiceHeaders() {
		return invoiceHeaderDao.getAllInvoiceHeaders();
	}

	public List<InvoiceHeader> getProcessedInvoicesList() {
		
		return invoiceHeaderDao.getProcessedInvoicesList();
	}
	
	public InvoiceHeader totalHeader(InvoiceHeader header) throws SOAPException, IOException {
		List<InvoiceItem> itemList = invoiceItemDao.getInvoice(header);
		UserProfile user = userProfileDao.getUserByID(header.getUserID());
		ShippingInfo ship = new ShippingInfo();
		ShippingAddress to = ship.receiverAddress(user);
		ShippingAddress from = ship.senderAdress();
		Parcel parcel = ship.parcelInfo();
		ShippingRates shipRate = new ShippingRates();
		
		double total = 0;
		double totalTax = 0;
		double weight = 0;

		for(InvoiceItem item: itemList) {
			total += (item.getAmount() * item.getPrice());
			totalTax += (item.getAmount() * item.getTax());
			weight += (item.getWeight());
			
			header.setTotal(total);
			header.setTotalTax(totalTax);
		}
		parcel.setWeight(weight);
		double shipCharge = shipRate.getShippingRate(from, to, parcel);
		header.setShippingCost(shipCharge);
		
		return header;
	}


}
