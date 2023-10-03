package com.bank;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "accounts") // Optional: You can specify the table name here
public class Account {

    // Attributes
    @DatabaseField(id = true) // Specifies this field as the primary key
    private String accountNumber;

    @DatabaseField // Specifies this field as a database column
    private String accountHolderName;

    @DatabaseField // Specifies this field as a database column
    private double balance;

    // Default (no-argument) constructor
    public Account() {
        // Initialize any default values here if needed
    }

    // Constructor
    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    // Deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money from the account
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;  // withdrawal was successful
        } else {
            System.out.println("Withdrawal amount invalid or exceeds balance.");
            return false; // withdrawal failed
        }
    }

    // Get the current balance
    public double getBalance() {
        return balance;
    }

    // Get account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Get account holder's name
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // You can also add setters if you need to modify account details, 
    // and any other additional methods specific to the account's functionality.
}
