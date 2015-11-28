package com.rentflicks.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class TestMovieController {

	 
	@Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }
    @Test
	public void testMovies() throws Exception {
    	
    	 mockMvc.perform(get("/address"))
         .andExpect(status().isOk())
         .andExpect(
                 content().contentType(MediaType.APPLICATION_JSON_VALUE))
         .andExpect(jsonPath("$.street").value("12345 Horton Ave"));

    }
}