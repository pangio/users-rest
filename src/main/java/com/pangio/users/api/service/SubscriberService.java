package com.pangio.users.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.users.api.dao.SubscriberDao;
import com.pangio.users.api.domain.Subscriber;
import com.pangio.users.api.domain.User;
import com.pangio.users.api.enums.Role;

/**
 * Service Layer. {@link SubscriberService} provides an interface to do CRUD
 * operations of {@link Subscriber}
 * 
 * @author pangio
 */
@Component
public class SubscriberService {

	@Autowired
	SubscriberDao subscriberDao;

	/**
	 * Default constructor
	 */
	public SubscriberService() {
	}

	/**
	 * Constructor with params. Required by Mockito
	 * @param subscriberDao
	 */
	public SubscriberService(SubscriberDao subscriberDao) {
		this.subscriberDao = subscriberDao;
	}
	
	/**
	 * Creates and Saves a {@link Subscriber}
	 * 
	 * @param subscriber
	 * @return the saved subscriber
	 */
	public Subscriber createAndSave(Subscriber subscriber) {
		subscriber.setRole(Role.SUBSCRIBER);
		return this.subscriberDao.createAndSave(subscriber);
	}

	/**
	 * Finds {@link Subscriber} by id
	 * 
	 * @param subscriber
	 * @return subscriberId
	 */
	public Subscriber findById(Long id) {
		return this.subscriberDao.findById(id);
	}
	
	/**
	 * Finds {@link Subscriber} given an email
	 * 
	 * @param email
	 * @return subscriber
	 */
	public User findByEmail(String email) {
		return this.subscriberDao.findByEmail(email);
	}
	
	/**
	 * Updates a {@link Subscriber}
	 * 
	 * @param subscriberId
	 * @param subscriber
	 */
	public Subscriber update(Long subscriberId, Subscriber subscriber) {        
		if (subscriberDao.findById(subscriberId) != null && subscriber != null) {
			subscriberDao.update(subscriberId, subscriber);
			return subscriber;
		} else {
			return null;
		}
	}

	/**
	 * Deletes a {@link Subscriber}
	 * 
	 * @param subscriberId
	 */
	public void delete(Long id) {
		User user = this.subscriberDao.findById(id);
		if (user != null) 
			this.subscriberDao.delete(id);
		return;
	}

	/**
	 * List all {@link Subscriber}
	 * 
	 * @return list of subscribers
	 */
	public List<Subscriber> list() {
		return new ArrayList<>(this.subscriberDao.list());
	}
}
