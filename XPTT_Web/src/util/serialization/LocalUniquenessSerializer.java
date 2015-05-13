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
public class LocalUniquenessSerializer implements SerializerCollector {

	public static final int FIRST_ID = 0;

	private HashMap<Integer, Serializable> items = new HashMap<Integer, Serializable>();
	private int nextEventId = FIRST_ID;

	/* (non-Javadoc)
	 * @see util.serialization.SerializerCollectorr#addItem(T)
	 */
	@Override
	public void addItem(Serializable item) {
		item.setId(nextEventId);
		this.items.put(nextEventId, item);
		nextEventId++;
	}

	/* (non-Javadoc)
	 * @see util.serialization.SerializerCollectorr#getItems()
	 */
	@Override
	public ArrayList<Serializable> getItems() {
		ArrayList<Serializable> list = new ArrayList<Serializable>();
		list.addAll(items.values());
		return list;
	}

	/* (non-Javadoc)
	 * @see util.serialization.SerializerCollectorr#getItem(int)
	 */
	@Override
	public Serializable getItem(int id) {
		return items.get(id);
	}

	/* (non-Javadoc)
	 * @see util.serialization.SerializerCollectorr#deleteItem(int)
	 */
	@Override
	public void deleteItem(int id) {
		items.remove(id);
	}
}
