package com.pangio.users.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link SessionServiceTest} guarantees the proper behavior of the {@link UserService} and its methods.
 * @author pangio
 */
public class SessionServiceTest {

	private Random tokenGeneratorMock;
	private SessionService sessionService;

	@Before
	public void setUp() {
		tokenGeneratorMock = mock(Random.class);
		sessionService = new SessionService(tokenGeneratorMock);
	}

	/**
	 * Verifies that a new token was created
	 */
	@Test
	public void createToken() {
		when(tokenGeneratorMock.nextLong()).thenReturn(123123123L);

		Long token = sessionService.generateTokenAndLogin("some@email.com");
		verify(tokenGeneratorMock, times(1)).nextLong();
		assertEquals(token.longValue(), 123123123L);
	}

}