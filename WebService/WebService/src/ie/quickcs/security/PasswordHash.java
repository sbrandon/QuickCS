package ie.quickcs.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;

public class PasswordHash {

	private final static int ITERATION_NUMBER = 1000;


	//Returns true if users password is correct
	public boolean authenticate(String digest, String salt, String password){
		try{
			byte[] bDigest = base64ToByte(digest);
		    byte[] bSalt = base64ToByte(salt);
		    byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);
		    return Arrays.equals(proposedDigest, bDigest);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	    return false;
	}

	//Takes in a plain text password applies hashing and returns a hash map with salt and password.
	public HashMap<String, String> hashPassword(String password){
		HashMap<String, String> values = new HashMap<String, String>();
		try{ 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	        byte[] bSalt = new byte[8];
	        random.nextBytes(bSalt);
	        byte[] bDigest = getHash(ITERATION_NUMBER,password,bSalt);
	        values.put("password", byteToBase64(bDigest));
	        values.put("salt", byteToBase64(bSalt));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return values;
	}

	//From a password, a number of iterations and a salt, returns the corresponding digest.
	public byte[] getHash(int iterationNb, String password, byte[] salt){
		byte[] input = null;
		try{
	    	MessageDigest digest = MessageDigest.getInstance("SHA-1");
		    digest.reset();
		    digest.update(salt);
		    input = digest.digest(password.getBytes("UTF-8"));
		    for (int i = 0; i < iterationNb; i++) {
		    	digest.reset();
		        input = digest.digest(input);
		    }
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
		return input;
	}

	public static byte[] base64ToByte(String data) throws IOException {
		return Base64.decodeBase64(data);
	}
	
	public static String byteToBase64(byte[] data){
		return Base64.encodeBase64String(data);
	}
	
}
