/**
 * 
 */
package tk.ccbluex.LoginPlus.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Marco
 *
 */
public class StringCoder {

	public static String encode(String string) {
		try{
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, getKey());
			return new BASE64Encoder().encode(cipher.doFinal(string.getBytes()));
		}catch(IllegalBlockSizeException e) {
			e.printStackTrace();
		}catch(BadPaddingException e) {
			e.printStackTrace();
		}catch(InvalidKeyException e) {
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch(NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decode(String string) {
		try{
			byte[] crypted = new BASE64Decoder().decodeBuffer(string);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, getKey());
			return new String(cipher.doFinal(crypted));
		}catch(InvalidKeyException e) {
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch(NoSuchPaddingException e) {
			e.printStackTrace();
		}catch(IllegalBlockSizeException e) {
			e.printStackTrace();
		}catch(BadPaddingException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static SecretKeySpec getKey(){
		try{
			String keyStr = "SECURITY";
			
			byte[] key = (keyStr).getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("MD5");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			return secretKeySpec;
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}