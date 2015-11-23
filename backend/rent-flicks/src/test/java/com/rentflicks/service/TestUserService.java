package com.rentflicks.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;
import com.rentflicks.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestUserService {
	
	@Autowired
    private UserService userService;
 
    //Add WebApplicationContext field here.
 
    //The setUp() method is omitted.
	@Test
	public void testValidGetUserById() throws Exception {
		boolean condition = false;
		User r = userService.findOne(1);
		if (r.getUserId() == 1)
			condition = true;
		assertTrue(condition);
	}

	@Test
	public void testInvalidGetUserById() throws Exception {
		boolean condition = false;
		if (userService.findOne(0) == null)
			condition = true;
		assertTrue(condition);
	}
	
	@Test
	public void testValidAddUser() throws Exception{
		boolean condition = false;
		User user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		user.setEmail("test@test.com");
		user.setPassword("test");
		User r = userService.createUser(user);
		User response = userService.findOne(r.getUserId());
		if (r.getUserId().equals(response.getUserId()))
			condition = true;
		assertTrue(condition);
	}
	
	@Test
	public void testInvalidAddUser() throws Exception{
		boolean condition = false;
		User user = new User();
		try {
			userService.createUser(user);
		} catch (Exception e) {
			condition = true;
		}
		assertTrue(condition);
	}
	
	@Test
	public void testValidSignIn() throws Exception{
		boolean condition = false;
		User user = userService.findOne(1);
		User r = userService.signIn(user);
		if (r.getUserId().equals(user.getUserId()))
			condition = true;
		assertTrue(condition);
	}
	
	@Test
	public void testInvalidSignIn() throws Exception{
		boolean condition = false;
		User user = new User();
		
		User response= userService.signIn(user);
		if(response == null)
			condition = true;
		assertTrue(condition);
	}
}
