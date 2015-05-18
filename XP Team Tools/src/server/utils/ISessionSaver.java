package server.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * An interface with methods for saving a session
 * 
 *
 */
public interface ISessionSaver {

	/**
	 * saves the current session
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean saveSession() throws IOException, NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * delete this session
	 * @return
	 * @throws IOException
	 */
	public boolean deleteSession() throws IOException;
	
	/**
	 * sets the values that identify this session, i.e., username and password
	 * @param user username
	 * @param pwd password
	 */
	public void setSessionValues(String user, String pwd);
	
	public String[] getSessionValues() throws IOException;;
}
