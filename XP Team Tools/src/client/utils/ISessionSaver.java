package client.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * An interface that abstracts from the ways of handling different sessions (i.e., saving
 * sessions, delete sessions...)
 * 
 * @author pavlo
 *
 */
public interface ISessionSaver {

	/**
	 * Saves the current session
	 * @return a boolean index that specifies if the save operation goes right
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean saveSession() throws IOException, NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * Deletes the current session
	 * @return a boolean index that specifies if the delete operation goes right
	 * @throws IOException
	 */
	public boolean deleteSession() throws IOException;
	
	/**
	 * Sets the values (i.e., nickname and password) for this session
	 * @param arg1
	 * @param arg2
	 */
	public void setSessionValues(String arg1, String arg2);
	
	/**
	 * Gets this session's values
	 * @return a String array containing this session's values
	 * @throws IOException
	 */
	public String[] getSessionValues() throws IOException;;
}
