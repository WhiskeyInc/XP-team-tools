package control;

import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.ConcreteTeamManager;
import model.ConcreteProjectSettings;
import model.TeamComponent;
import model.ProjectManager;
import model.exceptions.NameAlreadyInUseException;
import timeline.ConcreteTimeline;
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
		Timeline timeline = new ConcreteTimeline(
				TimeZone.getTimeZone("Europe/Rome"));
		ConcreteProjectSettings settings = new ConcreteProjectSettings();
		ProjectManager manager = new ConcreteTeamManager(settings, timeline);
		settings.setManager(manager);
		super.getServletContext().setAttribute("timeline", timeline);
		super.getServletContext().setAttribute("settings", settings);
		super.getServletContext().setAttribute("manager", manager);
		try {
			settings.addTeamMember(new TeamComponent("Simone", "Colucci",
					"Design Patterns Analyst"), new TeamComponent("Emanuele",
					"Fabbiani", "JUnit Tester"), new TeamComponent("Alessandro",
					"Incremona", "Open Source Responsable"), new TeamComponent(
					"Andrea", "Aschei", "Components Analyst"));
		} catch (NameAlreadyInUseException e) {
			e.printStackTrace();
		}
	}
}