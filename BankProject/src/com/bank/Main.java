package com.bank;

import java.sql.SQLException;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;
import com.bank.UserDao;


public class Main {

    public static void main(String[] args) throws Exception {
        try {
            // Initialize the database connection
            DatabaseConfig.initDatabase();
            DatabaseConfig.initTables();
            // Create an instance of AccountDao and pass the ConnectionSource
            AccountDao accountDao = new AccountDao(DatabaseConfig.getConnectionSource());
            UserDao userDao = new UserDao(DatabaseConfig.getConnectionSource());
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                
                if (choice == 3) {
                    DatabaseConfig.closeConnection();
                    System.exit(0);
                } else if (choice == 1) {
                    if (authenticateUser(userDao, scanner)) {
                        displayBankingOptions(accountDao, scanner);
                    }
                } else if (choice == 2) {
                    addNewUser(userDao, accountDao, scanner);
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error initializing the database.");
        }
    }
    
        private static void displayBankingOptions(AccountDao accountDao, Scanner scanner) {
            while (true) {
                System.out.println("Banking Options:");
                System.out.println("1. View account");
                System.out.println("2. Withdraw from account");
                System.out.println("3. Deposit into account");
                System.out.println("4. Transfer funds between accounts");
                System.out.println("5. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                try {
                    switch (choice) {
                        case 1:
                            viewAccount(accountDao, scanner);
                            break;
                        case 2:
                            withdrawFromAccount(accountDao, scanner);
                            break;
                        case 3:
                            depositIntoAccount(accountDao, scanner);
                            break;
                        case 4:
                            transferFunds(accountDao, scanner);
                            break;
                        case 5:
                            return;  // Logs the user out and returns to the main menu
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        }
    
        private static boolean authenticateUser(UserDao userDao, Scanner scanner) throws SQLException {
            System.out.println("Enter username:");
            String username = scanner.nextLine();

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            User user = userDao.getUserByUsername(username);
            if (user == null) {
                System.out.println("Invalid username.");
                return false;
            }

            // Here you'd use a library like bcrypt to hash the entered password
            // and compare it with the stored hash. This is a simplification.
            if (bcryptCheck(password, user.getHashedPassword())) {
                Session.setLoggedInUser(user); // Assuming Session is a utility class you've created to track the logged-in user.
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid password.");
                return false;
            }
        }

        
        private static void addNewUser(UserDao userDao, AccountDao accountDao, Scanner scanner) throws SQLException {
            System.out.println("Enter desired username:");
            String username = scanner.nextLine(); // Ensure you define username

            if (userDao.userExists(username)) {
                System.out.println("Username already taken. Choose a different one.");
                return;
            }

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            // Hash the password using bcrypt before storing
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User newUser = new User(username, hashedPassword); // Assuming the User constructor accepts these parameters

            String uniqueAccountNumber = getNextAccountNumber(); 
            Account newAccount = new Account(uniqueAccountNumber, username, 0.0);

            accountDao.createAccount(newAccount);

            // Link the new account to the user and save the user to the database
            // Assuming you've added the setter in the User class to associate an account
            newUser.setAccount(newAccount);
            userDao.createUser(newUser);

            System.out.println("User and account created successfully! Your account number is: " + newAccount.getAccountNumber());
        }


        private static String getNextAccountNumber() {
			// TODO Auto-generated method stub
			return null;
		}

		private static boolean bcryptCheck(String password, String hashedPassword) {
            // This function will use the bcrypt library to check the password.
            return BCrypt.checkpw(password, hashedPassword); 
        }

		private static void viewAccount(AccountDao accountDao, Scanner scanner) throws SQLException {
		    User currentUser = Session.getLoggedInUser();
		    Account userAccount = currentUser.getAccount();  // Directly get the account from the user
		    
		    System.out.println("Account Holder: " + userAccount.getAccountHolderName());
		    System.out.println("Balance: " + userAccount.getBalance());
		}
		private static void withdrawFromAccount(AccountDao accountDao, Scanner scanner) throws SQLException {
		    User currentUser = Session.getLoggedInUser();
		    Account userAccount = currentUser.getAccount();  // Directly get the account from the user

		    System.out.println("Enter amount to withdraw:");
		    double amount = scanner.nextDouble();
		    scanner.nextLine();  // Consume newline

		    boolean success = userAccount.withdraw(amount);
		    if (success) {
		        accountDao.updateAccount(userAccount); // Update the account in the database
		        System.out.println("Withdrawal successful! New balance: " + userAccount.getBalance());
		    } else {
		        // The withdraw method will print the relevant error message
		    }
		}
    
		private static void depositIntoAccount(AccountDao accountDao, Scanner scanner) throws SQLException {
		    User currentUser = Session.getLoggedInUser();
		    Account userAccount = currentUser.getAccount();  // Directly get the account from the user

		    System.out.println("Enter amount to deposit:");
		    double amount = scanner.nextDouble();
		    scanner.nextLine();  // Consume newline

		    userAccount.deposit(amount);
		    accountDao.updateAccount(userAccount); // Update the account in the database

		    System.out.println("Deposit successful! New balance: " + userAccount.getBalance());
		}
    
		private static void transferFunds(AccountDao accountDao, Scanner scanner) throws SQLException {
		    User currentUser = Session.getLoggedInUser();
		    Account sourceAccount = currentUser.getAccount();  // Directly get the account from the user

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
		        System.out.println("Insufficient balance.");
		        return;
		    }

		    // Now, perform the transfer
		    sourceAccount.withdraw(amount);
		    destAccount.deposit(amount);

		    // Update the accounts in the database
		    accountDao.updateAccount(sourceAccount);
		    accountDao.updateAccount(destAccount);

		    System.out.println("Transfer successful! New balance in your account: " + sourceAccount.getBalance());
		    System.out.println("New balance in destination account: " + destAccount.getBalance());
		

		}
}


