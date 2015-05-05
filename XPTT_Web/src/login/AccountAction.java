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

	public void signUpAuthenticate(String userId, String password,
			HashMap<String, String> registeredUsersPass,
			HashMap<String, String> registeredUsers) throws Exception {
		if (checkUserId(userId, registeredUsersPass)) {
			throw new Exception();
		}
	}

	public void signInAuthenticate(String userId, String password,
			HashMap<String, String> registeredUsersPass,
			HashMap<String, String> registeredUsers) throws Exception {
		if (checkUserId(userId, registeredUsersPass)) {
			if (!checkPassword(userId, password, registeredUsersPass)) {
				throw new Exception();
			}
		} else {
			throw new Exception();
		}
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
	public abstract void perform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

}