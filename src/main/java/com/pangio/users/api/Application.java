package com.pangio.users.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pangio.users.api.domain.Credential;
import com.pangio.users.api.domain.Subscriber;
import com.pangio.users.api.domain.User;
import com.pangio.users.api.enums.Role;
import com.pangio.users.api.service.SubscriberService;
import com.pangio.users.api.service.UserService;

/**
 * Main class. Performs the setup of the Spring Boot application. 
 * Also creates one user for each type.
 * 
 * @author pangio
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.pangio.users.api")
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		UserService userService = context.getBean(UserService.class);
		SubscriberService subscriberService = context.getBean(SubscriberService.class);

		User superUser = new User();
		superUser.setTitle("Mr");
		superUser.setFirstName("Super");
		superUser.setLastName("User");
		superUser.setCredentials(new Credential("super@user.com", "pass"));
		userService.createAndSaveSuper(superUser);

		User admin = new User();
		admin.setTitle("Mr");
		admin.setFirstName("Admin");
		admin.setLastName("User");
		admin.setCredentials(new Credential("admin@user.com", "pass"));
		userService.createAndSave(admin);

		Subscriber subscriber = new Subscriber();
		subscriber.setTitle("Mr");
		subscriber.setFirstName("Subscriber");
		subscriber.setLastName("User");
		subscriber.setCredentials(new Credential("subscriber@user.com", "pass"));
		subscriber.setRole(Role.SUBSCRIBER);
		subscriber.setHomeAddress("home address");
		subscriber.setBillingAddress("billing address");
		subscriberService.createAndSave(subscriber);

	}
}
