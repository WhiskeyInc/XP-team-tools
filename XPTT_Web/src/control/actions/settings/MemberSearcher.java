package control.actions.settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.HttpAction;

public class MemberSearcher implements HttpAction {

	@Override
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
