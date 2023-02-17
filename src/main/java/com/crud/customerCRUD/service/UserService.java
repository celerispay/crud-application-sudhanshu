package com.crud.customerCRUD.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.User;
import com.crud.customerCRUD.Repository.UserRepository;
import com.crud.exception.CustomerAlreadyExistsException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }
    
    public User getById(Integer id) {
        return userRepository.findById(id);
    }
    
    public User saveOrUpdate(User user) {
//        if(user.getPassword() != null){
//        	user.setPassword(encryptionService.encryptString(user.getPassword()));
//        }
        return userRepository.save(user);
    }
    
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByEmail(username);
    }

	public String getUserRoles(String name) {
		return userRepository.findByRole(name);
	}
	
}
