package com.rentflicks.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;
import com.rentflicks.model.Movie;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestMovieService {

	 
    @Autowired
    private MovieService movieService;
 
    //Add WebApplicationContext field here.
 
    //The setUp() method is omitted.
 
    @Test
	public void testMovies() throws Exception {
        List<Movie> movies = movieService.getMovies();
        Assert.assertTrue(movies.size()!=0);
    }
}