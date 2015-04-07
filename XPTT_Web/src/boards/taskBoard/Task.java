package boards.taskBoard;

import java.util.ArrayList;

/**
 * The Task class represents a generic development task. Like any task, it has a
 * state property to show its level of accomplishment, and a collection of
 * developers to represents people that are actually developing this specific
 * task
 * 
 * @author simone, lele, usk, incre
 * @since 1.0
 *
 */
public class Task {

	private final static String TODO = "TODO";

	private String name;
	private String description;
	private String state;
	private ArrayList<String> developers = new ArrayList<String>();

	/**
	 * Creates a new Task istance. The state initialization is forced to TODO
	 * 
	 * @param name
	 *            : the name of the task
	 * @param description
	 *            : a short string description to make task details more clear
	 */
	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.state = TODO;
	}

	/**
	 * Creates a new Task istance. The state initialization is forced to TODO
	 * 
	 * @param name
	 *            : the name of the task
	 */
	public Task(String name) {
		super();
		this.name = name;
		this.description = "";
		this.state = TODO;
	}

	/**
	 * Adds a developer to this task
	 * 
	 * @param developer
	 *            : a string representing the developer
	 */
	public void addDeveloper(String developer) {
		this.developers.add(developer);
	}

	/**
	 * Returns this task's description
	 * 
	 * @return: a string representing this task's details
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets this task's description
	 * 
	 * @param description
	 *            : a short string description to make task details more clear
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns this task's state
	 * 
	 * @return: a string representing this task's state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Changes this task's state
	 * 
	 * @param targetState
	 *            : the updated status of this task
	 */
	public void moveTaskToState(String targetState) {
		this.state = targetState;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * Returns the whole set of developers related to this task
	 * 
	 * @return: a list of string representing each developer
	 */
	public ArrayList<String> getDevelopers() {
		return this.developers;
	}

}
