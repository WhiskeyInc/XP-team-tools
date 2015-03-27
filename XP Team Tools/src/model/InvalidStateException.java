package model;

public class InvalidStateException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidStateException(String targetState) {
		super(targetState + " is not a valid state");
	}

}
