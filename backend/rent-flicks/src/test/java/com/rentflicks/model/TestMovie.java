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
import com.rentflicks.service.MovieService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestMovie {

	private Movie movie;
    
	@Autowired
    private MovieService movieService;

    @Before
	public void setUp() throws Exception{
		movie = new Movie();
		movie.setActor("test");
		movie.setDirector("test");
		movie.setTitle("test");
		movie.setYear(1991);
		movie.setImage("test");
		movie.setCriticRating((float) 9.2);
		movie.setPlot("test");
	}
 
    @Test
    public void testValidTitle() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		movieService.addMovie(movie);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidTitle() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			movie.setTitle(null);
			movieService.addMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidActor() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		movieService.addMovie(movie);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidActor() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			movie.setActor(null);
			movieService.addMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    @Test
    public void testValidDirector() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		movieService.addMovie(movie);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidDirector() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			movie.setDirector(null);
			movieService.addMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidPlot() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		movieService.addMovie(movie);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidPlot() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			movie.setPlot(null);
			movieService.addMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidImage() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		movieService.addMovie(movie);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidImage() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			movie.setImage(null);
			movieService.addMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidYear() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		movieService.addMovie(movie);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidYear() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			movie.setYear(null);
			movieService.addMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    
    @Test
    public void testValidCriticRating() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		movieService.addMovie(movie);
    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
    @Test
    public void testInvalidCriticRating() throws Exception{
    	
    	boolean exceptionCaught = false;
		try{
			movie.setCriticRating((float) 12.32);
			movieService.addMovie(movie);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
    }
    /*@Test
	public void testMovies() throws Exception {
        List<Movie> movies = movieService.getMovies();
        Assert.assertTrue(movies.size()!=0);
    }*/
}