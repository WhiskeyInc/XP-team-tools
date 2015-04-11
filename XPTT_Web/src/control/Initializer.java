package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.ConcreteTeamManager;
import model.ConcreteTeamSettings;
import model.TeamManager;
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
		Timeline timeline = new ConcreteTimeline();
		ConcreteTeamSettings settings = new ConcreteTeamSettings();
		TeamManager manager = new ConcreteTeamManager(settings, timeline);
		settings.setManager(manager);
		super.getServletContext().setAttribute("timeline", timeline);
		super.getServletContext().setAttribute("settings", settings);
		super.getServletContext().setAttribute("manager", manager);
		try {
			settings.addTeamMember("sumo", "usk", "incre", "lele");
		} catch (NameAlreadyInUseException e) {
			e.printStackTrace();
		}
	}
}