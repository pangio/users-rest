package com.pangio.users.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.users.api.domain.Credential;
import com.pangio.users.api.domain.User;

/**
 * Service Layer. {@link SessionService}. Handles the sessions and provides
 * tokens once a {@link User} is logged in.
 * 
 * @author pangio
 */
@Component
public class SessionService {

	@Autowired
	UserService userService;

	@Autowired
	SubscriberService subscriberService;

	/**
	 * Map used to stores sessions.
	 * 
	 * Key: token Value: email
	 */
	private Map<Long, String> sessions = new HashMap<Long, String>();

	private Random tokenGenerator = new Random();

	final static Logger logger = Logger.getLogger(SessionService.class);

	/**
	 * Default constructor
	 */
	public SessionService() {
	}

	/**
	 * Constructor with params. Required by Mockito
	 * 
	 * @param tokenGenerator
	 */
	public SessionService(Random tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}

	/**
	 * Creates a new Token and saves it into the Sessions
	 * 
	 * @param user
	 *            's email
	 * @return new token
	 */
	public Long generateTokenAndLogin(String email) {
		Long token = generateToken();
		sessions.put(token, email);
		logger.info("Token created : "+ token + " for user " + email);
		return token;
	}

	/**
	 * Retrieves the logged {@link User}
	 * 
	 * @param user
	 *            's token
	 * @return logged user
	 */
	public User getloggedUser(Long token) {
		User user = null;
		String email = this.findEmailByToken(token);

		user = subscriberService.findByEmail(email);
		if (user == null)
			user = userService.findByEmail(email);
		return user;
	}

	/**
	 * Validates whether the given credentials are correct or not.
	 * 
	 * @param credentials
	 * @return validation result
	 */
	public boolean validate(Credential credentials) {

		User user = subscriberService.findByEmail(credentials.getEmail());
		if (user != null
				&& credentials.getPassword().equals(
						user.getCredentials().getPassword()))
			return true;

		user = userService.findByEmail(credentials.getEmail());
		if (user != null
				&& credentials.getPassword().equals(
						user.getCredentials().getPassword()))
			return true;

		return false;
	}

	/**
	 * Removes the token and log the user out.
	 * 
	 * @param email
	 */
	public void logout(Long token) {
		sessions.remove(token);
		return;
	}

	/**
	 * Returns whether a user is logged in.
	 * 
	 * @param email
	 * @return whether is logged in
	 */
	public boolean isLoggedIn(Long token) {
		return sessions.containsKey(token);
	}

	/**
	 * Creates a new token
	 * 
	 * @return the token
	 */
	private Long generateToken() {
		return Math.abs(tokenGenerator.nextLong());
	}

	/**
	 * Finds {@link User} given an email
	 * 
	 * @param token
	 * @return user's email
	 */
	private String findEmailByToken(Long token) {
		return sessions.get(token);
	}
}
