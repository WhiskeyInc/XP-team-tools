package model;

public class FixedEventException extends Exception {

	private static final long serialVersionUID = 1L;

	public FixedEventException(String eventname) {
		super(eventname + "is not movable!");
	}
}
