package com.gemma.web.controllers;

import java.io.Serializable;

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

import com.gemma.web.dao.ChartOfAccounts;
import com.gemma.web.service.ChartOfAccountsService;

@Controller
public class AccountsController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AccountsController.class.getName());
	private final String pageLink = "/accountpaging";
	private final int pageSize = 10;
	@Autowired
	private ChartOfAccountsService chartOfAccountsService;
	
	PagedListHolder<ChartOfAccounts> accounts;
	
	@RequestMapping("/manageaccount")
	public String showMangeAccounts(Model model) {
		accounts = chartOfAccountsService.listAccounts();
		accounts.setPage(0);
		accounts.setPageSize(pageSize);
		
		if (accounts.getSource().size() == 0) {
			ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
			model.addAttribute("chartOfAccounts", chartOfAccounts);

			return "addaccount";
		}
		String deleteKey = new String("");

		model.addAttribute("objectList", accounts);
		model.addAttribute("deleteKey", deleteKey);
		model.addAttribute("pagelink", pageLink);
		
		return "manageaccount";
	}

	@RequestMapping("/addaccount")
	public String addAccount(Model model) {
		ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
		model.addAttribute("chartOfAccounts", chartOfAccounts);

		return "addaccount";
	}

	@RequestMapping("/submitaddaccount")
	public String submitAddAccount(@Valid ChartOfAccounts chartOfAccounts,
			Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "addaccount";
		}
		if (chartOfAccountsService.exists(chartOfAccounts.getAccountNum()) == true){
			result.rejectValue("accountNum", "DuplicateKey.chartOfAccounts.accountNum");
			return "addaccount";
		}
		chartOfAccountsService.create(chartOfAccounts);
		logger.info("'" + chartOfAccounts.getAccountNum() + "' has been created." );
		accounts = chartOfAccountsService.listAccounts();
		accounts.setPage(0);
		accounts.setPageSize(pageSize);

		String deleteKey = new String("");

		model.addAttribute("objectList", accounts);
		model.addAttribute("deleteKey", deleteKey);
		model.addAttribute("pagelink", pageLink);

		return "manageaccount";
	}


	@RequestMapping("/saveaccount")
	public String saveAccount(ChartOfAccounts chartOfAccounts, Model model) {
		chartOfAccountsService.update(chartOfAccounts);
		logger.info("Account # '" + chartOfAccounts.getAccountNum() + "' has been updated.");

		accounts = chartOfAccountsService.listAccounts();
		accounts.setPage(0);
		accounts.setPageSize(pageSize);
		model.addAttribute("objectList", accounts);
		model.addAttribute("pagelink", pageLink);

		return "manageaccount";
	}

	@RequestMapping("/deleteaccount")
	public String deleteAccount(@ModelAttribute("deleteKey") String deleteKey,
			Model model) {

		chartOfAccountsService.delete(deleteKey);
		
		logger.info("Account # '" + deleteKey + "' has been deleted.");

		accounts = chartOfAccountsService.listAccounts();
		accounts.setPage(0);
		accounts.setPageSize(pageSize);
		if (accounts.getSource().size() == 0) {
			ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
			model.addAttribute("chartOfAccounts", chartOfAccounts);

			return "addaccount";
		}
		deleteKey = new String("");

		model.addAttribute("objectList", accounts);
		model.addAttribute("deleteKey", deleteKey);
		model.addAttribute("pagelink", pageLink);

		return "manageaccount";
	}

	@RequestMapping("/accountdetail")
	public String showAccountDetail(
			@ModelAttribute("detailKey") String detailKey, Model model) {

		ChartOfAccounts account = chartOfAccountsService.getAccount(detailKey);
		model.addAttribute("chartOfAccounts", account);

		return "accountdetail";
	}
	
	@RequestMapping(value="/accountpaging", method=RequestMethod.GET)
	public ModelAndView handleAccountsRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");		

	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        request.getSession().setAttribute("SearchProductsController_productList", accounts);
	        ModelAndView model = new ModelAndView("manageaccount", "objectList", accounts);
	        model.addObject("pagelink", pageLink);
	        
	        return model;        
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (accounts == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	accounts.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	accounts.previousPage();
	        }else if (pgNum != -1) {
	        	accounts.setPage(pgNum);
	        }
	        ModelAndView model = new ModelAndView("manageaccount", "objectList", accounts);
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
