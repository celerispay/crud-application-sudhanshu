package com.crud.customerCRUD.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsDao {

	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	public String getPassword() {

		return null;
	}

	public String getUsername() {

		return null;
	}

	public boolean isAccountNonExpired() {

		return false;
	}

	public boolean isAccountNonLocked() {

		return false;
	}

	public boolean isCredentialsNonExpired() {

		return false;
	}

	public boolean isEnabled() {

		return false;
	}

}
