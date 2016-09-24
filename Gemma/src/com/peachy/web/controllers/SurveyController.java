package com.peachy.web.controllers;

import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.peachy.web.dao.Survey;
import com.peachy.web.dao.UserProfile;
import com.peachy.web.service.SurveyService;
import com.peachy.web.service.UserProfileService;

@Controller
public class SurveyController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UserProfileController.class
			.getName());
	
	PagedListHolder<Survey> objectList;
	
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
	private SurveyService surveyService;
	
	@Autowired
	private UserProfileService userProfileService;
	
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
	
	@RequestMapping("surveylist")
	public String showSurveyList(Model model) {
		if (objectList != null) {
			objectList.getSource().clear();
			objectList = null;
			System.gc();
		}
		
		objectList = surveyService.retrieveAll();
		objectList.setPage(0);
		objectList.setPageSize(10);
		
		model.addAttribute("objectList", objectList);
		model.addAttribute("pagelink", pageLink);
		 
		return "surveylist";
	}

	@RequestMapping(value = "/surveypaging", method = RequestMethod.GET)
	public ModelAndView handleSurveyPaging(HttpServletRequest request, HttpServletResponse response, Principal principal ) throws Exception {
		
		int pgNum;
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			objectList.setPageSize(4);
			request.getSession().setAttribute(
					"SearchProductsController_productList", objectList);
			ModelAndView model = new ModelAndView("surveylist");
			model.addObject("objectList", objectList);
	        model.addObject("pagelink", pageLink);

			return model;
		} else {
			String page = request.getParameter("page");

			pgNum = isInteger(page);

			if ("next".equals(page)) {
				objectList.nextPage();
			} else if ("prev".equals(page)) {
				objectList.previousPage();
			} else if (pgNum != -1) {
				objectList.setPage(pgNum);
			}

			ModelAndView model = new ModelAndView("surveylist");
			model.addObject("objectList", objectList);
	        model.addObject("pagelink", pageLink);

			return model;
		}
	}

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
