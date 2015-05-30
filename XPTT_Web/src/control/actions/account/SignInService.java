package control.actions.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.project.Project;
import model.project.ProjectsCollector;

/**
 * This class implements the log in service: it extracts the user name and the
 * password from request's parameters and checks if they match with ones of a
 * registered user. If they do, the user is set as the currentUser in the
 * session context; otherwise, an exception is raised and set in the same
 * context. The required parameters in the request are the same as in
 * {@link AccountAction}
 * 
 * @see {@link AccountAction}
 * @author lele, simo, incre, andre
 *
 */
public class SignInService extends AccountAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = super.getUserName(request);
		String password = super.getPassword(request);
		HashMap<String, String> users = super.getUsers(request);
		if (users.containsKey(userName)) {
			if (users.get(userName).equals(password)) {
				doLogin(userName, request);
			} else {
				handleFailure(request, "Invalid password!");
			}
		} else {
			handleFailure(request, "Invalid user name!");
		}
		super.forward(response);
	}

	private void handleFailure(HttpServletRequest request, String errormessage) {
		request.getSession().setAttribute("exception",
				new Exception(errormessage));
	}

	@SuppressWarnings("unchecked")
	private void doLogin(String userName, HttpServletRequest request) {
		request.getSession().setAttribute("currentUser", userName);
		HashMap<String, ProjectsCollector> environments = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		ProjectsCollector projects = environments.get(userName);
		ArrayList<Project> projectsList = projects.getProjects();
		request.getSession().setAttribute("currentProject",
				projects.getProject(projectsList.get(0).getId()));
	}
}
