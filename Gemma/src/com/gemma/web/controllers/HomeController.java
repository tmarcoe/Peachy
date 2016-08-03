package com.gemma.web.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.web.beans.FileLocations;
import com.gemma.web.dao.Inventory;
import com.gemma.web.local.CurrencyExchange;
import com.gemma.web.service.InventoryService;

@Controller
public class HomeController {
	final private String currency = "currency";
	final private String cookiePath = "/";

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private FileLocations fileLocations;

	@RequestMapping("/home")
	public String showRoot(Model model, HttpServletResponse response) throws ClientProtocolException, IOException, URISyntaxException {
		String fileLoc = fileLocations.getImageLoc();
		List<Inventory> inventory = inventoryService.listSaleItems();
		
		model.addAttribute("inventory",inventory);
		model.addAttribute("fileLoc", fileLoc);
		
		return "home";
	}
	
	@RequestMapping("/")
	public String showHome(Model model, HttpServletResponse response) throws ClientProtocolException, IOException, URISyntaxException {
		String fileLoc = fileLocations.getImageLoc();
		
		List<Inventory> inventory = inventoryService.listSaleItems();
		
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
	
	private void putRecord(HttpServletResponse response, String base) throws IOException {
		CurrencyExchange cur = new CurrencyExchange();
		String cookieValue = cur.getRecord(base);
		
		Cookie myCookie = new Cookie(currency, cookieValue);
		myCookie.setPath(cookiePath);
		myCookie.setMaxAge(-1);
		myCookie.setSecure(false);
		
		response.addCookie(myCookie);
		
	}
	
}
