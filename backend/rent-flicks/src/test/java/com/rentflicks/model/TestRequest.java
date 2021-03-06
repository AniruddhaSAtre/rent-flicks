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
import com.rentflicks.service.RequestService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestRequest {

	private Request request;
    
	@Autowired
    private RequestService requestService;

    @Before
	public void setUp() throws Exception{
		request = new Request();
		request.setBorrowerId(1);
		request.setVideoId(1);
		request.setCheckInDate(null);
		request.setCheckOutDate(null);
	}
 
    @Test
    public void testValidBorrowerId() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		requestService.createRequest(request);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidBorrowerId() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			request.setBorrowerId(0);
			requestService.createRequest(request);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidVideoId() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		requestService.createRequest(request);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidVideoId() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			request.setVideoId(null);
			requestService.createRequest(request);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    @Test
    public void testValidCheckInDate() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		requestService.createRequest(request);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    /*@Test
    public void testInvalidCheckInDate() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			request.setCheckInDate(new LocalDate());
			requestService.createRequest(request);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }*/
    
    @Test
    public void testValidCheckOutDate() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		requestService.createRequest(request);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    /*@Test
    public void testInvalidCheckOutDate() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			request.setCheckOutDate(new LocalDate().plusMonths(12));
			requestService.createRequest(request);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }*/
}