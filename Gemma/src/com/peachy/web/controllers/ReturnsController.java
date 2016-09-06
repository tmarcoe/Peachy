package com.peachy.web.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.peachy.web.orm.InvoiceHeader;
import com.peachy.web.orm.InvoiceItem;
import com.peachy.web.orm.Returns;
import com.peachy.web.service.InvoiceHeaderService;
import com.peachy.web.service.InvoiceService;
import com.peachy.web.service.ReturnsService;
import com.peachy.web.service.TransactionService;

@Controller
public class ReturnsController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ReturnsController.class.getName());
	private final String pageLink = "/returnspaging";
	private final String pageStatLink = "/returnsstatuspaging";

	@Autowired
	private ReturnsService returnsService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceHeaderService invoiceHeaderService;
	
	private PagedListHolder<Returns> returnsList;
		
		@RequestMapping("/returns-list")
		public String showReturns(Model model) {
			
			returnsList = returnsService.getReturnsList();

			model.addAttribute("objectList", returnsList);
			model.addAttribute("pagelink", pageLink);
			
			return "returns-list";
		}
		
		@RequestMapping("/returns-approve")
		public String approveReturns(@ModelAttribute("rmaId") Integer rmaId, Model model) {
			Returns returns = returnsService.getRma(rmaId);
			
			model.addAttribute("returns", returns);
			
			return "returns-approve";
		}
		
		@RequestMapping("/returns-update")
		public String saveReturns(@ModelAttribute("returns") Returns returns, Model model) throws URISyntaxException {
			
			returns.setDateProcessed(new Date());
			
			returnsService.update(returns);
			try {
				transactionService.returnMerchandise(returns);
			} catch (IOException | RuntimeException e) {
				return "error";
			}
			
			returnsList = returnsService.getReturnsList();

			model.addAttribute("objectList", returnsList);
			model.addAttribute("pagelink", pageLink);
			
			return "returns-list";
		
		}
		
		@RequestMapping("/returns-submit")
		public String returnsSubmit(@Valid @ModelAttribute("returns") Returns returns, Model model, BindingResult result) {
			InvoiceItem invoice = invoiceService.getInvoiceItem(returns.getInvoiceNum(), returns.getSkuNum());
			if (result.hasErrors() == true ){
				return "returns-getlookup";
			}
			if (invoice == null) {
				result.rejectValue("invoiceNum","NotFound.returns.invoiceNum");
				return "returns-getlookup";
			}
			InvoiceHeader header = invoiceHeaderService.getInvoiceHeader(returns.getInvoiceNum());
			if (returns.getAmtReturned() > invoice.getAmount()) {
				result.rejectValue("amtReturned","Amount.returns.amtReturned");
				return "returns-getlookup";
			}
			returns.setPurchasePrice((double) (invoice.getPrice() * returns.getAmtReturned()));
			returns.setPurchaseTax((double) (invoice.getTax() * returns.getAmtReturned()));
			returns.setDatePurchased(header.getProcessed());
			invoice.setAmount(invoice.getAmount() - returns.getAmtReturned());
			invoiceService.updateItem(invoice);
			
			model.addAttribute("returns", returns);
			
			return "returns-submit";
		}
		@RequestMapping("/returns-save")
		public String returnsSave(@ModelAttribute("returns") Returns returns, Model model, Principal principal) {
			
			returns.setDateReturned(new Date());
			returns.setUsername(principal.getName());
			
			returnsService.create(returns);
			logger.info(" RMA # '" + returns.getRmaId() + "' has been created.");
			
			model.addAttribute("returns", returns);
			
			return "returns-getrma";
		}
		
		@RequestMapping("/returns-getlookup")
		public String returnsLookup(Model model) {
			Returns returns = new Returns();
			
			model.addAttribute("returns", returns);
			
			return "returns-getlookup";
		}
		
		@RequestMapping("/returns-status")
		public String showReturnsStatus(Model model, Principal principal) {
			returnsList = returnsService.getReturnsList(principal.getName());
			returnsList.setPage(0);
			returnsList.setPageSize(10);
			model.addAttribute("objectList", returnsList);
			model.addAttribute("pagelink", pageStatLink);
			
			return "returns-status";
		}
		
		@RequestMapping(value="/returnsstatuspaging", method=RequestMethod.GET)
		public ModelAndView handleReturnsPaginRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
			int pgNum;
		    String keyword = request.getParameter("keyword");
		    if (keyword != null) {
		        if (!StringUtils.hasLength(keyword)) {
		            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
		        }

		        returnsList.setPageSize(3);
		        request.getSession().setAttribute("SearchProductsController_productList", returnsList);
		        ModelAndView model = new ModelAndView("returns-status", "objectList", returnsList);
		        model.addObject("pagelink", pageStatLink);
		        
		        return model;        
		    }
		    else {
		        String page = request.getParameter("page");
		        
		        if (returnsList == null) {
		            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
		        }
		        pgNum = isInteger(page);
		        
		        if ("next".equals(page)) {
		        	returnsList.nextPage();
		        }
		        else if ("prev".equals(page)) {
		        	returnsList.previousPage();
		        }else if (pgNum != -1) {
		        	returnsList.setPage(pgNum);
		        }
		        
		        ModelAndView model = new ModelAndView("returns-status", "objectList", returnsList);
		        model.addObject("pagelink", pageStatLink);
		        
		        return model;        
		    }
		}
		@RequestMapping(value="/returnspaging", method=RequestMethod.GET)
		public ModelAndView handleReturnsRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
			int pgNum;
		    String keyword = request.getParameter("keyword");
		    if (keyword != null) {
		        if (!StringUtils.hasLength(keyword)) {
		            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
		        }

		        returnsList.setPageSize(20);
		        request.getSession().setAttribute("SearchProductsController_productList", returnsList);
		        ModelAndView model = new ModelAndView("returns-list", "objectList", returnsList);
		        model.addObject("pagelink", pageLink);
		        
		        return model;        
		    }
		    else {
		        String page = request.getParameter("page");
		        
		        if (returnsList == null) {
		            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
		        }
		        pgNum = isInteger(page);
		        
		        if ("next".equals(page)) {
		        	returnsList.nextPage();
		        }
		        else if ("prev".equals(page)) {
		        	returnsList.previousPage();
		        }else if (pgNum != -1) {
		        	returnsList.setPage(pgNum);
		        }
		        ModelAndView model = new ModelAndView("returns-list", "objectList", returnsList);
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
