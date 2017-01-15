package com.peachy.web.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.net.URISyntaxException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.peachy.web.beans.AddressLabel;
import com.peachy.web.beans.FileLocations;
import com.peachy.web.dao.Inventory;
import com.peachy.web.dao.InvoiceHeader;
import com.peachy.web.dao.InvoiceItem;
import com.peachy.web.dao.UserProfile;
import com.peachy.web.email.ProcessEmail;
import com.peachy.web.local.CurrencyExchange;
import com.peachy.web.service.InventoryService;
import com.peachy.web.service.InvoiceHeaderService;
import com.peachy.web.service.InvoiceService;
import com.peachy.web.service.TransactionService;
import com.peachy.web.service.UserProfileService;

@Controller
@Scope(value = "session")
public class AdminController implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String pageLink = "/headerpaging";
	
	@Autowired
	private InvoiceHeaderService invoiceHeaderService;

	@Autowired
	private FileLocations fileLocations;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	TransactionService transactionService;

	private PagedListHolder<InvoiceHeader> headerList;

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(AdminController.class
			.getName());

	private SimpleDateFormat dateFormat;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	/******************************************************************************
	 * Admin Home Page
	 * @throws IOException 
	 * @throws URISyntaxException 
	 ******************************************************************************/
	@RequestMapping("/admin")
	public String showAdmin(Model model, Principal principal) throws IOException, URISyntaxException {
		UserProfile user = userProfileService.getUser(principal.getName());
		if (headerList != null) {
			headerList.getSource().clear();
			headerList = null;
			System.gc();
		}
		PagedListHolder<InvoiceHeader> headerList = invoiceHeaderService.getProcessedInvoices();
		headerList.setPage(0);
		headerList.setPageSize(10);
		CurrencyExchange currency = new CurrencyExchange();
		
		model.addAttribute("rate", currency.getRate(user.getCurrency()));
		model.addAttribute("currencySymbol", currency.getSymbol(user.getCurrency()));
		model.addAttribute("objectList", headerList);
		model.addAttribute("pagelink", pageLink);
		
		return "admin";
	}

	@RequestMapping("/processorders")
	public String processOrders() throws Exception {
		AddressLabel lbl = new AddressLabel();
		CurrencyExchange currency = new CurrencyExchange();
		double rate = 1;
		String symbol = "P";

		String[] label = { "firstname", "lastname", "address1", "address2",
				"city", "region", "postalCode", "country", "invoiceNum" };
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");

		String fileName = fileLocations.getOutPath() + sdf.format(new Date())
				+ ".csv";

		Writer hdr = new FileWriter(fileName);
		CsvBeanWriter csvWriter = new CsvBeanWriter(hdr,
				CsvPreference.STANDARD_PREFERENCE);

		List<InvoiceHeader> headers = invoiceHeaderService
				.getProcessedInvoicesList();
		csvWriter.writeHeader(label);
		for (InvoiceHeader header : headers) {
			UserProfile user = userProfileService.getUserByID(header
					.getUserID());
			if (user == null) {
				csvWriter.close();
				throw new Exception("User not found when referenced by 'header'.");
			}
			lbl.setFirstname(user.getFirstname());
			lbl.setLastname(user.getLastname());
			lbl.setAddress1(user.getaddress1());
			lbl.setAddress2(user.getaddress2());
			lbl.setCity(user.getcity());
			lbl.setRegion(user.getregion());
			lbl.setPostalCode(user.getpostalCode());
			lbl.setCountry(user.getcountry());
			lbl.setInvoiceNum(String.format("%06d", header.getInvoiceNum()));
			csvWriter.write(lbl, label);
			
			Writer inv = new FileWriter(fileLocations.getOutPath() + String.format("%08d", header.getInvoiceNum()) + ".inv");
			rate = currency.getRate(user.getCurrency());
			symbol = currency.getSymbol(user.getCurrency());
			List<InvoiceItem> invoices = invoiceService.getInvoice(header);
			String address2 = "";
			if ("".compareTo(user.getaddress2()) != 0) {
				address2 = user.getaddress2() + "\n";
			}
			String invoiceHeading = user.getFirstname() + " "
					+ user.getLastname() + "\n" + user.getaddress1() + "\n"
					+ address2 + user.getcity() + "," + user.getregion() + " "
					+ user.getpostalCode() + " " + user.getcountry() + "\n"
					+ "Invoice # "
					+ String.format("%06d", header.getInvoiceNum()) + "\n";
			if (header.isPod() == true) {
				invoiceHeading = invoiceHeading + "Payment ON Delivery\n\n";
			} else {
				invoiceHeading = invoiceHeading + "\n";
			}
			inv.write(invoiceHeading);
			double total = 0;
			double totalTax = 0;
			
			for (InvoiceItem invoice : invoices) {
				inventoryService.depleteInventory(invoice);
				double price = invoice.getAmount() * invoice.getPrice();
				double tax = invoice.getAmount() * invoice.getTax();
				total += price;
				totalTax += tax;
				inv.write(String.format("%s\t%d\t%s%.2f [SKU - %s]\n",
						invoice.getProductName(), invoice.getAmount(), symbol, price * rate,
						invoice.getSkuNum()));
			}
			inv.write("\n\n\n");
			inv.write("Subtotal................. "
					+ String.format("%s%.2f\n", symbol, total * rate));
			inv.write("POD Charge............... "
					+ String.format("%s%.2f\n", symbol, header.getAddedCharges() * rate));
			inv.write("Tax...................... "
					+ String.format("%s%.2f\n", symbol, totalTax * rate));
			inv.write("Shipping................. "
					+ String.format("%s%.2f\n", symbol, header.getShippingCost() * rate));
			inv.write("Total.................... "
					+ String.format("%s%.2f\n", symbol, 
							(total + totalTax + header.getAddedCharges() + header.getShippingCost()) * rate));
			inv.close();
			header.setDateShipped(new Date());
			invoiceHeaderService.updateHeader(header);
		}
		csvWriter.close();

		
		return "admin";
	}
	@RequestMapping("/podsave")
	public String podSave(@ModelAttribute("header") InvoiceHeader header,
			Model model) throws RecognitionException, IOException,
			RuntimeException {
		transactionService.processPod(header);
		header.setPod(false);
		invoiceHeaderService.updateHeader(header);

		if (headerList != null) {
			headerList.getSource().clear();
			headerList = null;
			System.gc();
		}
		headerList = invoiceHeaderService.getProcessedInvoices();
		headerList.setPage(0);
		headerList.setPageSize(10);
		model.addAttribute("objectList", headerList);
		model.addAttribute("pagelink", pageLink);
		
		
		return "admin";
	}

	@RequestMapping("/senddailyspecials")
	public String sendDailySpecial(Model model, Principal principal) throws Exception {
		ProcessEmail pe = new ProcessEmail();
		List <UserProfile> userList = userProfileService.getDailySpecialUsers();
		List <Inventory> inventoryList = inventoryService.listSaleItems();
		
		
		pe.sendDailySpecials(userList, pe.getDailySpecials(inventoryList));
		model.addAttribute("mailCount", userList.size());
		
		return "senddailyspecials";
	}
	
	/*********************************************************************************************************************
	 * Pageination Handlers
	 * 
	 ********************************************************************************************************************/

	@RequestMapping(value = "/headerpaging", method = RequestMethod.GET)
	public ModelAndView handleHeaderRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("Error", "message",
						"Please enter a keyword to search for, then press the search button.");
			}

			headerList.setPageSize(20);
			request.getSession().setAttribute(
					"SearchProductsController_productList", headerList);
	        ModelAndView model = new ModelAndView("admin", "objectList", headerList);
	        model.addObject("pagelink", pageLink);
	        
	        return model;
		} else {
			String page = request.getParameter("page");

			if (headerList == null) {
				return new ModelAndView("Error", "message",
						"Your session has timed out. Please start over again.");
			}
			pgNum = isInteger(page);

			if ("next".equals(page)) {
				headerList.nextPage();
			} else if ("prev".equals(page)) {
				headerList.previousPage();
			} else if (pgNum != -1) {
				headerList.setPage(pgNum);
			}
	        ModelAndView model = new ModelAndView("admin", "objectList", headerList);
	        model.addObject("pagelink", pageLink);
	        
	        return model;
		}
	}

	/**************************************************************************************************************************************
	 * Used for both detecting a number, and converting to a number. If this
	 * routine returns a -1, the input parameter was not a number.
	 * 
	 **************************************************************************************************************************************/

	private int isInteger(String s) {
		int retInt;
		try {
			retInt = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return -1;
		} catch (NullPointerException e) {
			return -1;
		}
		// only got here if we didn't return false
		return retInt;
	}
}
