package boards.UserStoryBoard;

import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;
import model.exceptions.NameAlreadyInUseException;

public class UserStory {

	private String title;
	private String description;
	private String state;
	private TaskManager taskManager = new ConcreteTaskManager();
	
	public UserStory(String title) {
		this.state = "TODO";
		this.description = "";
		this.title = title;
	}
	
	public UserStory(String title, String description) {
		this.state = "TODO";
		this.description = description;
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public String getState() {
		return this.state;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return this.title;
	}

	public TaskManager getTasksManager() {
		return this.taskManager ;
	}

	public int getTasksNumber() {
		return this.taskManager.getTasksNumber();
	}

	public void addTask(String taskName, String description) throws NameAlreadyInUseException {
		this.taskManager.addTask(taskName, description);		
	}
}
