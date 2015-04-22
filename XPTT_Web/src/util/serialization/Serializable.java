package util.serialization;

/**
 * Serializable class represents any subclass of {@link Object} that can
 * represented by an unique integer identifier
 * 
 * @author simone
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
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return super.toString()+" [id:"+this.id+"]";
	}
}
