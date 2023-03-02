package com.crud.customerCRUD.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.User;
import com.crud.customerCRUD.Repository.CustomerRepository;
import com.crud.customerCRUD.Repository.TransactionRepository;
import com.crud.customerCRUD.Repository.UserRepository;

import antlr.collections.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
    	this.userRepository=userRepository;
    }
	
	Logger logger = LogManager.getLogger(CustomerRepository.class);
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	public User save(final User userData) {
		User userModel = populateUserData(userData);
		return userRepository.save(userModel);
	}
	
	private User populateUserData(final User userData) {
		User user = new User();
		user.setUsername(userData.getUsername());
		user.setEmail(userData.getEmail());
		user.setPassword(passwordEncoder.encode(userData.getPassword()));
		user.setRole(userData.getRole());
		logger.info("Encoded password for test is : "+ passwordEncoder.encode("test"));
		return user;
	}

	public User getById(Integer id) {
		return userRepository.findById(id);
	}

	@Transactional
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void deleteByEmail(String email) {
		userRepository.deleteByEmail(email);
	}

	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public ArrayList<User> getAll() {
		return userRepository.findAll();
	}

}
