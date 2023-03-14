package com.crud.customer_crud.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crud.customercrud.controller.BankDetailsController;
import com.crud.customercrud.entity.BankDetails;
import com.crud.customercrud.enums.AccountType;
import com.crud.customercrud.service.BankDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class BankDetailsControllerTest {

	private MockMvc mockMvc;

	@Mock
	private BankDetailsService service;

	@InjectMocks
	BankDetailsController controller;

	private ObjectMapper objectMapper;

	private BankDetails bankDetails;
	private BankDetails bankDetails2;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		objectMapper = new ObjectMapper();
		bankDetails = BankDetails.builder()
				.id(500L)
				.bankName("Reserve Bank Of India")
				.accountNo("RBI123456788054")
				.accType(AccountType.CURRENT)
				.availableBalance(1000000L)
				.build();
		
		bankDetails2 = BankDetails.builder()
				.id(5002L)
				.bankName("State Bank Of India")
				.accountNo("RSBI123456788054")
				.accType(AccountType.SAVING)
				.availableBalance(10000L)
				.build();
	}

	@Test
	void testCreateBankDetails() throws Exception {
		when(service.createBankDetails(Mockito.any(BankDetails.class))).thenReturn(bankDetails);
		this.mockMvc.perform(post("/bankdetails/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bankDetails)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.accountNo", is(bankDetails.getAccountNo())))
		.andExpect(jsonPath("$.bankName", is(bankDetails.getBankName())));	
	}

	@Test
	void testgetAllBankDetails() throws Exception {

		List<BankDetails> list = new ArrayList<>();
		list.add(bankDetails);
		list.add(bankDetails);

		when(service.getAllBankDetails()).thenReturn(list);

		this.mockMvc.perform(get("/bankdetails/getAll")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(list.size())));
	}

	@Test
	void testGetBankDetailsByAccountNo() throws Exception {
		
		when(service.findBankDetailByAccountNo(Mockito.anyString())).thenReturn(bankDetails);
		
		this.mockMvc.perform(get("/bankdetails/getByAccountNo/{accountNo}", "RBI123456788054"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.accountNo", is(bankDetails.getAccountNo())))
			.andExpect(jsonPath("$.bankName", is(bankDetails.getBankName())));
	}

	@Test
	void testDeleteBankDetails() throws Exception {
		
		when(service.deleteBankDetailsByAccountNo(Mockito.anyString())).thenReturn(Mockito.anyString());
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/bankdetails/deleteByAccountNo/{accountNo}", "RBI123456788054"))
        .andExpect(status().isOk());	
	}

	@Test
	void testUpdateBankDetails() throws Exception {
		when(service.findBankDetailByAccountNo( Mockito.anyString())).thenReturn(bankDetails);
		this.mockMvc.perform(put("/bankdetails/update/{accountNo}", "RBI123456788054")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bankDetails2)))
		.andExpect(status().isOk());
		}

}
