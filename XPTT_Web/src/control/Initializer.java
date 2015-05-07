package control;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.exceptions.InvalidDateException;
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
		ProjectsCollector testCollector = new ProjectsCollector();
		Project project = new Project("test", new ConcreteProjectFactory(), "");
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

		super.getServletContext().setAttribute("environments", environments);
		super.getServletContext().setAttribute("users", users);

	}
}