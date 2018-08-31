package com.ftn.SBZ_2018.netgear.service;

import com.ftn.SBZ_2018.netgear.model.User;

public interface UserService {

	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
	public User insertUser(User user);
	public User updateUser(User user);
	public void deleteUser(Long id);
}
