package model;

import java.util.HashMap;

public class BlackBoard {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();

	public void addTask(String taskName, String description) {
		Task task = new Task(taskName, description);
		tasks.put(task.toString(), task);
	}
	
	public void addTask(String taskName) {
		this.addTask(taskName, "");		
	}

	public void deleteTask(String taskName) {
		tasks.remove(taskName);
	}

	public Task getTask(String taskName) {
		return tasks.get(taskName);
	}

	public int getTasksNumber() {
		return tasks.size();
	}

	public void moveTaskToState(String taskName, String targetState) {
		this.tasks.get(taskName).changeState(targetState);
	}

}
