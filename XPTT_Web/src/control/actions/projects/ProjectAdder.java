package control.actions.projects;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConcreteProjectSettings;
import model.TeamComponent;
import model.exceptions.NameAlreadyInUseException;
import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsCollector;

public class ProjectAdder extends ProjectAction {

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
		Project project = new Project(
				request.getParameter("projectName"),
				new ConcreteProjectFactory(),
				request.getParameter("description"));
		ConcreteProjectSettings settings = (ConcreteProjectSettings) project
				.getSettings();
		try {
			settings.addTeamMember(getTeamComponent(request));
		} catch (NameAlreadyInUseException e) {
		}
		projects.addProject(project);
		super.redirect(response);
	}

	private TeamComponent getTeamComponent(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute(
				"currentUser");
		@SuppressWarnings("unchecked")
		HashMap<String, TeamComponent> usersInfo = (HashMap<String, TeamComponent>) request
				.getServletContext().getAttribute("usersInfo");
		return usersInfo.get(username);
	}

}
