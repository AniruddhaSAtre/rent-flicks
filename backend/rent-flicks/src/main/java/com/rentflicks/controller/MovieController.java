package com.rentflicks.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentflicks.model.Movie;
import com.rentflicks.service.MovieService;

@RestController
public class MovieController {

	MovieService movieService;
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public List<Movie> GetMovies() {
		return movieService.getMovies();
	}
	@RequestMapping(value = "/movies/{title}", method = RequestMethod.GET)
	public List<Movie> GetMovies(@PathVariable String title) {
		return movieService.getMoviesByTitle(title);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Movie AddMovie(@RequestBody Movie movie) {
		LOGGER.debug("Received Movie as: \nTitle: " + movie.getTitle() + "\nActor: " + movie.getActor());
		return movieService.addMovie(movie);

	}

}
