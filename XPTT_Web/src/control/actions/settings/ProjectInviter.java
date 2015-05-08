package control.actions.settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import model.project.ProjectsCollector;
import control.HttpAction;

public class ProjectInviter implements HttpAction {

	@Override
	@SuppressWarnings("unchecked")
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String invitedUser = request.getParameter("user");
		Project currentProject = (Project) request.getSession().getAttribute(
				"currentProject");
		System.out.println(invitedUser + " invited to " + currentProject);
		HashMap<String, ProjectsCollector> pendingProjects = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("pendingProjects");
		ProjectsCollector projects = pendingProjects.get(invitedUser);
		projects.addProject(currentProject);
		System.out.println(invitedUser + " invited to " + currentProject);
		response.sendRedirect("members.jsp");
	}
}
