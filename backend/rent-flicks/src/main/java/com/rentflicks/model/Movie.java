package com.rentflicks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rentflicks.markers.Authenticate;
import com.rentflicks.markers.Create;

@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Movie {

	@Id
	@GeneratedValue
	//@Column(name="ID")
	private Integer movieId;

	/*@Size(max = 256, message = "Title should not be longer than 256 characters",
			groups = { Create.class, Authenticate.class })*/
	//@NotNull(message = "Missing Title", groups = { Create.class, })
	private String title;

	//@NotNull(message = "Missing plot", groups = { Create.class, })
	private String plot;

	//@NotNull(message = "Missing actor", groups = { Create.class })
	private String actor;

	//@NotNull(message = "Missing director", groups = { Create.class,})
	private String director;

	//@NotNull(message = "Missing image URL", groups = { Create.class,})
	private String image;
	
	//@NotNull(message = "Missing year released", groups = { Create.class,})
	private Integer year;
	
	//@NotNull(message = "Missing critc rating", groups = { Create.class,})
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
