package com.ftn.SBZ_2018.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class JWTAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	protected JWTAuthenticationTokenFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		// TODO Auto-generated method stub
		
	}

	public void setAuthenticationSuccessHandler(JWTSuccessHandler jwtSuccessHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

}
