package boards;

import java.util.ArrayList;
import java.util.HashMap;

import model.NameAlreadyInUseException;

public class TasksManager {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();

	public void addTask(String taskName, String description) throws NameAlreadyInUseException {
		checkTaskName(taskName);
		Task task = new Task(taskName, description);
		tasks.put(task.toString(), task);
	}
	
	private void checkTaskName(String taskName) throws NameAlreadyInUseException {
		if(tasks.containsKey(taskName)){
			throw new NameAlreadyInUseException(taskName);
		}
	}

	public void addTask(String taskName) throws NameAlreadyInUseException {
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
		this.tasks.get(taskName).moveTaskToState(targetState);
	}

	public ArrayList<Task> getTasks(String targetState) {
		ArrayList<Task> list = new ArrayList<Task>();
		for (Task task : tasks.values()) {
			if (task.getState().compareTo(targetState)==0) {
				list.add(task);
			}
		}
		return list;
	}

	public ArrayList<Task> getTasks() {
		ArrayList<Task> list = new ArrayList<Task>();
		for (Task task : tasks.values()) {
			list.add(task);
		}
		return list;
	}

}
