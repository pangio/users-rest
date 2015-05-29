package com.pangio.users.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.users.api.domain.User;
import com.pangio.users.api.enums.Role;

/**
 * Service Layer. {@link SecurityService}.
 * Handles the rules to grant permissions for a given {@link User}.
 * 
 * @author pangio
 */
@Component
public class SecurityService {

	@Autowired
	SessionService sessionService;

	@Autowired
	UserService userService;

	@Autowired
	SubscriberService subscriberService;

	public SecurityService() {
	}

	public boolean canReadSubscriber(Long token, Long subscriberId) {
		User loggedUser = sessionService.getloggedUser(token);
		if (loggedUser != null && ((isSuper(token) || isAdmin(token) || isMe(loggedUser, subscriberId))))
			return true;
		return false;
	}

	public boolean canReadAdmin(Long token, Long userId) {
		User loggedUser = sessionService.getloggedUser(token);
		if (loggedUser != null && (isSuper(token) || isMe(loggedUser, userId)))
			return true;
		return false;
	}

	public boolean isSuper(Long token) {
		User user = sessionService.getloggedUser(token);
		if (user != null && user.getRole().equals(Role.SUPER))
			return true;
		return false;
	}

	public boolean isAdmin(Long token) {
		User user = sessionService.getloggedUser(token);
		if (user != null && user.getRole().equals(Role.ADMIN))
			return true;
		return false;
	}

	public boolean isAdminOrSuper(Long token) {
		return isAdmin(token) || isSuper(token);
	}

	private boolean isMe(User loggedUser, Long id) {
		return loggedUser.getId() == id;
	}
}
