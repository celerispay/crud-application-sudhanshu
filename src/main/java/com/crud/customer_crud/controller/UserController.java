package com.crud.customer_crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customer_crud.entity.User;
import com.crud.customer_crud.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
    @GetMapping("/addnew")
    public String addNewEmployee(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "newuser";
    }
    
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAll();
    }
 
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }
 
    @GetMapping("/update/{email}")
    public String updateForm(@Valid @PathVariable(value = "email") String email, Model model) {
        User user = userService.findByEmail(email);
        model.addAttribute("employee", user);
        return "update";
    }
 
    @DeleteMapping("/deleteEmployee/{email}")
    public String deleteThroughId(@PathVariable(value = "email") String email) {
        userService.deleteByEmail(email);
        return "redirect:/";
 
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@PostMapping("register")
//	public User registerUser(@RequestBody User user) {
//		return userService.save(user);
//	}
//
//	@GetMapping("findByName/{name}")
//	public User getUserInfo(@RequestBody String name) {
//		return userService.findByUsername(name);
//	}
//
//	@PostMapping("/update/{email}")
//	public String updateUser(@PathVariable String email, @Valid User user, 
//	  BindingResult result, Model model) {
//	    if (result.hasErrors()) {
//	        user.setEmail(email);
//	        return "update-user";
//	    }
//	        
//	    userRepository.save(user);
//	    return "redirect:/header";
//	}
//	
//	@GetMapping("/delete/{email}")
//	public String deleteUser(@PathVariable String email, Model model) {
//	    User user = userRepository.findByEmail(email);
////	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//	    userRepository.delete(user);
//	    return "redirect:/header";
//	}
//
//	@GetMapping({"/list", "/"})
//	public ModelAndView getAllUsers() {
//		ModelAndView mav = new ModelAndView("list-users");
//		mav.addObject("users", userRepository.findAll());
//		return mav;
//	}
//	
}
