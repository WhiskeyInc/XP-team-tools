package boards.UserStoryBoard;

import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.TaskManager;
import model.exceptions.NameAlreadyInUseException;

public class UserStory {

	private String title;
	private String description;
	private String state;
	private String priority;
	private TaskManager taskManager = new ConcreteTaskManager(); //TODO: useless interface, to became a constructor parameter??
	
	public UserStory(String title) {
		this.state = "TODO";
		this.description = "";
		this.title = title;
		this.priority = "DEFAULT";
	}
	
	public UserStory(String title, String description) {
		this.state = "TODO";
		this.description = description;
		this.title = title;
	    this.priority = "DEFAULT";
	}

	public String getDescription() {
		return this.description;
	}

	public String getState() {
		return this.state;
	}

	public String getPriority() {
		return this.priority;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
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
