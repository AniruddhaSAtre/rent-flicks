package com.rentflicks.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;
import com.rentflicks.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestUser {

	private User user;
    
	@Autowired
    private UserService userService;

    @Before
	public void setUp() throws Exception{
		user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		user.setEmail("test");
		user.setPassword("test12");
	}
 
    @Test
    public void testValidFirstName() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		userService.createUser(user);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidFirstName() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			user.setFirstName(null);
			userService.createUser(user);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidLastName() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		userService.createUser(user);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidLastName() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			user.setLastName(null);
			userService.createUser(user);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    @Test
    public void testValidEmail() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		userService.createUser(user);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidEmail() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			user.setEmail(null);
			userService.createUser(user);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidPassword() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		userService.createUser(user);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidPassword() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			user.setPassword(null);
			userService.createUser(user);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
}