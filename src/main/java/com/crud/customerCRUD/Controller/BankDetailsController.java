package com.crud.customerCRUD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customerCRUD.Entity.BankDetails;
import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.BankDetailsRepository;

@RestController
public class BankDetailsController {
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;

	@GetMapping("/bankdetails/getAll")
	private List<BankDetails> getAllBankDetails() {
		System.out.println("Get mapping is called....");
		return bankDetailsRepository.findAll();
	}

	@GetMapping("/bankdetails/{id}")
	private BankDetails getCustomerBankDetailsById(@PathVariable int id) {
		return bankDetailsRepository.findByCustId(id);
	}

	@DeleteMapping("/bankdetails/delete/{accountNo}")
	private String deleteBankDetails(@PathVariable String accountNo) {
		System.out.println("Delete mapping is called....");
		bankDetailsRepository.deleteById(accountNo);
		return "Customer with Bank Account number "+accountNo+" is deleted successfully...";
	}

	@PostMapping("/bankdetails/create")
	private BankDetails createBankDetails(@RequestBody BankDetails bankDetails) {
		System.out.println("Post mapping is called....");
		return bankDetailsRepository.save(bankDetails);
	}

	private String update(@RequestBody BankDetails bankDetails, @PathVariable String accountNo) {
		BankDetails currDetail = bankDetailsRepository.findByAccountNo(accountNo);
		currDetail.setAccountNo(bankDetails.getAccountNo());
		currDetail.setBankName(bankDetails.getAccountNo());
		currDetail.setAccType(bankDetails.getAccType());
		currDetail.setCustId(bankDetails.getCustId());
		
		System.out.println("Put mapping is called....");
		return "Bank Details with Account number " + accountNo + " is updated Successfully!!!!!";
	}
}
