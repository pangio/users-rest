package com.pangio.users.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pangio.users.api.domain.Credential;
import com.pangio.users.api.service.SessionService;
import com.pangio.users.api.service.UserService;

/**
 * {@link SessionController} handles login & logout operations. Manages all
 * requests under the URI '/session' .
 * 
 * @author pangio
 */
@RestController
@ComponentScan(basePackages = "com.pangio.users.api")
@RequestMapping(value = "/session")
public class SessionController {

	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;

	SessionController() {
	}

	/**
	 * Login
	 * 
	 * @param credentials
	 *            - email and password
	 * @return token
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody Credential credentials) {
		// TODO encrypt password
		if (sessionService.validate(credentials)) {
			Long token = sessionService.generateTokenAndLogin(credentials
					.getEmail());
			return new ResponseEntity<String>(token.toString(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Logout
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logout(@RequestHeader("token") Long token) {

		if (sessionService.isLoggedIn(token)) {
			sessionService.logout(token);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}
}
