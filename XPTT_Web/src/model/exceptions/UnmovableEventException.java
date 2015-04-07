package model.exceptions;

public class UnmovableEventException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnmovableEventException(String eventname) {
		super(eventname + "is not movable!");
	}
}
