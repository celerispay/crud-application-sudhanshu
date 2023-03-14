package com.crud.customercrud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.customercrud.entity.Customer;
import com.crud.customercrud.entity.User;
import com.crud.customercrud.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

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
	
	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User updateUser(String email, User user) {
		log.info("updateUser method for updating User is called");
		User currentUser = userRepository.findByEmail(email);
		currentUser.setEmail(user.getEmail());
		currentUser.setPassword(user.getPassword());
		currentUser.setUsername(user.getUsername());
		currentUser.setRole(user.getRole());
		return userRepository.save(currentUser);
	}
	
}
