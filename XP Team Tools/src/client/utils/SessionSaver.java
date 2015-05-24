package client.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import server.utils.auth.BaseToByteViceversa;
import server.utils.auth.Hash;

public class SessionSaver implements ISessionSaver {

	public FileWriter writer;
	private BufferedReader reader;
	private String user;
	private String pwd;
	private String sDigest;
	private String sSalt;

	private final static int ITERATION_NUMBER = 1000;
	private Hash hasher = new Hash();

	public SessionSaver(String user, String pwd) throws IOException {

		this.user = user;
		this.pwd = pwd;

		// TODO risolvere la riscrittura del file
		// TODO spostare pacchetti in client.utils
		// TODO crittare file in locale e trasmettere solo HASH

	}

	@Override
	public boolean saveSession() throws IOException, NoSuchAlgorithmException,
			UnsupportedEncodingException {

		writer = new FileWriter(new File("localData/session.ini"));

		try {
			if (user != null && pwd != null && user.length() <= 100) {

				SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

				byte[] bSalt = new byte[8];
				random.nextBytes(bSalt);
				// Digest computation
				byte[] bDigest = hasher.getHash(ITERATION_NUMBER, pwd, bSalt);

				// TODO
				String sDigest = BaseToByteViceversa.byteToBase64(bDigest);
				String sSalt = BaseToByteViceversa.byteToBase64(bSalt);

				System.out.println(user);
				writer.write(user + "\n" + pwd);
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
		// System.out.println(line);

		if (!(line == null)) {
			this.user = line;
			this.pwd = reader.readLine();
			// this.sDigest = reader.readLine();
			// this.sSalt = reader.readLine();
			String[] tmp = { user, pwd };
			return tmp;
		} else {

			return null;
		}

	}

}
