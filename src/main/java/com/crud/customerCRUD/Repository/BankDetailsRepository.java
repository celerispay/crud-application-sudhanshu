package com.crud.customerCRUD.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.customerCRUD.Entity.BankDetails;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, String>{
	List<BankDetails> findAll();

	BankDetails findByAccountNo(String accountNo);
	
	void deleteByAccountNo(String accountNo);

}
