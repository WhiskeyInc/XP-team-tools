package tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import timeline.Event;
import timeline.Timeline;
import boards.TasksManager;

public class Tool {

	private TasksManager taskManager = new TasksManager();
	private Timeline timeline = new Timeline();

	public void addTask(String taskName, String description) {
		this.taskManager.addTask(taskName, description);
		String creationDate = getCurrentDate();
		this.timeline.addEvent(new Event("Created task: " + taskName, creationDate));
	}

	public int getEventsNumber() {
		return this.timeline.getEventsNumber();
	}
	
	private String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		Calendar cal = Calendar.getInstance();
		String creationDate = format.format(cal.getTime());
		return creationDate;
	}
	
	
	
	

}
