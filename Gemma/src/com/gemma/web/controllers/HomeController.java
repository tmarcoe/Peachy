package com.gemma.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.spring.web.dao.Inventory;
import com.gemma.spring.web.service.InventoryService;

@Controller
public class HomeController {

	@Autowired
	private InventoryService inventoryService;
	

	
	@RequestMapping("/home")
	public String showRoot(Model model) {
		List<Inventory> inventory = inventoryService.listSaleItems();
		model.addAttribute("inventory",inventory);

		return "home";
	}
	
	@RequestMapping("/")
	public String showHome(Model model) {
		List<Inventory> inventory = inventoryService.listSaleItems();
		model.addAttribute("inventory",inventory);


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
