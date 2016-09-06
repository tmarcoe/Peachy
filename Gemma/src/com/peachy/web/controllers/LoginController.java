package com.peachy.web.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peachy.web.orm.UserProfile;
import com.peachy.web.service.UserProfileService;

@Controller
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserProfileService userProfileService;

	private static Logger logger = Logger.getLogger(LoginController.class
			.getName());

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
		logger.error("User was blocked.");
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

	@RequestMapping("/verify")
	public String verifyEmail(@ModelAttribute("userID") int userID, @ModelAttribute("h") String h, Model model) {
		
		UserProfile user = userProfileService.getUserByID(userID);
		if (user == null) {
			logger.error("User ID " + userID + " failed to load.");
			return "error";
		}
		if (h.compareTo(user.getPassword()) == 0) {
			user.setEnabled(true);
		}else{
			logger.error("User ID " + userID + " had a wrong password.");
			return "blocked";
		}
		userProfileService.updateProfile(user);
		String name = user.getFirstname() + " " + user.getLastname();
		model.addAttribute("name", name);

		return "verify";
	}

}