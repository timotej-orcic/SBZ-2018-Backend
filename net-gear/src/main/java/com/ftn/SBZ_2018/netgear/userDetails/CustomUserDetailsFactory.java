package com.ftn.SBZ_2018.netgear.userDetails;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.ftn.SBZ_2018.netgear.model.User;

public class CustomUserDetailsFactory {

	private CustomUserDetailsFactory() {}
	
	public static CustomUserDetails createCustomUserDetails(User user) {
		return new CustomUserDetails(
				user.getUsername(),
				user.getId(),
				mapToGrantedAuthorities(user.getRole().getId().toString())
				);
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(String role) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }
}
