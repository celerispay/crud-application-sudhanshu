package com.crud.customercrud.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud.customercrud.entity.Customer;
import com.crud.customercrud.entity.Transaction;
import com.crud.customercrud.entity.User;
import com.crud.customercrud.service.CustomerService;
import com.crud.customercrud.service.TransactionService;
import com.crud.customercrud.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	TransactionService transactionService;

	@Operation(summary = "This will lead to user header page")
	@GetMapping("/home")
	String home(Model model) {
		setupMDC("/home");
        log.info("This api will return the Home page and display the user data");
		model.addAttribute("alluserlist", userService.getAll());
		return "header";
	}
	
	@Operation(summary = "This will lead to user header page")
	@GetMapping("/customers")
	String customers(Model model) {
		setupMDC("/customers");
        log.info("This api will return the Customer data Page and display the List of Customers");
		model.addAttribute("customerlist", customerService.getAll());
		return "customer_data";
	}
	
	@Operation(summary = "This API will Return Register User form")
	@GetMapping("/addUser")
	String addUser(Model model) {
		setupMDC("/addUser");
        log.info("This api will return the User registration form");
		User user = new User();
		model.addAttribute("user",user);
		return "register-user";
	}
	
	@Operation(summary = "This API will save the User and return back to the header.html")
	@PostMapping("/addUser")
	public String userSubmit(@Valid @ModelAttribute User user) {
		setupMDC("/addUser");
        userService.save(user);
        log.info("This api will save user and will display the header page ");
		return "index";
	}

	@Operation(summary = "This API will Return the transaction Form ")
	@GetMapping("/performtransaction")
	public String transactionsForm(Model model) {
		setupMDC("/perfromtransaction");
        log.info("This api will display the transaction form");
		Transaction transaction = new Transaction();
		model.addAttribute("transaction", transaction);
		return "transactionForm";
	}

	@Operation(summary = "This API will save the the Transaction and return back to the index.html")
	@PostMapping("/performtransaction")
	public String transactionSubmit(@Valid @ModelAttribute Transaction transaction) {
		setupMDC("/perfromtransaction");
        log.info("This api will save transaction details and display the index page ");
		transactionService.save(transaction);
		return "index";
	}


	@Operation(summary = "This API will Return Customer Registration Form")
	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		setupMDC("/addCustomer");
        log.info("This api will display the Customer registration form");
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "register-customer";
	}

	@Operation(summary = "This API will add the Customer and return the index Page")
	@PostMapping("/addCustomer")
	public String customerRegistration(@Valid @ModelAttribute Customer customer) {
		setupMDC("/addCustomer");
        log.info("This api will save the new User and will display the index page");
		customerService.saveCustomer(customer);
		return "index";
	}	

	/**
     * Setup MDC variables.
     *
     * @param method the api endpoint that was called
     */
	
	private void setupMDC(String method) {
        MDC.clear();
        MDC.put("method", method);
        MDC.put("transactionId", UUID.randomUUID().toString());
    }
	
}