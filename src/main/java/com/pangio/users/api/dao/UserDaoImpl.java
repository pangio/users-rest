package com.pangio.users.api.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pangio.users.api.IdGenerator;
import com.pangio.users.api.domain.User;

/**
 * {@link UserDao} implementation class.
 * 
 * @author pangio
 */
@Component
public class UserDaoImpl implements UserDao {

	Map<Long, User> users = new HashMap<Long, User>();

	/**
	 * Creates and Saves an Admin {@link User}
	 * 
	 * @param user
	 * @return the saved user
	 */
	@Override
	public User createAndSave(User user) {
		save(user);
		return user;
	}

	/**
	 * Deletes an Admin {@link User}
	 * 
	 * @param userId
	 */
	@Override
	public void delete(Long userId) {
		users.remove(userId);
	}

	/**
	 * List all Admin Users
	 * 
	 * @return list of admin
	 */
	@Override
	public Collection<User> list() {
		return users.values();
	}

	/**
	 * Finds {@link User} by id
	 * 
	 * @param userId
	 * @return user
	 */
	@Override
	public User findById(Long userId) {
		return users.get(userId);
	}

	/**
	 * Finds {@link User} given an email
	 * 
	 * @param email
	 * @return user
	 */
	@Override
	public User findByEmail(String email) {
		Iterator<User> it = users.values().iterator();
		while (it.hasNext()) {
			User user = it.next();
			if (email.equalsIgnoreCase(user.getCredentials().getEmail())) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Updates an Admin {@link User}
	 * 
	 * @param userId
	 * @param user
	 */
	@Override
	public void update(Long userId, User updatedUser) {
		updatedUser.setId(userId);
		users.put(userId, updatedUser);
	}

	/**
	 * Saves the user into the DB
	 * 
	 * @param user
	 */
	private void save(User user) {
		user.setId(IdGenerator.generateId());
		users.put(user.getId(), user);
	}
}
