package control.actions.account;

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

/**
 * This class implements registration service. When a new user signs in, its
 * user name is checked for uniqueness. If the test is positive, its credential
 * are stored in an HashMap stored in the application context as the "users"
 * attribute. After that, a {@link ProjectsCollector} for the new user is
 * created and put in another HashMap placed in the "environments" attribute.
 * Every new account has a default project, named "General", which is assumed to
 * contain everything is related to the account.
 * 
 * @author lele, simo, incre, andre
 * @see {@link AccountAction}
 *
 */
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
		Project project = new Project("General", new ConcreteProjectFactory(),
				"Everything related to your account");
		ConcreteProjectSettings settings = (ConcreteProjectSettings) project
				.getSettings();
		projectsCollector.addProject(project);
		environments.put(userName, projectsCollector);
		HashMap<String, TeamComponent> usersInfo = (HashMap<String, TeamComponent>) request
				.getServletContext().getAttribute("usersInfo");
		TeamComponent teamComponent = new TeamComponent(
				request.getParameter("name"), request.getParameter("lastName"),
				"Owner");
		usersInfo.put(userName, teamComponent);
		try {
			settings.addTeamMember(teamComponent);
		} catch (NameAlreadyInUseException e) {
		}
		SignInService signIn = new SignInService();
		signIn.perform(request, response);
	}

}
