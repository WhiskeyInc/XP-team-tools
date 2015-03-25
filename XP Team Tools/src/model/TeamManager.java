package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import timeline.Event;
import timeline.Timeline;
import boards.Task;
import boards.TasksManager;

public class TeamManager {

	private TasksManager taskManager = new TasksManager();
	private Timeline timeline = new Timeline();
	private ArrayList<String> members = new ArrayList<String>();

	public void addTask(String taskName, String description) {
		this.taskManager.addTask(taskName, description);
		this.timeline.addEvent(new Event("Created task: " + taskName,
				getCurrentDate()));
	}

	public int getEventsNumber() {
		return this.timeline.getEventsNumber();
	}

	public void deleteTask(String taskName) {
		this.taskManager.deleteTask(taskName);
		timeline.addEvent(new Event("Deleted task: " + taskName,
				getCurrentDate()));
	}

	public Event getEvent(String eventName) {
		return this.timeline.getEvent(eventName);
	}

	public void moveTaskToState(String taskName, String targetState) {
		this.taskManager.moveTaskToState(taskName, targetState);
		this.timeline.addEvent(new Event("Changed state of task " + taskName
				+ ": now it is " + targetState, this.getCurrentDate()));
	}

	public void addDeveloperTo(String taskName, String developer) throws InvalidMemberException {
		checkTask(taskName);
		checkMember(developer);
		taskManager.getTask(taskName).addParticipant(developer);
		timeline.addEvent(new Event("Added "+developer+" to task: "+taskName, getCurrentDate()));
	}

	private void checkTask(String taskName) {
		Task task = taskManager.getTask(taskName);
		if (task == null) {
			taskManager.addTask(taskName);
		}
	}

	private String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		Calendar cal = Calendar.getInstance();
		String creationDate = format.format(cal.getTime());
		return creationDate;
	}

	public void addMember(String member) {		
		members.add(member);
	}
	
	private void checkMember(String member) throws InvalidMemberException {
		if (!members .contains(member)) {
			throw new InvalidMemberException(member);
		}		
	}
}
