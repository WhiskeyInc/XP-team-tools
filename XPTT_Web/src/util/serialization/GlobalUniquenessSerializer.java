package util.serialization;

import java.util.ArrayList;

public class GlobalUniquenessSerializer extends LocalUniquenessSerializer {

	private static GlobalUniquenessSerializer instance;

	private GlobalUniquenessSerializer() {
		super();
	}

	public static GlobalUniquenessSerializer getInstance() {
		if (instance != null) {
			return instance;
		}
		return new GlobalUniquenessSerializer();
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
	public ArrayList<Serializable> getItems() {
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
