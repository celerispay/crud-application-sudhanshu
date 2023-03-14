package com.crud.customercrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customercrud.entity.User;
import com.crud.customercrud.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Update the existing user",description = "This API will update the existing user data")
    @PutMapping("/update/{email}")
    public User updateForm(@Valid @PathVariable(value = "email") String email, @RequestBody User user) {
        log.debug("This api will be updating the user with email id : {}",email);
		return userService.updateUser(email, user);
    }
 
	@Operation(summary = "Delete the existing user",description = "This API will delete the existing user data")
    @DeleteMapping("/deleteUser/{email}")
    public void deleteThroughId(@PathVariable(value = "email") String email) {
        userService.deleteByEmail(email);
        log.debug("This api will be deleting the user with email id : {}",email);
    }
	
}
