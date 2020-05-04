package view;

import java.util.EventObject;

public class MailEvent extends EventObject {

	private String sender, receiver, text;

	public MailEvent(Object object) {

		super(object);
	}

	// getters and setters
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
