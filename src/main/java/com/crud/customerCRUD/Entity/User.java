package com.crud.customerCRUD.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
	
	@Id
	int id;
	
	@Column(name="email",unique = true)
	String email;
	
	@Column(name="password")
	String password;
	
	@Column(name="role")
	String role;
	
	
	

}
