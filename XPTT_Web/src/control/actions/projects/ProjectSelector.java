package control.actions.projects;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import model.project.ProjectsCollector;

/**
 * This class selects a project from the {@link ProjectsCollector} of the user
 * and set it as the "currentProject" attribute in the session context. To work
 * properly, this class requires that the following attributes are properly set
 * in the request: id (the unique identifier of the project to be selected).
 * 
 * @author lele, simo, incre, andre
 * @see {@link ProjectAction}
 *
 */
public class ProjectSelector extends ProjectAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProjectsCollector projects = super.getProjects(request);
		Project project = projects.getProject(getId(request));
		request.getSession().setAttribute("currentProject", project);
		super.redirect(response);
	}

	private int getId(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("id"));
	}
}
