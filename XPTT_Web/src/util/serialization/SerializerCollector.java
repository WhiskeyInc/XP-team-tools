package util.serialization;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class can represent a collector of {@link Serializable} object. It
 * provides basic operations and id uniqueness control
 * 
 * @author simone
 *
 * @param <T>: the {@link Serializable} instances to collect
 */
public class SerializerCollector<T extends Serializable> {

	public static final int FIRST_ID = 0;

	private HashMap<Integer, T> items = new HashMap<Integer, T>();
	private int nextEventId = FIRST_ID;

	/**
	 * Provides addition to the collection, including serialization
	 * 
	 * @param item
	 *            : the {@link Serializable} item to add
	 */
	protected void addItem(T item) {
		item.setId(nextEventId);
		this.items.put(nextEventId, item);
		nextEventId++;
	}

	/**
	 * Returns a list containing the whole set of items collected
	 * 
	 * @return: an {@link ArrayList} containing every item
	 */
	protected ArrayList<T> getItems() {
		ArrayList<T> list = new ArrayList<T>();
		list.addAll(items.values());
		return list;
	}

	/**
	 * Provides a simple method to access an item by its id
	 * 
	 * @param id
	 *            : the unique identifier for the desired item
	 * @return: the item whose id matches with the parameter. If no such item
	 *          can be found, the return value is null
	 */
	protected T getItem(int id) {
		return items.get(id);
	}

	/**
	 * Provides a simple way to delete an item from this collection.
	 * 
	 * @param id: the unique identifier for the item to delete
	 */
	protected void deleteItem(int id) {
		items.remove(id);
	}
}
