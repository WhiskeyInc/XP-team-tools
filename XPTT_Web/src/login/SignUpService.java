package login;

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
import util.serialization.GlobalIdentifiabilitySerializer;

public class SignUpService extends AccountAction {

	private String userName;
	private String password;
	private HashMap<String, String> users;

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see login.AccountAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		userName = super.getUserName(request);
		password = super.getPassword(request);
		users = super.getUsers(request);
		if (users.containsKey(userName)) {
			request.getSession().setAttribute("exception",
					new NameAlreadyInUseException(userName));
			super.forward(response);
		} else {
			doRegister(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	private void doRegister(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		users.put(userName, password);
		ProjectsCollector projectsCollector = new ProjectsCollector(
				GlobalIdentifiabilitySerializer.getInstance());
		HashMap<String, ProjectsCollector> environments = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		HashMap<String, ProjectsCollector> pendingProjects = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("pendingProjects");
		Project project = new Project("General", new ConcreteProjectFactory(),
				"Everything related to your account");
		ConcreteProjectSettings settings = (ConcreteProjectSettings) project
				.getSettings();
		projectsCollector.addProject(project);
		pendingProjects.put(userName, new ProjectsCollector(
				GlobalIdentifiabilitySerializer.getInstance()));
		environments.put(userName, projectsCollector);
		HashMap<String, TeamComponent> usersInfo = (HashMap<String, TeamComponent>) request
				.getServletContext().getAttribute("usersInfo");
		TeamComponent teamComponent = new TeamComponent(
				request.getParameter("name"), request.getParameter("lastName"),
				"Developer");
		usersInfo.put(userName, teamComponent);
		// TODO: role?
		try {
			settings.addTeamMember(teamComponent);
		} catch (NameAlreadyInUseException e) {
		}
		SignInService signIn = new SignInService();
		signIn.perform(request, response);
	}

}
