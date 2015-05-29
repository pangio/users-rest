package com.pangio.users.api.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pangio.users.api.IdGenerator;
import com.pangio.users.api.domain.Subscriber;

/**
 * {@link SubscriberDao} implementation class.
 * 
 * @author pangio
 */
@Component
public class SubscriberDaoImpl implements SubscriberDao {

	Map<Long, Subscriber> users = new HashMap<Long, Subscriber>();

	/**
	 * Creates and Saves a {@link Subscriber}
	 * 
	 * @param subscriber
	 * @return the saved subscriber
	 */
	@Override
	public Subscriber createAndSave(Subscriber subscriber) {
		save(subscriber);
		return subscriber;
	}

	/**
	 * Deletes a {@link Subscriber}
	 * 
	 * @param subscriberId
	 */
	@Override
	public void delete(Long subscriberId) {
		users.remove(subscriberId);
	}

	/**
	 * List all {@link Subscriber}
	 * 
	 * @return list of subscribers
	 */
	@Override
	public Collection<Subscriber> list() {
		return users.values();
	}

	/**
	 * Finds {@link Subscriber} by id
	 * 
	 * @param subscriber
	 * @return subscriberId
	 */
	@Override
	public Subscriber findById(Long subscriberId) {
		return users.get(subscriberId);
	}

	/**
	 * Finds {@link Subscriber} given an email
	 * 
	 * @param email
	 * @return subscriber
	 */
	@Override
	public Subscriber findByEmail(String email) {
		Iterator<Subscriber> it = users.values().iterator();
		while (it.hasNext()) {
			Subscriber subscriber = it.next();
			if (email.equalsIgnoreCase(subscriber.getCredentials().getEmail())) {
				return subscriber;
			}
		}
		return null;
	}

	/**
	 * Updates a {@link Subscriber}
	 * 
	 * @param subscriberId
	 * @param subscriber
	 */
	@Override
	public void update(Long subscriberId, Subscriber updatedSubscriber) {
		updatedSubscriber.setId(subscriberId);
		users.put(subscriberId, updatedSubscriber);
	}

	/**
	 * Saves the user into the DB
	 * 
	 * @param subscriber
	 */
	private void save(Subscriber subscriber) {
		subscriber.setId(IdGenerator.generateId());
		users.put(subscriber.getId(), subscriber);
	}
}
