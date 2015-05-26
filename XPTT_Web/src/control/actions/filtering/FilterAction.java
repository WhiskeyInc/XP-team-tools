package control.actions.filtering;

import javax.servlet.http.HttpServletRequest;

import control.HttpAction;
import filtering.Filter;

/**
 * This class represents an abstraction of a generic action dealing with
 * filtering. It provides the basic methods to set a filter in the "filter"
 * attribute of the session context.
 * 
 * @author lele, simo, incre, andre
 * @param <T>
 *            : the class the filter is to work on
 * @see {@link Filter}, {@link FilterAction}
 */
public abstract class FilterAction<T> implements HttpAction {

	/**
	 * Set a filter in the session context. The attribute used is named
	 * "filter".
	 * 
	 * @param request
	 *            : the {@link HttpServletRequest} for the resource
	 * @param filter
	 *            : the filter to be set
	 */
	protected void setSessionFilter(HttpServletRequest request, Filter<T> filter) {
		request.getSession().setAttribute("filter", filter);
	}
}
