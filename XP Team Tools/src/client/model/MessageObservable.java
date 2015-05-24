package client.model;

import java.util.Observable;

/**
 * This Observable class notify the Observer that the message is changed: the message can be a chat message,
 * the time to be shown on timer's display, etc...
 * 
 * @author alberto
 *
 */
public class MessageObservable extends Observable {

	private String message;

	public MessageObservable(String message) {
		super();
		this.message = message;
		update();
	}

	public void setMessage(String message) {
		this.message = message;
		waitObserver();
		update();
	}
	
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	public String getMessage() {
		return message;
	}
	
	
	private void waitObserver() {
		while(countObservers() == 0) {
			
		}
	}
}
