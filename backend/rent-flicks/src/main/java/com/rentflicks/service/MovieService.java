package com.rentflicks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentflicks.model.Movie;
import com.rentflicks.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

	MovieRepository movieRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	public List<Movie> getMovies() {
		List<Movie> movies = movieRepository.findAll();
		return movies;
	}
	
	public Movie addMovie(Movie movie){
		return movieRepository.save(movie);
	}

}
