package com.rentflicks.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

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

	// Add WebApplicationContext field here.

	// The setUp() method is omitted.
	public boolean compareMovies(Movie first, Movie second) {
		boolean movieCheck = first.getMovieId().equals(second.getMovieId());
		boolean actorCheck = first.getActor().equals(second.getActor());
		boolean titleCheck = first.getTitle().equals(second.getTitle());
		boolean plotCheck = first.getPlot().equals(second.getPlot());
		boolean yearCheck = first.getYear().equals(second.getYear());
		boolean directorCheck = first.getDirector().equals(second.getDirector());
		boolean criticRatingCheck = first.getCriticRating().equals(second.getCriticRating());
		boolean imageCheck = first.getImage().equals(second.getImage());
		if (movieCheck && actorCheck && titleCheck && plotCheck && yearCheck && directorCheck && criticRatingCheck
				&& imageCheck)
			return true;
		return false;
	}

	@Test
	public void testGetMovies() throws Exception {
		boolean condition = false;
		List<Movie> movies = movieService.getMovies();
		Movie m = movies.get(1);
		Movie resp = movieService.findOne(m.getMovieId());
		if (compareMovies(m, resp))
			condition = true;
		assertTrue(condition);
	}

	@Test
	public void testValidAddMovie() throws Exception {
		boolean condition = false;
		Movie movie = new Movie();
		movie.setActor("test");
		movie.setCriticRating((float) 3.5);
		movie.setDirector("test");
		movie.setImage("test");
		movie.setPlot("test");
		movie.setTitle("test");
		movie.setYear(2013);
		Movie response = movieService.addMovie(movie);
		List<Movie> movies = movieService.getMovies();
		for (Movie m : movies) {
			if (m.getMovieId() == response.getMovieId())
				condition = true;
		}
		assertTrue(condition);
	}

	@Test
	public void testInvalidAddMovie() throws Exception {
		boolean condition = false;
		Movie movie = new Movie();
		try {
			movieService.addMovie(movie);
		} catch (Exception e) {
			condition = true;
		}
		assertTrue(condition);
	}

	@Test
	public void testValidFindOne() throws Exception {
		boolean condition = false;
		Movie r = movieService.findOne(2);
		if (r.getMovieId() == 2)
			condition = true;
		assertTrue(condition);
	}

	@Test
	public void testInvalidFindOne() throws Exception {
		boolean condition = false;
		if (movieService.findOne(0) == null)
			condition = true;
		assertTrue(condition);
	}

	@Test
	public void testValidGetMoviesByTitle() throws Exception {
		boolean condition = false;
		Movie movie = movieService.getMovies().get(0);
		if (compareMovies(movieService.getMoviesByTitle(movie.getTitle()).get(0), movie))
			condition = true;
		assertTrue(condition);
	}

	@Test
	public void testInvalidGetMoviesByTitle() throws Exception {
		boolean condition = false;
		List<Movie> m = movieService.getMoviesByTitle("invalid title");
		if (m.size() == 0)
			condition = true;
		assertTrue(condition);
	}

}