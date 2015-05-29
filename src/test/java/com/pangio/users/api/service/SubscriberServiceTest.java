package com.pangio.users.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.pangio.users.api.dao.SubscriberDao;
import com.pangio.users.api.domain.Subscriber;

/**
 * {@link SubscriberServiceTest} guarantees the proper behavior of the {@link SubscriberService} and its methods.
 * @author pangio
 */
public class SubscriberServiceTest {

	private SubscriberDao subscriberDaoMock;
	private SubscriberService subscriberService;

	@Before
	public void setUp() {
		subscriberDaoMock = mock(SubscriberDao.class);
		subscriberService = new SubscriberService(subscriberDaoMock);
	}

	/**
	 * Verifies that a new Subscriber was created and saved
	 */
	@Test
	public void createSubscriber() {
		when(subscriberDaoMock.createAndSave(any(Subscriber.class))).thenReturn(new Subscriber());		
		subscriberService.createAndSave(new Subscriber());
		verify(subscriberDaoMock).createAndSave(any(Subscriber.class));
		verify(subscriberDaoMock, times(1)).createAndSave(any(Subscriber.class));
		verifyNoMoreInteractions(subscriberDaoMock);
	}

    /**
	 * Verifies that a subscriber was removed
	 */
	@Test
	public void deleteSubscriber() {
        
		Subscriber subscriber = new Subscriber();
		when(subscriberDaoMock.createAndSave(any(Subscriber.class))).thenReturn(subscriber);
		when(subscriberDaoMock.findById(any(Long.class))).thenReturn(subscriber);
        
		subscriberService.createAndSave(new Subscriber());
		subscriberService.delete(1L);
        
		verify(subscriberDaoMock).createAndSave(any(Subscriber.class));
		verify(subscriberDaoMock).delete(1L);
		verify(subscriberDaoMock, times(1)).createAndSave(any(Subscriber.class));
		verify(subscriberDaoMock, times(1)).delete(1L);
        
	}

	@Test
	public void getSubscriber() {

		Subscriber subscriber = new Subscriber();
		subscriber.setTitle("Sir");
		when(subscriberDaoMock.createAndSave(any(Subscriber.class))).thenReturn(subscriber);
		when(subscriberDaoMock.findById(any(Long.class))).thenReturn(subscriber);

		subscriberService.createAndSave(new Subscriber());
		verify(subscriberDaoMock).createAndSave(any(Subscriber.class));
		verify(subscriberDaoMock, times(1)).createAndSave(any(Subscriber.class));

		Subscriber retrievedSubscriber = subscriberService.findById(1L);
		assertEquals("Sir", retrievedSubscriber.getTitle());
	}
}