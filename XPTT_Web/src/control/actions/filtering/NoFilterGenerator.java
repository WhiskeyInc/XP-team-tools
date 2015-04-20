package control.actions.filtering;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filtering.Filter;
import filtering.NoFilter;

public class NoFilterGenerator<T> extends FilterAction<T> {

	private String destination;

	public NoFilterGenerator(String destination) {
		super();
		this.destination = destination;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Filter<T> filter = new NoFilter<T>();
		super.returnFilter(request, filter);
		response.sendRedirect(destination);		
	}

}
