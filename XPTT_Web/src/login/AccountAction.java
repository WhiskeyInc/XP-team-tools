package login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.HttpAction;

public abstract class AccountAction implements HttpAction {

	@SuppressWarnings("unchecked")
	protected HashMap<String, String> getUsers(HttpServletRequest request) {
		return (HashMap<String, String>) request.getServletContext()
				.getAttribute("users");
	}

	protected String getUserName(HttpServletRequest request) {
		return request.getParameter("userName");
	}
	
	protected String getPassword(HttpServletRequest request) {
		return request.getParameter("password");
	}

	protected void forward(HttpServletResponse response) throws IOException {
		response.sendRedirect("home.jsp");
	}

}