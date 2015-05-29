package com.pangio.users.api.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.pangio.users.api.dao.UserDao;
import com.pangio.users.api.domain.User;

/**
 * {@link UserServiceTest} guarantees the proper behavior of the {@link UserService} and its methods.
 * @author pangio
 */
public class UserServiceTest {

	private UserDao userDaoMock;
	private UserService userService;

	@Before
	public void setUp() {
		userDaoMock = mock(UserDao.class);
		userService = new UserService(userDaoMock);
	}

	/**
	 * Verifies that a new User was created and saved
	 */
	@Test
	public void createUser() {
		when(userDaoMock.createAndSave(any(User.class))).thenReturn(new User());		
		userService.createAndSave(new User());
		verify(userDaoMock).createAndSave(any(User.class));
		verify(userDaoMock, times(1)).createAndSave(any(User.class));
		verifyNoMoreInteractions(userDaoMock);
	}

    /**
	 * Verifies that a user was removed
	 */
	@Test
	public void deleteUser() {
        
		User user = new User();
		when(userDaoMock.createAndSave(any(User.class))).thenReturn(user);
		when(userDaoMock.findById(any(Long.class))).thenReturn(user);
        
		userService.createAndSave(new User());
		userService.delete(1L);
        
		verify(userDaoMock).createAndSave(any(User.class));
		verify(userDaoMock).delete(1L);
		verify(userDaoMock, times(1)).createAndSave(any(User.class));
		verify(userDaoMock, times(1)).delete(1L);
	}

	@Test
	public void getUser() {

		User user = new User();
		when(userDaoMock.createAndSave(any(User.class))).thenReturn(new User());
		when(userDaoMock.findById(any(Long.class))).thenReturn(user);
		when(userDaoMock.findByEmail(any(String.class))).thenReturn(user);

		userService.createAndSave(new User());
		userService.findById(1L);
		userService.findByEmail("my@email.com");

		verify(userDaoMock).createAndSave(any(User.class));
		verify(userDaoMock).findByEmail(any(String.class));
		verify(userDaoMock).findById(any(Long.class));

	}
}