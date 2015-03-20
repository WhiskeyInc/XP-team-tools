package model;

import java.util.HashMap;

public class BlackBoard {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();

	public void addNewTask(String taskName, String description) {
		Task task = new Task(taskName, description);
		tasks.put(task.toString(), task);
	}

	public void taskInProgress(String taskName) {
		tasks.get(taskName).toInProgress();
	}

	public void taskDone(String taskname) {
		tasks.get(taskname).toDone();
	}

	public void dropTask(String taskName) {
		tasks.remove(taskName);
	}

	public Task getTask(String taskName) {
		return tasks.get(taskName);
	}

	public int getTasksNumber() {
		return tasks.size();
	}

}
