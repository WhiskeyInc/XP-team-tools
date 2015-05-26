package control.actions.filtering;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filtering.Filter;
import filtering.NoFilter;

/**
 * This class provides the basic methods to set a {@link NoFilter} in the
 * Session context. As a this filter makes no choice among the elements, setting
 * a {@link NoFilter} equals removing any filter.
 * 
 * @author lele, simo, incre, andre
 * @param <T>
 *            : the class the filter is to work on
 * @see {@link Filter}, {@link FilterAction}, {@link NoFilter}
 */
public class NoFilterGenerator<T> extends FilterAction<T> {

	private String destination;

	/**
	 * Return an instance of the class.
	 * 
	 * @param destination
	 *            : the URI of the resource the {@link HttpServletResponse}
	 *            should be redirected to
	 */
	public NoFilterGenerator(String destination) {
		super();
		this.destination = destination;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see control.HttpAction#perform(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Filter<T> filter = new NoFilter<T>();
		super.setSessionFilter(request, filter);
		response.sendRedirect(destination);
	}

}
