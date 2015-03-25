package model;

public class InvalidMemberException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidMemberException(String participant) {
		super(participant + " is not a member of this team");
	}

}
