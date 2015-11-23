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
import com.rentflicks.service.ReviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestReview {

	private Review review;
	
	@Autowired
	private ReviewService reviewService;
	
	@Before
	public void setUp() throws Exception{
		review = new Review();
		review.setUserId(1);
		review.setMovieId(2);
		review.setReview("test");
		review.setRating((float) 9.8);
	}
 
    @Test
    public void testValidUserId() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		reviewService.addMovie(review);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidUserId() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			review.setUserId(null);
			reviewService.addMovie(review);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidMovieId() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		reviewService.addMovie(review);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidMovieId() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			review.setMovieId(1);
			reviewService.addMovie(review);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidReview() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		reviewService.addMovie(review);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidReview() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			review.setReview(null);
			reviewService.addMovie(review);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidRating() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		reviewService.addMovie(review);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidRating() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			review.setRating((float) 12.5);
			reviewService.addMovie(review);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
}
