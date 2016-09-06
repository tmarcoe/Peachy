package com.peachy.web.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peachy.web.beans.FileLocations;
import com.peachy.web.local.CurrencyExchange;
import com.peachy.web.orm.Inventory;
import com.peachy.web.orm.UserProfile;
import com.peachy.web.service.InventoryService;
import com.peachy.web.service.UserProfileService;

@Controller
public class HomeController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private FileLocations fileLocations;
	
	@RequestMapping("/home")
	public String showRoot(Model model, HttpServletResponse response, Principal principal) throws Exception {
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
