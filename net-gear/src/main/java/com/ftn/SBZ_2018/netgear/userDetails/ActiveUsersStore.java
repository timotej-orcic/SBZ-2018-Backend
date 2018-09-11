package com.ftn.SBZ_2018.netgear.userDetails;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "singleton")
@Component
public class ActiveUsersStore  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, ActiveUser> activeUsers = new HashMap<String, ActiveUser>();
	
	public ActiveUsersStore() {}

	public HashMap<String, ActiveUser> getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(HashMap<String, ActiveUser> activeUsers) {
		this.activeUsers = activeUsers;
	}
}
