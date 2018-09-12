package com.ftn.SBZ_2018.netgear.userDetails;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "singleton")
@Component
public class ActiveUsersStore {
	
	private HashMap<String, ActiveUser> activeUsers = new HashMap<String, ActiveUser>();
	
	public ActiveUsersStore() {}

	public HashMap<String, ActiveUser> getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(HashMap<String, ActiveUser> activeUsers) {
		this.activeUsers = activeUsers;
	}
}
