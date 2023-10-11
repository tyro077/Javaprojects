package com.bank;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
	private final ConnectionSource connectionSource;
    private final Dao<User, String> userDao;
    
    public UserDao(ConnectionSource connectionSource) throws SQLException {
        this.connectionSource = connectionSource;
        this.userDao = DaoManager.createDao(connectionSource, User.class);
    }

    // Create a new user entry in the database
    public void createUser(User user) throws SQLException {
        userDao.create(user);
    }

    public User getUserByUsername(String username) throws SQLException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        List<User> users = userDao.queryForEq("username", username.trim());

        if (users != null && !users.isEmpty()) {
            if (users.size() > 1) {
                // This should not happen if usernames are unique. It's here just for safety.
                throw new SQLException("Multiple users found with the same username: " + username);
            }
            return users.get(0);
        }
        return null;  // Instead of throwing an exception, just return null.
    }



    // Check if a user with the given username exists
    public boolean userExists(String username) throws SQLException {
        return getUserByUsername(username) != null;
    }

    // Update the user details in the database
    public void updateUser(User user) throws SQLException {
        userDao.update(user);
    }

    // Delete a user from the database
    public void deleteUser(User user) throws SQLException {
        userDao.delete(user);
    }

    // Close the connection source when done
    public void close() throws Exception {
        connectionSource.close();
    }
}

