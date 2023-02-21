package com.crud.customerCRUD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.crud.customerCRUD.Entity.User;

import antlr.collections.List;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
	
	User findByEmail(String email);

	User findById(int id);
	
	ArrayList<User> findAll();
	
	String findByRole(String userName);
	
	void deleteByEmail(String email);

	void deleteById(int id);
}
