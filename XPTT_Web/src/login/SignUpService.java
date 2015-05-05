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
				throws ServletException,IOException{
			String userName = request.getParameter("userName");
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");

			HashMap<String, String> registeredUsersPass = new HashMap<String, String>();
			HashMap<String, String> registeredUsers = new HashMap<String, String>();
			registeredUsersPass = (HashMap<String, String>) request
					.getServletContext().getAttribute("registeredUsersPass");
			registeredUsers = (HashMap<String, String>) request.getServletContext()
					.getAttribute("registeredUsers");

			try {
				signUpAuthenticate(userId, password, registeredUsersPass,
						registeredUsers);
				registeredUsers.put(userId, userName);
				registeredUsersPass.put(userId, password);
			    request.getServletContext().setAttribute("registeredUsers", registeredUsers);
				request.getServletContext().setAttribute("registeredUsersPass", registeredUsersPass);
				response.sendRedirect("timeline.jsp");
			} catch (Exception e) {
				request.getSession().setAttribute("exception", e);	
				response.sendRedirect("home.jsp");
			}
		}

		
	}

