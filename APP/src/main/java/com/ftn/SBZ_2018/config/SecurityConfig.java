package com.ftn.SBZ_2018.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ftn.SBZ_2018.security.JWTAuthenticationTokenFilter;
import com.ftn.SBZ_2018.security.JWTSuccessHandler;
import com.ftn.SBZ_2018.security.JWTAuthenticationEntryPoint;
import com.ftn.SBZ_2018.security.JWTAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private JWTAuthenticationProvider authenticationProvider;
	private JWTAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public AuthenticationManager authManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
	
	@Bean
	public JWTAuthenticationTokenFilter authenticationTokenFilter() throws Exception {		
		JWTAuthenticationTokenFilter filter = new JWTAuthenticationTokenFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JWTSuccessHandler());
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("**/auth/**").authenticated()
				.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	//stigao do 18og min u tutorijalu
}
