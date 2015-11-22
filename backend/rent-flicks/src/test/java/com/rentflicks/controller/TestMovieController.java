package com.rentflicks.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rentflicks.RentFlicksApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
@WebAppConfiguration
public class TestMovieController {

	 
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
 
    //Add WebApplicationContext field here.
 
    //The setUp() method is omitted.
 
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
	public void testMovies() throws Exception {
    	/*mockMvc.perform("/mail")
        .andExpect(status().isCreated());*/
    }
}