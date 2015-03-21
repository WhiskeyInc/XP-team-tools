package boards;

import java.util.ArrayList;
import java.util.HashMap;

public class TasksManager {

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

	public ArrayList<Task> getTasks(String targetState) {
		ArrayList<Task> list = new ArrayList<Task>();
		for (Task task : tasks.values()) {
			if (task.getState().compareTo(targetState)==0) {
				list.add(task);
			}
		}
		return list;
	}

}
