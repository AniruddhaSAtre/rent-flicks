package com.rentflicks.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Video {

	@Id
	@GeneratedValue
	private Integer videoId;

	 @ManyToOne(cascade=CascadeType.ALL)
	  @JoinColumn(name="movieId", insertable=false, updatable=false)
	  private Movie movie;
	
	 private Integer movieId;
	 
	 @ManyToOne
	  @JoinColumn(name="ownerId", insertable=false, updatable=false)
	  private User owner;
	 
	 private Integer ownerId;
	 
	 @OneToMany
	 @JoinColumn(name="videoId")
	 private List<Request> requests;
	
	 public Integer getVideoId() {
		return videoId;
	}
	public List<Request> getRequests() {
		return requests;
	}
	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Integer getMovieId() {
		return this.movieId;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
}
