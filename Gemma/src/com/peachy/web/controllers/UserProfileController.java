package com.peachy.web.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.peachy.web.beans.FileLocations;
import com.peachy.web.dao.Inventory;
import com.peachy.web.dao.UserProfile;
import com.peachy.web.local.CurrencyExchange;
import com.peachy.web.service.InventoryService;
import com.peachy.web.service.UserProfileService;

@Controller
public class UserProfileController {
	private final String pageLink = "/userpaging";

	private static Logger logger = Logger.getLogger(AdminController.class
			.getName());

	@Autowired
	private UserProfileService userProfileService;

	private PagedListHolder<UserProfile> userList;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	FileLocations fileLocations;

	private SimpleDateFormat dateFormat;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

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

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("userProfile", new UserProfile());
		
		return "signup";
	}
	
	@RequestMapping("/mydonzalmart")
	public String showMyDonzalMart(Model model, Principal principal) {
		
		UserProfile user = userProfileService.getUser(principal.getName());
		model.addAttribute("userProfile", user);
		
		return "mydonzalmart";
	}
	
	@RequestMapping("/saveuser")
	public String partialUpdate(
			@ModelAttribute("userProfile") UserProfile userProfile, Model model)
			throws ClientProtocolException, IOException, URISyntaxException {

		userProfileService.partialUpdate(userProfile);
		
		if (userProfile.getAuthority().compareTo("ROLE_ADMIN") == 0) {
			userList = userProfileService.getPagedList();
			userList.setPageSize(10);
			userList.setPage(0);

			model.addAttribute("objectList", userList);
			model.addAttribute("pagelink", pageLink);

			return "users";
		} else {
			String fileLoc = fileLocations.getImageLoc();

			List<Inventory> inventory = inventoryService.listSaleItems();
			CurrencyExchange currency = new CurrencyExchange();

			model.addAttribute("rate", currency.getRate(userProfile.getCurrency()));
			model.addAttribute("currencySymbol", currency.getSymbol(userProfile.getCurrency()));
			model.addAttribute("inventory", inventory);
			model.addAttribute("fileLoc", fileLoc);
		}
		return "home";
	}

	@RequestMapping("/createprofile")
	public String createProfile( @Valid UserProfile user, BindingResult result) {
		if (result.hasErrors()) {
			return "signup";
		}

		if (userProfileService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.userProfile.username");
			return "signup";
		}
		try {
			userProfileService.create(user);
		}catch(DuplicateKeyException e){
			result.reject("duplicate.userProfile.email", "A user already exists with that email address.");
			return "signup";
		}
		logger.info("'" + user.getUsername() + "' has just signed up.");
		return "createprofile";
	}
	
	@RequestMapping("/changepassword")
	public String showChangePassword(Model model, Principal principal) {
		UserProfile user = userProfileService.getUser(principal.getName());
		
		user.setPassword("");
		
		model.addAttribute("userProfile", user);
		
		return "changepassword";
	}
	
	@RequestMapping("/passwordchanged")
	public String passwordChanged(@ModelAttribute("userProfile") UserProfile user, Principal principal, Model model) {
		
		userProfileService.updatePassword(user);
		
		user = userProfileService.getUser(principal.getName());
		
		logger.info("," + user.getUsername() + "' has just changed the password.");
		
		model.addAttribute("userProfile", user);
		return "mydonzalmart";
	}
	
	@RequestMapping("/updateuser")
	public String updateProfile(@ModelAttribute("userProfile") UserProfile user){
		
		userProfileService.updateProfile(user);
		logger.info("'" + user.getUsername() + "' has just changed the user info.");
		
		return("mydonzalmart");
	}
	
	@RequestMapping(value = "/userpaging", method = RequestMethod.GET)
	public ModelAndView handleUserRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");

		if (keyword != null) {
			if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("Error", "message",
						"Please enter a keyword to search for, then press the search button.");
			}

			request.getSession().setAttribute(
					"SearchProductsController_productList", userList);
			ModelAndView model = new ModelAndView("users", "objectList",
					pageLink);
			model.addObject("pagelink", pageLink);

			return model;
		} else {
			String page = request.getParameter("page");

			if (userList == null) {
				ModelAndView model = new ModelAndView("users", "objectList",
						userList);
				model.addObject("pagelink", pageLink);

				return model;
			}
			pgNum = isInteger(page);

			if ("next".equals(page)) {
				userList.nextPage();
			} else if ("prev".equals(page)) {
				userList.previousPage();
			} else if (pgNum != -1) {
				userList.setPage(pgNum);
			}
			ModelAndView model = new ModelAndView("users", "objectList",
					userList);
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
