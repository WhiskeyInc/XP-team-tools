package model.exceptions;

public class NoSuchEventException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchEventException(String eventName) {
		super(eventName);
	}

}
