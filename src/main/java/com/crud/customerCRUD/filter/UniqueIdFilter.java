package com.crud.customerCRUD.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class UniqueIdFilter extends OncePerRequestFilter{

	private final String responseHeader;
	private final String mdcKey;
	private final String requestHeader;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
	}

}
