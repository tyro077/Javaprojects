package com.bank;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
public class DatabaseConfig {
    // Database URL for SQLite
    private static final String DATABASE_URL = "jdbc:sqlite:bank_database.db";

    // Singleton instance of the ConnectionSource
    private static ConnectionSource connectionSource;

    /**
     * Initializes the database connection.
     *
     * @throws SQLException If there's an error establishing the connection.
     */
    public static void initDatabase() throws SQLException {
        if (connectionSource == null) {
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
        }
    }
    public static void initTables() throws SQLException {
        TableUtils.createTableIfNotExists(getConnectionSource(), User.class);
        TableUtils.createTableIfNotExists(getConnectionSource(), Account.class);
    }

    
    /**
     * Retrieves a singleton instance of the ConnectionSource.
     * If it doesn't exist, it creates one.
     *
     * @return The ConnectionSource instance.
     * @throws SQLException If there's an error establishing the connection.
     */
    public static ConnectionSource getConnectionSource() throws SQLException {
        if (connectionSource == null) {
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
        }
        return connectionSource;
    }
    
    /**
     * Closes the database connection if it's open.
     * @throws SQLException If there's an error closing the connection.
     */
    public static void closeConnection() throws SQLException {
        if (connectionSource != null && connectionSource.isOpen(null)) {
            try {
				connectionSource.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
            connectionSource = null;
        }
    }
}
