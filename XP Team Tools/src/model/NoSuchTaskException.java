package model;

public class NoSuchTaskException extends Exception {

	public NoSuchTaskException(String taskName) {
		super("No task named " + taskName);
	}
}
