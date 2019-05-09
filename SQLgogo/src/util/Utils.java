package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Utils {
	public static String md5(String message) {
		try {
			MessageDigest md;

			md = MessageDigest.getInstance("md5");
			byte m5[] = md.digest(message.getBytes());

			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(m5);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

}
