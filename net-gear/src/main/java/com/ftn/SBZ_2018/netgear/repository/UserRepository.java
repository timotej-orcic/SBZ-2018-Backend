package com.ftn.SBZ_2018.netgear.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018.netgear.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findOneByUsername(String username);
	public User findOneByUsernameAndPassword(String username, String password);
}
