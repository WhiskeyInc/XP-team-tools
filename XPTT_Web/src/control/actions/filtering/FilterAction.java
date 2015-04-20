package control.actions.filtering;

import javax.servlet.http.HttpServletRequest;

import control.HttpAction;
import filtering.Filter;

public abstract class FilterAction<T> implements HttpAction {

	protected void returnFilter(HttpServletRequest request, Filter<T> filter){
		request.getSession().setAttribute("filter", filter);
	}
}
