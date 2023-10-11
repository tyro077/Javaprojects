package com.bank;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {

	@DatabaseField(foreign = true, columnName = "account_id", canBeNull = false, foreignAutoRefresh = true)
	private Account account;


    @DatabaseField(id = true)
    private String username;

    @DatabaseField
    private String hashedPassword;

    // No-arg constructor
    public User() {}

    // Constructors, getters, setters, etc.
    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setAccount(Account newAccount) {
        this.account = newAccount;
    }


	public Account getAccount() {
	    return this.account;
	}


}
