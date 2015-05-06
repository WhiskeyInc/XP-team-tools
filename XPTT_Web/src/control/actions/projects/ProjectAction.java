package control.actions.projects;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.ProjectsCollector;
import control.HttpAction;

public abstract class ProjectAction implements HttpAction {

	protected ProjectsCollector getProjects(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		HashMap<String, ProjectsCollector> environments = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		String currentUser = (String) request.getSession().getAttribute(
				"currentUser");
		return environments.get(currentUser);
	}

	protected void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("projects.jsp");
	}
}
