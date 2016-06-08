package com.gemma.web.controllers;

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

import com.gemma.web.dao.Coupons;
import com.gemma.web.service.CouponsService;

@Controller
public class CouponController {
	
	@Autowired
	CouponsService couponsService;
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
		model.addAttribute("couponList", couponList);
		return "listcoupons";
	}
	
	@RequestMapping("/listcoupons")
	public String listCoupons(Model model) {
		couponList = couponsService.getList();
		if (couponList.getPageList().size() == 0) {
			model.addAttribute("coupon", new Coupons());
			return "createcoupon";
		}
		model.addAttribute("couponList", couponList);
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
		model.addAttribute("couponList", couponList);
		
		return "listcoupons";
	}
	
	@RequestMapping("/deletecoupon")
	public String deleteCoupon(@ModelAttribute("key") String key, Model model) {
		Coupons coupon = couponsService.retrieve(key);
		
		couponsService.delete(coupon);
		couponList = couponsService.getList();
		model.addAttribute("couponList", couponList);
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
	        return new ModelAndView("listcoupons", "couponList", couponList);
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
	        return new ModelAndView("listcoupons", "couponList", couponList);
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
