package boards.taskBoard;

import java.util.ArrayList;

public class Task {

	private final static String TODO = "TODO";

	private String name;
	private String description;
	private String state;
	private ArrayList<String> developers = new ArrayList<String>();

	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.state = TODO;
	}

	public Task(String name) {
		super();
		this.name = name;
		this.description = "";
		this.state = TODO;
	}

	public void addDeveloper(String developer) {
		this.developers.add(developer);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void moveTaskToState(String targetState) {
		this.state = targetState;		
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name;
	}

	public ArrayList<String> getDevelopers() {
		return this.developers;
	}

}
