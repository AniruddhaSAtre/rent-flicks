package com.rentflicks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentflicks.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	public List<Movie> findByTitle(String title);	
}
