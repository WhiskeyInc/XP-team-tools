package util.serialization;

import java.util.List;

/**
 * This DP Singleton oriented implementation of {@link SerializerCollector}
 * interface provides the same behavior of
 * {@link LocalIdentifiabilitySerializer} but uses static access to object in
 * order to ensure global identifiability for collected {@link Serializable}
 * instaces
 * 
 * @author simone, lele, incre, andre
 *
 */
public class GlobalUniquenessSerializer extends LocalIdentifiabilitySerializer {

	private static GlobalUniquenessSerializer instance;

	private GlobalUniquenessSerializer() {
		super();
	}

	/**
	 * Returns the static instance of this object, to be used for items
	 * collection
	 * 
	 * @return
	 */
	//TODO: ma siamo sicuri che il singleton si faccia così?? By Lele to Simo
	public static GlobalUniquenessSerializer getInstance() {
		if (instance == null) {
			instance = new GlobalUniquenessSerializer();
		}
		return instance;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * util.serialization.LocalUniquenessSerializer#addItem(util.serialization
	 * .Serializable)
	 */
	public void addItem(Serializable item) {
		super.addItem(item);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.LocalUniquenessSerializer#getItems()
	 */
	public List<Serializable> getItems() {
		return super.getItems();
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.LocalUniquenessSerializer#getItem(int)
	 */
	public Serializable getItem(int id) {
		return super.getItem(id);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see util.serialization.LocalUniquenessSerializer#deleteItem(int)
	 */
	public void deleteItem(int id) {
		super.deleteItem(id);
	}

}
