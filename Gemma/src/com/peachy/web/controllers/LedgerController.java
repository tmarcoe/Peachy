package com.peachy.web.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
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

import com.peachy.web.beans.DatePicker;
import com.peachy.web.orm.GeneralLedger;
import com.peachy.web.service.GeneralLedgerService;

@Controller
public class LedgerController implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String pageLink = "/ledgerpaging";

	@Autowired
	private GeneralLedgerService generalLedgerService;

	private PagedListHolder<GeneralLedger> ledgerList;

	private SimpleDateFormat dateFormat;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping("/datepicker")
	public String pickDate(@Validated Model model) {
		DatePicker datePicker = new DatePicker();

		model.addAttribute("datePicker", datePicker);

		return "datepicker";
	}

	@RequestMapping(value = "/generalledger", method = RequestMethod.POST)
	public String viewGeneralLedger(
			@Validated @ModelAttribute(value = "datePicker") DatePicker picker,
			Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "datepicker";
		}

		picker.setSf(dateFormat);
		ledgerList = generalLedgerService.getPagedList(picker);
		ledgerList.setPage(0);
		ledgerList.setPageSize(20);

		model.addAttribute("objectList", ledgerList);
		model.addAttribute("pagelink", pageLink);

		return "generalledger";
	}

	@RequestMapping(value = "/ledgerpaging", method = RequestMethod.GET)
	public ModelAndView handleLedgerRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pgNum;
		String keyword = request.getParameter("keyword");

		if (keyword != null) {
			if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("Error", "message",
						"Please enter a keyword to search for, then press the search button.");
			}

			ledgerList.setPageSize(25);
			request.getSession().setAttribute(
					"SearchProductsController_productList", ledgerList);
	        ModelAndView model = new ModelAndView("generalledger", "objectList", ledgerList);
	        model.addObject("pagelink", pageLink);
	        
	        return model;
		} else {
			String page = request.getParameter("page");

			if (ledgerList == null) {
				return new ModelAndView("Error", "message",
						"Your session has timed out. Please start over again.");
			}
			pgNum = isInteger(page);

			if ("next".equals(page)) {
				ledgerList.nextPage();
			} else if ("prev".equals(page)) {
				ledgerList.previousPage();
			} else if (pgNum != -1) {
				ledgerList.setPage(pgNum);
			}
	        ModelAndView model = new ModelAndView("generalledger", "objectList", ledgerList);
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
