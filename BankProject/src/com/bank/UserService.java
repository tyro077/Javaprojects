package com.bank;

import java.sql.SQLException;

public class UserService {
	private final AccountDao accountDao;
    // Assuming you also have a UserDao
    private final UserDao userDao;

    public UserService(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public User fetchUserByUsername(String username) {
        try {
            User user = userDao.getUserByUsername(username);
            return user;  // Do something with the user if needed, or simply return it
        } catch (IllegalArgumentException e) {
            // Handle invalid username input
            // Maybe log the error or throw a custom exception to be caught higher up
            throw new RuntimeException("Invalid username provided.", e);
        } catch (SQLException e) {
            // Handle case where user is not found or other database issues
            throw new RuntimeException("Error retrieving user from the database.", e);
        }
    }
    public void registerUser(String username, String password /*, other needed parameters*/) throws SQLException {
        // Create a new account for the user
        Account newAccount = new Account(/*parameters*/);
        accountDao.createAccount(newAccount);
        
        // Create the user and associate the account with them
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setHashedPassword(password/* hashed version of password */);
        newUser.setAccount(newAccount);
        
        // Save the user to the database (assuming userDao has a createUser method)
        userDao.createUser(newUser);
    }
}
