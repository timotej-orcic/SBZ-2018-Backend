package com.ftn.SBZ_2018.netgear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018.netgear.dao.ResponseWrapper;
import com.ftn.SBZ_2018.netgear.model.User;
import com.ftn.SBZ_2018.netgear.model.UserRole;
import com.ftn.SBZ_2018.netgear.service.UserService;

@RestController
@RequestMapping(value = "rest/")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> register(@RequestBody User user) {
		ResponseWrapper<String> retObj = new ResponseWrapper<String>();
		
		User existingUser = userService.findByUsername(user.getUsername());
		if(existingUser != null) {
			retObj.setPayload(null);
			retObj.setMessage("Username exists");
			retObj.setSuccess(false);
		}
		else {
			UserRole role = new UserRole(new Long(0), "user");
			user.setRole(role);
			userService.insertUser(user);
			retObj.setPayload(user.getUsername());
			retObj.setMessage("Registration successfull");
			retObj.setSuccess(true);
		}
		
		return retObj;
	}
}
