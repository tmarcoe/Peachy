package com.gemma.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.web.beans.FileLocations;
import com.gemma.web.dao.Inventory;
import com.gemma.web.service.InventoryService;

@Controller
public class HomeController {

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private FileLocations fileLocations;
	
	@RequestMapping("/home")
	public String showRoot(Model model) {
		String fileLoc = fileLocations.getImageLoc();
		List<Inventory> inventory = inventoryService.listSaleItems();
		
		model.addAttribute("inventory",inventory);
		model.addAttribute("fileLoc", fileLoc);

		return "home";
	}
	
	@RequestMapping("/")
	public String showHome(Model model) {
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
	
	
}
