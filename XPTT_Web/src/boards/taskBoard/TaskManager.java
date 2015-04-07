package boards.taskBoard;

import java.util.ArrayList;

import model.exceptions.InvalidMemberException;
import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchTaskException;
import filtering.Filter;

/**
 * TaskManager interface represents a generic manager for a collection of tasks.
 * It provides task addition, deletion and editing, within some logic controls.
 * Task istances are identified by their name, so it is impossible to add two
 * tasks with the same name
 * 
 * @author simone, lele, usk, incre
 * @since 1.0
 * @see Task
 */
public interface TaskManager {

	/**
	 * Adds a new task to the collection
	 * 
	 * @param taskName
	 *            : the name of this task
	 * @param description
	 *            : a short string description to make task details more clear
	 * @throws NameAlreadyInUseException
	 *             : if taskName matches with an existing task
	 */
	public void addTask(String taskName, String description)
			throws NameAlreadyInUseException;

	/**
	 * Adds a new task to the collection
	 * 
	 * @param taskName
	 *            : the name of this task
	 * @throws NameAlreadyInUseException
	 *             : if taskName matches with an existing task
	 */
	public void addTask(String taskName) throws NameAlreadyInUseException;

	/**
	 * Deletes an existing task from this collection
	 * 
	 * @param taskName
	 *            : the name of the task to be deleted
	 * @throws NoSuchTaskException
	 *             : if taskName does not match with an existing task
	 */
	public void deleteTask(String taskName) throws NoSuchTaskException;

	/**
	 * Returns a specific task of this collection
	 * 
	 * @param taskName
	 *            : the name of the desired task
	 * @return: the {@link Task} istance whose name matches with taskName
	 * @throws NoSuchTaskException
	 *             : if taskName does not match with an existing task
	 */
	public Task getTask(String taskName) throws NoSuchTaskException;

	/**
	 * Returns the number of tasks inside this collection
	 * 
	 * @return: an integer representing this collection's size
	 */
	public int getTasksNumber();

	/**
	 * Changes the state of a specific task
	 * 
	 * @param taskName
	 *            : the name of the task to be moved
	 * @param targetState
	 *            : the updated state for that state
	 * @throws InvalidStateException
	 *             : if targetState is considered invalid
	 * @throws NoSuchTaskException
	 *             : if taskName does not match with an existing task
	 */
	public void moveTaskToState(String taskName, String targetState)
			throws InvalidStateException, NoSuchTaskException;

	/**
	 * Returns a list of specific tasks
	 * 
	 * @param filter
	 *            : the rule to choose whether or not a task should be included
	 *            in the returned list
	 * @return: the filtered list of tasks
	 * @see Filter
	 */
	public ArrayList<Task> getTasks(Filter<Task> filter);

	/**
	 * Adds one or more developers to a specific task
	 * 
	 * @param taskName
	 *            : the task wich the developers will be added to
	 * @param developers
	 *            : one or more string representing the developers
	 * @throws InvalidMemberException
	 *             : if even one developer is considered invalid
	 * @throws NoSuchTaskException
	 *             : if taskName does not match with an existing task
	 */
	public void addDevelopersToTask(String taskName, String... developers)
			throws InvalidMemberException, NoSuchTaskException;

}