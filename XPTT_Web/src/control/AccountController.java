package control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.actions.account.SignInService;
import control.actions.account.SignOutAction;
import control.actions.account.SignUpService;

/**
 * This {@link HttpServlet} performs any action required to manage the users'
 * accounts: log in, log out and registration are supported.
 */
@WebServlet("/login")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, HttpAction> actions = new HashMap<String, HttpAction>();

	/**
	 * Return an instance of this servlet
	 */
	public AccountController() {
		super();
		this.initializeMap();
	}

	private void initializeMap() {
		actions.put("login", new SignInService());
		actions.put("register", new SignUpService());
		actions.put("logout", new SignOutAction());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.actions.get(request.getParameter("action")).perform(request,
				response);

	}

}
