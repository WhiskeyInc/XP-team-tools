package control.actions.settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.HttpAction;

/**
 * This class searches all the registered users whose name partially matches a
 * given string. The list of the user is saved in the application context. Once
 * the matching list is build it is set in the membersList attribute of the
 * session context.
 * 
 * @author lele, simo, incre, andre
 * @see {@link HttpAction}
 *
 */
public class MemberSearcher implements HttpAction {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> list = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		HashMap<String, String> users = (HashMap<String, String>) request
				.getServletContext().getAttribute("users");
		for (String string : users.keySet()) {
			if (string.contains(request.getParameter("search"))) {
				list.add(string);
			}
		}
		request.getSession().setAttribute("membersList", list);
		response.sendRedirect("members.jsp");
	}

}
