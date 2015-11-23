package com.rentflicks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentflicks.model.Movie;
import com.rentflicks.repository.MovieRepository;

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
	
	public Movie findOne(Integer movieId){
		return movieRepository.findOne(movieId);
	}
	
	public List<Movie> getMoviesByTitle(String title) {
		List<Movie> movies = movieRepository.findByTitle(title);
		return movies;
	}
	
	public Movie addMovie(Movie movie){
		return movieRepository.save(movie);
	}

}
