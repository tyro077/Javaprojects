package com.bank;

import java.util.Date;

public class Transactions {

	//Attributes
	private long transactionId;
	private String accountNumber;
	private String type;
	private double amount;
	private Date date;
	private String description;
	private String status;
	
	//constructor
	public Transactions(long transactionId, String accountNumber, String type, double amount, String description) {
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.type = type;
		this.amount = amount;
		this.date = new Date();
		this.description = description;
		this.status = "PENDING";
	}
	public void completeTransaction() {
		this.status = "Completed";
	}
	public void failTransaction() {
		this.status = "Failed";
	}
	
	@Override
	public String toString() {
		return "Transaction [ID=" + transactionId + ", Type=" + type + ", Amount=" + amount + ", Date=" + date + ", Description=" + description + "]";
	}
}
