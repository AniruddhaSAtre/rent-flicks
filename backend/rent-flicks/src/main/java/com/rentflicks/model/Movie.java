package com.rentflicks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Movie {

	@Id
	@GeneratedValue
	//@Column(name="ID")
	private Integer movieId;

	@Size(max = 256, message = "Title should not be longer than 256 characters")
	@NotNull(message = "Missing Title")
	private String title;

	@NotNull(message = "Missing plot")
	private String plot;

	@NotNull(message = "Missing actor")
	private String actor;

	@NotNull(message = "Missing director")
	private String director;

	@NotNull(message = "Missing image URL")
	private String image;
	
	@NotNull(message = "Missing year released")
	private Integer year;
	
	@NotNull(message = "Missing critc rating")
	private Float criticRating;
	


	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Float getCriticRating() {
		return criticRating;
	}

	public void setCriticRating(Float rating) {
		this.criticRating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
