package com.crud.customerCRUD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customerCRUD.Entity.User;
import com.crud.customerCRUD.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/userInfo/register")
	public User registerUser(@RequestBody User user) {
		User user1 = new User();
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		user1.setRole(user.getRole());
		return userService.saveOrUpdate(user1);
	}

	@GetMapping("/userInfo/{name}")
	public User getUserInfo(@RequestBody String name) {
		return userService.findByUsername(name);
	}
	
	@GetMapping("/userInfo")
	public List<User> getUser() {
		return userService.findAllUser();
	}

}
