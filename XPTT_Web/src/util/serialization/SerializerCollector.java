package util.serialization;

import java.util.ArrayList;

public interface SerializerCollector{

	/**
	 * Provides addition to the collection, including serialization
	 * 
	 * @param item
	 *            : the {@link Serializable} item to add
	 */
	public void addItem(Serializable item);

	/**
	 * Returns a list containing the whole set of items collected
	 * 
	 * @return: an {@link ArrayList} containing every item
	 */
	public ArrayList<Serializable> getItems();

	/**
	 * Provides a simple method to access an item by its id
	 * 
	 * @param id
	 *            : the unique identifier for the desired item
	 * @return: the item whose id matches with the parameter. If no such item
	 *          can be found, the return value is null
	 */
	public Serializable getItem(int id);

	/**
	 * Provides a simple way to delete an item from this collection.
	 * 
	 * @param id
	 *            : the unique identifier for the item to delete
	 */
	public void deleteItem(int id);

}