package com.gemma.web.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.web.dao.UserProfile;
import com.gemma.web.service.AccountingService;
import com.gemma.web.service.UserProfileService;


@Controller
public class LoginController {
	
	@Autowired
	private UserProfileService userProfileService;
	
	private static Logger logger = Logger.getLogger(AccountingService.class.getName());
	
	private SimpleDateFormat dateFormat;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping("/login")
	public String showLogin() {
		
		return "login";
	}
	
	@RequestMapping("/blocked")
	public String showBlocked() {
		return "blocked";
	}
	
	@RequestMapping("/error")
	public String showError() {
		return "error";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut(HttpSession session, Model model) {
		session.invalidate();

		return "loggedout";
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

	@RequestMapping("/createprofile")
	public String createProfile( @Valid UserProfile user, BindingResult result) {
		if (result.hasErrors()) {
			return "signup";
		}

		if (userProfileService.exists(user.getUsername())) {
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
	
}
