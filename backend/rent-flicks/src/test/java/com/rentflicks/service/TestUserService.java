package com.rentflicks.service;

import org.junit.Assert;
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
	public void testUser() throws Exception {
        User user = userService.findOne(1);
        Assert.assertTrue(user.getUserId() == 1);
    }
}
