package com.crud.customer_crud.entity;

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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.crud.customer_crud.Enum.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="bank_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "nameAttributeInThisClassWithOneToMany")
public class BankDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name="account_no")
	@Size(min = 10, max =15)
	private String accountNo;
	
	@NotBlank(message = "Bank Name is mandatory")
	@Column(name= "bank_name")
	private String bankName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="acc_type")
	private AccountType accType;

	@Column(name="available_balance")
	private Long availableBalance;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	public BankDetails(Long id, @Size(min = 10, max = 15) String accountNo,
			@NotBlank(message = "Bank Name is mandatory") String bankName, AccountType accType, Long availableBalance) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.accType = accType;
		this.availableBalance = availableBalance;
	}

	
	
}
