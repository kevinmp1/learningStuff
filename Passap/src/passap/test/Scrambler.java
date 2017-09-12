package passap.test;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Scrambler {
	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();
	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'a', '3', 'B', 'B', 'q', '9', '5', 'M', '1', 'X', 'g','5', 'L', '6', 'e', 'T' };

	public static String encrypt(String Data) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encVal = c.doFinal(Data.getBytes());
	        String encryptedValue = encoder.encodeToString(encVal);
	        return encryptedValue;
	    }

	    public static String decrypt(String encryptedData) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.DECRYPT_MODE, key);
	        byte[] decordedValue = decoder.decode(encryptedData);
	        byte[] decValue = c.doFinal(decordedValue);
	        String decryptedValue = new String(decValue);
	        return decryptedValue;
	    }
	    private static Key generateKey() throws Exception {
	        Key key = new SecretKeySpec(keyValue, ALGO);
	        return key;
	}
}
