package server.utils.auth;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class is used to generate a hash using SHA-1 algorithm of the inputed password and 
 * salt 
 * 
 * @author pavlo
 *
 */
public class Hash {

	/**
	    * From a password, a number of iterations and a salt,
	    * returns the corresponding digest
	    * @param iterationNb int The number of iterations of the algorithm
	    * @param password String The password to encrypt
	    * @param salt byte[] The salt
	    * @return byte[] The digested password
	    * @throws NoSuchAlgorithmException If the algorithm doesn't exist
	 * @throws UnsupportedEncodingException needed for getBytes encoding
	    */
	public byte[] getHash(int iterationNb, String password, byte[] salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for (int i = 0; i < iterationNb; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}

}
