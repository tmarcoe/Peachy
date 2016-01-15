package com.gemma.web.controllers;

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
import com.gemma.spring.web.service.InventoryService;

import org.springframework.util.StringUtils;


@Controller
@Scope(value="session")
public class ShopController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private PagedListHolder<Inventory> inventoryList;
	

	@RequestMapping(value="/products")
	public String products(	@ModelAttribute("page") String page, Model model) {
		inventoryList.setSource(inventoryService.listProducts());

		inventoryList.setPageSize(4);
		inventoryList.setPage(0);
		model.addAttribute("inventoryList",inventoryList);
	
		return "products";
	}
	
	@RequestMapping(value="/productdetails", method=RequestMethod.GET)
	public String showProductDetails(@ModelAttribute("skuNum") String skuNum, Model model ) {
		Inventory inventory = inventoryService.getItem(skuNum);
		model.addAttribute("inventory", inventory);
		return "productdetails";
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
