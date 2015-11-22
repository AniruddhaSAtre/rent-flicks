package com.rentflicks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentflicks.model.Review;
import com.rentflicks.service.ReviewService;

@RestController
public class ReviewController {

	ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
	public List<Review> GetReviews() {
		return reviewService.getReviews();
	}

	@RequestMapping(value = "/review/add", method = RequestMethod.POST)
	public Review AddReview(@RequestBody Review review) {
		return reviewService.addMovie(review);

	}
}
