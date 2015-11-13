package com.rentflicks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.rentflicks.RentFlicksApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RentFlicksApplication.class})
@WebAppConfiguration
public class TestMovieController {

	private MockMvc mockMvc;
	 
    @Autowired
    private MovieController todoServiceMock;
 
    //Add WebApplicationContext field here.
 
    //The setUp() method is omitted.
 
    @Test
	public void testMovies() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk());
 
        /*verify(todoServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(todoServiceMock);*/
    }
}