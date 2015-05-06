package login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInService extends AccountAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = super.getUserName(request);
		String password = super.getPassword(request);
		HashMap<String, String> users = super.getUsers(request);
		if (users.containsKey(userName)) {
			if (users.get(userName).equals(password)) {
				doLogin(userName, request);
			}
		} else {
			request.getSession().setAttribute("exception",
					new Exception("Invalid user name or password"));
			super.forward(response);
		}
	}

	private void doLogin(String userName, HttpServletRequest request) {
		request.getSession().setAttribute("currentUser", userName);
	}
}
