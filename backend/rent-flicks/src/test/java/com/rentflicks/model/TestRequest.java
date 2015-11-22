package com.rentflicks.model;

import static org.junit.Assert.assertFalse;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestRequest {
	
	private Request request;
	
	@Before
	public void setUp() throws Exception{
		request = new Request();
	}
	
	@Test
	public void testValidBorrower(){
		boolean exceptionCaught = false;
		User borrower = new User();
		borrower.setEmail("aatre@uncc.edu");
		borrower.setFirstName("Aniruddha");
		borrower.setLastName("Atre");
		borrower.setPassword("test12");
		try{
			request.setBorrower(borrower);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testValidCheckOutDate(){
		boolean exceptionCaught = false;
		try{
			request.setCheckOutDate(new LocalDate());
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testValidCheckInDate(){
		boolean exceptionCaught = false;
		try{
			request.setCheckOutDate(new LocalDate());
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testInvalidBorrower(){
		boolean exceptionCaught = false;
		User borrower = new User();
		try{
			request.setBorrower(borrower);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	@Test
	public void testInvalidCheckOutDate(){
		boolean exceptionCaught = false;
		try{
			request.setCheckOutDate(null);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testInvalidCheckInDate(){
		boolean exceptionCaught = false;
		try{
			request.setCheckOutDate(null);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
}

