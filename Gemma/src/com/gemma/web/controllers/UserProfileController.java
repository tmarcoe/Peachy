package com.gemma.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gemma.web.beans.FileLocations;
import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.UserProfile;
import com.gemma.web.service.InventoryService;
import com.gemma.web.service.UserProfileService;

@Controller
public class UserProfileController {
	private final String pageLink = "/userpaging";

	@Autowired
	private UserProfileService userProfileService;

	private PagedListHolder<UserProfile> userList;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	FileLocations fileLocations;
	
	private static Logger logger = Logger.getLogger(AdminController.class.getName());
	
	@RequestMapping("/users")
	public String showUsers(@ModelAttribute("page") String page, Model model) {
		userList = userProfileService.getPagedList();
		userList.setPageSize(10);
		userList.setPage(0);

		model.addAttribute("objectList", userList);
		model.addAttribute("pagelink", pageLink);

		return "users";

	}

	@RequestMapping("/deleteuser")
	public String deleteUser(@ModelAttribute("deleteKey") String deleteKey,
			Model model) {

		userProfileService.delete(deleteKey);
		logger.info("User: '" + deleteKey + "' Deleted");
		userList = userProfileService.getPagedList();
		userList.setPageSize(10);
		userList.setPage(0);

		model.addAttribute("objectList", userList);
		model.addAttribute("pagelink", pageLink);
		

		return "users";
	}

	@RequestMapping("/userdetails")
	public String showUserDetails(
			@ModelAttribute("detailKey") String detailKey, Model model) {

		UserProfile userProfile = userProfileService.getUser(detailKey);

		model.addAttribute("userProfile", userProfile);

		return "userdetails";
	}

	@RequestMapping("/saveuser")
	public String partialUpdate(
			@ModelAttribute("userProfile") UserProfile userProfile, Model model) {
		userProfileService.partialUpdate(userProfile);
		if (userProfile.getAuthority().compareTo("ROLE_ADMIN") == 0) {
			userList = userProfileService.getPagedList();
			userList.setPageSize(10);
			userList.setPage(0);

			model.addAttribute("objectList", userList);
			model.addAttribute("pagelink", pageLink);
			
			return "users";
		}else{
			String fileLoc = fileLocations.getImageLoc();
			
			List<Inventory> inventory = inventoryService.listSaleItems();
			
			model.addAttribute("inventory",inventory);
			model.addAttribute("fileLoc", fileLoc);			
		}
		return "home";
	}
	@RequestMapping(value="/userpaging", method=RequestMethod.GET)
	public ModelAndView handleUserRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");
	

	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        request.getSession().setAttribute("SearchProductsController_productList", userList);
	        ModelAndView model = new ModelAndView("users", "objectList", pageLink);
	        model.addObject("pagelink", pageLink);
	        
	        return model;        
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (userList == null) {
		        ModelAndView model = new ModelAndView("users", "objectList", userList);
		        model.addObject("pagelink", pageLink);
		        
		        return model;        
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	userList.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	userList.previousPage();
	        }else if (pgNum != -1) {
	        	userList.setPage(pgNum);
	        }
	        ModelAndView model = new ModelAndView("users", "objectList", userList);
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
