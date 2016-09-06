package com.peachy.web.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.peachy.web.orm.Coupons;
import com.peachy.web.service.CouponsService;
import com.peachy.web.service.InvoiceHeaderService;
import com.peachy.web.service.InvoiceService;
import com.peachy.web.service.TransactionService;
import com.peachy.web.service.UsedCouponsService;
import com.peachy.web.service.UserProfileService;

@Controller
public class CouponController implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String pageLink = "/couponpaging";
	@Autowired
	private CouponsService couponsService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceHeaderService invoiceHeaderService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	UsedCouponsService usedCouponsService;
	
	PagedListHolder<Coupons> couponList;
	
	private SimpleDateFormat dateFormat;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping("/createcoupon")
	public String createCoupon(Model model) {
		model.addAttribute("coupon", new Coupons());
		
		return "createcoupon";
	}

	@RequestMapping("/savecoupon")
	public String saveCoupon(@ModelAttribute("coupon") Coupons coupon, Model model) {
		coupon.setCouponID("CPN" + coupon.getCouponID());
		couponsService.create(coupon);
		couponList = couponsService.getList();
		model.addAttribute("objectList", couponList);
		model.addAttribute("pagelink", pageLink);
		
		return "listcoupons";
	}
	/*	
	@RequestMapping("/entercoupon")
	public String enterCoupon(Model model) {
		String errorMsg = "";
		String couponNum = "";
		
		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("couponNum", couponNum);
		
		return "entercoupon";
	}

	@RequestMapping("/redeemcoupon")
	public String redeemCoupon(@ModelAttribute("couponNum") String couponNum, 
							   @ModelAttribute("errorMsg") String errorMsg, Principal principal, Model model) throws IOException, RecognitionException, NestedServletException, SOAPException {
		if (couponNum.length() < 4) {
			errorMsg = "Please enter a coupon number";
			
			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("couponNum", couponNum);
			
			return "entercoupon";
		}
		Coupons coupon = couponsService.retrieve(couponNum);
		if (coupon == null ) {
			errorMsg = "That coupon does not exist";
			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("couponNum", couponNum);
			
			return "entercoupon";
		}
		if (new Date().after(coupon.getExpires())) {
			errorMsg = "That coupon has expired";
			
			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("couponNum", couponNum);
			
			return "entercoupon";

		}
		UserProfile user = userProfileService.getUser(principal.getName());
		InvoiceHeader header = invoiceHeaderService.getOpenOrder(user.getUserID());
		long count = usedCouponsService.getCount(user.getUserID(), coupon.getCouponID());
		if (coupon.getUseage() <= count ) {
			errorMsg = "That coupon is used up.";
			
			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("couponNum", couponNum);
			
			return "entercoupon";
		}
		
		if (coupon.isExclusive() && invoiceService.hasCoupons(header.getInvoiceNum())) {
			errorMsg = "This coupon cannot be used with any other coupon";
			
			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("couponNum", couponNum);
			
			return "entercoupon";
		}
		
		transactionService.useCoupon(header, coupon);
		invoiceHeaderService.totalHeader(header);
		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header, invoiceList);
		
		model.addAttribute("invoice", invoice);

		return("cart");
	}
	*/
	@RequestMapping("/listcoupons")
	public String listCoupons(Model model) {
		couponList = couponsService.getList();
		if (couponList.getPageList().size() == 0) {
			model.addAttribute("coupon", new Coupons());
			return "createcoupon";
		}
		model.addAttribute("objectList", couponList);
		return "listcoupons";
	}
	
	@RequestMapping("/editcoupon")
	public String editCoupon(@ModelAttribute("key") String key, Model model) {
		model.addAttribute("coupon", couponsService.retrieve(key));
		
		return "editcoupon";
	}
	
	@RequestMapping("/updatecoupon")
	public String updateCoupon(@ModelAttribute("coupon") Coupons coupon, Model model) {
		couponsService.update(coupon);
		couponList = couponsService.getList();
		model.addAttribute("objectList", couponList);
		
		return "listcoupons";
	}
	
	@RequestMapping("/deletecoupon")
	public String deleteCoupon(@ModelAttribute("key") String key, Model model) {
		Coupons coupon = couponsService.retrieve(key);
		
		couponsService.delete(coupon);
		couponList = couponsService.getList();
		model.addAttribute("objectList", couponList);
		model.addAttribute("pagelink", pageLink);
		
		return "listcoupons";
	}
	
	/******************************************************************************************************
	 * Pagination handler
	 ******************************************************************************************************/
	@RequestMapping(value="/couponpaging", method=RequestMethod.GET)
	public ModelAndView handleCouponRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
	    String keyword = request.getParameter("keyword");
	    if (keyword != null) {
	        couponList.setPageSize(20);
	        request.getSession().setAttribute("SearchProductsController_productList", couponList);
	        ModelAndView model = new ModelAndView("listcoupons", "objectList", couponList);
	        model.addObject("pagelink", pageLink);
	        
	        return model;
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (couponList == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	couponList.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	couponList.previousPage();
	        }else if (pgNum != -1) {
	        	couponList.setPage(pgNum);
	        }
	        ModelAndView model = new ModelAndView("listcoupons", "objectList", couponList);
	        model.addObject("pagelink", pageLink);
	        
	        return model;
	    }
	}

	
	/**************************************************************************************************************************************
	 * Used for both detecting a number, and converting to a number. If this routine returns a -1, the input parameter was not a number.
	 * 
	 **************************************************************************************************************************************/
		
		private int isInteger(String s) {
			int retInt;
		    try { 
		    	retInt = Integer.parseInt(s); 
		    } catch(NumberFormatException e) { 
		        return -1; 
		    } catch(NullPointerException e) {
		        return -1;
		    }
		    // only got here if we didn't return false
		    return retInt;
		}
	
}
