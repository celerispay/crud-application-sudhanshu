package com.crud.customer_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.customer_crud.entity.User;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
	
	User findByEmail(String email);

	User findById(int id);
	
	ArrayList<User> findAll();
	
	String findByRole(String userName);
	
	void deleteByEmail(String email);

	void deleteById(int id);
}
