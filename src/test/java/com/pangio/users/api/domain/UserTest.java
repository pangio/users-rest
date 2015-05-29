package com.pangio.users.api.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pangio.users.api.enums.Role;

/**
 * Unit test for the {@link User} pojo.
 * @author pangio
 */
public class UserTest {
	
	@Test
	public void canCreateUser() {
		
		User user = new User();
		user.setTitle("Sir");
		user.setRole(Role.ADMIN);
		user.setFirstName("Admin");
		user.setLastName("User");
		user.setDateOfBirth("23-06-1912");
		
		assertEquals("Sir", user.getTitle());
		assertEquals(Role.ADMIN, user.getRole());
		assertEquals("Admin", user.getFirstName());
		assertEquals("User", user.getLastName());
		assertEquals("23-06-1912", user.getDateOfBirth());
	}
		
	@Test
	public void canCreateEmptyUser() {
	
		User subscriber = new User();
		assertEquals(null, subscriber.getTitle());
		assertEquals(null, subscriber.getRole());
		assertEquals(null, subscriber.getFirstName());
		assertEquals(null, subscriber.getLastName());
	}
}
