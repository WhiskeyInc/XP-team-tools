package filtering;

import java.util.ArrayList;

/**
 * No filtering implementation of {@link Filter} interface. The filtered
 * collection of element will always be the same of the given one
 * 
 * @author simone
 *
 * @param <T>: the param of the collection to be filtered
 */
public class NoFilter<T> implements Filter<T> {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see filtering.ListFilter#filter(java.util.List)
	 */
	public ArrayList<T> filter(ArrayList<T> list) {
		return list;
	}

}
