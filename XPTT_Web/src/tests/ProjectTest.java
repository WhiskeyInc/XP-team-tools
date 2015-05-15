package tests;

import static org.junit.Assert.*;

import java.util.TimeZone;

import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsCollector;

import org.junit.Test;

import timeline.Timeline;
import util.serialization.GlobalUniquenessSerializer;
import boards.UserStoryBoard.UserStoriesManager;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import boards.taskBoard.ProjectTaskManager;

public class ProjectTest {

	private Project project = new Project("ciao", new ConcreteProjectFactory(
			TimeZone.getTimeZone("Europe/Rome")), null);

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
		Project other = new Project("nome", new ConcreteProjectFactory(
				TimeZone.getTimeZone("Europe/Rome")), null);
		other.getUserStoriesManager().addUserStory(
				new UserStory("Test", new ProjectTaskManager(
						new ConcreteTaskManager(), project.getManager())));
		assertEquals(1, project.getTimeline().getEventsNumber());
	}

	@Test
	public void test04() throws Exception {
		ProjectsCollector manager = new ProjectsCollector(
				GlobalUniquenessSerializer.getInstance());
		manager.addProject(project);
		assertEquals(project, manager.getProject(project.getId()));
	}

	@Test
	public void test05() throws Exception {
		ProjectsCollector manager = new ProjectsCollector(
				GlobalUniquenessSerializer.getInstance());
		manager.addProject(project);
		manager.addProject(new Project(null, new ConcreteProjectFactory(
				TimeZone.getTimeZone("Europe/Rome")), null));
		manager.deleteProject(project.getId());
		assertEquals(1, manager.getProjects().size());
	}

	@Test
	public void test06() throws Exception {
		Project project1 = new Project("uno", new ConcreteProjectFactory(
				TimeZone.getTimeZone("Europe/Rome")), "unouno");
		System.out.println(project1.equals(project));
	}

}
