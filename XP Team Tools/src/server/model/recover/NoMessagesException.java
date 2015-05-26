package server.model.recover;

/**
 * An exception that appears when there aren't messages in chat's memory
 * 
 * @author alessandro
 *
 */
public class NoMessagesException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoMessagesException(String message) {
		super(message);
	}

}
