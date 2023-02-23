package com.crud.customerCRUD.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="bank_details")
@Data
public class BankDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Size(min = 10, max = 12)
	private String accountNo;
	
	@NotBlank(message = "Bank Name is mandatory")
	private String bankName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="acc_type")
	private accountType accType;

	@Column(name="available_balance")
	private Long availableBalance;
	
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	}
