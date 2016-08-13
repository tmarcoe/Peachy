package com.gemma.web.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.web.beans.FileLocations;
import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.UserProfile;
import com.gemma.web.local.CurrencyExchange;
import com.gemma.web.service.InventoryService;
import com.gemma.web.service.UserProfileService;

@Controller
public class HomeController {

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private FileLocations fileLocations;
	
	@RequestMapping("/home")
	public String showRoot(Model model, HttpServletResponse response, Principal principal) throws ClientProtocolException, IOException, URISyntaxException {
		double rate;
		String currencySymbol = "₱";
		if (principal != null) {
			CurrencyExchange currency = new CurrencyExchange();
			UserProfile user = userProfileService.getUser(principal.getName());
			rate = currency.getRate(user.getCurrency());
			currencySymbol = currency.getSymbol(user.getCurrency());
		}else{
			rate = 1;
		}
		String fileLoc = fileLocations.getImageLoc();
		List<Inventory> inventory = inventoryService.listSaleItems();
		
		model.addAttribute("currencySymbol", currencySymbol);
		model.addAttribute("rate", rate);
		model.addAttribute("inventory",inventory);
		model.addAttribute("fileLoc", fileLoc);
		
		return "home";
	}
	
	@RequestMapping("/")
	public String showHome(Model model, HttpServletResponse response, Principal principal) throws ClientProtocolException, IOException, URISyntaxException {
		double rate;
		String currencySymbol = "₱";
		if (principal != null) {
			CurrencyExchange currency = new CurrencyExchange();
			UserProfile user = userProfileService.getUser(principal.getName());
			rate = currency.getRate(user.getCurrency());
			currencySymbol = currency.getSymbol(user.getCurrency());
		}else{
			rate = 1;
		}
		String fileLoc = fileLocations.getImageLoc();
		
		List<Inventory> inventory = inventoryService.listSaleItems();

		model.addAttribute("currencySymbol", currencySymbol);
		model.addAttribute("rate", rate);
		model.addAttribute("inventory",inventory);
		model.addAttribute("fileLoc", fileLoc);
		
		return "home";
	}
	
	
	@RequestMapping("/contactus")
	public String contactUs() {

		return "contactus";
	}
	
	@RequestMapping("/aboutus")
	public String aboutUs() {

		return "aboutus";
	}
	
}
