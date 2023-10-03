package com.bank;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            // Initialize the database connection
            DatabaseConfig.initDatabase();

            // Create an instance of AccountDao and pass the ConnectionSource
            AccountDao accountDao = new AccountDao(DatabaseConfig.getConnectionSource());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Add new account");
                System.out.println("2. View account");
                System.out.println("3. Withdraw from account");
                System.out.println("4. Deposit into account");
                System.out.println("5. Transfer funds between accounts");
                System.out.println("6. Exit");


                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        addNewAccount(accountDao, scanner);
                        break;
                    case 2:
                        viewAccount(accountDao, scanner);
                        break;
                    case 3:
                        withdrawFromAccount(accountDao, scanner);
                        break;
                    case 4:
                        depositIntoAccount(accountDao, scanner);
                        break;
                    case 5:
                        transferFunds(accountDao, scanner);
                        break;
                    case 6:
                        DatabaseConfig.closeConnection();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewAccount(AccountDao accountDao, Scanner scanner) throws SQLException {
        System.out.println("Enter account number:");
        String accountNumber = scanner.nextLine();

        // Validate if account already exists
        if (accountDao.accountExists(accountNumber)) {
            System.out.println("Account with this number already exists. Choose a different number.");
            return;
        }

        System.out.println("Enter account holder name:");
        String accountHolderName = scanner.nextLine();

        System.out.println("Enter initial balance:");
        double balance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Account newAccount = new Account(accountNumber, accountHolderName, balance);
        accountDao.createAccount(newAccount);

        System.out.println("Account created successfully!");
    }

    private static void viewAccount(AccountDao accountDao, Scanner scanner) throws SQLException {
        System.out.println("Enter account number to view:");
        String accountNumber = scanner.nextLine();

        Account retrievedAccount = accountDao.getAccountById(accountNumber);
        if (retrievedAccount != null) {
            System.out.println("Account Holder: " + retrievedAccount.getAccountHolderName());
            System.out.println("Balance: " + retrievedAccount.getBalance());
        } else {
            System.out.println("No account found with that number.");
        }
    }
    private static void withdrawFromAccount(AccountDao accountDao, Scanner scanner) throws SQLException {
        System.out.println("Enter account number to withdraw from:");
        String accountNumber = scanner.nextLine();

        Account existingAccount = accountDao.getAccountById(accountNumber);
        if (existingAccount == null) {
            System.out.println("No account found with that number.");
            return;
        }

        System.out.println("Enter amount to withdraw:");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        boolean success = existingAccount.withdraw(amount);
        if (success) {
            accountDao.updateAccount(existingAccount); // Update the account in the database
            System.out.println("Withdrawal successful! New balance: " + existingAccount.getBalance());
        } else {
            // The withdraw method will print the relevant error message
        }
    }
    
    private static void depositIntoAccount(AccountDao accountDao, Scanner scanner) throws SQLException {
        System.out.println("Enter account number to deposit into:");
        String accountNumber = scanner.nextLine();

        Account existingAccount = accountDao.getAccountById(accountNumber);
        if (existingAccount == null) {
            System.out.println("No account found with that number.");
            return;
        }

        System.out.println("Enter amount to deposit:");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        existingAccount.deposit(amount);
        accountDao.updateAccount(existingAccount); // Update the account in the database

        System.out.println("Deposit successful! New balance: " + existingAccount.getBalance());
    }
    
    private static void transferFunds(AccountDao accountDao, Scanner scanner) throws SQLException {
        System.out.println("Enter source account number:");
        String sourceAccountNumber = scanner.nextLine();

        Account sourceAccount = accountDao.getAccountById(sourceAccountNumber);
        if (sourceAccount == null) {
            System.out.println("No account found with that source account number.");
            return;
        }

        System.out.println("Enter destination account number:");
        String destAccountNumber = scanner.nextLine();

        Account destAccount = accountDao.getAccountById(destAccountNumber);
        if (destAccount == null) {
            System.out.println("No account found with that destination account number.");
            return;
        }

        System.out.println("Enter amount to transfer:");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        // Ensure sufficient balance in the source account
        if (sourceAccount.getBalance() < amount) {
            System.out.println("Insufficient balance in the source account.");
            return;
        }

        // Now, perform the transfer
        sourceAccount.withdraw(amount);
        destAccount.deposit(amount);

        // Update the accounts in the database
        accountDao.updateAccount(sourceAccount);
        accountDao.updateAccount(destAccount);

        System.out.println("Transfer successful! New balance in source account: " + sourceAccount.getBalance());
        System.out.println("New balance in destination account: " + destAccount.getBalance());
    }



}

