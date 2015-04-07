package filtering;

import java.util.ArrayList;

/**
 * This implementation of {@link Filter} interface supports target check for the
 * collection's element.
 * 
 * @author simone
 *
 * @param <T>: the param of the collection
 */
public class TargetFilter<T> implements Filter<T> {

	private Checker<T> checker;

	/**
	 * Create a new instance of this class
	 * 
	 * @param ckecker
	 *            : the concrete checker that will be used to decide whether or
	 *            not a single T element has to be added to the filtered
	 *            collection
	 */
	public TargetFilter(Checker<T> ckecker) {
		super();
		this.checker = ckecker;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see filtering.Filter#filter(java.util.Collection)
	 */
	public ArrayList<T> filter(ArrayList<T> collection) {
		ArrayList<T> filteredCollection = new ArrayList<T>();
		for (T element : collection) {
			if(checker.check(element))
				filteredCollection.add(element);
		}
		return (ArrayList<T>) filteredCollection;
	}

}
