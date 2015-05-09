package model.exceptions;

public class NameAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1L;

	public NameAlreadyInUseException(String name) {
		super(name + " is already in use!");
	}
}
