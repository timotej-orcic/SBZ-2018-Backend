package com.ftn.SBZ_2018.netgear.userDetails;

import java.util.HashMap;

import org.kie.api.runtime.KieSession;

public class ActiveUser {
	
	private String username;
	private HashMap<String, KieSession> kieSessions;
	
	public ActiveUser() {}

	public ActiveUser(String username, HashMap<String, KieSession> kieSessions) {
		this.username = username;
		this.kieSessions = kieSessions;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HashMap<String, KieSession> getKieSessions() {
		return kieSessions;
	}

	public void setKieSessions(HashMap<String, KieSession> kieSessions) {
		this.kieSessions = kieSessions;
	}

	@Override
	public String toString() {
		return "ActiveUser [username=" + username + ", kieSessions=" + kieSessions + "]";
	}
}
