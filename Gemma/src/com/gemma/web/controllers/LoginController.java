package com.gemma.web.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.web.dao.UserProfile;
import com.gemma.web.service.UserProfileService;


@Controller
public class LoginController {
	
	@Autowired
	private UserProfileService userProfileService;
	
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/error")
	public String showError() {
		return "error";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut(HttpSession session, Model model) {

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
	
		model.addAttribute("userProfile", user);
		
		
		return "mydonzalmart";
	}
	
	@RequestMapping("/updateuser")
	public String updateProfile(@ModelAttribute("userProfile") UserProfile user){
		
		userProfileService.updateProfile(user);
		
		return("mydonzalmart");
	}
	
}
