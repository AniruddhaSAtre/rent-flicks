package com.rentflicks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentflicks.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
