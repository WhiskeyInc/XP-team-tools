package model.exceptions;

public class UnEditableEventException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnEditableEventException(String eventname) {
		super(eventname + "is not editable!");
	}
}
