package com.rentflicks.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;
import com.rentflicks.model.Review;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestReviewService {

	@Autowired
	private ReviewService reviewService;
	
	public boolean compareMovies(Review first, Review second){
		boolean reviewIdCheck = first.getReviewId().equals(second.getReviewId());
		boolean reviewCheck = first.getReview().equals(second.getReview());
		boolean ratingCheck = first.getRating().equals(second.getRating());
		boolean movieCheck = first.getMovieId().equals(second.getMovieId());
		boolean userCheck = first.getUserId().equals(second.getUserId());
		if(reviewIdCheck && reviewCheck && ratingCheck && movieCheck && userCheck)
			return true;
		return false;
	}
	
	@Test
	public void testMovies() throws Exception {
		boolean condition = false;
		List<Review> reviews = reviewService.getReviews();
		Review r = reviews.get(1);
		Review resp = reviewService.getReviewById(r.getReviewId());
		if (compareMovies(r, resp))
			condition = true;
		assertTrue(condition);
	}
	
	@Test
	public void testValidGetReviewById() throws Exception {
		boolean condition = false;
		Review r = reviewService.getReviewById(1);
		if (r.getReviewId() == 1)
			condition = true;
		assertTrue(condition);
	}

	@Test
	public void testInvalidGetReviewById() throws Exception {
		boolean condition = false;
		if (reviewService.getReviewById(0) == null)
			condition = true;
		assertTrue(condition);
	}
	
	@Test
	public void testValidAddMovieReview() throws Exception{
		boolean condition = false;
		Review review = new Review();
		review.setReview("test");
		review.setRating((float) 3.5);
		review.setMovieId(2);
		review.setUserId(1);
		Review response = reviewService.addMovie(review);
		List<Review> reviews = reviewService.getReviews();
		for (Review r : reviews) {
			if (r.getReviewId() == response.getReviewId())
				condition = true;
		}
		assertTrue(condition);
	}
	
	@Test
	public void testInvalidAddMovieReview() throws Exception{
		boolean condition = false;
		Review review = new Review();
		try {
			reviewService.addMovie(review);
		} catch (Exception e) {
			condition = true;
		}
		assertTrue(condition);
	}
}
