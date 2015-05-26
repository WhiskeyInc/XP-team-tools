package control.actions.settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import model.project.ProjectsCollector;
import control.HttpAction;

/**
 * This class "invites" a member to a project. This is realized by adding to the
 * {@link ProjectsCollector} of the invited user the project he has been
 * invited to. To properly work, the following attributes are assumed
 * to be set in the request: user (the name of the invited user)
 * 
 * @author lele, simo, incre, andre
 *
 */
public class ProjectInviter implements HttpAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String invitedUser = request.getParameter("user");
		Project currentProject = (Project) request.getSession().getAttribute(
				"currentProject");
		HashMap<String, ProjectsCollector> envp = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		ProjectsCollector projects = envp.get(invitedUser);
		projects.addProject(currentProject);
		response.sendRedirect("members.jsp");
	}
}
