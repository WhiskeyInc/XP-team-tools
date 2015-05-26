package control.actions.filtering;

import javax.servlet.http.HttpServletRequest;

import filtering.Checker;
import filtering.Filter;
import filtering.TargetFilter;

/**
 * This class represents an abstraction of a generic action dealing with
 * filtering. It provides the basic methods to set the checker for the filter
 * which is to be set in the session context.
 * 
 * @author lele, simo, incre, andre
 * @param <T>
 *            : the class the checker is to work on
 * @see {@link Filter}, {@link FilterAction}
 */
public abstract class CheckerAction<T> extends FilterAction<T> {

	/**
	 * Set the checker for the filter.
	 * 
	 * @param request
	 *            : the {@link HttpServletRequest} for the resource
	 * @param checker
	 *            : the filter to be set
	 */
	protected void setSessionChecker(HttpServletRequest request,
			Checker<T> checker) {
		super.setSessionFilter(request, new TargetFilter<T>(checker));
	}

}
