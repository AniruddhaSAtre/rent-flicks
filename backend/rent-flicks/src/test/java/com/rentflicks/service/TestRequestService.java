package com.rentflicks.service;

import java.util.List;

import org.junit.Assert;
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

	// Add WebApplicationContext field here.

	// The setUp() method is omitted.

	@Test
	public void testRequests() throws Exception {
		List<Request> requests = requestService.getRequests();
		Assert.assertTrue(requests.size() != 0);
	}
	
	@Test
	public void testRequestsOfUser() throws Exception {
		Request requests = requestService.findOne(1);
		Assert.assertTrue(requests.getRequestId() == 1);
	}
}