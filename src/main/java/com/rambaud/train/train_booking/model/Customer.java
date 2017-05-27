package com.rambaud.train.train_booking.model;

public enum Customer {
	
	FOOBAR(0, "01011991", "500000000", "foobar@mail.com", "password"),
	FOOBAZ(1, "01011991", "600000000", "foobaz@mail.com", "password");
	
	private Customer(int customerId, String dateOfBirth, String cardNumber, String email, String password) {
		this.customerId = customerId;
		this.dateOfBirth = dateOfBirth;
		this.cardNumber = cardNumber;
		this.email = email;
		this.password = password;
	}
	
	private int customerId;
	private String dateOfBirth;
	private String cardNumber;
	private String email;
	private String password;
	
	public static Customer getById(int id) {
		for (Customer customer : Customer.values()) {
			if (customer.customerId == id) {
				return customer;
			}
		}
		return null;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

}
