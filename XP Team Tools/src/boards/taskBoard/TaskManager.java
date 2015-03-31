package boards.taskBoard;

import java.util.ArrayList;

import model.exceptions.InvalidMemberException;
import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import filtering.Filter;

public interface TaskManager {

	public void addTask(String taskName, String description)
			throws NameAlreadyInUseException;

	public void addTask(String taskName) throws NameAlreadyInUseException;

	public void deleteTask(String taskName);

	public Task getTask(String taskName);

	public int getTasksNumber();

	public void moveTaskToState(String taskName, String targetState) throws InvalidStateException;

	public ArrayList<Task> getTasks(Filter<Task> filter);

	public abstract void addDevelopersToTask(String taskName, String... developers) throws InvalidMemberException;

}