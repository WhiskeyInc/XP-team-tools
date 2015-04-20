package control.actions.settings;

public class AccessDeniedException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
		super("You are not permitted to do this");
	}
}
