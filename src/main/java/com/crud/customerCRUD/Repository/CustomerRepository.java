package com.crud.customerCRUD.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.customerCRUD.Entity.BankDetails;
import com.crud.customerCRUD.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {  

	@EntityGraph(attributePaths = {"transaction","bankDetails"})                
	List<Customer> findAll();

}
