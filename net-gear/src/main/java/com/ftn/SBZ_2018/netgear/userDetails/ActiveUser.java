package com.ftn.SBZ_2018.netgear.userDetails;

import java.io.Serializable;

public class ActiveUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;

	public ActiveUser() {}

	public ActiveUser(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ActiveUser [username=" + username + "]";
	}
}
