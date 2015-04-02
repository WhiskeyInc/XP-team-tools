package filtering;

/**
 * Checker interface represents a generic element validator
 * 
 * @author simone
 *
 * @param <T>: the type of items to validate
 */
public interface Checker<T> {

	/**
	 * Check the element
	 * 
	 * @param tobeChecked
	 *            : the T element to be checked
	 * @return: true if this element is valid, false otherwise
	 */
	public boolean check(T tobeChecked);
}
