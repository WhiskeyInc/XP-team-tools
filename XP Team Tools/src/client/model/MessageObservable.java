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
	//Crucial for the solution adopted to catch the server alignment
	private void waitObserver() {
		while(countObservers() == 0) {
			//wait
		}
	}
}
