package com.peachy.web.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.peachy.web.beans.FileLocations;
import com.peachy.web.beans.FileUpload;
import com.peachy.web.beans.Order;
import com.peachy.web.dao.Inventory;
import com.peachy.web.service.InventoryService;
import com.peachy.web.service.TransactionService;

@Controller
public class InventoryController implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String pageLink = "/productpaging";

	private static Logger logger = Logger.getLogger(InventoryController.class
			.getName());

	@Autowired
	private InventoryService inventoryService;

	private PagedListHolder<Inventory> adminInventoryList;
	private PagedListHolder<Inventory> orderList;

	@Autowired
	FileLocations fileLocations;

	@Autowired
	TransactionService transactionService;

	@RequestMapping("/productadded")
	public String productAdded(
			@Valid @ModelAttribute("inventory") Inventory inventory,
			BindingResult result, Model model) throws ClientProtocolException,
			IOException, URISyntaxException {
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
		model.addAttribute("objectList", adminInventoryList);
		model.addAttribute("pagelink", pageLink);

		return "manageinventory";
	}

	@RequestMapping("/manageinventory")
	public String showManageInventory(Model model)
			throws ClientProtocolException, IOException, URISyntaxException {

		adminInventoryList = inventoryService.getPagedList();
		adminInventoryList.setPage(0);
		adminInventoryList.setPageSize(15);

		model.addAttribute("objectList", adminInventoryList);
		model.addAttribute("pagelink", pageLink);

		return "manageinventory";
	}

	@RequestMapping("/inventorysaved")
	public String inventorySaved(
			@ModelAttribute("inventory") Inventory inventory, Model model)
			throws ClientProtocolException, IOException, URISyntaxException {

		inventoryService.update(inventory);
		logger.info("'" + inventory.getSkuNum() + "' has been updated.");

		adminInventoryList = inventoryService.getPagedList();
		adminInventoryList.setPage(0);
		adminInventoryList.setPageSize(15);
		model.addAttribute("objectList", adminInventoryList);
		model.addAttribute("pagelink", pageLink);

		return "manageinventory";
	}

	@RequestMapping("/deleteinventory")
	public String deleteInventory(
			@ModelAttribute("deleteKey") String deleteKey, Model model)
			throws URISyntaxException, ClientProtocolException, IOException {
		Inventory inventory = inventoryService.getItem(deleteKey);

		File file = new File(fileLocations.getImgUploadLoc()
				+ inventory.getImage());
		file.delete();
		logger.info("File: " + fileLocations.getImageLoc()
				+ inventory.getImage() + " is deleted.");

		inventoryService.delete(deleteKey);
		logger.info("Item '" + deleteKey + "' is deleted.");
		adminInventoryList = inventoryService.getPagedList();
		adminInventoryList.setPageSize(15);
		model.addAttribute("objectList", adminInventoryList);
		model.addAttribute("pagelink", pageLink);

		return "manageinventory";
	}

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
		String inventoryKey = "";

		model.addAttribute("sku", inventoryKey);

		return "singleitem";
	}

	@RequestMapping("/addinventory")
	public String addInventory(
			@ModelAttribute("fileUpload") FileUpload fileUpload, Model model,
			BindingResult result) throws URISyntaxException {
		File file = null;

		String contentType = fileUpload.getFile().getContentType();

		if (!contentType.equals("image/png")) {
			result.rejectValue("file", "Unsupported.fileUpload.file");
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
		} catch (IOException i) {
			result.rejectValue("file", "IOException.fileUpload.file",
					i.getMessage());
			i.printStackTrace();
		} catch (IllegalStateException s) {
			result.rejectValue("file", "IllegalStateException.fileUpload.file",
					s.getMessage());
		}
		logger.info("'" + file.getPath() + file.getName()
				+ "' has been created.");
		Inventory inventory = new Inventory();
		inventory.setImage(file.getName());

		model.addAttribute("inventory", inventory);

		return "addinventory";
	}

	@RequestMapping("/inventorydetails")
	public String showInventoryDetails(
			@ModelAttribute("InventoryKey") String inventoryKey, Model model)
			throws ClientProtocolException, IOException, URISyntaxException {

		Inventory inventory = inventoryService.getItem(inventoryKey);
		model.addAttribute("inventory", inventory);

		return "inventorydetails";
	}

	@RequestMapping("/orderinventory")
	public String orderInventory(Model model) {

		orderList = inventoryService.getReplenishList();
		orderList.setPageSize(20);
		model.addAttribute("objectList", orderList);
		model.addAttribute("pagelink", "/orderpaging");

		return "orderinventory";
	}

	@RequestMapping("/stockshelves")
	public String stockShelves(@ModelAttribute("order") Order order, Model model)
			throws ClientProtocolException, IOException, URISyntaxException {

		order.setInventory(inventoryService.getItem(order.getInventory()
				.getSkuNum()));
		try {
			transactionService.purchaseInventory(order);
		} catch (IOException | RuntimeException e) {
			return "error";
		}

		logger.info("'" + order.getInventory().getSkuNum()
				+ "' has been purchased.");
		orderList = inventoryService.getReplenishList();
		orderList.setPageSize(20);
		model.addAttribute("objectList", orderList);
		model.addAttribute("pagelink", "/orderpaging");

		return "orderinventory";
	}

	@RequestMapping("/replenish")
	public String replenishStock(@ModelAttribute("sku") String skuNum,
			Model model) throws ClientProtocolException, IOException,
			URISyntaxException {
		Inventory inventory = inventoryService.getItem(skuNum);

		if (inventory == null) {
			String inventoryKey = "";
			String error = "Invalid product code.";

			model.addAttribute("sku", inventoryKey);
			model.addAttribute("error", error);

			return "singleitem";
		}
		Order order = new Order(inventory);
		model.addAttribute("order", order);

		return "replenish";
	}

	@RequestMapping(value = "/orderpaging", method = RequestMethod.GET)
	public ModelAndView handleOrderRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");

		if (keyword != null) {
			if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("error", "message",
						"Please enter a keyword to search for, then press the search button.");
			}

			request.getSession().setAttribute(
					"SearchProductsController_productList", adminInventoryList);
			ModelAndView model = new ModelAndView("orderinventory",
					"objectList", orderList);
			model.addObject("pagelink", "/orderpaging");

			return model;
		} else {
			String page = request.getParameter("page");

			if (orderList == null) {
				return new ModelAndView("error", "message",
						"Your session has timed out. Please start over again.");
			}
			pgNum = isInteger(page);

			if ("next".equals(page)) {
				orderList.nextPage();
			} else if ("prev".equals(page)) {
				orderList.previousPage();
			} else if (pgNum != -1) {
				orderList.setPage(pgNum);
			}
			ModelAndView model = new ModelAndView("orderinventory",
					"objectList", orderList);
			model.addObject("pagelink", "/orderpaging");

			return model;
		}
	}

	@RequestMapping(value = "/productpaging", method = RequestMethod.GET)
	public ModelAndView handleProductRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");

		if (keyword != null) {
			if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("error", "message",
						"Please enter a keyword to search for, then press the search button.");
			}

			request.getSession().setAttribute(
					"SearchProductsController_productList", adminInventoryList);
			ModelAndView model = new ModelAndView("manageinventory",
					"objectList", adminInventoryList);
			model.addObject("pagelink", pageLink);

			return model;
		} else {
			String page = request.getParameter("page");

			if (adminInventoryList == null) {
				return new ModelAndView("error", "message",
						"Your session has timed out. Please start over again.");
			}
			pgNum = isInteger(page);

			if ("next".equals(page)) {
				adminInventoryList.nextPage();
			} else if ("prev".equals(page)) {
				adminInventoryList.previousPage();
			} else if (pgNum != -1) {
				adminInventoryList.setPage(pgNum);
			}
			ModelAndView model = new ModelAndView("manageinventory",
					"objectList", adminInventoryList);
			model.addObject("pagelink", pageLink);

			return model;
		}
	}

	/**************************************************************************************************************************************
	 * Used for both detecting a number, and converting to a number. If this
	 * routine returns a -1, the input parameter was not a number.
	 * 
	 **************************************************************************************************************************************/

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
