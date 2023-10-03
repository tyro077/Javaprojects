package com.bank;

public class Customer {

	// Attributes
    private String customerId;  // Unique identifier for each customer
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;

    // Constructor
    public Customer(String customerId, String firstName, String lastName,
                    String address, String phoneNumber, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    // Getters and setters for the attributes
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + customerId + ", Name=" + firstName + " " + lastName + 
               ", Address=" + address + ", PhoneNumber=" + phoneNumber + ", Email=" + email + "]";
    }
}
