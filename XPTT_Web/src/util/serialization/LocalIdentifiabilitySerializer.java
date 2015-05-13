package util.serialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This implementation of {@link SerializerCollector} interface provides proper
 * behavior to all inherited methods and control for identifiability. This
 * control works properlyonly if the collected objects refer to one
 * single instance of {@link SerializerCollector}. Which means that when one
 * {@link Serializable} intance happens to be collected in two or more different
 * collectos, that may result in a identifiability failure.
 * 
 * @author simone, lele, incre, andre
 */
public class LocalIdentifiabilitySerializer implements SerializerCollector {

	private HashMap<Integer, Serializable> items = new HashMap<Integer, Serializable>();
	private int nextEventId = FIRST_ID;

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#addItem(T)
	 */
	@Override
	public void addItem(Serializable item) {
		item.setId(nextEventId);
		this.items.put(nextEventId, item);
		nextEventId++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#getItems()
	 */
	@Override
	public List<Serializable> getItems() {
		ArrayList<Serializable> list = new ArrayList<Serializable>();
		list.addAll(items.values());
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#getItem(int)
	 */
	@Override
	public Serializable getItem(int id) {
		return items.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#deleteItem(int)
	 */
	@Override
	public void deleteItem(int id) {
		items.remove(id);
	}
}
