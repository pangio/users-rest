package com.pangio.users.api.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pangio.users.api.domain.User;
import com.pangio.users.api.enums.Role;
import com.pangio.users.api.service.SecurityService;
import com.pangio.users.api.service.UserService;

/**
 * RestController of used for CRUD operations of ADMIN {@link User}.
 * Manages all requests under the URI '/user/admin' . See {@link HttpMethod} and
 * {@link Role}
 * 
 * @author pangio
 */
@RestController
@ComponentScan(basePackages = "com.pangio.users.api")
@RequestMapping(value = "/user/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	final static Logger logger = Logger.getLogger(AdminController.class);

	AdminController() {
	}

	/**
	 * Creates a new ADMIN {@link User}.
	 * 
	 * @param new admin
	 * @param token
	 * @return created admin
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody User admin, @RequestHeader("token") Long token) {
		if (securityService.isSuper(token)) {
			admin = userService.createAndSave(admin);
			logger.info("New Admin created: " + admin);
			return new ResponseEntity<User>(admin, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Retrieves an ADMIN {@link User}.
	 * 
	 * @param adminId to be retrieved
	 * @param token
	 * @return the admin
	 */
	@RequestMapping(value = "/{adminId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable Long adminId,
			@RequestHeader("token") Long token) {
		User admin = null;
		if (securityService.canReadAdmin(token, adminId)) {
			admin = userService.findById(adminId);
			if (admin != null) {
				return new ResponseEntity<User>(admin, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(admin, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Updates an ADMIN {@link User}.
	 * 
	 * @param adminId to be updated
	 * @param the admin as json
	 * @param token
	 * @return updated admin
	 */
	@RequestMapping(value = "/{adminId}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Long adminId,
			@RequestBody User admin, @RequestHeader("token") Long token) {

		User updatedAdmin = null;
		if (securityService.isSuper(token)) {
			updatedAdmin = userService.findById(adminId);
			if (updatedAdmin != null) {
				updatedAdmin = userService.update(adminId, admin);
				logger.info("Admin updated: " + admin);
				return new ResponseEntity<User>(updatedAdmin, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(admin, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Deletes an ADMIN {@link User}
	 * 
	 * @param adminId to be deleted.
	 */
	@RequestMapping(value = "/{adminId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long adminId,
			@RequestHeader("token") Long token) {

		if (adminId != null && securityService.isSuper(token)) {
			User admin = userService.findById(adminId);
			if (admin == null) {
				return new ResponseEntity<User>(admin, HttpStatus.NOT_FOUND);
			} else {
				userService.delete(adminId);
				logger.info("Admin deleted id: " + admin);
				return new ResponseEntity<User>(admin, HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/**
	 * Provides a list of all the ADMIN {@link User}.
	 * 
	 * @return list of ADMIN users.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> list(@RequestHeader("token") Long token) {
		if (securityService.isSuper(token)) {
			List<User> admins = userService.listAdmins();
			return new ResponseEntity<List<User>>(admins, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
}
