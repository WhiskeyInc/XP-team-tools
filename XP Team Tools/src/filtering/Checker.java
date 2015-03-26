package filtering;

public interface Checker<T> {

	/**
	 * Check the parameter
	 * 
	 * @param tobeChecked
	 *            : the T element to check
	 * @return: true if this element is valid, false of it's not
	 */
	public boolean check(T tobeChecked);
}
