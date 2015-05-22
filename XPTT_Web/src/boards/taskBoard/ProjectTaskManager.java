package boards.taskBoard;

import java.util.ArrayList;

import model.ProjectManager;
import model.exceptions.InvalidMemberException;
import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchTaskException;
import filtering.Filter;

/**
 * A DP Decorator oriented implementation of {@link TaskManager} interface. This
 * class encapsulates a concrete implementation of that interface but adds
 * project oriented functionalities, like parameters validation (over
 * {@link ProjectManager} rules) and event notification to the project's
 * timeline
 * 
 * @author simone, lele, usk, incre
 * @see {@link TaskManager}, {@link ProjectManager}
 */
public class ProjectTaskManager implements TaskManager {

	private TaskManager taskManager;
	private ProjectManager teamManager;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param taskManager
	 *            : the concrete implementation of {@link TaskManager} to
	 *            encapsulate
	 * @param teamManager
	 *            : the {@link ProjectManager} instance that will implement team
	 *            functionalities
	 */
	public ProjectTaskManager(TaskManager taskManager,
			ProjectManager teamManager) {
		super();
		this.taskManager = taskManager;
		this.teamManager = teamManager;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.taskBoard.TaskManager#addTask(java.lang.String,
	 * java.lang.String)
	 */
	public void addTask(String taskName, String description)
			throws NameAlreadyInUseException {
		taskManager.addTask(taskName, description);
		try {
			teamManager.taskAdded(this.getTask(taskName));
		} catch (NoSuchTaskException e) {
			throw new RuntimeException("Fatal Error");
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.taskBoard.TaskManager#addTask(java.lang.String)
	 */
	public void addTask(String taskName) throws NameAlreadyInUseException {
		this.taskManager.addTask(taskName);
		try {
			teamManager.taskAdded(this.getTask(taskName));
		} catch (NoSuchTaskException e) {
			throw new RuntimeException("Fatal Error");
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.taskBoard.TaskManager#deleteTask(java.lang.String)
	 */
	public void deleteTask(String taskName) throws NoSuchTaskException {
		Task task = taskManager.getTask(taskName);
		this.taskManager.deleteTask(taskName);
		this.teamManager.taskDeleted(task);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.taskBoard.TaskManager#getTask(java.lang.String)
	 */
	public Task getTask(String taskName) throws NoSuchTaskException {
		return this.taskManager.getTask(taskName);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.taskBoard.TaskManager#getTasksNumber()
	 */
	public int getTasksNumber() {
		return this.taskManager.getTasksNumber();
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.taskBoard.TaskManager#moveTaskToState(java.lang.String,
	 * java.lang.String)
	 */
	public void moveTaskToState(String taskName, String targetState)
			throws InvalidStateException, NoSuchTaskException {
		if (!teamManager.isValidTaskState(targetState)) {
			throw new InvalidStateException(targetState);
		}
		this.taskManager.moveTaskToState(taskName, targetState);
		this.teamManager.taskStateChanged(this.getTask(taskName), targetState);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.taskBoard.TaskManager#addDevelopersToTask(java.lang.String,
	 * java.lang.String[])
	 */
	public void addDevelopersToTask(String taskName, String... developers)
			throws InvalidMemberException, NoSuchTaskException {
		for (String string : developers) {
			if (!this.teamManager.isValidMember(string)) {
				throw new InvalidMemberException(string);
			}
		}
		this.taskManager.addDevelopersToTask(taskName, developers);
		this.teamManager.developersAdded(this.getTask(taskName), developers);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see boards.taskBoard.TaskManager#getTasks(filtering.Filter)
	 */
	public ArrayList<Task> getTasks(Filter<Task> filter) {
		return this.taskManager.getTasks(filter);
	}

}
