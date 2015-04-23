package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.ConcreteProjectSettings;
import model.TeamComponent;
import model.exceptions.NameAlreadyInUseException;
import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsManager;

/**
 * Servlet implementation class Initializer
 */
@WebServlet("/initialize")
public class Initializer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();

		ProjectsManager projects = new ProjectsManager();
		Project project = new Project(new ConcreteProjectFactory());
		projects.addProject(project);
		super.getServletContext().setAttribute("currentProject", project);
		super.getServletContext().setAttribute("projects", projects);
		ConcreteProjectSettings settings = (ConcreteProjectSettings) ((Project) super
				.getServletContext().getAttribute("currentProject"))
				.getSettings();
		settings.setManager(project.getManager());
		try {
			settings.addTeamMember(new TeamComponent("Simone", "Colucci",
					"Design Patterns Analyst"), new TeamComponent("Emanuele",
					"Fabbiani", "JUnit Tester"), new TeamComponent(
					"Alessandro", "Incremona", "Open Source Responsable"),
					new TeamComponent("Andrea", "Aschei", "Components Analyst"));
		} catch (NameAlreadyInUseException e) {
			e.printStackTrace();
		}
	}
}