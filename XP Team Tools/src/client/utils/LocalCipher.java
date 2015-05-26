package client.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import server.utils.auth.BaseToByteViceversa;

/**
 * A class responsible for encrypting and decrypting via Password Base
 * Encryption by single-DES algorithm and provides a MD5 Checksum. A randomly
 * generated master password declared statically in-code is used to encrypt the
 * passwords to be inputed in encrypt() and decrypt(). TODO: generate on-time
 * master password thru physical entropy generator (i.e. keyboard, mouse or
 * current date) and implement AES encryption insted of DES.
 * 
 * @author pavlo
 *
 */

public class LocalCipher {

	private static final char[] MASTER_PWD = "9VknsDyFpaq7fHZN".toCharArray();
	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10,
			(byte) 0x12, (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, };

	public static String encrypt(String input) throws GeneralSecurityException,
			UnsupportedEncodingException {
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(MASTER_PWD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		pbeCipher
				.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return BaseToByteViceversa.byteToBase64(pbeCipher.doFinal(input
				.getBytes("UTF-8")));
	}

	public static String decrypt(String input) throws GeneralSecurityException,
			IOException {
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(MASTER_PWD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		pbeCipher
				.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return new String(pbeCipher.doFinal(BaseToByteViceversa
				.base64ToByte(input)), "UTF-8");
	}

}
