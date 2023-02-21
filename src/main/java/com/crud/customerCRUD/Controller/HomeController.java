package com.crud.customerCRUD.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Entity.User;
import com.crud.customerCRUD.service.CustomerService;
import com.crud.customerCRUD.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	@GetMapping("/home")
	String index() {
		return "header";
	}

	@GetMapping("/login")
	public String logindetails() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String customerRegistration(final User user, final Model model) {
		userService.save(user);
		return "header";
	}

}