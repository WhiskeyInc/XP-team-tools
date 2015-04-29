package control.services;

import javax.servlet.http.HttpServletRequest;

public class LoginService {

	private static final String PASSWORD = "hardCodedPWD";

	public static boolean autenticateUser(HttpServletRequest request){
		return request.getParameter("password").equals(PASSWORD);
	}
}
