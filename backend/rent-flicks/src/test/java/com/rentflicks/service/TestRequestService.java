package com.rentflicks.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;
import com.rentflicks.model.Request;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestRequestService {

	 
    @Autowired
    private RequestService requestService;
 
    //Add WebApplicationContext field here.
 
    //The setUp() method is omitted.
    
    public boolean compareRequests(Request first, Request second){
    	boolean borrowerCheck = first.getBorrowerId() == second.getBorrowerId();
    	boolean requestCheck = first.getRequestId() == second.getRequestId();
    	boolean videoCheck = first.getVideoId() == second.getVideoId();
    	boolean checkOutCheck = first.getCheckInDate() == second.getCheckInDate();
    	boolean checkInCheck = first.getCheckOutDate().equals(second.getCheckOutDate());
    	if(borrowerCheck && requestCheck  && videoCheck && checkOutCheck && checkInCheck)
    			return true;
    	return false;
    }
 
    @Test
	public void testGetRequests() throws Exception {
    	boolean condition = false;
        List<Request> requests = requestService.getRequests();
        Request r = requests.get(1);
        Request resp = requestService.findOne(r.getRequestId());
        if(compareRequests(r, resp))
        	condition = true;
        assertTrue(condition);
    }
    
    @Test
    public void testValidCreateRequest() throws Exception{
    	boolean condition = false;
    	Request request = new Request();
    	request.setBorrowerId(1);
    	request.setVideoId(1);
    	Request response = requestService.createRequest(request);
    	List<Request> requests = requestService.getRequests();
    	for(Request r: requests){
    		if(r.getRequestId() == response.getRequestId())
    			condition = true;
    	}
    	assertTrue(condition);
    }
    
    @Test
    public void testInvalidCreateRequest() throws Exception{
    	boolean condition = false;
    	Request request = new Request();
    	request.setBorrowerId(1);
    	request.setVideoId(0);
    	try{
    		requestService.createRequest(request);
    	}catch(Exception e){
    		condition = true;
    	}
    	assertTrue(condition);
    }
    
    @Test
    public void testValidFindOne() throws Exception{
    	boolean condition = false;
    	Request r = requestService.findOne(1);
    	if(r.getRequestId() == 1)
    		condition = true;
    	assertTrue(condition);
    }
    
    @Test
    public void testInvalidFindOne() throws Exception{
    	boolean condition = false;
    	if(requestService.findOne(0)== null)
    		condition = true;
    	assertTrue(condition);
    }
    
    @Test
    public void testValidDeleteRequest() throws Exception{
    	boolean condition = false;
    	Request r = new Request();
    	r.setBorrowerId(1);
    	r.setVideoId(1);
    	requestService.createRequest(r);
    	requestService.deleteRequest(r.getRequestId());
    	List<Request> requests = requestService.getRequests();
    	for(Request req: requests){
    		if(req.getRequestId() == r.getRequestId())
    			condition = true;
    	}
    	assertFalse(condition);
    }
    
    @Test
    public void testInvalidDeleteRequest() throws Exception{
    	boolean condition = false;
    	try{
    		requestService.deleteRequest(0);
    	}
    	catch(Exception e){
    		condition = true;
    	}
    	assertTrue(condition);
    }
}