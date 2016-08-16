package com.smartSchoolService.login;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public final class RandomPasswordGenerator {
	private SecureRandom random = new SecureRandom();

  public String generateNewPassword() {
    return new BigInteger(130, random).toString(32);
  }
  
  public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	  SecureRandom random = new SecureRandom();
	  String pwd=new BigInteger(130, random).toString(32);
	  System.out.println(pwd);
	  String hashedPassword=SmartSchoolHash.customHashing(pwd);
	  System.out.println(hashedPassword);
	  String s1="Srikanth";
	  String s2 = null;
	  String s3="Ande";
	  System.out.println(s1+s2+s3);
  }
}