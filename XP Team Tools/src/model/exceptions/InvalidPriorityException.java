package model.exceptions;

public class InvalidPriorityException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPriorityException(String targetPriority) {
		super(targetPriority + " is not a valid priority");
	}
}
