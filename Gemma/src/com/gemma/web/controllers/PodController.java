package com.gemma.web.controllers;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.service.InvoiceHeaderService;
import com.gemma.web.service.TransactionService;

@Controller
public class PodController implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String pageLink = "/headerpaging";

	@Autowired
	private InvoiceHeaderService invoiceHeaderService;

	@Autowired
	TransactionService transactionService;

	@RequestMapping("/podnotify")
	public String podNotify(Model model) {
		String invNum = "";

		model.addAttribute("invNum", invNum);

		return "podnotify";
	}

	@RequestMapping("/podconfirm")
	public String podConfirm(@ModelAttribute("invNum") String invNum,
			Model model, BindingResult result) {
		if ("".compareTo(invNum) == 0) {
			PagedListHolder<InvoiceHeader> headerList =invoiceHeaderService.getProcessedInvoices();
			headerList.setPage(0);
			headerList.setPageSize(10);
			model.addAttribute("objectList", headerList);
			model.addAttribute("pagelink", pageLink);

			return "admin";
		}
		InvoiceHeader header = invoiceHeaderService.getInvoiceHeader(Integer
				.valueOf(invNum));
		if (header == null) {
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


}
