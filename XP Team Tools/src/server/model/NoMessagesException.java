package server.model;

public class NoMessagesException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoMessagesException(String message) {
		super(message);
	}

}
