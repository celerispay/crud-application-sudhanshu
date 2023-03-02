package com.crud.customerCRUD.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crud.customerCRUD.config.UniqueIdFilterConfig;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class UniqueIdFilter extends OncePerRequestFilter{

	private final String responseHeader;
	private final String mdcKey;
	private final String requestHeader;
	
	public UniqueIdFilter(String responseHeader, String mdcKey, String requestHeader) {
		super();
		this.responseHeader = responseHeader;
		this.mdcKey = mdcKey;
		this.requestHeader = requestHeader;
	}


//	public UniqueIdFilter() {
//		requestHeader=UniqueIdFilterConfiguration.HEADER_HEADER_TOKEN;
//
//	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
	}


	

}
