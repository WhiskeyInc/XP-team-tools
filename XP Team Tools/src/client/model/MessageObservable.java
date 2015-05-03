package client.model;

import java.util.Observable;

public class MessageObservable extends Observable {

	private String message;

	public MessageObservable(String message) {
		super();
		this.message = message;
		update();
	}

	public void setMessage(String message) {
		this.message = message;
		update();
	}
	
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	public String getMessage() {
		return message;
	}

}
