package com.ftn.SBZ_2018.netgear.userDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "singleton")
@Component
public class ActiveUsersStore  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<ActiveUser> activeUsers = new ArrayList<ActiveUser>();
	
	public ActiveUsersStore() {}

	public ActiveUsersStore(List<ActiveUser> activeUsers) {
		this.activeUsers = activeUsers;
	}

	public List<ActiveUser> getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(List<ActiveUser> activeUsers) {
		this.activeUsers = activeUsers;
	}
}
