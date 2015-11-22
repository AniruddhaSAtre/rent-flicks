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
public class TestVideo {
	
	private Video video;
	
	@Before
	public void setUp() throws Exception{
		video = new Video();
	}
	
	@Test
	public void testValidMovie(){
		boolean exceptionCaught = false;
		Movie movie = new Movie();
		movie.setActor("test");
		movie.setCriticRating((float)8.2);
		movie.setDirector("test");
		movie.setImage("test");
		movie.setPlot("test");
		movie.setTitle("test");
		movie.setYear(2004);
		try{
			video.setMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testValidOwner(){
		boolean exceptionCaught = false;
		User owner = new User();
		owner.setEmail("aatre@uncc.edu");
		owner.setFirstName("test");
		owner.setLastName("test");
		owner.setPassword("test12");
		try{
			video.setOwner(owner);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	
	@Test
	public void testInvalidMovie(){
		boolean exceptionCaught = false;
		Movie movie = new Movie();
		try{
			video.setMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testInvalidOwner(){
		boolean exceptionCaught = false;
		User owner = new User();
		try{
			video.setOwner(owner);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}

}

