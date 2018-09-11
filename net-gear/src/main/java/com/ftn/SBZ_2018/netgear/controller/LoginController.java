package com.ftn.SBZ_2018.netgear.controller;

import java.util.HashMap;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018.netgear.dao.ResponseWrapper;
import com.ftn.SBZ_2018.netgear.model.User;
import com.ftn.SBZ_2018.netgear.model.UserRole;
import com.ftn.SBZ_2018.netgear.security.JwtUtils;
import com.ftn.SBZ_2018.netgear.service.UserService;
import com.ftn.SBZ_2018.netgear.userDetails.ActiveUser;
import com.ftn.SBZ_2018.netgear.userDetails.ActiveUsersStore;
import com.ftn.SBZ_2018.netgear.userDetails.CustomUserDetailsFactory;

@RestController
@RequestMapping(value = "rest/")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private ActiveUsersStore activeUsersStore;
	
	@Autowired
	private KieContainer kieContainer;
	
	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> login(@RequestBody User user) {		
		ResponseWrapper<String> retObj = new ResponseWrapper<String>();
		
		user = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if(user==null) {
			retObj.setPayload(null);
			retObj.setMessage("Wrong username or password");
			retObj.setSuccess(false);
		}
		else {
			String token = jwtUtils.generateToken(CustomUserDetailsFactory.createCustomUserDetails(user));
			ActiveUser activeUser = new ActiveUser(user.getUsername(), createKieSessions(user.getRole()));
			activeUsersStore.getActiveUsers().put(activeUser.getUsername(), activeUser);
			retObj.setPayload(token);
			retObj.setMessage("Login successfull");
			retObj.setSuccess(true);
		}
		
		return retObj;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@PreAuthorize("hasAuthority('0') or hasAuthority('1')")
	@RequestMapping(value = "secured/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> logout(@RequestParam(value = "username", required = true) String username) {
		ResponseWrapper<String> retObj = new ResponseWrapper<String>();
		
		ActiveUser activeUser = activeUsersStore.getActiveUsers().get(username);
		
		if(activeUser == null) {
			retObj.setPayload(null);
			retObj.setMessage("Logout unsuccessfull: user is not logged in");
			retObj.setSuccess(false);
		}
		else {
			activeUsersStore.getActiveUsers().remove(activeUser);
			retObj.setPayload(null);
			retObj.setMessage("Logout successfull");
			retObj.setSuccess(true);
		}
		return retObj;
	}
	
	private HashMap<String, KieSession> createKieSessions(UserRole userRole) {
		HashMap<String, KieSession> kieSessions = new HashMap<String, KieSession>();
		
		if(userRole.getName().equals("user")) {
			KieSession userSession = kieContainer.newKieSession("userSession");
			kieSessions.put("userSession", userSession);
		}
		else {
			KieSession adminSession = kieContainer.newKieSession("adminSession");
			kieSessions.put("adminSession", adminSession);
		}
		
		return kieSessions;
	}
}
