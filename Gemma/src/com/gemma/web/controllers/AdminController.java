package com.gemma.web.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gemma.spring.web.dao.ChartOfAccounts;
import com.gemma.spring.web.dao.ChartOfAccountsContainer;
import com.gemma.spring.web.dao.GeneralLedger;
import com.gemma.spring.web.dao.Inventory;
import com.gemma.spring.web.dao.InventoryContainer;
import com.gemma.spring.web.dao.InvoiceHeader;
import com.gemma.spring.web.dao.UserProfile;
import com.gemma.spring.web.service.AccountingService;
import com.gemma.spring.web.service.ChartOfAccountsService;
import com.gemma.spring.web.service.GeneralLedgerService;
import com.gemma.spring.web.service.InventoryService;
import com.gemma.spring.web.service.InvoiceService;
import com.gemma.spring.web.service.UserProfileService;
import com.gemma.web.beans.DatePicker;
import com.gemma.web.beans.FileUpload;
import com.gemma.web.beans.Order;

@Controller
@Scope(value = "session")
public class AdminController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String filePath = "C:\\Users\\Timothy Marcoe\\WebSite Archive\\Gemma\\WebContent\\resources\\images\\products";

	@Autowired
	ServletContext servletContext;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ChartOfAccountsService chartOfAccountsService;
	
	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private PagedListHolder<UserProfile> userList;
	
	@Autowired
	private PagedListHolder<GeneralLedger> ledgerList;
	
	@Autowired 
	private PagedListHolder<InvoiceHeader> headerList;
	
	@Autowired
	private PagedListHolder<Inventory> adminInventoryList;
	
	@Autowired
	private FileUpload fileUpload;
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private AccountingService accountingService;
	
	private SimpleDateFormat dateFormat;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	/******************************************************************************
	 * Admin Home Page
	 ******************************************************************************/
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		headerList.setSource(invoiceService.getProcessedInvoices());
		headerList.setPage(0);
		headerList.setPageSize(20);
		model.addAttribute("headerList", headerList);
		return "admin";
	}

	/******************************************************************************
	 * Manage Inventory
	 ******************************************************************************/
	@RequestMapping("/uploadfile")
	public String showUploadFile(Model model) {
		model.addAttribute("fileUpload", fileUpload);

		return "uploadfile";
	}

	@RequestMapping("/addinventory")
	public String addInventory(
			@ModelAttribute("fileUpload") FileUpload fileUpload, Model model, BindingResult result){
		File file = null;
		String contentType = fileUpload.getFile().getContentType();

		if (!contentType.equals("image/png")) {
			result.rejectValue("file","Unsupported.fileUpload.file");
			return "uploadfile";
		}
		
		try {
			InputStream is = fileUpload.getFile().getInputStream();

			File f1 = new File(filePath);

			file = File.createTempFile("img", ".png", f1);

			FileOutputStream fos = new FileOutputStream(file);

			int data = 0;
			while ((data = is.read()) != -1) {
				fos.write(data);
			}

			fos.close();
			is.close();
		}catch(IOException i) {
			result.rejectValue("file", "IOException.fileUpload.file", i.getMessage());
			i.printStackTrace();
		}catch(IllegalStateException s){
			result.rejectValue("file", "IllegalStateException.fileUpload.file", s.getMessage());
		}
		Inventory inventory = new Inventory();
		inventory.setImage(file.getName());

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
	public String showInventoryDetails(
			@ModelAttribute("InventoryKey") String inventoryKey, Model model) {
		Inventory inventory = inventoryService.getItem(inventoryKey);
		model.addAttribute("inventory", inventory);

		return "inventorydetails";
	}

	@RequestMapping("/manageinventory")
	public String showManageInventory(Model model) {
		adminInventoryList.setSource(inventoryService.listProducts());
		adminInventoryList.setPage(0);
		adminInventoryList.setPageSize(15);
		model.addAttribute("inventory", adminInventoryList);

		return "manageinventory";
	}

	@RequestMapping("/inventorysaved")
	public String inventorySaved(
			@ModelAttribute("Inventory") Inventory inventory, Model model) {

		inventoryService.update(inventory);

		adminInventoryList.setSource(inventoryService.listProducts());
		adminInventoryList.setPage(0);
		adminInventoryList.setPageSize(15);
		model.addAttribute("inventory", adminInventoryList);

		return "manageinventory";
	}

	@RequestMapping("/deleteinventory")
	public String deleteInventory(
			@ModelAttribute("deleteKey") String deleteKey, Model model) {
		Inventory inventory = inventoryService.getItem(deleteKey);
		
		File file = new File(filePath + "\\" + inventory.getImage());
		file.delete();

		inventoryService.delete(deleteKey);

		InventoryContainer container = inventoryService.getContainer();
		model.addAttribute("inventoryContainer1", container);

		return "manageinventory";
	}

	@RequestMapping("/saveinventory")
	public String saveOrUpdateInventory(InventoryContainer inventory,
			Model model) {
		List<Inventory> inventoryList = inventory.getInventoryList();
		inventoryService.saveOrUpdate(inventoryList);
		return "manageinventory";
	}

	/****************************************************************************************
	 * Manage Accounts
	 ****************************************************************************************/

	@RequestMapping("/manageaccount")
	public String showMangeAccounts(Model model) {
		List<ChartOfAccounts> accounts = chartOfAccountsService.listAccounts();

		if (accounts.size() == 0) {
			ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
			model.addAttribute("chartOfAccounts", chartOfAccounts);

			return "addaccount";
		}
		String deleteKey = new String("");

		model.addAttribute("accounts", accounts);
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
	public String submitAddAccount(@Valid ChartOfAccounts chartOfAccounts,
			Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "manageaccount";
		}

		chartOfAccountsService.create(chartOfAccounts);
		ChartOfAccountsContainer container = chartOfAccountsService
				.getContainer();
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

		List<ChartOfAccounts> accounts = chartOfAccountsService.listAccounts();
		model.addAttribute("accounts", accounts);

		return "manageaccount";
	}

	@RequestMapping("/deleteaccount")
	public String deleteAccount(@ModelAttribute("deleteKey") String deleteKey,
			Model model) {

		chartOfAccountsService.delete(deleteKey);

		ChartOfAccountsContainer container = chartOfAccountsService
				.getContainer();
		model.addAttribute("chartOfAccountsContainer1", container);

		return "manageaccount";
	}

	@RequestMapping("/accountdetail")
	public String showAccountDetail(
			@ModelAttribute("detailKey") String detailKey, Model model) {

		ChartOfAccounts account = chartOfAccountsService.getAccount(detailKey);
		model.addAttribute("chartOfAccounts", account);

		return "accountdetail";
	}

	/**************************************************************************************************
	 * User Profile Management
	 **************************************************************************************************/
	@RequestMapping("/users")
	public String showUsers(@ModelAttribute("page") String page, Model model) {
		userList.setSource(userProfileService.getAllUsers());
		userList.setPageSize(10);
		userList.setPage(0);

		model.addAttribute("userList", userList);

		return "users";

	}

	@RequestMapping("/deleteuser")
	public String deleteUser(@ModelAttribute("deleteKey") String deleteKey,
			Model model) {

		userProfileService.delete(deleteKey);

		return "users";
	}

	@RequestMapping("/userdetails")
	public String showUserDetails(
			@ModelAttribute("detailKey") String detailKey, Model model) {

		UserProfile userProfile = userProfileService.getUser(detailKey);

		model.addAttribute("userProfile", userProfile);

		return "userdetails";
	}

	@RequestMapping("/saveuser")
	public String partialUpdate(
			@ModelAttribute("userProfile") UserProfile userProfile, Model model) {
		userProfileService.partialUpdate(userProfile);
		return "users";
	}
/**************************************************************************************
 * Misc admin tasks
 * 
 *************************************************************************************/

	@RequestMapping("/datepicker")
	public String pickDate(Model model) {
		DatePicker datePicker = new DatePicker();
		
		model.addAttribute("datePicker", datePicker);
		
		return "datepicker";
	}
	
	@RequestMapping("/generalledger")
	public String viewGeneralLedger(@ModelAttribute("datePicker") DatePicker picker, Model model) {

		picker.setSf(dateFormat);
		ledgerList.setSource(generalLedgerService.getList(picker));
		ledgerList.setPage(0);
		ledgerList.setPageSize(25);

		model.addAttribute("ledgerList", ledgerList);
		
		return "generalledger";
	}
	@RequestMapping(value="/userpaging", method=RequestMethod.GET)
	public ModelAndView handleUserRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");
	

	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        request.getSession().setAttribute("SearchProductsController_productList", adminInventoryList);
	        return new ModelAndView("users", "userList", userList);
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (userList == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	userList.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	userList.previousPage();
	        }else if (pgNum != -1) {
	        	userList.setPage(pgNum);
	        }
	        return new ModelAndView("users", "userList", userList);
	    }
	}
	
	@RequestMapping(value="/productpaging", method=RequestMethod.GET)
	public ModelAndView handleProductRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");
	

	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        request.getSession().setAttribute("SearchProductsController_productList", adminInventoryList);
	        return new ModelAndView("manageinventory", "inventory", adminInventoryList);
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (adminInventoryList == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	adminInventoryList.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	adminInventoryList.previousPage();
	        }else if (pgNum != -1) {
	        	adminInventoryList.setPage(pgNum);
	        }
	        return new ModelAndView("manageinventory", "inventory", adminInventoryList);
	    }
	}
	
	
	@RequestMapping(value="/ledgerpaging", method=RequestMethod.GET)
	public ModelAndView handleLedgerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");
	

	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        ledgerList.setPageSize(25);
	        request.getSession().setAttribute("SearchProductsController_productList", ledgerList);
	        return new ModelAndView("generalledger", "ledgerList", ledgerList);
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (ledgerList == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	ledgerList.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	ledgerList.previousPage();
	        }else if (pgNum != -1) {
	        	ledgerList.setPage(pgNum);
	        }
	        return new ModelAndView("generalledger", "ledgerList", ledgerList);
	    }
	}
	
	private int isInteger(String s) {
		int retInt;
	    try { 
	    	retInt = Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return -1; 
	    } catch(NullPointerException e) {
	        return -1;
	    }
	    // only got here if we didn't return false
	    return retInt;
	}
	@RequestMapping("/orderinventory")
	public String orderInventory(Model model) {
		int min = 100;
		List<Inventory> orderList = inventoryService.getReplenishList(min);
		model.addAttribute("orderList", orderList);
		
		return "orderinventory";
	}
	@RequestMapping("/stockshelves")
	public String stockShelves(@ModelAttribute("/stockshelves") Order order, Model model) {
		order.setInventory(inventoryService.getItem(order.getInventory().getSkuNum()));
		accountingService.purchaseInventory(order);
		int min = 100;
		List<Inventory> orderList = inventoryService.getReplenishList(min);
		model.addAttribute("orderList", orderList);
		
		return "orderinventory";
	}
	
	@RequestMapping("/replenish")
	public String replenishStock(@ModelAttribute("sku") String skuNum, Model model) {
		Inventory inventory = inventoryService.getItem(skuNum);
		Order order = new Order(inventory);
		model.addAttribute("order", order);
		
		return "replenish";
	}
	
	@RequestMapping(value="/headerpaging", method=RequestMethod.GET)
	public ModelAndView handleHeaderRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
	    String keyword = request.getParameter("keyword");
	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        headerList.setPageSize(20);
	        request.getSession().setAttribute("SearchProductsController_productList", headerList);
	        return new ModelAndView("admin", "headerList", headerList);
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (headerList == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	headerList.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	headerList.previousPage();
	        }else if (pgNum != -1) {
	        	headerList.setPage(pgNum);
	        }
	        return new ModelAndView("admin", "headerList", headerList);
	    }
	}
	
}
