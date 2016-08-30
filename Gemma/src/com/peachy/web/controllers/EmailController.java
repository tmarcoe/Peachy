package com.peachy.web.controllers;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.peachy.web.email.Email;
import com.peachy.web.email.MsgDisplay;
import com.peachy.web.email.ProcessEmail;

@Controller
public class EmailController {
	private final String pageLink = "/productpaging";
	
	private PagedListHolder<MsgDisplay> msgs;
	
	@RequestMapping("/checkemail")
	public String checkEmail(Model model) throws MessagingException, IOException, URISyntaxException {
		ProcessEmail email = new ProcessEmail();
		Email myEmail = new Email();
		myEmail.setFrom("customer_service@donzalmart.com");
		myEmail.setPassword("In_heaven3!");
		
		msgs = email.receiveEmail(myEmail);
		msgs.setPageSize(15);
		msgs.setPage(0);
		model.addAttribute("objectList", msgs);
		model.addAttribute("pagelink", pageLink);
				
		return "checkemail";
	}
/************************************************************************************************************
 * Paging Handlers
 ************************************************************************************************************/
	
	
	@RequestMapping(value="/emailpaging", method=RequestMethod.GET)
	public ModelAndView handleEmailRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
	    String keyword = request.getParameter("keyword");
	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        request.getSession().setAttribute("SearchProductsController_productList", msgs);
	        ModelAndView model = new ModelAndView("checkemail", "objectList", msgs);
	        model.addObject("pagelink", pageLink);
	        
	        return model;        
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (msgs == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	msgs.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	msgs.previousPage();
	        }else if (pgNum != -1) {
	        	msgs.setPage(pgNum);
	        }
	        ModelAndView model = new ModelAndView("checkemail", "objectList", msgs);
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
