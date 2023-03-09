package com.crud.customer_crud.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.customer_crud.entity.BankDetails;
import com.crud.customer_crud.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {  

	@EntityGraph(attributePaths = {"transaction","bankDetails"})                
	List<Customer> findAll();

}
