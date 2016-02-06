package com.gemma.web.controllers;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gemma.spring.web.dao.Inventory;
import com.gemma.spring.web.dao.InvoiceContainer;
import com.gemma.spring.web.dao.InvoiceHeader;
import com.gemma.spring.web.dao.InvoiceItem;
import com.gemma.spring.web.dao.UserProfile;
import com.gemma.spring.web.service.InventoryService;
import com.gemma.spring.web.service.InvoiceService;
import com.gemma.spring.web.service.UserProfileService;
import com.gemma.web.beans.Categories;

import org.springframework.util.StringUtils;


@Controller
@Scope(value="session")
public class ShopController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private PagedListHolder<Inventory> inventoryList;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private Categories categories;

	@RequestMapping(value="/products")
	public String products(	@ModelAttribute("page") String page, Model model) {
		if (categories.getCategory() == null) {			
			categories.setCategory("");
		}
		if (categories.getSubCategory() == null) {
			categories.setSubCategory("");
		}

		if (categories.getCategory().length() == 0) {
			inventoryList.setSource(inventoryService.listProducts());
		}else if (categories.getSubCategory().length() == 0){

			inventoryList.setSource(inventoryService.listProducts(categories.getCategory()));
		}else{
			inventoryList.setSource(inventoryService.listProducts(categories.getCategory(), categories.getSubCategory()));
		}

		inventoryList.setPageSize(4);
		inventoryList.setPage(0);
		model.addAttribute("inventoryList",inventoryList);
		model.addAttribute("categories", categories);
	
		return "products";
	}
	
	@RequestMapping(value="/productdetails", method=RequestMethod.GET)
	public String showProductDetails(@ModelAttribute("skuNum") String skuNum, Model model ) {
		Inventory inventory = inventoryService.getItem(skuNum);
		InvoiceItem item = new InvoiceItem(inventory);
		model.addAttribute("invoiceItem", item);

		return "productdetails";
	}
	
	@RequestMapping("/orderproduct")
	public String orderProduct(@ModelAttribute("invoiceItem")InvoiceItem item, Model model, Principal principal ) {
		UserProfile user = userProfileService.getUser(principal.getName());
		InvoiceHeader header = invoiceService.getOpenOrder(user.getUserID());
		if (header == null) {
			header = new InvoiceHeader();
			header.setModified(new Date());
			header.setUserID(user.getUserID());
			header = invoiceService.createHeader(header);
		}
		item.setInvoiceNum(header.getInvoiceNum());
		item = invoiceService.addLineItem(item);

		List<InvoiceItem> invoiceList = invoiceService.getInvoice(header);
		InvoiceContainer invoice = new InvoiceContainer(header,invoiceList );
		
		
		model.addAttribute("invoice", invoice);
		return "cart";
	}
	
	@RequestMapping("/pickcategory")
	public String pickCategory(Model model) {
		List<String> catList = inventoryService.getCategory();
		
		model.addAttribute("catList", catList);
		
		return "pickcategory";
	}
	@RequestMapping("/setcategory")
	public String setCategory(@ModelAttribute("cat") String cat, Model model){
		categories.setCategory("");
		categories.setSubCategory("");
		if (cat.length()== 0) {
			inventoryList.setSource(inventoryService.listProducts());

			inventoryList.setPageSize(4);
			inventoryList.setPage(0);
			model.addAttribute("inventoryList",inventoryList);

			return "products";
		}
		categories.setCategory(cat);
		List<String> catList = inventoryService.getSubCategory(categories.getCategory());
		model.addAttribute("catList", catList);

		return "picksubcategory";
	}
	
	
	@RequestMapping("/setsubcategory")
	public String setSubCategory(@ModelAttribute("cat") String cat, Model model){
		categories.setSubCategory("");
		if (cat.length()== 0) {
			inventoryList.setSource(inventoryService.listProducts(categories.getCategory()));

			inventoryList.setPageSize(4);
			inventoryList.setPage(0);
			model.addAttribute("inventoryList",inventoryList);
			model.addAttribute("categories", categories);
			return "products";
		}
		categories.setSubCategory(cat);
		inventoryList.setSource(inventoryService.listProducts(categories.getCategory(), categories.getSubCategory()));

		inventoryList.setPageSize(4);
		inventoryList.setPage(0);
		model.addAttribute("inventoryList",inventoryList);
		model.addAttribute("categories", categories);
		
		return "products";
	}
	
	@RequestMapping(value="/paging", method=RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
	    String keyword = request.getParameter("keyword");
	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        inventoryList.setPageSize(4);
	        request.getSession().setAttribute("SearchProductsController_productList", inventoryList);
	        return new ModelAndView("products", "inventoryList", inventoryList);
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (inventoryList == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	inventoryList.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	inventoryList.previousPage();
	        }else if (pgNum != -1) {
	        	inventoryList.setPage(pgNum);
	        }
	        return new ModelAndView("products", "inventoryList", inventoryList);
	    }
	}
	
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
