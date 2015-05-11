package control;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import model.ConcreteProjectSettings;
import model.exceptions.InvalidDateException;
import model.exceptions.NameAlreadyInUseException;
import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsCollector;
import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.MacroEvent;
import timeline.Timeline;

/**
 * Servlet implementation class Initializer
 */
@WebServlet("/initialize")
public class Initializer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();

		HashMap<String, String> users = new HashMap<String, String>();
		users.put("admin", "1789");
		HashMap<String, ProjectsCollector> environments = new HashMap<String, ProjectsCollector>();
		HashMap<String, ProjectsCollector> pendingProjects = new HashMap<String, ProjectsCollector>();
		ProjectsCollector testCollector = new ProjectsCollector();
		Project project = new Project("test", new ConcreteProjectFactory(), "");
		ConcreteProjectSettings settings = (ConcreteProjectSettings) project.getSettings();
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS", "DONE");
		// Add a user story to test jsp page.
		UserStory story = new UserStory("TestStory", "ciao", new ConcreteTaskManager());
		story.moveToState("TODO");
		UserStory story1 = new UserStory("TestStory2", "ciao", new ConcreteTaskManager());
		story1.moveToState("DONE");
		UserStory story2 = new UserStory("TestSory3", "ciao", new ConcreteTaskManager());
		story2.moveToState("DONE");
		try {
			project.getUserStoriesManager().addUserStory(story);
			project.getUserStoriesManager().addUserStory(story1);
			project.getUserStoriesManager().addUserStory(story2);
		} catch (NameAlreadyInUseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Timeline timeline = new ConcreteTimeline(TimeZone.getDefault());
		MacroEvent test;
		try {
			test = new MacroEvent("test", new GregorianCalendar(2020, 2, 3, 4,
					5), new GregorianCalendar(3020, 2, 3, 4, 5), timeline);
			test.addEvent(new Event("1",
					new GregorianCalendar(2050, 2, 3, 4, 5)));
			test.addEvent(new Event("2",
					new GregorianCalendar(2060, 2, 3, 4, 5)));
			test.addEvent(new Event("3",
					new GregorianCalendar(2030, 2, 3, 4, 5)));
			test.addEvent(new Event("4",
					new GregorianCalendar(2150, 2, 3, 4, 5)));
			project.getTimeline().addEvent(test);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testCollector.addProject(project);
		environments.put("admin", testCollector);
		super.getServletContext().setAttribute("pendingProjects", pendingProjects);
		super.getServletContext().setAttribute("environments", environments);
		super.getServletContext().setAttribute("users", users);

	}
}