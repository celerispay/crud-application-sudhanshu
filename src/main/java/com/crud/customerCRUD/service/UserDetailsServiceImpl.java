package com.crud.customerCRUD.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.User;
import com.crud.customerCRUD.Repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LogManager.getLogger(CustomerRepository.class);

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final User user = userService.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		logger.info("The user details is : " + user);
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword()).authorities("ADMIN","USER").build();
	}

}
