package com.ftn.SBZ_2018.netgear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018.netgear.model.User;
import com.ftn.SBZ_2018.netgear.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User findByUsername(String username) {
		return userRepo.findOneByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {		
		return userRepo.findOneByUsernameAndPassword(username, password);
	}

	@Override
	public User insertUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

}
