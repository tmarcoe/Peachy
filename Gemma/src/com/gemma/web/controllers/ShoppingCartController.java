package com.gemma.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.spring.web.dao.InvoiceContainer;
import com.gemma.spring.web.dao.InvoiceHeader;
import com.gemma.spring.web.dao.InvoiceItem;
import com.gemma.spring.web.dao.UserProfile;
import com.gemma.spring.web.service.GeneralLedgerService;
import com.gemma.spring.web.service.InvoiceService;
import com.gemma.spring.web.service.UserProfileService;

@Controller
@Scope(value="session")
public class ShoppingCartController {
	
	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@RequestMapping("/saveitem")
	public String saveInvoiceItem(@ModelAttribute("item") InvoiceItem item, Model model) {
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
}
