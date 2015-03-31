package boards.taskBoard;

import java.util.ArrayList;
import java.util.HashMap;

import model.exceptions.NameAlreadyInUseException;
import filtering.Filter;

public class ConcreteTaskManager implements TaskManager {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();

	/* (non-Javadoc)
	 * @see boards.TaskManager#addTask(java.lang.String, java.lang.String)
	 */
	@Override
	public void addTask(String taskName, String description) throws NameAlreadyInUseException {
		checkTaskName(taskName);
		Task task = new Task(taskName, description);
		tasks.put(task.toString(), task);
	}

	/* (non-Javadoc)
	 * @see boards.TaskManager#addTask(java.lang.String)
	 */
	@Override
	public void addTask(String taskName) throws NameAlreadyInUseException {
		this.addTask(taskName, "");		
	}

	/* (non-Javadoc)
	 * @see boards.TaskManager#deleteTask(java.lang.String)
	 */
	@Override
	public void deleteTask(String taskName) {
		tasks.remove(taskName);
	}

	/* (non-Javadoc)
	 * @see boards.TaskManager#getTask(java.lang.String)
	 */
	@Override
	public Task getTask(String taskName) {
		return tasks.get(taskName);
	}

	/* (non-Javadoc)
	 * @see boards.TaskManager#getTasksNumber()
	 */
	@Override
	public int getTasksNumber() {
		return tasks.size();
	}

	/* (non-Javadoc)
	 * @see boards.TaskManager#moveTaskToState(java.lang.String, java.lang.String)
	 */
	@Override
	public void moveTaskToState(String taskName, String targetState) {
		this.tasks.get(taskName).moveTaskToState(targetState);
	}
	
	/* (non-Javadoc)
	 * @see boards.TaskManager#getTasks(filtering.Filter)
	 */
	@Override
	public ArrayList<Task> getTasks(Filter<Task> filter){
		return filter.filter(this.getAllTasks());
	}
	
	private ArrayList<Task> getAllTasks() {
		ArrayList<Task> list = new ArrayList<Task>();
		list.addAll(this.tasks.values());
		return list;
	}
	
	private void checkTaskName(String taskName) throws NameAlreadyInUseException {
		if(tasks.containsKey(taskName)){
			throw new NameAlreadyInUseException(taskName);
		}
	}

	public void addDevelopersToTask(String taskName, String... developers) {
		for (String developer : developers) {
			this.getTask(taskName).addDeveloper(developer);
		}
		
	}
}
