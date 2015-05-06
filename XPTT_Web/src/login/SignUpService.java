package login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.exceptions.NameAlreadyInUseException;

public class SignUpService extends AccountAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see login.AccountAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = super.getUserName(request);
		String password = super.getPassword(request);
		HashMap<String, String> users = super.getUsers(request);
		if (users.containsKey(userName)) {
			request.getSession().setAttribute("exception",
					new NameAlreadyInUseException(userName));
		} else {
			users.put(userName, password);
			System.out.println("new account:" + userName);
			super.forward(response);
		}
	}

}
