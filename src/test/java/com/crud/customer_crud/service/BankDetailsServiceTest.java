package com.crud.customer_crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crud.customer_crud.entity.BankDetails;
import com.crud.customer_crud.enums.AccountType;
import com.crud.customer_crud.exception.NotFoundException;
import com.crud.customer_crud.repository.BankDetailsRepository;

@DisplayName("This Test class will test all the BankDetails Service")
@ExtendWith(MockitoExtension.class)
class BankDetailsServiceTest {

	@InjectMocks
	BankDetailsService service;

	@Mock
	BankDetailsRepository repo;

	@Mock
	Logger logger;
	
	private BankDetails bankDetails;

	@BeforeEach
	public void setup() {
		bankDetails = BankDetails.builder().accountNo("SBI235432678").bankName("State Bank Of India")
				.accType(AccountType.SAVING).availableBalance(30000L).build();
	}

	@DisplayName("JUnit test for CreateBankDetails method")
	@Test
	public void testCreateBankDetails() {
		Mockito.when(repo.save(Mockito.any(BankDetails.class))).thenReturn(bankDetails);

		BankDetails savedBankDetails = service.createBankDetails(bankDetails);

		System.out.println(savedBankDetails);

		assertThat(savedBankDetails).isNotNull();

	}

	@DisplayName("JUnit test for GetAllBankDetails method")
	@Test
	public void testGetAllBankDetails() {
		BankDetails bDetails = BankDetails.builder().accountNo("SBI235432876").bankName("State Bank Of India")
				.accType(AccountType.CURRENT).availableBalance(60000L).build();

		// when
		Mockito.when(repo.findAll()).thenReturn(List.of(bankDetails, bDetails));

		List<BankDetails> bd = service.getAllBankDetails();

		// then - verify the output
		assertThat(bd).isNotNull();
		assertThat(bd.size()).isEqualTo(2);
	}

	@DisplayName("JUnit test for FindBankDetailByAccountNo method")
	@Test
	public void testFindBankDetailByAccountNo() {
		// when
		Mockito.when(repo.findByAccountNo(Mockito.anyString())).thenReturn(bankDetails);

		BankDetails savedBankDetails = service.findBankDetailByAccountNo(bankDetails.getAccountNo());

		// then
		assertThat(savedBankDetails).isNotNull();

	}
	
	@DisplayName("JUnit test for findBankDetailByAccountNo method which throws exception")
	@Test
	void testFindBankDetailByAccountNoThrowsException() {

		NotFoundException bankDetailsNotFoundException =assertThrows(NotFoundException.class,
				() -> service.findBankDetailByAccountNo(""));

		assertEquals("Bank details not found !", bankDetailsNotFoundException.getMessage());
	
	}

	@DisplayName("JUnit test for UpdateBankDetails method")
	@Test
	public void testUpdateBankDetails() {
		BankDetails bDetails = BankDetails.builder().accountNo("CBI98765432").bankName("Central Bank Of India")
				.accType(AccountType.SAVING).availableBalance(6000L).build();


		Mockito.when(repo.findByAccountNo(Mockito.anyString())).thenReturn(bankDetails);
		Mockito.when(repo.save(Mockito.any(BankDetails.class))).thenReturn(bankDetails);

		BankDetails updatedDetails = service.updateBankDetails(bankDetails.getAccountNo(), bDetails);
		System.out.println(bDetails);
		System.out.println(updatedDetails);

		assertThat(updatedDetails.getAccountNo()).isEqualTo("CBI98765432");
		assertThat(updatedDetails.getAvailableBalance()).isEqualTo(6000L);
	}

	@DisplayName("JUnit test for deleteBankDetailsByAccountNo method")
	@Test
	public void testDeleteBankDetailsByAccountNo() {

		String accountNo = "SBI235432678";

		willDoNothing().given(repo).deleteByAccountNo(accountNo);

		service.deleteBankDetailsByAccountNo(accountNo);
		
		verify(repo, times(1)).deleteByAccountNo(accountNo);
	}
}
