package model;

public class NoSuchTaskException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchTaskException(String taskName) {
		super("No task named " + taskName);
	}
}
