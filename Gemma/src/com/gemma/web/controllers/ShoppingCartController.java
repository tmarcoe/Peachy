package com.gemma.web.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.gemma.spring.web.dao.InvoiceContainer;
import com.gemma.spring.web.dao.InvoiceHeader;
import com.gemma.spring.web.dao.InvoiceItem;
import com.gemma.spring.web.dao.UserProfile;
import com.gemma.spring.web.service.GeneralLedgerService;
import com.gemma.spring.web.service.InvoiceService;
import com.gemma.spring.web.service.UserProfileService;
import com.gemma.web.beans.AddressLabel;
import com.gemma.web.beans.FileUpload;

@Controller
@Scope(value="session")
public class ShoppingCartController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4725326820861092920L;
	private static final String OUTPATH = "C:\\Repository\\";


	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping("/saveitem")
	public String saveInvoiceItem(@Valid @ModelAttribute("item") InvoiceItem item, Model model, BindingResult result) {
		if(result.hasErrors()) {
			return "editcart";
		}
		int invoiceNum = item.getInvoiceNum();
		invoiceService.updateItem(item);
		InvoiceHeader header = invoiceService.getInvoiceHeader(invoiceNum);
		if (header == null) {
			return "nocart";
		}
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header,invoiceList );
		
		model.addAttribute("invoice", invoice);
		
		return "cart";
	}
	
	@RequestMapping("/deleteinvoiceitem")
	public String deleteInvoiceItem(int invoiceNum, String skuNum, Model model){
		invoiceService.deleteInvoiceItem(invoiceNum,skuNum);
		InvoiceHeader header = invoiceService.getInvoiceHeader(invoiceNum);
		if (header == null) {
			return "nocart";
		}
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header,invoiceList );
		
		model.addAttribute("invoice", invoice);

		
		return "cart";
	}
	
	
	@RequestMapping("/cart")
	public String showCart(Principal principal, Model model) {
		UserProfile user = userProfileService.getUser(principal.getName());
		InvoiceHeader header = invoiceService.getOpenOrder(user.getUserID());
		if (header == null) {
			return "nocart";
		}
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header,invoiceList );
		
		
		model.addAttribute("invoice", invoice);
		
		return "cart";
	}
	@RequestMapping("/viewcart")
	public String viewCart(@ModelAttribute("invoiceNum") int invoiceNum, Model model ) {
		InvoiceHeader header = invoiceService.getInvoiceHeader(invoiceNum);
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header,invoiceList );

		model.addAttribute("invoice", invoice);
		
		return "cart";
	}
	
	@RequestMapping("/editcart")
	public String editCart(int invoiceNum, String skuNum, Model model) {
		InvoiceItem item = invoiceService.getInvoiceItem(invoiceNum, skuNum);
		
		model.addAttribute("item", item);
		
		return "editcart";
	}
	
	@RequestMapping("/processcart")
	public String processShoppingCart(@ModelAttribute("item") InvoiceItem item, Principal principal) {
		UserProfile user = userProfileService.getUser(principal.getName());
		InvoiceHeader header = invoiceService.getOpenOrder(user.getUserID());
		invoiceService.processShoppingCart(header);
		
		return "thankyou";
	}
	@RequestMapping("/filepicker")
	public String filePicker(Model model){
		FileUpload filePrint = new FileUpload();
		
		model.addAttribute("filePrint", filePrint);
			
		return "filepicker";
	}
	@RequestMapping("/processorders")
	public String processOrders() throws IOException{
		AddressLabel lbl = new AddressLabel();

		String[] label = {"firstname","lastname","address1","address2","city","region","postalCode","country","invoiceNum"};
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		String fileName = OUTPATH + sdf.format(new Date()) + ".csv";
		Writer hdr = new FileWriter(fileName);
		CsvBeanWriter csvWriter = new CsvBeanWriter(hdr, CsvPreference.STANDARD_PREFERENCE);
		
		List<InvoiceHeader> headers = invoiceService.getProcessedInvoices();
		csvWriter.writeHeader(label);
		for(InvoiceHeader header: headers) {
			UserProfile user = userProfileService.getUserByID(header.getUserID());
			lbl.setFirstname(user.getFirstname());
			lbl.setLastname(user.getLastname());
			lbl.setAddress1(user.getaddress1());
			lbl.setAddress2(user.getaddress2());
			lbl.setCity(user.getcity());
			lbl.setRegion(user.getregion());
			lbl.setPostalCode(lbl.getPostalCode());
			lbl.setCountry(user.getcountry());
			lbl.setInvoiceNum(String.format("%06d", header.getInvoiceNum()));
			csvWriter.write(lbl,label);

			Writer inv = new FileWriter(OUTPATH + String.format("%06d",header.getInvoiceNum()) + ".inv");
			List<InvoiceItem> invoices = invoiceService.getInvoice(header);
			String invoiceHeading = user.getFirstname() + " " + user.getLastname() + "\n" +
									user.getaddress1()  + "\n" +
									user.getaddress2()  + "\n" +
									user.getcity()      + "," +
									user.getregion()    + " " +
									user.getpostalCode()+ " " +
									user.getcountry()   + "\n" +
									"Invoice # " + String.format("%06d", header.getInvoiceNum()) + "\n\n";
			inv.write(invoiceHeading);
			double total = 0;
			for (InvoiceItem invoice: invoices) {
				double price = invoice.getAmount() * invoice.getPrice();
				total += price;
				inv.write(String.format("%s\t%d\tP%.2f\n", invoice.getProductName(), invoice.getAmount(), price));
			}
			inv.write("Total -> " + String.format("P%.2f\n", total));
			inv.close();
			header.setDateShipped(new Date());
			invoiceService.updateHeader(header);
		}
		csvWriter.close();

		
		return "admin";
	}
}
