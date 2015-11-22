package com.rentflicks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentflicks.model.Review;
import com.rentflicks.repository.ReviewRepository;

@Service
public class ReviewService {
	
	ReviewRepository reviewRepository;
	
	@Autowired
	public ReviewService(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}

	public List<Review> getReviews() {
		List<Review> reviews = reviewRepository.findAll();
		return reviews;
	}
	
	public Review addMovie(Review review){
		return reviewRepository.save(review);
	}


}
