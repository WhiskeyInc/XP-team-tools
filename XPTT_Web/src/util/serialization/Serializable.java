package util.serialization;

/**
 * Serializable class represents any object that can uniquely identified by an
 * integer identifier
 * 
 * @author simone, lele, incre, andre
 *
 */
public class Serializable {

	protected int id;

	/**
	 * Sets the identifier for this object. This operation is available only for
	 * subclasses or for package components
	 * 
	 * @param id
	 *            : an integer that will identify the object
	 */
	protected void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the identifier of this object
	 * 
	 * @return: the id of this item
	 */
	public int getId() {
		return this.id;
	}
}
