package control.actions.projects;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import model.project.ProjectsCollector;
import control.HttpAction;

/**
 * This class provides an abstraction for every action which involves a
 * {@link Project}. It provides the basic methods to get the
 * {@link ProjectsCollector} for a given user and redirect the response to the
 * correct resource after the action is performed.
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}, {@link ProjectsCollector}
 *
 */
public abstract class ProjectAction implements HttpAction {

	/**
	 * Return the {@link ProjectsCollector} for the current user from the
	 * "environments" attributes in the session context. The current user is get
	 * form the "currentUser" attributes in the session context
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @return the correct instance of {@link ProjectsCollector}
	 */
	protected ProjectsCollector getProjects(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		HashMap<String, ProjectsCollector> environments = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		String currentUser = (String) request.getSession().getAttribute(
				"currentUser");
		return environments.get(currentUser);
	}

	/**
	 * Redirect the response to "project.jsp"
	 * 
	 * @param response
	 *            : the {@link HttpServletResponse} for the specific request
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("projects.jsp");
	}
}
