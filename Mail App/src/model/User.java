package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

	private String name, mail;
	private int id;
	private ArrayList<String> sentMail, receivedMail;

	public User(String name, String mail, int id) {

		this.name = name;
		this.mail = mail;
		this.id = id;
		sentMail = new ArrayList<>();
		receivedMail = new ArrayList<>();

	}

	// getters
	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public int getId() {
		return id;
	}

	public ArrayList<String> getSentMail() {
		return sentMail;
	}

	public ArrayList<String> getReceivedMail() {
		return receivedMail;
	}

	public void addSentMail(String mail) {
		sentMail.add(mail);
	}

	public void addReceivedMail(String mail) {
		receivedMail.add(mail);
	}

}
