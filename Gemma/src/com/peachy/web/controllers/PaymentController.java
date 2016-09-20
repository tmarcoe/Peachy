package com.peachy.web.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.Principal;

import javax.xml.soap.SOAPException;

import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.braintreegateway.BraintreeGateway;
import com.peachy.web.beans.BeansHelper;
import com.peachy.web.beans.FileLocations;
import com.peachy.web.dao.InvoiceContainer;
import com.peachy.web.dao.InvoiceHeader;
import com.peachy.web.dao.UserProfile;
import com.peachy.web.local.CurrencyExchange;
import com.peachy.web.payment.BraintreeGatewayFactory;
import com.peachy.web.payment.Checkout;
import com.peachy.web.payment.Payment;
import com.peachy.web.service.InventoryService;
import com.peachy.web.service.InvoiceHeaderService;
import com.peachy.web.service.InvoiceService;
import com.peachy.web.service.TransactionService;
import com.peachy.web.service.UserProfileService;

@Controller
public class PaymentController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private FileLocations fileLocations;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceHeaderService invoiceHeaderService;

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	TransactionService transactionService;

	private BraintreeGateway gateway;

	private static Logger logger = Logger.getLogger(PaymentController.class
			.getName());

	@RequestMapping("/pcinfo")
	public String processPayment(
			@ModelAttribute("invoice") InvoiceContainer invoice,
			Principal principal, Model model) throws SecurityException,
			IllegalArgumentException, IOException, URISyntaxException,
			UnknownHostException {
		InvoiceHeader header = invoice.getInvoiceHeader();
		Payment payment = new Payment();
		UserProfile user = userProfileService.getUser(principal.getName());
		Checkout checkout = new Checkout();
		payment.setPaymentType("braintree");
		
		header = invoiceHeaderService.getOpenOrder(user.getUserID());
		switch (payment.getPaymentType()) {
		case "7-connect":
			checkout.sevenConnect(header, payment);
			break;
		case "braintree":
			FileLocations fl = (FileLocations) new BeansHelper().getBean(
					"config-context.xml", "fileLocations");
			gateway = BraintreeGatewayFactory.fromConfigFile(new URL(
					fl.getPaymentConfig() + "braintree.properties"));
			checkout.populatePayment(payment, user);
			model.addAttribute("clientToken", gateway.clientToken().generate());
			model.addAttribute("payment", payment);
			return "pcinfo";
		default:
			return "error";
		}
		CurrencyExchange currency = new CurrencyExchange();
		
		model.addAttribute("rate", currency.getRate(user.getCurrency()));
		model.addAttribute("currencySymbol", currency.getSymbol(user.getCurrency()));

		return "thankyou";
	}

	@RequestMapping("/pod")
	public String paymentOnDelivery(Principal principal, Model model) throws IOException, URISyntaxException, SOAPException {
		UserProfile user = userProfileService.getUser(principal.getName());
		InvoiceHeader header = invoiceHeaderService.getOpenOrder(user
				.getUserID());
		if (header == null) {
			return "nocart";
		}
		header = invoiceHeaderService.totalHeader(header);
		header.setPod(true);
		header.setAddedCharges((header.getTotal() * .1));

		try {
			logger.info("Processing shopping cart.");
			transactionService.podProcessShoppingCart(header);
		} catch (IOException | RuntimeException e) {
			return "error";
		}

		model.addAttribute("invoiceHeader", header);
		CurrencyExchange currency = new CurrencyExchange();
		
		model.addAttribute("rate", currency.getRate(user.getCurrency()));
		model.addAttribute("currencySymbol", currency.getSymbol(user.getCurrency()));

		return "thankyou";
	}

	@RequestMapping("/pcdenied")
	public String showPcDenied() {
		return "pcdenied";
	}

	@RequestMapping("/processcart")
	public String processShoppingCart(
			@ModelAttribute("payment") Payment payment,
			@ModelAttribute("payment_method_nonce") String nonce,
			Principal principal, Model model) throws RecognitionException,
			URISyntaxException, IOException, SOAPException {

		UserProfile user = userProfileService.getUser(principal.getName());
		payment.setUsername(user.getUsername());
		InvoiceHeader header = invoiceHeaderService.getOpenOrder(user
				.getUserID());
		Checkout checkout = new Checkout();
		header = invoiceHeaderService.totalHeader(header);
		header.setUserID(user.getUserID());

		if (checkout.paymentCard(
				payment,
				gateway,
				BigDecimal.valueOf(header.getTotal() + header.getTotalTax()
						+ header.getShippingCost() + header.getAddedCharges()),
				nonce) == true) {
			logger.info("Processing shopping cart.");
			try {
				transactionService.processShoppingCart(header);
			} catch (IOException | RuntimeException e) {
				logger.error(e.getMessage());
				return "error";
			}
		} else {
			return "pcdenied";
		}
		model.addAttribute("invoiceHeader", header);
		CurrencyExchange currency = new CurrencyExchange();
		
		model.addAttribute("rate", currency.getRate(user.getCurrency()));
		model.addAttribute("currencySymbol", currency.getSymbol(user.getCurrency()));

		return "thankyou";
	}
}
