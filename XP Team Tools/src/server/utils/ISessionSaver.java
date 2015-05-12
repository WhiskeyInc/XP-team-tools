package server.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface ISessionSaver {

	public boolean saveSession() throws IOException, NoSuchAlgorithmException, UnsupportedEncodingException;

	public boolean deleteSession() throws IOException;
	
	public void setSessionValues(String user, String pwd);
	
	public String[] getSessionValues() throws IOException;;
}
