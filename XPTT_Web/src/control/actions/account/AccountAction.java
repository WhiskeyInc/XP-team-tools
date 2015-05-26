package control.actions.account;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.HttpAction;

/**
 * This class provides an abstraction for every action which involves the
 * account management. It provides the basic methods to access the list of
 * registered users and their passwords, and redirect the response to a proper
 * location.
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}
 *
 */
public abstract class AccountAction implements HttpAction {

	/**
	 * Return users' password
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @return an {@link HashMap} where the key is the name of the user and the
	 *         value is its password
	 */
	@SuppressWarnings("unchecked")
	protected HashMap<String, String> getUsers(HttpServletRequest request) {
		return (HashMap<String, String>) request.getServletContext()
				.getAttribute("users");
	}

	/**
	 * Return the user name stored in the request passed as a parameter. In
	 * order to work properly, the request must contain the "userName"
	 * parameter.
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @return
	 */
	protected String getUserName(HttpServletRequest request) {
		return request.getParameter("userName");
	}

	/**
	 * Return the user name stored in the request passed as a parameter. In
	 * order to work properly, the request must contain the "password"
	 * parameter.
	 * 
	 * @param request
	 *            : the specific {@link HttpServletRequest}
	 * @return
	 */
	protected String getPassword(HttpServletRequest request) {
		return request.getParameter("password");
	}

	/**
	 * Redirect the response to "home"
	 * 
	 * @param response
	 *            : the {@link HttpServletResponse} to the specific request
	 * @throws IOException
	 */
	protected void forward(HttpServletResponse response) throws IOException {
		response.sendRedirect("home");
	}

}