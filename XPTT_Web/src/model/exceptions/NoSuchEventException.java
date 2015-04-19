package model.exceptions;

public class NoSuchEventException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchEventException(int eventId) {
		super("The id " + eventId + "is not linked to an event.");
	}

}
