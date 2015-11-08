package com.rentflicks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rentflicks.markers.Authenticate;
import com.rentflicks.markers.Create;

@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User {

	@Id
	@GeneratedValue
	//@Column(name="UID")
	private Integer userId;

	@Size(max = 256, message = "Email should not be longer than 256 characters",
			groups = { Create.class, Authenticate.class })
	@NotNull(message = "Missing email", groups = { Create.class, Authenticate.class })
	@Pattern(message = "Email is not in valid format", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
			groups = { Create.class, Authenticate.class })
	private String email;

	@Size(min = 2, max = 256, message = "First name should be between 2 and 256 characters", groups = { Create.class })
	@NotNull(message = "Missing first name", groups = { Create.class, })
	private String firstName;

	@Size(min = 2, max = 256, message = "Last name should be between 2 and 256 characters", groups = { Create.class })
	@NotNull(message = "Missing last name", groups = { Create.class })
	private String lastName;

	@Size(min = 6, message = "Password should be at least 6 characters long",
			groups = { Create.class, Authenticate.class })
	@NotNull(message = "Missing password", groups = { Create.class, Authenticate.class })
	private String password;

	@Transient
	private String sessionId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
