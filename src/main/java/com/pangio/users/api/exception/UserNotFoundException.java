package com.pangio.users.api.exception;

import com.pangio.users.api.domain.User;

/**
 * This exception is thrown when an {@link User} is not found.
 * 
 * @author pangio
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = -2441684481224169429L;

	/**
	 * Constructor of the exception
	 * 
	 * @param userId
	 */
	public UserNotFoundException(Long userId) {
		super("The User with id " + userId + " doesn't exist");
	}

	public UserNotFoundException(String  email) {
		super("The User with email " + email + " doesn't exist");
	}
}
