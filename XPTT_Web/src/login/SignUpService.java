package login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.HttpAction;

public class SignUpService extends AccountAction implements HttpAction {

	
		@SuppressWarnings("unchecked")
		@Override
		public void perform(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String userName = request.getParameter("userName");
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");

			HashMap<String, String> registeredUsersPass = new HashMap<String, String>();
			HashMap<String, String> registeredUsers = new HashMap<String, String>();
			registeredUsersPass = (HashMap<String, String>) request
					.getServletContext().getAttribute("registeredUsersPass");
			registeredUsers = (HashMap<String, String>) request.getServletContext()
					.getAttribute("registeredUsers");

			boolean result = isregistered(userId, password, registeredUsersPass,
					registeredUsers);

			if (!result) {
				registeredUsers.put(userId, userName);
				registeredUsersPass.put(userId, password);
				request.getServletContext().setAttribute("registeredUsers", registeredUsers);
				request.getServletContext().setAttribute("registeredUsersPass", registeredUsersPass);
				response.sendRedirect("timeline.jsp");
				return;
			} else {
				response.sendRedirect("home.jsp"); //TODO: modificare in modo più furbo
				return;
			}
		}

		
	}

