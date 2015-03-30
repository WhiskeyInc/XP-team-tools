package model.exceptions;

public class NoSuchUserStoryException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchUserStoryException(String userStory) {
		super("User story " + userStory + " does not exist");
	}

}
