package com.ftn.SBZ_2018.netgear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018.netgear.model.User;
import com.ftn.SBZ_2018.netgear.repository.UserRepository;
import com.ftn.SBZ_2018.netgear.userDetails.CustomUserDetailsFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOneByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Unexisting user");
		}else {
			return CustomUserDetailsFactory.createCustomUserDetails(user);
		}
	}

}
