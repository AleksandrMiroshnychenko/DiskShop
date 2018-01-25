package authorizate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UserDAO;

public class Authorization {
public String pass1, pass2;
public String Login, Password;

	public Authorization(String pass1, String pass2) {
		this.pass1 = pass1;
		this.pass2 = pass2;
	}
	
	public Authorization(){
		
	}
	
	public Boolean comparePasswords(){
		if(pass1.equals(pass2))return true;
		else return false;
	}
	
	public static Boolean comparePasswords(String pass1, String pass2){
		if(pass1.equals(pass2))return true;
		else return false;
	}

	public static String md5Crypt(String st) {
	    MessageDigest messageDigest = null;
	    byte[] digest = new byte[0];
	 
	    try {
	        messageDigest = MessageDigest.getInstance("MD5");
	        messageDigest.reset();
	        messageDigest.update(st.getBytes());
	        digest = messageDigest.digest();
	    } catch (NoSuchAlgorithmException e) {
	    	
	        e.printStackTrace();
	    }
	 
	    BigInteger bigInt = new BigInteger(1, digest);
	    String md5Hex = bigInt.toString(16);
	    while( md5Hex.length() < 32 ){
	        md5Hex = "0" + md5Hex;
	    }
	 
	    return md5Hex;
	}
	
	
}
