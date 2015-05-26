package util.serialization;

import java.util.List;

/**
 * SerializerCollector interface provides simple methods to handle collections
 * of {@link Serializable} instances, making them univocally identifiable. Each
 * collected item refer to one or more owner {@link Object} instance, so that
 * any action on that single item is avoided to other objects
 * 
 * @author simone, lele, andre, incre
 * @see Serializable
 *
 */
public interface SerializerCollector {

	/**
	 * The integer identifier of the very first item collected in this object.
	 */
	public static final int FIRST_ID = 0;

	/**
	 * Provides addition to the collection, including serialization.
	 * 
	 * @param item
	 *            : the {@link Serializable} item to add
	 * @param owner
	 *            : the {@link Object} that owns this item
	 */
	public void addItem(Serializable item, Object owner);

	/**
	 * Returns a list containing the whole set of items collected.
	 * 
	 * @param owner
	 *            : the owner {@link Object} whose items will be returned
	 * 
	 * @return: a {@link List} containing every item belonging to this owner
	 */
	public List<Serializable> getItems(Object owner);

	/**
	 * Provides a simple method to access an item by its identifier.
	 * 
	 * @param id
	 *            : the identifier for the desired item
	 * 
	 * @param owner
	 *            : the {@link Object} owner of the required item
	 * 
	 * @return: the item whose id matches with the parameter. If no such item
	 *          can be found, the return value is null
	 */
	public Serializable getItem(int id, Object owner);

	/**
	 * Provides a simple way to delete an item from this collection.
	 * 
	 * @param id
	 *            : the identifier for the item to delete
	 * 
	 * @param owner
	 *            : the {@link Object} owner of that item. If it is not, no
	 *            action will be performed
	 */
	public void deleteItem(int id, Object owner);

}