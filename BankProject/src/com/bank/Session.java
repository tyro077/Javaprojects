package com.bank;
import com.bank.Session;


public class Session {
	
	private static User currentUser;
	public static void setLoggedInUser(User user) {
		currentUser = user;
		
	}
	public static User getLoggedInUser() {
        return currentUser;
    }
	public static void logout() {
        currentUser = null;
    }
	
	
}


