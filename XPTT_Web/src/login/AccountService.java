package login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.HttpAction;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/login")
public class AccountService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, HttpAction> actions = new HashMap<String, HttpAction>();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	public AccountService() {
		super();
		this.initializeMap();
	}

	private void initializeMap() {
		actions.put("login", new SignInService());
		actions.put("register", new SignUpService());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.actions.get(request.getParameter("action")).perform(request,
				response);

	}

}
