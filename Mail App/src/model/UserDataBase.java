package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDataBase implements Serializable {

	private ArrayList<User> userDB = new ArrayList<>();
	
	public UserDataBase() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<User> getUserDB() {
		return userDB;
	}
	
	public void storeUser(User user) {
		
		userDB.add(user);
	}

	
}
