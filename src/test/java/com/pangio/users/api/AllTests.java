package com.pangio.users.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pangio.users.api.domain.SubscriberTest;
import com.pangio.users.api.domain.UserTest;
import com.pangio.users.api.service.SessionServiceTest;
import com.pangio.users.api.service.SubscriberServiceTest;
import com.pangio.users.api.service.UserServiceTest;

/**
 * Test Suite. 
 * Tests cover all layers of the application Domain Controllers and Services.
 * @author pangio
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	UserTest.class, 
	SubscriberTest.class, 
	SessionServiceTest.class,
	UserServiceTest.class,
	SubscriberServiceTest.class })
public class AllTests {

}
