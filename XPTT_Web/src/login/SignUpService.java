package login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.NameAlreadyInUseException;
import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsCollector;

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

	private void doRegister(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		users.put(userName, password);
		ProjectsCollector projectsCollector = new ProjectsCollector();
		@SuppressWarnings("unchecked")
		HashMap<String, ProjectsCollector> environments = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		projectsCollector
				.addProject(new Project("General",
						new ConcreteProjectFactory(),
						"Everything related to your team"));
		environments.put(userName, projectsCollector);
		SignInService signIn = new SignInService();
		signIn.perform(request, response);
	}

}
