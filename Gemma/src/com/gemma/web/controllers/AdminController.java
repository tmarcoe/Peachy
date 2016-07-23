package com.gemma.web.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.antlr.v4.runtime.RecognitionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gemma.web.beans.DatePicker;
import com.gemma.web.beans.FileLocations;
import com.gemma.web.beans.FileUpload;
import com.gemma.web.beans.Order;
import com.gemma.web.dao.ChartOfAccounts;
import com.gemma.web.dao.GeneralLedger;
import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.dao.Returns;
import com.gemma.web.dao.UserProfile;
import com.gemma.web.service.ChartOfAccountsService;
import com.gemma.web.service.GeneralLedgerService;
import com.gemma.web.service.InventoryService;
import com.gemma.web.service.InvoiceHeaderService;
import com.gemma.web.service.InvoiceService;
import com.gemma.web.service.ReturnsService;
import com.gemma.web.service.TransactionService;
import com.gemma.web.service.UserProfileService;

@Controller
@Scope(value = "session")
public class AdminController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private InvoiceHeaderService invoiceHeaderService;

	@Autowired
	private ChartOfAccountsService chartOfAccountsService;
	
	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private UserProfileService userProfileService;

	private PagedListHolder<UserProfile> userList;
	
	@Autowired
	private PagedListHolder<GeneralLedger> ledgerList;
	
	@Autowired 
	private PagedListHolder<InvoiceHeader> headerList;
	
	private PagedListHolder<Returns> returnsList;
	
	@Autowired
	private PagedListHolder<Inventory> adminInventoryList;
	
	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	FileLocations fileLocations;
	
	@Autowired
	private ReturnsService returnsService;
	
	private static Logger logger = Logger.getLogger(AdminController.class.getName());
	
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

		headerList.setSource(invoiceHeaderService.getProcessedInvoices());
		headerList.setPage(0);
		headerList.setPageSize(5);
		model.addAttribute("headerList", headerList);
		return "admin";
	}

	/******************************************************************************
	 * Manage Inventory
	 ******************************************************************************/
	@RequestMapping("/uploadfile")
	public String showUploadFile(Model model) {

		FileUpload fileUpload = new FileUpload();
		model.addAttribute("fileUpload", fileUpload);

		return "uploadfile";
	}

	@RequestMapping("/admininventory")
	public String inventoryMainPage() {

		return "admininventory";
	}
	
	@RequestMapping("/singleitem")
	public String restockSingleItem(Model model) {
		String inventoryKey ="";
		
		model.addAttribute("sku", inventoryKey);

		
		return "singleitem";
	}
	
	@RequestMapping("/addinventory")
	public String addInventory(
			@ModelAttribute("fileUpload") FileUpload fileUpload, Model model, BindingResult result) throws URISyntaxException{
		File file = null;


		String contentType = fileUpload.getFile().getContentType();

		if (!contentType.equals("image/png")) {
			result.rejectValue("file","Unsupported.fileUpload.file");
			return "uploadfile";
		}
		
		try {
			InputStream is = fileUpload.getFile().getInputStream();
			File f1 = new File(fileLocations.getImgUploadLoc());

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
		logger.info("'" + file.getPath() + file.getName() + "' has been created.");
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
		logger.info("New inventory item added.");
		adminInventoryList = inventoryService.getPagedList();
		adminInventoryList.setPage(0);
		adminInventoryList.setPageSize(15);
		model.addAttribute("inventory", adminInventoryList);

		return "manageinventory";
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
		
		adminInventoryList = inventoryService.getPagedList();
		adminInventoryList.setPage(0);
		adminInventoryList.setPageSize(15);
		model.addAttribute("inventory", adminInventoryList);

		return "manageinventory";
	}

	@RequestMapping("/inventorysaved")
	public String inventorySaved(
			@ModelAttribute("inventory") Inventory inventory, Model model) {

		inventoryService.update(inventory);
		logger.info("'" + inventory.getSkuNum() + "' has been updated.");

		adminInventoryList = inventoryService.getPagedList();
		adminInventoryList.setPage(0);
		adminInventoryList.setPageSize(15);
		model.addAttribute("inventory", adminInventoryList);

		return "manageinventory";
	}

	@RequestMapping("/deleteinventory")
	public String deleteInventory(
			@ModelAttribute("deleteKey") String deleteKey, Model model) throws URISyntaxException {
		Inventory inventory = inventoryService.getItem(deleteKey);
		
		File file = new File(new URI(fileLocations.getImageLoc() + inventory.getImage()));
		file.delete();
		logger.info("File: " + fileLocations.getImageLoc() + inventory.getImage() + " is deleted." );

		inventoryService.delete(deleteKey);
		logger.info("Item '" + deleteKey + "' is deleted.");
		adminInventoryList = inventoryService.getPagedList();
		adminInventoryList.setPageSize(15);
		model.addAttribute("inventory", adminInventoryList);

		return "manageinventory";
	}

	@RequestMapping("/orderinventory")
	public String orderInventory(Model model) {

		List<Inventory> orderList = inventoryService.getReplenishList();
		model.addAttribute("orderList", orderList);
		
		return "orderinventory";
	}
	
	@RequestMapping("/stockshelves")
	public String stockShelves(@ModelAttribute("order") Order order, Model model) {
		
		order.setInventory(inventoryService.getItem(order.getInventory().getSkuNum()));
		try {
			transactionService.purchaseInventory(order);
		} catch (IOException | RuntimeException e) {
			return "error";
		}
		
		logger.info("'" + order.getInventory().getSkuNum() + "' has been purchased.");
		List<Inventory> orderList = inventoryService.getReplenishList();
		model.addAttribute("orderList", orderList);
		
		return "orderinventory";
	}
	
	@RequestMapping("/replenish")
	public String replenishStock(@ModelAttribute("sku") String skuNum, Model model) {
		Inventory inventory = inventoryService.getItem(skuNum);
		
		if (inventory == null) {			
			String inventoryKey ="";
			String error = "Invalid product code.";
			
			model.addAttribute("sku", inventoryKey);
			model.addAttribute("error", error);
			
			return "singleitem";
		}
		Order order = new Order(inventory);
		model.addAttribute("order", order);
		
		return "replenish";
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
			return "addaccount";
		}
		if (chartOfAccountsService.exists(chartOfAccounts.getAccountNum()) == true){
			result.rejectValue("accountNum", "DuplicateKey.chartOfAccounts.accountNum");
			return "addaccount";
		}
		chartOfAccountsService.create(chartOfAccounts);
		logger.info("'" + chartOfAccounts.getAccountNum() + "' has been created." );
		List<ChartOfAccounts> accounts = chartOfAccountsService.listAccounts();

		String deleteKey = new String("");

		model.addAttribute("accounts", accounts);
		model.addAttribute("deleteKey", deleteKey);

		return "manageaccount";
	}


	@RequestMapping("/saveaccount")
	public String saveAccount(ChartOfAccounts chartOfAccounts, Model model) {
		chartOfAccountsService.update(chartOfAccounts);
		logger.info("Account # '" + chartOfAccounts.getAccountNum() + "' has been updated.");

		List<ChartOfAccounts> accounts = chartOfAccountsService.listAccounts();
		model.addAttribute("accounts", accounts);

		return "manageaccount";
	}

	@RequestMapping("/deleteaccount")
	public String deleteAccount(@ModelAttribute("deleteKey") String deleteKey,
			Model model) {

		chartOfAccountsService.delete(deleteKey);
		
		logger.info("Account # '" + deleteKey + "' has been deleted.");

		List<ChartOfAccounts> accounts = chartOfAccountsService.listAccounts();

		if (accounts.size() == 0) {
			ChartOfAccounts chartOfAccounts = new ChartOfAccounts();
			model.addAttribute("chartOfAccounts", chartOfAccounts);

			return "addaccount";
		}
		deleteKey = new String("");

		model.addAttribute("accounts", accounts);
		model.addAttribute("deleteKey", deleteKey);

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
		userList = userProfileService.getPagedList();
		userList.setPageSize(10);
		userList.setPage(0);

		model.addAttribute("userList", userList);

		return "users";

	}

	@RequestMapping("/deleteuser")
	public String deleteUser(@ModelAttribute("deleteKey") String deleteKey,
			Model model) {

		userProfileService.delete(deleteKey);
		logger.info("User: '" + deleteKey + "' Deleted");
		userList = userProfileService.getPagedList();
		userList.setPageSize(10);
		userList.setPage(0);

		model.addAttribute("userList", userList);
		

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
		if (userProfile.getAuthority().compareTo("ROLE_ADMIN") == 0) {
			userList = userProfileService.getPagedList();
			userList.setPageSize(10);
			userList.setPage(0);

			model.addAttribute("userList", userList);
			return "users";
		}else{
			String fileLoc = fileLocations.getImageLoc();
			
			List<Inventory> inventory = inventoryService.listSaleItems();
			
			model.addAttribute("inventory",inventory);
			model.addAttribute("fileLoc", fileLoc);			
		}
		return "home";
	}
/************************************************************************************
 * Returns
 *************************************************************************************/
	
	@RequestMapping("/returns-list")
	public String showReturns(Model model) {
		
		returnsList = returnsService.getReturnsList();

		model.addAttribute("returnsList", returnsList);
		
		return "returns-list";
	}
	
	@RequestMapping("/returns-approve")
	public String approveReturns(@ModelAttribute("rmaId") Integer rmaId, Model model) {
		Returns returns = returnsService.getRma(rmaId);
		
		model.addAttribute("returns", returns);
		
		return "returns-approve";
	}
	
	@RequestMapping("/returns-update")
	public String saveReturns(@ModelAttribute("returns") Returns returns, Model model) {
		
		returns.setDateProcessed(new Date());
		
		returnsService.update(returns);
		try {
			transactionService.returnMerchandise(returns);
		} catch (IOException | RuntimeException e) {
			return "error";
		}
		
		returnsList = returnsService.getReturnsList();

		model.addAttribute("returnsList", returnsList);
		
		return "returns-list";
	}
/**************************************************************************************
 * General Ledger
 * 
 *************************************************************************************/

	@RequestMapping("/datepicker")
	public String pickDate(@Validated Model model) {
		DatePicker datePicker = new DatePicker();
		
		model.addAttribute("datePicker", datePicker);
		
		return "datepicker";
	}
	
	@RequestMapping(value="/generalledger", method = RequestMethod.POST)
	public String viewGeneralLedger(@Validated @ModelAttribute(value="datePicker") DatePicker picker, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "datepicker";
		}
		
		picker.setSf(dateFormat);
		ledgerList = generalLedgerService.getPagedList(picker);
		ledgerList.setPage(0);
		ledgerList.setPageSize(20);

		model.addAttribute("ledgerList", ledgerList);
		
		return "generalledger";
	}
/*******************************************************************************************************************
 * POD
 *******************************************************************************************************************/
	@RequestMapping("/podnotify")
	public String podNotify(Model model) {
		String invNum = "";
		
		model.addAttribute("invNum", invNum);
		
		return "podnotify";
	}
	
	@RequestMapping("/podconfirm")
	public String podConfirm(@ModelAttribute("invNum") String invNum, Model model, BindingResult result) {
		if ("".compareTo(invNum) == 0) {
			
			headerList.setSource(invoiceHeaderService.getProcessedInvoices());
			headerList.setPage(0);
			headerList.setPageSize(10);
			model.addAttribute("headerList", headerList);
			
			return "admin";
		}
		InvoiceHeader header = invoiceHeaderService.getInvoiceHeader(Integer.valueOf(invNum));
		if (header == null ) {
			String errMsg = "Invoice not found";
			
			model.addAttribute("errMsg", errMsg);
			model.addAttribute("invNum", invNum);
			
			return "podnotify";
		}
		if (header.isPod() == false) {
			
			String errMsg = "This invoice is not available for processing.";
			
			model.addAttribute("errMsg", errMsg);
			model.addAttribute("invNum", invNum);
			
			return "podnotify";
		}
		if (header.getDateShipped() == null) {
			
			String errMsg = "This invoice has not shipped yet.";
			
			model.addAttribute("errMsg", errMsg);
			model.addAttribute("invNum", invNum);
			
			return "podnotify";
		}
		
		model.addAttribute("invHeader", header);
		
		return "podconfirm";
	}
	@RequestMapping("/podsave")
	public String podSave(@ModelAttribute("header") InvoiceHeader header, Model model ) throws RecognitionException, IOException, RuntimeException {
		transactionService.processPod(header);
		header.setPod(false);
		invoiceHeaderService.updateHeader(header);
		
		headerList.setSource(invoiceHeaderService.getProcessedInvoices());
		headerList.setPage(0);
		headerList.setPageSize(10);
		model.addAttribute("headerList", headerList);
		
		return "admin";
	}
/*********************************************************************************************************************
 * Pageination Handlers
 * 
 ********************************************************************************************************************/
	
	@RequestMapping(value="/returnspaging", method=RequestMethod.GET)
	public ModelAndView handleReturnsRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pgNum;
	    String keyword = request.getParameter("keyword");
	    if (keyword != null) {
	        if (!StringUtils.hasLength(keyword)) {
	            return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
	        }

	        returnsList.setPageSize(20);
	        request.getSession().setAttribute("SearchProductsController_productList", headerList);
	        return new ModelAndView("returns-list", "returnsList", returnsList);
	    }
	    else {
	        String page = request.getParameter("page");
	        
	        if (returnsList == null) {
	            return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
	        }
	        pgNum = isInteger(page);
	        
	        if ("next".equals(page)) {
	        	returnsList.nextPage();
	        }
	        else if ("prev".equals(page)) {
	        	returnsList.previousPage();
	        }else if (pgNum != -1) {
	        	returnsList.setPage(pgNum);
	        }
	        return new ModelAndView("returns-list", "returnsList", returnsList);
	    }
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
/**************************************************************************************************************************************
 * Used for both detecting a number, and converting to a number. If this routine returns a -1, the input parameter was not a number.
 * 
 **************************************************************************************************************************************/
	
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
}
