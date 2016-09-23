package com.peachy.web.controllers;

import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peachy.web.dao.Survey;
import com.peachy.web.dao.UserProfile;
import com.peachy.web.service.SurveyService;
import com.peachy.web.service.UserProfileService;

@Controller
public class SurveyController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UserProfileController.class
			.getName());
	private final String pageLink = "/surveypaging";
	private SimpleDateFormat dateFormat;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@Autowired
	SurveyService surveyService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@RequestMapping("/surveyinput")
	public String surveyInput(Model model, Principal principal) {
		UserProfile user = userProfileService.getUser(principal.getName());
		logger.info(user.getFirstname() + " " + user.getLastname() + " has filled out the survey.");
		Survey survey = new Survey();
		survey.setUserID(user.getUserID());
		
		model.addAttribute("survey", survey);
		
		return "surveyinput";
	}
	
	@RequestMapping("/submitsurvey")
	public String submitSurvey(@ModelAttribute("survey") Survey survey) {
		survey.setFilledOut(new Date());
		surveyService.create(survey);
		
		return "submitsurvey";
	}

	

}
