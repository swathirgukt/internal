package com.indianeagle.internal.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;
import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * Utility Class for Encryption and Decryption
 * @author kiran.paluvadi
 */
public class CryptoUtil {

private static final Logger log = LogManager.getLogger(CryptoUtil.class);
	
	private static Cipher encryptCipher;
	private static Cipher decryptCipher;
	private static sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	private static sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	private static String MESSAGE_DIGEST = "MD5";
	private static String UTF8 = "UTF8";
	
	static {
		java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());
		char[] pass = "CHANGE THIS TO A BUNCH OF RANDOM CHARACTERS".toCharArray();
		byte[] salt = { (byte) 0xa3, (byte) 0x21, (byte) 0x24, (byte) 0x2c, (byte) 0xf2, (byte) 0xd2, (byte) 0x3e, (byte) 0x19 };
		int iterations = 4;
		init(pass, salt, iterations);
	}
	
	/**
	 * initialize encryptCipher and decryptCipher
	 * @param pass
	 * @param salt
	 * @param iterations
	 */
	public static void init(char[] pass, byte[] salt, int iterations){
		try {
			PBEParameterSpec ps = new PBEParameterSpec(salt, 20);
			SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec(pass));
			encryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, k, ps);
			decryptCipher = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, k, ps);
		} catch (Exception e) {
			log.debug("Could not initialize CryptoLibrary: " + e.getMessage());
		}
	}

	/**
	 * convenience method for encrypting a string.
	 * @param str
	 * @return String the encrypted string.
	 */
	public static String encrypt(String str){		
		try {
			byte[] utf8 = str.getBytes(UTF8);
			byte[] enc = encryptCipher.doFinal(utf8);
			return encoder.encode(enc);
		} catch (Exception e) {
			log.debug("Could not encryp given string" + e.getMessage());
			return null;
		}
	}

	/**
	 * convenience method for encrypting a string.
	 * @param str
	 * @return String the encrypted string.
	 */
	public static String decrypt(String str){
		try {
			byte[] decode = decoder.decodeBuffer(str);
			byte[] decryptStr = decryptCipher.doFinal(decode);
			return new String(decryptStr,UTF8);
		} catch (Exception e) {
			log.debug("Could not decrypt given string" +e.getMessage());
			return null;
		}
	}
	
	/**
	 * convenience method for encrypting a string.
	 * @param plaintext 
	 * @return String the encrypted string.
	 */
	public static String encryptPassWord(String plaintext) {
		try {
			MessageDigest digest = MessageDigest.getInstance(MESSAGE_DIGEST);
			digest.update(plaintext.getBytes(), 0, plaintext.length());
			return  new BigInteger(1, digest.digest()).toString(16);
		} catch (Exception e) {
			log.debug("Fial To Convert As Encrypt Given Password");
			return null;
		}
	}

}
