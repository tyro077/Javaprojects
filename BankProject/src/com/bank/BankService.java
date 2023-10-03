package com.bank;

import java.sql.SQLException;
import java.util.List;

public class BankService {
    private AccountDao accountDao;

    public BankService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account createAccount(String accountHolderName, double initialBalance) {
        try {
            Account newAccount = new Account(accountHolderName, accountHolderName, initialBalance);
            accountDao.createAccount(newAccount);
            System.out.println("Account created successfully: " + newAccount.getAccountNumber());
            return newAccount;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating account: " + e.getMessage());
            return null;
        }
    }
    

    // Deposit funds into an account
    public void deposit(String accountNumber, double amount) {
        try {
            Account account = accountDao.getAccountById(accountNumber);
            if (account != null) {
                account.deposit(amount);
                accountDao.updateAccount(account);
            } else {
                System.out.println("Account not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error appropriately
        }
    }

    // Withdraw funds from an account
    public boolean withdraw(String accountNumber, double amount) {
        try {
            Account account = accountDao.getAccountById(accountNumber);
            if (account != null && account.withdraw(amount)) {
                accountDao.updateAccount(account);
                return true;
            } else {
                System.out.println("Withdrawal failed!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error appropriately
            return false;
        }
    }

    // Transfer funds from one account to another
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        try {
            Account fromAccount = accountDao.getAccountById(fromAccountNumber);
            Account toAccount = accountDao.getAccountById(toAccountNumber);

            if (fromAccount != null && toAccount != null && fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
                accountDao.updateAccount(fromAccount);
                accountDao.updateAccount(toAccount);
                return true;
            } else {
                System.out.println("Transfer failed!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error appropriately
            return false;
        }
    }

    // Get account balance
    public double getBalance(String accountNumber) {
        try {
            Account account = accountDao.getAccountById(accountNumber);
            if (account != null) {
                return account.getBalance();
            } else {
                System.out.println("Account not Found!");
                return -1; // Indicates an error. Alternatively, you could throw an exception.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error appropriately
            return -1;
        }
    }

    // Get a list of all accounts (for admin purposes, etc.)
    public List<Account> getAllAccounts() {
        try {
            return accountDao.getAllAccounts();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error appropriately
            return null;
        }
    }
}

