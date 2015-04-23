package tests;

import static org.junit.Assert.*;
import model.project.ConcreteProjectFactory;
import model.project.Project;

import org.junit.Test;

import timeline.Timeline;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.ProjectTaskManager;

public class ProjectTest {

	private Project project = new Project(new ConcreteProjectFactory());

	@Test
	public void test01() throws Exception {
		UserStoriesManager manager = project.getUserStoriesManager();
		manager.addUserStory(new UserStory("Test", new ConcreteTaskManager()));
		Timeline timeline = project.getTimeline();
		assertEquals(2, timeline.getEventsNumber());
	}

	@Test
	public void test02() throws Exception {
		UserStoriesManager manager = project.getUserStoriesManager();
		manager.addUserStory(new UserStory("Test", new ProjectTaskManager(
				new ConcreteTaskManager(), project.getManager())));
		manager.getUserStory("Test").addTask("Fare i test", "farne tanti");
		Timeline timeline = project.getTimeline();
		assertEquals(3, timeline.getEventsNumber());
	}

	@Test
	public void test03() throws Exception {
		Project other = new Project(new ConcreteProjectFactory());
		other.getUserStoriesManager().addUserStory(
				new UserStory("Test", new ProjectTaskManager(
						new ConcreteTaskManager(), project.getManager())));
		assertEquals(1, project.getTimeline().getEventsNumber());
	}

}
