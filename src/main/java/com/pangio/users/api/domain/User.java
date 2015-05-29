package com.pangio.users.api.domain;

import com.pangio.users.api.enums.Role;

/**
 * Domain Layer. Represents the POJO of an {@link User}.
 * 
 * @author pangio
 */
public class User {

	private Long id;
	private String title;
	private String firstName;
	private String lastName;
	private Credential credentials = new Credential();
	private String dateOfBirth;
	private Role role;

	/**
	 * Default constructor
	 */
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Override
	public String toString() {
		return this.title +" "+ this.firstName + " " + this.lastName;
	}

	public Credential getCredentials() {
		return credentials;
	}

	public void setCredentials(Credential credentials) {
		this.credentials = credentials;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
