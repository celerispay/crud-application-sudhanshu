package com.crud.customerCRUD.Controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.crud.customerCRUD.Entity.BankDetails;
import com.crud.customerCRUD.Repository.BankDetailsRepository;
import com.crud.customerCRUD.service.BankDetailsService;
import com.crud.exception.BankDetailsAlreadyExistsException;
import com.crud.exception.BankDetailsNotFoundException;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/bankdetails/")
public class BankDetailsController {
	
	Logger logger = LogManager.getLogger(BankDetailsController.class);
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;
	
	@Autowired
	private BankDetailsService bankDetailsService;

	@GetMapping("getAll")
	private List<BankDetails> getAllBankDetails() {
		logger.debug("User has requested to fetch all bank details");
		List<BankDetails> lst = bankDetailsService.getAllBankDetails();
		return lst;
	}

	@GetMapping("{accountNo}")
	private BankDetails getBankDetailsByAccountNo(@PathVariable String accountNo) throws BankDetailsNotFoundException{
		logger.debug("User has requested to fetch bank details of account :"+ accountNo);
		try {
			BankDetails bd = bankDetailsService.findBankDetailByAccountNo(accountNo);
			if(bd==null) throw new BankDetailsNotFoundException("Bank Account with "+accountNo+" not found");
			else return bd;
		}
		catch (BankDetailsNotFoundException e) {
			throw new BankDetailsNotFoundException("Bank Details not found for this account number");
		}
	}

	@DeleteMapping("delete/{accountNo}")
	private String deleteBankDetails(@PathVariable String accountNo) throws BankDetailsNotFoundException{
		logger.debug("Delete mapping is called....");
		bankDetailsRepository.deleteByAccountNo(accountNo);
		return "Customer with Bank Account number "+accountNo+" is deleted successfully...";
	}

	@PostMapping("create")
	private BankDetails createBankDetails(@Valid @RequestBody BankDetails bankDetails) throws BankDetailsAlreadyExistsException{
		logger.debug("Post mapping is called....");
		return bankDetailsRepository.save(bankDetails);
	}

	@PutMapping("update/{accountNo}")
	private String update(@RequestBody BankDetails bankDetails, @PathVariable String accountNo) {
		BankDetails currDetail = bankDetailsService.findBankDetailByAccountNo(accountNo);
		currDetail.setAccountNo(bankDetails.getAccountNo());
		currDetail.setBankName(bankDetails.getAccountNo());
		currDetail.setAccType(bankDetails.getAccType());
		currDetail.setCustomer(bankDetails.getCustomer());
		
		logger.debug("Put mapping is called....");
		return "Bank Details with Account number " + accountNo + " is updated Successfully!!!!!";
	}
}
