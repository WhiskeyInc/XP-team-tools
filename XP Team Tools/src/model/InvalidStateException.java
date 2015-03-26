package model;

public class InvalidStateException extends Exception {

	public InvalidStateException(String targetState) {
		super(targetState + " is not a valid state");
	}

}
