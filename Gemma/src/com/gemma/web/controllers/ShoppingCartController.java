package com.gemma.web.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;
import com.gemma.web.beans.FileLocations;
import com.gemma.web.beans.FileUpload;
import com.gemma.web.dao.Coupons;
import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.InvoiceContainer;
import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.dao.InvoiceItem;
import com.gemma.web.dao.UsedCoupons;
import com.gemma.web.dao.UserProfile;
import com.gemma.web.service.CouponsService;
import com.gemma.web.service.GeneralLedgerService;
import com.gemma.web.service.InventoryService;
import com.gemma.web.service.InvoiceHeaderService;
import com.gemma.web.service.InvoiceService;
import com.gemma.web.service.TransactionService;
import com.gemma.web.service.UsedCouponsService;
import com.gemma.web.service.UserProfileService;

@Controller
@Scope(value = "session")
public class ShoppingCartController implements Serializable {
	private static final long serialVersionUID = 4725326820861092920L;
	private static Logger logger = Logger
			.getLogger(ShoppingCartController.class.getName());
	private final String pageLink = "/historypaging";

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceHeaderService invoiceHeaderService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private GeneralLedgerService generalLedgerService;

	@Autowired
	private CouponsService couponsService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	private UsedCouponsService usedCouponsService;

	@Autowired
	private FileLocations fileLocations;

	private PagedListHolder<InvoiceHeader> historyList;

	private SimpleDateFormat dateFormat;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping("/saveitem")
	public String saveInvoiceItem(
			@Valid @ModelAttribute("item") InvoiceItem item, Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			return "editcart";
		}
		int invoiceNum = item.getInvoiceNum();
		invoiceService.updateItem(item);
		InvoiceHeader header = invoiceHeaderService
				.getInvoiceHeader(invoiceNum);
		if (header == null) {
			return "nocart";
		}
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header, invoiceList);
		String errorMsg = "";
		String couponNum = "CPN";

		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("couponNum", couponNum);
		model.addAttribute("invoice", invoice);

		return "cart";
	}

	@RequestMapping("/cancelsale")
	public String cancelSale(Principal principal, Model model) {
		String fileLoc = fileLocations.getImageLoc();

		UserProfile user = userProfileService.getUser(principal.getName());
		InvoiceHeader header = invoiceHeaderService.getOpenOrder(user
				.getUserID());

		InvoiceItem item = invoiceService.getCouponFromInvoice(header.getInvoiceNum());
		if (item != null) {
			UsedCoupons used = usedCouponsService.retrieve(item.getSkuNum(), user.getUserID());
			usedCouponsService.delete(used);
		}
		invoiceService.deleteInvoice(header);
		List<Inventory> inventory = inventoryService.listSaleItems();
		model.addAttribute("inventory", inventory);
		model.addAttribute("fileLoc", fileLoc);

		return "home";
	}

	@RequestMapping("/deleteinvoiceitem")
	public String deleteInvoiceItem(int invoiceNum, String skuNum, Principal principal, Model model) {
		if (skuNum.startsWith("CPN")) {
			UserProfile user = userProfileService.getUser(principal.getName());
			UsedCoupons coupon = usedCouponsService.retrieve(skuNum, user.getUserID());
			usedCouponsService.delete(coupon);
		}
		invoiceService.deleteInvoiceItem(invoiceNum, skuNum);
		logger.info(String.format(
				"'%s' has been removed from invoice # '%08d'.", skuNum,
				invoiceNum));
		InvoiceHeader header = invoiceHeaderService
				.getInvoiceHeader(invoiceNum);
		if (header == null) {
			return "nocart";
		}

		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header, invoiceList);
		String errorMsg = "";
		String couponNum = "CPN";

		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("couponNum", couponNum);

		model.addAttribute("invoice", invoice);

		return "cart";
	}

	@RequestMapping("/cart")
	public String showCart(Principal principal, Model model) {

		UserProfile user = userProfileService.getUser(principal.getName());
		InvoiceHeader header = invoiceHeaderService.getOpenOrder(user
				.getUserID());
		if (header == null) {
			return "nocart";
		}
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header, invoiceList);

		model.addAttribute("invoice", invoice);
		String errorMsg = "";
		String couponNum = "CPN";

		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("couponNum", couponNum);

		return "cart";
	}

	@RequestMapping("/vieworder")
	public String viewOrder(@ModelAttribute("errorMsg") String errorMsg,
			@ModelAttribute("couponNum") String couponNum, Principal principal,
			Model model) throws RecognitionException, NestedServletException,
			IOException {
		
		UserProfile user = userProfileService.getUser(principal.getName());
		InvoiceHeader header = invoiceHeaderService.getOpenOrder(user
				.getUserID());
		if (header == null) {
			return "nocart";
		}
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header, invoiceList);

		model.addAttribute("invoice", invoice);

		if (couponNum.length() > 3) {

			Coupons coupon = couponsService.retrieve(couponNum);
			if (coupon == null) {
				errorMsg = "That coupon does not exist";
				
				model.addAttribute("errorMsg", errorMsg);
				model.addAttribute("couponNum", couponNum);

				return "cart";
			}
			if (new Date().after(coupon.getExpires())) {
				errorMsg = "That coupon has expired";

				model.addAttribute("errorMsg", errorMsg);
				model.addAttribute("couponNum", couponNum);

				return "cart";

			}
			long count = usedCouponsService.getCount(user.getUserID(),
					coupon.getCouponID());
			if (coupon.getUseage() <= count) {
				errorMsg = "That coupon is used up.";

				model.addAttribute("errorMsg", errorMsg);
				model.addAttribute("couponNum", couponNum);

				return "cart";
			}

			if (coupon.isExclusive()
					&& invoiceService.hasCoupons(header.getInvoiceNum())) {
				errorMsg = "Only one coupon per order";

				model.addAttribute("errorMsg", errorMsg);
				model.addAttribute("couponNum", couponNum);

				return "cart";
			}

			transactionService.redeemCoupon(header, coupon);
		}
		invoiceList = invoiceService.getInvoice(header);
		invoice.setInvoiceHeader(header);
		invoice.setInvoiceList(invoiceList);

		model.addAttribute("invoice", invoice);


		return "vieworder";
	}

	@RequestMapping("/viewcart")
	public String viewCart(@ModelAttribute("invoiceNum") int invoiceNum,
			Model model) {
		InvoiceHeader header = invoiceHeaderService
				.getInvoiceHeader(invoiceNum);
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header, invoiceList);
		String errorMsg = "";
		String couponNum = "CPN";

		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("couponNum", couponNum);

		model.addAttribute("invoice", invoice);

		return "cart";
	}

	@RequestMapping("/editcart")
	public String editCart(int invoiceNum, String skuNum, Model model) {

		InvoiceItem item = invoiceService.getInvoiceItem(invoiceNum, skuNum);

		model.addAttribute("item", item);

		return "editcart";
	}

	@RequestMapping("/filepicker")
	public String filePicker(Model model) {
		FileUpload filePrint = new FileUpload();

		model.addAttribute("filePrint", filePrint);

		return "filepicker";
	}

	@RequestMapping("/shoppinghistory")
	public String showShoppingHistory(Principal principal, Model model) {
		UserProfile user = userProfileService.getUser(principal.getName());
		historyList = invoiceHeaderService.getHistory(user.getUserID());
		historyList.setPageSize(15);
		historyList.setPage(0);
		model.addAttribute("objectList", historyList);
		model.addAttribute("pagelink", pageLink);

		return "shoppinghistory";
	}


	/********************************************************************************************************
	 * Pagination Handlers
	 ********************************************************************************************************/
	@RequestMapping(value = "/historypaging", method = RequestMethod.GET)
	public ModelAndView handleHostoryRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int pgNum;
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			historyList.setPageSize(4);
			request.getSession().setAttribute(
					"SearchProductsController_productList", historyList);
			ModelAndView model = new ModelAndView("shoppinghistory");
			model.addObject("objectList", historyList);
	        model.addObject("pagelink", pageLink);

			return model;
		} else {
			String page = request.getParameter("page");

			pgNum = isInteger(page);

			if ("next".equals(page)) {
				historyList.nextPage();
			} else if ("prev".equals(page)) {
				historyList.previousPage();
			} else if (pgNum != -1) {
				historyList.setPage(pgNum);
			}

			ModelAndView model = new ModelAndView("shoppinghistory");
			model.addObject("objectList", historyList);
	        model.addObject("pagelink", pageLink);

			return model;
		}
	}

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
