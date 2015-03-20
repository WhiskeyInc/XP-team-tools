package model;

import java.util.HashMap;

public class BlackBoard {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();

	public void addNewTask(String name, String description) {
		Task task = new Task(name, description);
		tasks.put(task.toString(), task);
	}

	public void moveTaskInProgress(String name) {
		tasks.get(name).toInProgress();
	}

	public void moveTaskDone(String name) {
		tasks.get(name).toDone();
	}

	public void dropTask(String name) {
		tasks.remove(name);
	}

	public HashMap<String, Task> getTasks() {
		return tasks;
	}

}
