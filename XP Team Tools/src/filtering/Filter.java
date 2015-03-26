package filtering;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter interface can implement any kind of filtering algohrithm that work on
 * a certain {@link List} of data. WARNING: tested behavior only on
 * {@link ArrayList} set of data
 * 
 * @author simone
 *
 * @param <T>: the param of the collection to be filtered
 */
public interface Filter<T> {

	/**
	 * Performs the filtering
	 * 
	 * @param collection
	 *            : the collection to be filtered
	 * @return: a new collection that represents the filtered one
	 */
	public ArrayList<T> filter(ArrayList<T> collection);
}
