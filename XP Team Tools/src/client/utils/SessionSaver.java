package client.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/**
 * An implementation of @ISessionSaver with encrypting functionalities.
 * 
 * @author pavlo
 *
 */
public class SessionSaver implements ISessionSaver {

	public FileWriter writer;
	private BufferedReader reader;
	private String user;
	private String pwd;
	private String encryptedPwd;

	public SessionSaver(String user, String pwd) throws IOException {

		this.user = user;
		this.pwd = pwd;

	}

	@Override
	public boolean saveSession() throws IOException, NoSuchAlgorithmException,
			UnsupportedEncodingException {

		writer = new FileWriter(new File("localData/session.ini"));

		try {
			if (user != null && pwd != null && user.length() <= 100) {

				try {
					encryptedPwd = LocalCipher.encrypt(pwd);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				writer.write(user + "\n" + encryptedPwd);
				return true;

			} else {
				return false;
			}
		} finally {
			writer.close();
		}

	}

	@Override
	public boolean deleteSession() throws IOException {
		try {

			writer = new FileWriter(new File("localData/session.ini"));

			writer.write("");

			writer.close();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setSessionValues(String user, String pwd) {
		this.user = user;
		this.pwd = pwd;
	}

	public String[] getSessionValues() throws IOException {

		reader = new BufferedReader(new FileReader(new File(
				"localData/session.ini")));

		String line = reader.readLine();

		if (!(line == null)) {
			this.user = line;
			this.encryptedPwd = reader.readLine();
			try {
				this.pwd = LocalCipher.decrypt(encryptedPwd);
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] tmp = { user, pwd };
			return tmp;
		} else {

			return null;
		}

	}

}
