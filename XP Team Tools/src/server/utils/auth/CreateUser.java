package server.utils.auth;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class allows to insert a new user in database
 * 
 * @author pavlo
 *
 */
public class CreateUser {

	private final static int ITERATION_NUMBER = 1000;
	private Hash hasher = new Hash();

	/**
	 * Inserts a new user in the database
	 * 
	 * @param con
	 *            Connection An open connection to a databse
	 * @param login
	 *            String The login of the user
	 * @param password
	 *            String The password of the user
	 * @return boolean Returns true if the login and password are ok (not null
	 *         and length(login)<=100
	 * @throws SQLException
	 *             If the database is unavailable
	 * @throws NoSuchAlgorithmException
	 *             If the algorithm SHA-1 or the SecureRandom is not supported
	 *             by the JVM
	 * @throws UnsupportedEncodingException needed for getBytes encoding
	 */
	public boolean createUser(Connection con, String login, String password)
			throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		PreparedStatement ps = null;
		try {
			if (login != null && password != null && login.length() <= 100) {
				// Uses a secure Random not a simple Random
				SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
				// Salt generation 64 bits long
				byte[] bSalt = new byte[8];
				random.nextBytes(bSalt);
				// Digest computation
				byte[] bDigest = hasher.getHash(ITERATION_NUMBER, password,
						bSalt);

				String sDigest = BaseToByteViceversa.byteToBase64(bDigest);
				String sSalt = BaseToByteViceversa.byteToBase64(bSalt);

				ps = con.prepareStatement("INSERT INTO CREDENTIAL (USER, PASSWORD, SALT, DATUM, COMMENTS) VALUES (?,?,?,?,?)");
				ps.setString(1, login);
				ps.setString(2, sDigest);
				ps.setString(3, sSalt);
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				ps.setString(5, "");
				ps.executeUpdate();
				return true;
			} else {
				return false;
			}
		} finally {
			close(ps);
		}
	}
	
	/**
	    * Closes the current statement
	    * @param ps Statement
	    */
	   public void close(Statement ps) {
	       if (ps!=null){
	           try {
	               ps.close();
	           } catch (SQLException ignore) {
	           }
	       }
	   }

}
