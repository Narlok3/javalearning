package com.othello.model.database;

import java.net.Authenticator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

public class PasswordHashingDemo {

	Map<String, String> DB = new HashMap<String, String>();
	public static final String SALT = "my-salt-text"; // should be random

	public static void main(String args[]) {
		PasswordHashingDemo demo = new PasswordHashingDemo();
		demo.signup("john", "dummy123");

		// login should succeed.
		if (demo.login("john", "dummy123"))
			System.out.println("user login successfull.");

		// login should fail because of wrong password.
		if (demo.login("john", "blahblah"))
			System.out.println("User login successfull.");
		else
			System.out.println("user login failed.");
	}

	public void signup(String username, String password) {
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		DB.put(username, hashedPassword);
	}

	public Boolean login(String username, String password) {
		Boolean isAuthenticated = false;

		// remember to use the same SALT value use used while storing password
		// for the first time.
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);

		String storedPasswordHash = DB.get(username);
		if (hashedPassword.equals(storedPasswordHash)) {
			isAuthenticated = true;
		} else {
			isAuthenticated = false;
		}
		return isAuthenticated;
	}

	// better way would be jbcrypt
	// algorithms should use a work factor
	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}
		System.out.println(hash.toString());
		return hash.toString();
	}

}