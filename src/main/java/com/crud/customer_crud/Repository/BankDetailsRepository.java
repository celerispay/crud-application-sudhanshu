package com.crud.customer_crud.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.customer_crud.entity.BankDetails;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, String>{
	
	@EntityGraph(attributePaths = {"customer"})                
    List<BankDetails> findAll();
	
	BankDetails findByAccountNo(String accountNo);
	
	void deleteByAccountNo(String accountNo);

}
