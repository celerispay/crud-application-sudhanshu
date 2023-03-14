package com.crud.customercrud.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crud.customercrud.entity.Transaction;
import com.crud.customercrud.enums.TransactionType;
import com.crud.customercrud.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private TransactionService service;
	
	@InjectMocks
	private TransactionController controller;
	
	private ObjectMapper objectMapper;
	
	private Transaction transaction;
	private Transaction transaction2;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		objectMapper = new ObjectMapper();
		transaction = Transaction.builder().transactionId(12345).transactionAmount(20000L).transactionType(TransactionType.PAYMENT).build();
		transaction2 = Transaction.builder().transactionId(6789).transactionAmount(2000L).transactionType(TransactionType.PURCHASE).build();
		

	}
	
	@Test
	void testPerformTransaction() throws JsonProcessingException, Exception {
		when(service.save(Mockito.any(Transaction.class))).thenReturn(transaction);
		this.mockMvc.perform(post("/transaction/performtransaction")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(transaction)))
		.andExpect(status().isOk());
//		.andExpect(jsonPath("$.transactionId", is(transaction.getTransactionId())));
	}

	@Test
	void testGetAllTransaction() throws Exception {
		List<Transaction> list = new ArrayList<>();
		list.add(transaction);
		list.add(transaction2);

		when(service.getAllTransactions()).thenReturn(list);

		this.mockMvc.perform(get("/transaction/getAll")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(list.size())));
	}

	@Test
	void testGetTransactionById() throws Exception {
		lenient().when(service.findByTransactionId(Mockito.anyLong())).thenReturn(transaction);
		this.mockMvc.perform(get("/transaction/getTransactionById/{id}", "12345"))
			.andExpect(status().isOk());
	}

}
