package com.pangio.users.api.domain;

/**
 * Domain Layer. Represents the POJO of an {@link Subscriber}.
 * Extends {@link User}.
 * 
 * @author pangio
 */
public class Subscriber extends User {

	private String homeAddress;
	private String billingAddress;

	/**
	 * Default constructor
	 */
	public Subscriber() {
		super();
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

}
