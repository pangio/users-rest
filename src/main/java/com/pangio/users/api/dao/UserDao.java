package com.pangio.users.api.dao;

import java.util.Collection;

import javax.annotation.Resource;

import com.pangio.users.api.domain.User;

/**
 * {@link SubscriberDao} is where all {@link User} will be stored. including
 * Super and Admin.
 * 
 * @author pangio
 */
@Resource
public interface UserDao {

	/**
	 * Creates and Saves an Admin {@link User}
	 * @param user
	 * @return the saved user
	 */
	public User createAndSave(User user);

	/**
	 * Finds {@link User} by id
	 * 
	 * @param userId
	 * @return user
	 */
	public User findById(Long userId);

	/**
	 * Finds {@link User} given an email
	 * 
	 * @param email
	 * @return user
	 */
	public User findByEmail(String email);

	/**
	 * List all Admin Users
	 * 
	 * @return list of admin
	 */
	public Collection<User> list();

	/**
	 * Deletes an Admin {@link User}
	 * 
	 * @param userId
	 */
	public void delete(Long userId);

	/**
	 * Updates an Admin {@link User}
	 * 
	 * @param userId
	 * @param user
	 */
	public void update(Long userId, User user);

}
