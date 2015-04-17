package control.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpAction {

	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
