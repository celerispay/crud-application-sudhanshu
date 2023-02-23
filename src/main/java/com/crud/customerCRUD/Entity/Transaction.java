package com.crud.customerCRUD.Entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.UniqueElements;

import com.crud.customerCRUD.Enum.transactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	

	@Column(name="transaction_id")
	String transactionId;
	
	@Column(name="transaction_date")
	LocalDate transactionDate;
	
	@Column(name="client_name")
	String clientName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="transaction_type")
	transactionType  transactionType;
	
	@Column(name="transaction_amount")
	Long transactionAmount;
	
	@OneToOne(mappedBy = "transaction")
	private Customer customer;
}
