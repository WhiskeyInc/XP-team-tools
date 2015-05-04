package control.actions.projects;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import model.project.ProjectsCollector;

public class ProjectSelector extends ProjectAction {

	@Override
	/*
	 * (non-Javadoc)
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProjectsCollector projects = super.getProjects(request);
		Project project = projects.getProject(getId(request));
		request.getSession().setAttribute("currentProject", project);
		System.err.println(getId(request));
		super.redirect(response);
	}

	private int getId(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("id"));
	}
}
