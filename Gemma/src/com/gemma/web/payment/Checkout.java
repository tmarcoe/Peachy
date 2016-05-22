package com.gemma.web.payment;

import java.math.BigDecimal;
import java.util.Arrays;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.ValidationError;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;


public class Checkout {
	private static Logger logger = Logger.getLogger(Checkout.class.getName());
	
     private Status[] TRANSACTION_SUCCESS_STATUSES = new Status[] {
        Transaction.Status.AUTHORIZED,
        Transaction.Status.AUTHORIZING,
        Transaction.Status.SETTLED,
        Transaction.Status.SETTLEMENT_CONFIRMED,
        Transaction.Status.SETTLEMENT_PENDING,
        Transaction.Status.SETTLING,
        Transaction.Status.SUBMITTED_FOR_SETTLEMENT
     };


    public boolean postForm(Payment payment, BraintreeGateway gateway, BigDecimal amount, String nonce) {
        TransactionRequest request = new TransactionRequest()
            	.amount(amount)
            	.paymentMethodNonce(nonce)
            	.billingAddress()
            		.firstName(payment.getFirstName())
            		.lastName(payment.getLastName())
            		.streetAddress(payment.getAddress1())
            		.extendedAddress(payment.getAddress2())
            		.locality(payment.getCity())
            		.region(payment.getRegion())
            		.countryCodeAlpha3(payment.getCountry())
            		.done()
            	.options()
            		.storeInVault(payment.isSaveInfo())
            		.addBillingAddressToPaymentMethod(payment.isSaveInfo())
            		.submitForSettlement(true)
            		.done();

        Result<Transaction> result = gateway.transaction().sale(request);

        if (result.isSuccess()) {    
            return true;
        } else if (result.getTransaction() != null) {
        	logger.error(result.getMessage());
            return false;
        } else {
            String errorString = "";
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
               errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
            }
            logger.error(errorString);
        }
        return false;
    }

    public String getTransaction(BraintreeGateway gateway, String transactionId, Model model) throws Exception {
        Transaction transaction;
        CreditCard creditCard;
        Customer customer;

        transaction = gateway.transaction().find(transactionId);
        creditCard = transaction.getCreditCard();
        customer = transaction.getCustomer();

        model.addAttribute("isSuccess", Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains(transaction.getStatus()));
        model.addAttribute("transaction", transaction);
        model.addAttribute("creditCard", creditCard);
        model.addAttribute("customer", customer);

        return "checkouts/show";
    }
}
