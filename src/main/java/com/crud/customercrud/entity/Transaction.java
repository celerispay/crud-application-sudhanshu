package com.crud.customercrud.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.crud.customercrud.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name="transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
	
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	long transactionId;
	
	@Column(name="transaction_date")
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$",
    message="Transaction date is invalid")
	LocalDate transactionDate;
	
	@Column(name="client_name")
	@Size(min = 10, max = 25)
	String clientName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="transaction_type")
	TransactionType  transactionType;
	
	@Column(name="transaction_amount")
	@Positive
	Long transactionAmount;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToOne(mappedBy = "transaction")
	private Customer customer;

	public Transaction(long transactionId, LocalDate transactionDate, String clientName,
			TransactionType transactionType, Long transactionAmount) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.clientName = clientName;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}
	
	
}
