package com.pangio.users.api.domain;

/**
 * {@link Credential} POJO. Used to validate user and password in the login.
 * 
 * @author pangio
 */
public class Credential {

	private String email;
	private String password;

	/**
	 * Default constructor
	 */
	public Credential() {
	}

	/**
	 * Constructor
	 */
	public Credential(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
