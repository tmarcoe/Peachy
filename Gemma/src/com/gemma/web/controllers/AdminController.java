package com.gemma.web.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.gemma.spring.web.dao.ChartOfAccounts;
import com.gemma.spring.web.dao.ChartOfAccountsContainer;
import com.gemma.spring.web.dao.Inventory;
import com.gemma.spring.web.dao.InventoryContainer;
import com.gemma.spring.web.dao.UserProfile;
import com.gemma.spring.web.service.ChartOfAccountsService;
import com.gemma.spring.web.service.FileUpload;
import com.gemma.spring.web.service.InventoryService;
import com.gemma.spring.web.service.UserProfileService;

@Controller
@Scope(value="session")
public class AdminController {

	@Autowired
	ServletContext servletContext;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ChartOfAccountsService chartOfAccountsService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private PagedListHolder<UserProfile> userList;
/******************************************************************************
 * Admin Home Page
 ******************************************************************************/
	@RequestMapping("/admin")
	public String showAdmin() {
		return "admin";
	}
/******************************************************************************
 * Manage Inventory
 ******************************************************************************/
	@RequestMapping("/uploadfile")
	public String showUploadFile( Model model) {
		model.addAttribute("fileUpload", new FileUpload());
		
		return "uploadfile";
	}
	
	
	@RequestMapping("/addinventory")
	public String addInventory(@ModelAttribute("fileUpload") FileUpload fileUpload, Model model) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		MultipartFile file = fileUpload.getFile();
		String fileName = file.getOriginalFilename();
		  try {
			   inputStream = file.getInputStream();
			   //String path = servletContext.getResource("WEB-INF").getFile();

			   
			   File newFile = new File("/Users/Timothy Marcoe/workspace/Gemma/WebContent/resources/images/" + fileName);
			   if (!newFile.exists()) {
			    newFile.createNewFile();
			   }
			   outputStream = new FileOutputStream(newFile);
			   int read = 0;
			   byte[] bytes = new byte[1024];

			   while ((read = inputStream.read(bytes)) != -1) {
			    outputStream.write(bytes, 0, read);
			   }
			   outputStream.close();
			  } catch (IOException e) {
			   e.printStackTrace();
			  }

		Inventory inventory = new Inventory();
		inventory.setImage(fileName);
		model.addAttribute("inventory", inventory);

		return "addinventory";
	}

	@RequestMapping("/productadded")
	public String productAdded(@Valid Inventory inventory, Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			return "addinventory";
		}
		
		if (inventoryService.getItem(inventory.getSkuNum()) != null) {
			result.rejectValue("skuNum", "Duplicate.inventory.skuNum");
			return "addinventory";
		}
		
		if (inventory.getImage().length() == 0) {
			inventory.setImage("notavailable.jpg");
		}

		inventoryService.create(inventory);
		List<Inventory> invlist = inventoryService.listSaleItems();
		model.addAttribute("inventory", invlist);

		return "home";
	}
	
	@RequestMapping("/inventorydetails")
	public String showInventoryDetails(@ModelAttribute("InventoryKey") String inventoryKey, Model model) {
		Inventory inventory = inventoryService.getItem(inventoryKey);
		model.addAttribute("inventory", inventory);
	
		return "inventorydetails";
	}

	@RequestMapping("/manageinventory")
	public String showManageInventory(Model model) {
		InventoryContainer container = inventoryService.getContainer();
		model.addAttribute("inventoryContainer1", container);
		
		return "manageinventory";
	}
	
	@RequestMapping("/inventorysaved")
	public String inventorySaved(@ModelAttribute("Inventory") Inventory inventory, Model model) {
		
		inventoryService.update(inventory);
		
		InventoryContainer container = inventoryService.getContainer();
		model.addAttribute("inventoryContainer1", container);

		return "manageinventory";
	}
	
	@RequestMapping("/deleteinventory")
	public String deleteInventory(@ModelAttribute("deleteKey") String deleteKey, Model model) {
		
		inventoryService.delete(deleteKey);
		
		InventoryContainer container = inventoryService.getContainer();
		model.addAttribute("inventoryContainer1", container);
	
		
		return "manageinventory";
	}
	
	@RequestMapping("/saveinventory")
	public String saveOrUpdateInventory(InventoryContainer inventory, Model model) {
		List<Inventory> inventoryList = inventory.getInventoryList();
		inventoryService.saveOrUpdate(inventoryList);
		return "manageinventory";
	}
	
/****************************************************************************************
 * Manage Accounts 
 ****************************************************************************************/
	
	@RequestMapping("/manageaccount")
	public String showMangeAccounts(Model model) {
		ChartOfAccountsContainer accounts = chartOfAccountsService.getContainer();
		
		if (accounts.getAccountsList().size() == 0) {
			ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
			model.addAttribute("chartOfAccounts", chartOfAccounts);

			return "addaccount";
		}
		String deleteKey = new String("");

		model.addAttribute("chartOfAccountsContainer1", accounts);
		model.addAttribute("deleteKey", deleteKey);

		return "manageaccount";
	}

	@RequestMapping("/addaccount")
	public String addAccount(Model model) {
		ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
		model.addAttribute("chartOfAccounts", chartOfAccounts);

		return "addaccount";
	}

	@RequestMapping("/submitaddaccount")
	public String submitAddAccount(@Valid ChartOfAccounts chartOfAccounts, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "manageaccount";
		}

		chartOfAccountsService.create(chartOfAccounts);
		ChartOfAccountsContainer container = chartOfAccountsService.getContainer();
		model.addAttribute("chartOfAccountsContainer1", container);

		return "manageaccount";
	}
	
	@RequestMapping("/saveaccounts")
	public String saveAccounts(ChartOfAccountsContainer accounts, Model model) {
		List<ChartOfAccounts> accountsList = accounts.getAccountsList();
		chartOfAccountsService.update(accountsList);

		accounts.setAccountsList(chartOfAccountsService.listAccounts());
		if (accounts.getAccountsList().size() == 0) {
			ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
			model.addAttribute("chartOfAccounts", chartOfAccounts);

			return "addaccount";
		}

		model.addAttribute("chartOfAccountsContainer1", accounts);

		return "manageaccount";
	}
	
	@RequestMapping("/saveaccount")
	public String saveAccount(ChartOfAccounts chartOfAccounts, Model model) {
		chartOfAccountsService.update(chartOfAccounts);
		
		ChartOfAccountsContainer accounts = chartOfAccountsService.getContainer();
		model.addAttribute("chartOfAccountsContainer1", accounts);

		return "manageaccount";
	}
	
	@RequestMapping("/deleteaccount")
	public String deleteAccount(@ModelAttribute("deleteKey")String deleteKey, Model model) {
		
		chartOfAccountsService.delete(deleteKey);
		
		ChartOfAccountsContainer container = chartOfAccountsService.getContainer();
		model.addAttribute("chartOfAccountsContainer1", container);
		
		return "manageaccount";
	}
	
	@RequestMapping("/accountdetail")
	public String showAccountDetail(@ModelAttribute("detailKey") String detailKey, Model model) {
		
		ChartOfAccounts account = chartOfAccountsService.getAccount(detailKey);
		model.addAttribute("chartOfAccounts", account);
		
		return "accountdetail";
	}
	/**************************************************************************************************
	 * User Profile Management
	 **************************************************************************************************/
	@RequestMapping("/users")
	public String showUsers(@ModelAttribute("page") String page, Model model) {
		userList.setSource( userProfileService.getAllUsers());
		userList.setPageSize(15);
		userList.setPage(0);
 
		model.addAttribute("userList", userList);
		
		return "users";
		
	}
	
	@RequestMapping("/deleteuser")
	public String deleteUser(@ModelAttribute("deleteKey") String deleteKey, Model model){
		
		userProfileService.delete(deleteKey);
		
		return "users";
	}
	
	@RequestMapping("/userdetails")
	public String showUserDetails(@ModelAttribute("detailKey") String detailKey, Model model){
		
		UserProfile userProfile = userProfileService.getUser(detailKey);
		
		model.addAttribute("userProfile", userProfile);
		
		return "userdetails";
	}
	
	@RequestMapping("/saveuser")
	public String partialUpdate(@ModelAttribute("userProfile") UserProfile userProfile, Model model) {
		userProfileService.partialUpdate(userProfile);
		return "users";
	}
	
}
