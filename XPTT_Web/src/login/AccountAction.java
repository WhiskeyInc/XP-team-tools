package login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.HttpAction;

public abstract class AccountAction implements HttpAction {

	public AccountAction() {
		super();
	}

	public boolean isregistered(String userId, String password,
			HashMap<String, String> registeredUsersPass,
			HashMap<String, String> registeredUsers) {
		boolean valid = checkUserId(userId, registeredUsersPass);
		if (valid == true) {
			valid = checkPassword(userId, password, registeredUsersPass);
		}
		return valid;
	}

	private boolean checkPassword(String userId, String password,
			HashMap<String, String> registeredUsersPass) {
		if (registeredUsersPass.get(userId).compareTo(password) == 0) {
			return true;
		}
		return false;
	}

	private boolean checkUserId(String userId,
			HashMap<String, String> registeredUsersPass) {
		if (!registeredUsersPass.containsKey(userId)) {
			return false;
		}
		return true;
	}

	@Override
	public abstract void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
}