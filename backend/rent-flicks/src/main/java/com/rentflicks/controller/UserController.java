package com.rentflicks.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentflicks.markers.Authenticate;
import com.rentflicks.markers.Create;
import com.rentflicks.model.User;
import com.rentflicks.repository.UserRepository;
import com.rentflicks.service.UserService;

@RestController
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public User signUp(@Validated(Create.class) @RequestBody User user) {
		return userService.createUser(user);
	}

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public User signIn(@Validated(Authenticate.class) @RequestBody User user, HttpServletResponse response) {
		user = userService.signIn(user);
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		} else {
			user.setSessionId("DummySessionId");
			return user;
		}
	}
	
	@RequestMapping(value="/update_password", method=RequestMethod.POST)
	public User update(@RequestBody User user){
		User usr = userService.findOne(user.getUserId());
		usr.setPassword(user.getPassword());
		return userService.createUser(usr);
	}

}
