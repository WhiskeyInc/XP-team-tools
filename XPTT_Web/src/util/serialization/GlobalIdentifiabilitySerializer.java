package util.serialization;

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
public class GlobalIdentifiabilitySerializer extends
		LocalIdentifiabilitySerializer {

	private static GlobalIdentifiabilitySerializer instance;

	private GlobalIdentifiabilitySerializer() {
		super();
	}

	/**
	 * Returns the static instance of this object, to be used for items
	 * collection
	 * 
	 * @return: the unique instance of this class
	 */
	public static GlobalIdentifiabilitySerializer getInstance() {
		if (instance == null) {
			instance = new GlobalIdentifiabilitySerializer();
		}
		return instance;
	}
}
