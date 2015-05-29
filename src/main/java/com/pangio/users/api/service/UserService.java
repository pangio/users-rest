package com.pangio.users.api.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.users.api.dao.UserDao;
import com.pangio.users.api.domain.User;
import com.pangio.users.api.enums.Role;

/**
 * Service Layer. {@link UserService} provides an interface to do CRUD
 * operations of {@link User}.
 * 
 * @author pangio
 */
@Component
public class UserService {

	@Autowired
	UserDao userDao;

	/**
	 * Default constructor
	 */
	public UserService() {
	}
	
	/**
	 * Constructor with params. Required by Mockito
	 * @param userDao
	 */
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * Creates and Saves an ADMIN {@link User}
	 * @param admin user
	 * @return saved admin user
	 */
	public User createAndSave(User admin) {
		admin.setRole(Role.ADMIN);
		return this.userDao.createAndSave(admin);
	}

	/**
	 * Creates and Saves a SUPER {@link User}
	 * @param super user
	 * @return the saved superUser
	 */
	public User createAndSaveSuper(User superUser) {
		superUser.setRole(Role.SUPER);
		return this.userDao.createAndSave(superUser);
	}

	/**
	 * Finds {@link User} by id
	 * 
	 * @param id
	 * @return user
	 */
	public User findById(Long id) {
		return this.userDao.findById(id);
	}

	/**
	 * Finds {@link User} given an email
	 * 
	 * @param email
	 * @return user
	 */
	public User findByEmail(String email){
		return this.userDao.findByEmail(email);
	}
	
	/**
	 * Updates an ADMIN {@link User}
	 * 
	 * @param userId
	 * @param user
	 */
	public User update(Long userId, User user) {        
		if (userDao.findById(userId) != null && user != null) {
			userDao.update(userId, user);
			return user;
		} else {
			return null;
		}
	}

	/**
	 * Deletes an ADMIN {@link User}
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		User user = this.userDao.findById(id);
		if (user != null)
			this.userDao.delete(id);
		return;
	}
	
	/**
	 * List all ADMIN Users
	 * 
	 * @return list of admin users
	 */
	public List<User> listAdmins() {
		List<User> admins = new ArrayList<User>();		
		Iterator<User> it = this.userDao.list().iterator();
		while (it.hasNext()) {
			User user = it.next();
			if (Role.ADMIN.equals(user.getRole())) {
				admins.add(user);
			}
		}
		return admins;
	}
}
