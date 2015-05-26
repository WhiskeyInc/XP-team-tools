package util.serialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This implementation of {@link SerializerCollector} interface provides proper
 * behavior to all inherited methods and control for identifiability. This
 * control works properly only if the collected objects refer to one single
 * instance of this class. Which means that when one {@link Serializable}
 * instance happens to be collected in two or more different collectors, that
 * may result in a identifiability failure.
 * 
 * @see {@link SerializerCollector}
 * 
 * @author simone, lele, incre, andre
 */
public class LocalIdentifiabilitySerializer implements SerializerCollector {

	private HashMap<Object, HashMap<Integer, Serializable>> collections = new HashMap<Object, HashMap<Integer, Serializable>>();
	private int nextEventId = FIRST_ID;

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#addItem(T)
	 */
	@Override
	public void addItem(Serializable item, Object owner) {
		item.setId(nextEventId);
		this.collections.get(owner).put(nextEventId, item);
		nextEventId++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#getItems()
	 */
	@Override
	public List<Serializable> getItems(Object owner) {
		ArrayList<Serializable> list = new ArrayList<Serializable>();
		list.addAll(collections.get(owner).values());
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#getItem(int)
	 */
	@Override
	public Serializable getItem(int id, Object owner) {
		return collections.get(owner).get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#deleteItem(int)
	 */
	@Override
	public void deleteItem(int id, Object owner) {
		collections.get(owner).remove(id);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * util.serialization.SerializerCollector#registerOwner(java.lang.Object)
	 */
	public void registerOwner(Object owner) {
		this.collections.put(owner, new HashMap<Integer, Serializable>());
	}
}
