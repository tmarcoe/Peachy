package com.peachy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peachy.web.dao.InventoryDao;
import com.peachy.web.dao.InvoiceItemDao;
import com.peachy.web.orm.InvoiceHeader;
import com.peachy.web.orm.InvoiceItem;
import com.peachy.web.orm.UsedCoupons;

@Service("invoiceService")
public class InvoiceService {
	
	@Autowired
	private InvoiceItemDao invoiceItemDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	ChartOfAccountsService chartOfAccountsService;
	
	@Autowired
	UsedCouponsService usedCouponsService;
	
	public List<InvoiceItem> getInvoice(InvoiceHeader header) {
	
		return invoiceItemDao.getInvoice(header);
	}


	public InvoiceItem addLineItem(InvoiceItem item) {
		
		return invoiceItemDao.addLineItem(item);
	}

	public void deleteInvoiceItem(int invoiceNum, String skuNum) {
		invoiceItemDao.deleteInvoiceItem(invoiceNum, skuNum);
	}

	public InvoiceItem getInvoiceItem(int invoiceNum, String skuNum) {
		return invoiceItemDao.getInvoiceItem(invoiceNum, skuNum);
	}


	public void updateItem(InvoiceItem item) {
		invoiceItemDao.updateItem(item);
		
	}

	public double totalShoppingCart(InvoiceHeader header) {
		return invoiceItemDao.totalShoppingCart(header);
	}


	public void deleteInvoice(InvoiceHeader header) {
		invoiceItemDao.deleteInvoice(header);
	}


	public boolean hasCoupons(int invoiceNum) {
		return invoiceItemDao.hasCoupons(invoiceNum);
	}


	public InvoiceItem getCouponFromInvoice(Integer invoiceNum) {
		return invoiceItemDao.getCouponFromInvoice(invoiceNum);
	}
	
	public void purgeCoupons(List<InvoiceItem> invoice, int user) {
		for (InvoiceItem item : invoice) {
			if (item.getSkuNum().startsWith("CPN")) {
				invoiceItemDao.deleteInvoiceItem(item.getInvoiceNum(), item.getSkuNum());
				UsedCoupons uc = usedCouponsService.retrieve(item.getSkuNum(), user);
				usedCouponsService.delete(uc);
			}
		}
	}


}
