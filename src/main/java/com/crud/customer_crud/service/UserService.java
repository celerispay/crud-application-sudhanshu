package com.crud.customer_crud.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.customer_crud.entity.User;
import com.crud.customer_crud.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
    	this.userRepository=userRepository;
    }
	
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
