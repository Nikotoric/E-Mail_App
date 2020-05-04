package controller;

import java.util.ArrayList;

import model.User;
import model.UserDataBase;

public class Controller {

	User user;
	UserDataBase userDB = new UserDataBase();

	public void newUser(String name, String mail, int id) {

		user = new User(name, mail, id);

		userDB.storeUser(user);

	}

	public ArrayList<String> getSentList(User us) {

		return us.getSentMail();

	}

	public ArrayList<String> getReceivedList(User us) {

		return us.getReceivedMail();

	}

	public String getName(User user) {
		return user.getName();
	}

	public String getMail(User user) {
		return user.getMail();
	}

	public int getId() {
		return user.getId();
	}

	public ArrayList<User> getUserDB() {
		return userDB.getUserDB();
	}

	public void addUsers(User user) {
		userDB.storeUser(user);
	}

	public void addSentMail(User us, String mail) {
		us.addSentMail(mail);
	}

	public void addReceivedMail(User us, String mail) {
		us.addReceivedMail(mail);
	}

	public User getUser(String mail) {

		for (User u : userDB.getUserDB()) {
			if (u.getMail().equals(mail)) {
				return u;
			}
		}

		return null;
	}

}
