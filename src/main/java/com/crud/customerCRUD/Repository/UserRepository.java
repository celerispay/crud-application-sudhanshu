package com.crud.customerCRUD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.customerCRUD.Entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);

	User findById(int id);
	
	String findByRole(String userName);
	
	void deleteByEmail(String email);

	void deleteById(int id);
}
