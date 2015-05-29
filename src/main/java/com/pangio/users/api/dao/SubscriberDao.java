package com.pangio.users.api.dao;

import java.util.Collection;

import javax.annotation.Resource;

import com.pangio.users.api.domain.Subscriber;

/**
 * {@link SubscriberDao} is where all {@link Subscriber} will be stored.
 * 
 * @author pangio
 */
@Resource
public interface SubscriberDao {

	/**
	 * Creates and Saves a {@link Subscriber}
	 * 
	 * @param subscriber
	 * @return the saved subscriber
	 */
	public Subscriber createAndSave(Subscriber subscriber);

	/**
	 * Finds {@link Subscriber} by id
	 * 
	 * @param subscriber
	 * @return subscriberId
	 */
	public Subscriber findById(Long subscriberId);

	/**
	 * Finds {@link Subscriber} given an email
	 * 
	 * @param email
	 * @return subscriber
	 */
	public Subscriber findByEmail(String email);

	/**
	 * List all {@link Subscriber}
	 * 
	 * @return list of subscribers
	 */
	public Collection<Subscriber> list();

	/**
	 * Deletes a {@link Subscriber}
	 * 
	 * @param subscriberId
	 */
	public void delete(Long subscriberId);

	/**
	 * Updates a {@link Subscriber}
	 * 
	 * @param subscriberId
	 * @param subscriber
	 */
	public void update(Long subscriberId, Subscriber subscriber);

}
