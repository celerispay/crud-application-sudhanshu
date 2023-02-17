package com.crud.customerCRUD.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userService.findByUsername(username);
		 if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
	                            .password(user.getPassword())
	                            .authorities("USER").build(); 
	}
	

}
