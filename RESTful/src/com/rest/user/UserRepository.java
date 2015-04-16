package com.rest.user;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;



public interface UserRepository {
	
	public void insertUser() throws NoSuchAlgorithmException, InvalidKeySpecException;

}
