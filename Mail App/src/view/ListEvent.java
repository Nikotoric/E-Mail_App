package view;

import java.util.ArrayList;
import java.util.EventObject;

public class ListEvent extends EventObject {

	private String user;

	private ArrayList<String> sentMail, receivedMail;

	public ListEvent(Object object) {

		super(object);
		sentMail = new ArrayList<>();
		receivedMail = new ArrayList<>();

	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ArrayList<String> getSentMail() {
		return sentMail;
	}

	public ArrayList<String> getReceivedMail() {
		return receivedMail;
	}

	public void setReceivedMail(ArrayList<String> list) {
		receivedMail = list;
	}

	public void setSentMail(ArrayList<String> list) {
		sentMail = list;
	}

}
