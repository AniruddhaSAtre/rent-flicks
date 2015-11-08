package com.rentflicks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentflicks.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {


}
