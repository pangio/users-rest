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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pangio.users.api.domain.Subscriber;
import com.pangio.users.api.enums.Role;
import com.pangio.users.api.service.SecurityService;
import com.pangio.users.api.service.SubscriberService;

/**
 * RestController of used for CRUD operations of {@link Subscriber}
 * Manages all requests under the URI '/user/subscriber' . See {@link HttpMethod} and
 * {@link Role}
 * 
 * @author pangio
 */
@RestController
@ComponentScan(basePackages = "com.pangio.users.api")
@RequestMapping(value = "/user/subscriber")
public class SubscriberController {

	final static Logger logger = Logger.getLogger(SubscriberController.class);

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	private SecurityService securityService;

	SubscriberController() {
	}

	/**
	 * Creates a new {@link Subscriber}.
	 * 
	 * @param new subscriber
	 * @return created subscriber
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Subscriber subscriber) {
		subscriber = subscriberService.createAndSave(subscriber);
		logger.info("New Subscriber created: " + subscriber);
		return new ResponseEntity<Subscriber>(subscriber, HttpStatus.CREATED);
	}

	/**
	 * Retrieves a {@link Subscriber}.
	 * 
	 * @param subscriberId to be retrieved
	 * @param token
	 * @return the subscriber
	 */
	@RequestMapping(value = "/{subscriberId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable Long subscriberId, @RequestHeader("token") Long token) {

		Subscriber subscriber = null;
			if (securityService.canReadSubscriber(token, subscriberId)) {
				subscriber = subscriberService.findById(subscriberId);
				if (subscriber != null) {
					return new ResponseEntity<Subscriber>(subscriber, HttpStatus.OK);
				} else {
					return new ResponseEntity<Subscriber>(subscriber, HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			}
	}

	/**
	 * Updates a {@link Subscriber}.
	 * 
	 * @param subscriberId to be updated
	 * @param subscriber as json
	 * @param token
	 * @return updated subscriber
	 */
	@RequestMapping(value = "/{subscriberId}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Long subscriberId, @RequestBody Subscriber subscriber, @RequestHeader("token") Long token) {
		
		Subscriber updatedSubscriber = null;
		if (securityService.isAdminOrSuper(token)) {
			updatedSubscriber = subscriberService.findById(subscriberId);
			if (updatedSubscriber != null) {
				updatedSubscriber = subscriberService.update(subscriberId, subscriber);
				logger.info("Subscriber updated: " + subscriber);
				return new ResponseEntity<Subscriber>(updatedSubscriber, HttpStatus.OK);
			} else {
				return new ResponseEntity<Subscriber>(subscriber, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Deletes a {@link Subscriber}
	 * 
	 * @param subscriberId to be deleted
	 * @param token
	 * @return the subscriber
	 */
	@RequestMapping(value = "/{subscriberId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long subscriberId, @RequestHeader("token") Long token) {

		if (subscriberId != null && securityService.isSuper(token)) {
			Subscriber subscriber = subscriberService.findById(subscriberId);
			if (subscriber == null) {
				return new ResponseEntity<Subscriber>(subscriber, HttpStatus.NOT_FOUND);
			} else {
				subscriberService.delete(subscriberId);
				logger.info("Subscriber deleted id: " + subscriber);
				return new ResponseEntity<Subscriber>(subscriber, HttpStatus.NO_CONTENT);
			}			
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);			
	}

	/**
	 * Provides a list of all the {@link Subscriber}.
	 * 
	 * @return list of subscribers.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> list(@RequestHeader("token") Long token) {
		if (securityService.isAdminOrSuper(token)) {
			List<Subscriber> subscribers = subscriberService.list();
			return new ResponseEntity<List<Subscriber>>(subscribers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
}
