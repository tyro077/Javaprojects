package com.bank;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class AccountDao {
    private final ConnectionSource connectionSource;
    private final Dao<Account, String> accountDao;

    public AccountDao(ConnectionSource connectionSource) throws SQLException {
        this.connectionSource = connectionSource;
        this.accountDao = DaoManager.createDao(connectionSource, Account.class);
    }

    public void createAccount(Account account) throws SQLException {
        accountDao.create(account);
    }
    public Account findByAccountHolderName(String accountHolderName) throws SQLException {
        List<Account> accounts = accountDao.queryBuilder().where().eq("accountHolderName", accountHolderName).query();
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }


    public Account getAccountById(String accountNumber) throws SQLException {
        return accountDao.queryForId(accountNumber);
    }

    public boolean accountExists(String accountNumber) throws SQLException {
        // Use queryForId to get an account by its account number
        Account account = this.getAccountById(accountNumber);

        // If account is not null, it means the account already exists in the database
        return account != null;
    }

    public void updateAccount(Account account) throws SQLException {
        accountDao.update(account);
    }

    public boolean withdrawFromAccount(String accountNumber, double amount) throws SQLException {
        // Step 1: Fetch the account using its account number
        Account account = getAccountById(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        // Step 2: Use the withdraw method to attempt to withdraw the amount
        boolean withdrawalSuccessful = account.withdraw(amount);

        // Step 3: If the withdrawal is successful, update the account in the database
        if (withdrawalSuccessful) {
            updateAccount(account);
        }

        return withdrawalSuccessful;
    }

    public void deleteAccount(Account account) throws SQLException {
        accountDao.delete(account);
    }

    public List<Account> getAllAccounts() throws SQLException {
        return accountDao.queryForAll();
    }

    public List<Account> getAccountsByAccountHolderName(String accountHolderName) throws SQLException {
        return accountDao.queryForEq("accountHolderName", accountHolderName);
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
