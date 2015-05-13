package util.serialization;

import java.util.List;

/**
 * SerializerCollector interface provides simple methods to handle a collection
 * of {@link Serializable} instances
 * 
 * @author simone
 * @see Serializable
 *
 */
public interface SerializerCollector {

	/**
	 * The integer identifier of the very first item collected in this object
	 */
	public static final int FIRST_ID = 0;

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
	 * @return: a {@link List} containing every item
	 */
	public List<Serializable> getItems();

	/**
	 * Provides a simple method to access an item by its identifier
	 * 
	 * @param id
	 *            : the identifier for the desired item
	 * @return: the item whose id matches with the parameter. If no such item
	 *          can be found, the return value is null
	 */
	public Serializable getItem(int id);

	/**
	 * Provides a simple way to delete an item from this collection.
	 * 
	 * @param id
	 *            : the identifier for the item to delete
	 */
	public void deleteItem(int id);

}