package com.ftn.SBZ_2018.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ftn.SBZ_2018.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	public User findOneByUsername(String username);
	public User findOneByUsernameAndPassword(String username, String password);
}
