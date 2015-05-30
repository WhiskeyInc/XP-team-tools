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

	private HashMap<Integer, Serializable> items = new HashMap<Integer, Serializable>();
	private HashMap<Serializable, ArrayList<Object>> owners = new HashMap<Serializable, ArrayList<Object>>();
	private int nextEventId = FIRST_ID;

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#addItem(T)
	 */
	@Override
	public void addItem(Serializable item, Object owner) {
		if (!items.containsKey(item.getId())) {
			item.setId(nextEventId);
			items.put(nextEventId, item);
			owners.put(item, new ArrayList<Object>());
			nextEventId++;
		}
		owners.get(item).add(owner);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#getItems()
	 */
	@Override
	public List<Serializable> getItems(Object owner) {
		List<Serializable> list = new ArrayList<Serializable>();
		for (Serializable item : owners.keySet()) {
			if (owners.get(item).contains(owner)) {
				list.add(item);
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#getItem(int)
	 */
	@Override
	public Serializable getItem(int id, Object owner) {
		Serializable item = items.get(id);
		if(item == null){
			return null;
		}
		if (owners.get(item).contains(owner)) {
			return item;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.SerializerCollectorr#deleteItem(int)
	 */
	@Override
	public void deleteItem(int id, Object owner) {
		Serializable item = items.get(id);
		if (owners.get(item).contains(owner)) {
			owners.remove(item);
			items.remove(id);
		}
	}
}
