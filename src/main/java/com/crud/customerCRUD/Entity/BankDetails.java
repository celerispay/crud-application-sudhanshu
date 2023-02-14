package com.crud.customerCRUD.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.crud.customerCRUD.Enum.accountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="bankDetails")
@Data
public class BankDetails {
	
	@Id
	@Size(min = 10, max = 12)
	private String accountNo;
	
	@NotBlank(message = "Bank Name is mandatory")
	private String bankName;
	
	@Enumerated(EnumType.STRING)
	private accountType accType;

	@ToString.Exclude
	@ManyToOne
	private Customer customer;

	}
