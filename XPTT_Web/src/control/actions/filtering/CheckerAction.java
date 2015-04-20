package control.actions.filtering;

import javax.servlet.http.HttpServletRequest;

import filtering.Checker;
import filtering.TargetFilter;

public abstract class CheckerAction<T> extends FilterAction<T> {

	protected void returnChecker(HttpServletRequest request, Checker<T> checker) {
		super.returnFilter(request, new TargetFilter<T>(checker));
	}

}
