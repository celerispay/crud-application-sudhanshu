package com.crud.customercrud.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crud.customercrud.entity.Transaction;
import com.crud.customercrud.enums.TransactionType;
import com.crud.customercrud.repository.TransactionRepository;

@DisplayName("This Test class will test all the Customer Service")
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

	@InjectMocks
	TransactionService service;

	@Mock
	TransactionRepository repo;

	private Transaction transaction;

	@BeforeEach
	public void setup() {
		transaction = Transaction.builder().transactionId(10203040)
				.transactionDate(LocalDate.of(1970, 10, 11)).clientName("Marcus Leonard").transactionType(TransactionType.PAYMENT).transactionAmount(20000L).build();
	}
	
	@DisplayName("JUnit test for saveTransaction() method")
	@Test
	void testSave() {
		Mockito.when(repo.save(Mockito.any(Transaction.class))).thenReturn(transaction);

		Transaction savedTransaction = service.save(transaction);

		System.out.println(savedTransaction);

		assertThat(savedTransaction).isNotNull();
	}

	@DisplayName("JUnit test for getAllTransactions() method")
	@Test
	void testGetAllTransactions() {
		Transaction transaction1 = Transaction.builder().transactionId(20406080)
				.transactionDate(LocalDate.of(1980, 1, 9)).clientName("Patience Parrish").transactionType(TransactionType.TRANSFER).transactionAmount(200000L).build();
	
		
		// when
		Mockito.when(repo.findAll()).thenReturn(List.of(transaction, transaction1));

		List<Transaction> transactionList = service.getAllTransactions();

		// then - verify the output
		assertThat(transactionList).isNotNull();
		assertThat(transactionList.size()).isEqualTo(2);
	}


	@DisplayName("JUnit test for findByTransactionId() method")
	@Test
	void testFindByTransactionId() { 
		Mockito.when(service.findByTransactionId(Mockito.anyLong())).thenReturn(transaction);

		Transaction savedTransaction = service.findByTransactionId(transaction.getTransactionId());

		// then
		assertThat(savedTransaction).isNotNull();

	}

}
