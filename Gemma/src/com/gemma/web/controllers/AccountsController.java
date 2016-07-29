package com.gemma.web.controllers;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.web.dao.ChartOfAccounts;
import com.gemma.web.service.ChartOfAccountsService;

@Controller
public class AccountsController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AccountsController.class.getName());
	
	@Autowired
	private ChartOfAccountsService chartOfAccountsService;
	
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

}
