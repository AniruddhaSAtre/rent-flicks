package com.rentflicks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Review {

	@Id
	@GeneratedValue
	private Integer reviewId;

	private String review;

	@OneToOne
	@JoinColumn(name="movieId", insertable=false, updatable=false)
	 private Movie movie;
	
	private Integer movieId;
	
	@OneToOne
	@JoinColumn(name="userId", insertable=false, updatable=false)
	  private User user;
	
	private Integer userId;
	
	private Float rating;

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
}
