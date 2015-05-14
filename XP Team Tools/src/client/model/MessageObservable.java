package client.model;

import java.util.Observable;

/**
 * This Observable class notify the Observer that the message is changed
 * 
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
	
	/**
	 * Enters in a no-end cycle if there are no more Observer: in this way, the update operation
	 * takes place only if there's at least one Observer
	 */
	private void waitObserver() {
		while(countObservers() == 0) {
			
		}
	}
}
