package com.crud.customerCRUD.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.customerCRUD.Entity.BankDetails;

@Repository
public interface BankDetailsRepository extends CrudRepository<BankDetails, String>{
	List<BankDetails> findAll();

	BankDetails findByAccountNo(String accountNo);
	
	BankDetails findByCustId(int id);

}