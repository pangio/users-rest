package com.pangio.users.api.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pangio.users.api.enums.Role;

/**
 * Unit test for the {@link Subscriber} pojo.
 * @author pangio
 */
public class SubscriberTest {
	
	@Test
	public void canCreateSubscriber() {
		
		Subscriber subscriber = new Subscriber();
		subscriber.setTitle("MR");
		subscriber.setRole(Role.SUBSCRIBER);
		subscriber.setFirstName("Subscriber");
		subscriber.setLastName("User");
		subscriber.setBillingAddress("billing address");
		subscriber.setHomeAddress("home address");
		subscriber.setDateOfBirth("23-06-1912");

		assertEquals("MR", subscriber.getTitle());
		assertEquals(Role.SUBSCRIBER, subscriber.getRole());
		assertEquals("Subscriber", subscriber.getFirstName());
		assertEquals("User", subscriber.getLastName());
		assertEquals("billing address", subscriber.getBillingAddress());
		assertEquals("home address", subscriber.getHomeAddress());
		assertEquals("23-06-1912", subscriber.getDateOfBirth());
	}

	@Test
	public void canCreateEmptySubscriber() {
		
		Subscriber subscriber = new Subscriber();
		assertEquals(null, subscriber.getTitle());
		assertEquals(null, subscriber.getRole());
		assertEquals(null, subscriber.getFirstName());
		assertEquals(null, subscriber.getLastName());
		assertEquals(null, subscriber.getBillingAddress());
		assertEquals(null, subscriber.getHomeAddress());
	}
}
