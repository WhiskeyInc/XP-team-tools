package control.actions.projects;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.ProjectsCollector;
import control.HttpAction;

public abstract class ProjectAction implements HttpAction {

	protected ProjectsCollector getProjects(HttpServletRequest request) {
		return (ProjectsCollector) request.getServletContext().getAttribute(
				"projects");
	}

	protected void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("projects.jsp");
	}
}
