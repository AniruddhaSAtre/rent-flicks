package com.rentflicks.model;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestUser {
	
	private User user;
	
	@Before
	public void setUp() throws Exception{
		user = new User();
	}
	
	@Test
	public void testValidEmail(){
		boolean exceptionCaught = false;
		try{
			user.setEmail("aatre@uncc.edu");
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testInvalidEmail(){
		boolean exceptionCaught = false;
		try{
			user.setEmail("inalidemail");
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testValidPassword(){
		boolean exceptionCaught = false;
		try{
			user.setPassword("test12");
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testInvalidPassword(){
		boolean exceptionCaught = false;
		try{
			user.setPassword("test");
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
}
