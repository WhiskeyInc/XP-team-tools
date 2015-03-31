package boards.taskBoard;

import java.util.ArrayList;

import model.TeamManager;
import model.exceptions.InvalidMemberException;
import model.exceptions.InvalidStateException;
import model.exceptions.NameAlreadyInUseException;
import model.exceptions.NoSuchTaskException;
import filtering.Filter;

public class TeamTaskManager implements TaskManager {

	private TaskManager taskManager;
	private TeamManager teamManager;

	public TeamTaskManager(TaskManager taskManager, TeamManager teamManager) {
		super();
		this.taskManager = taskManager;
		this.teamManager = teamManager;
	}

	@Override
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
	public void addTask(String taskName) throws NameAlreadyInUseException {
		this.taskManager.addTask(taskName);
		try {
			teamManager.taskAdded(this.getTask(taskName));
		} catch (NoSuchTaskException e) {
			throw new RuntimeException("Fatal Error");
		}
	}

	@Override
	public void deleteTask(String taskName) throws NoSuchTaskException {
		Task task = taskManager.getTask(taskName);
		this.taskManager.deleteTask(taskName);
		this.teamManager.taskDeleted(task);
	}

	@Override
	public Task getTask(String taskName) throws NoSuchTaskException {
		return this.taskManager.getTask(taskName);
	}

	@Override
	public int getTasksNumber() {
		return this.taskManager.getTasksNumber();
	}

	@Override
	public void moveTaskToState(String taskName, String targetState)
			throws InvalidStateException, NoSuchTaskException {
		if (!teamManager.isValidTaskState(targetState)) {
			throw new InvalidStateException(targetState);
		}
		this.taskManager.moveTaskToState(taskName, targetState);
		this.teamManager.taskStateChanged(this.getTask(taskName),
				targetState);
	}

	@Override
	public void addDevelopersToTask(String taskName, String... developers)
			throws InvalidMemberException, NoSuchTaskException {
		for (String string : developers) {
			if (!this.teamManager.isValidMember(string)) {
				throw new InvalidMemberException(string);
			}
		}
		this.taskManager.addDevelopersToTask(taskName, developers);
		this.teamManager.developersAdded(this.getTask(taskName),
				developers);
	}

	@Override
	public ArrayList<Task> getTasks(Filter<Task> filter) {
		return this.taskManager.getTasks(filter);
	}

}
