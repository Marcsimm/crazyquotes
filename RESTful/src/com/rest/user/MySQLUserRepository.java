package com.rest.user;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rest.database.Database;
import com.rest.passwordhash.PasswordHash;


public class MySQLUserRepository implements UserRepository  {

	@Override
	public void insertUser() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
		User user = new User();
		PasswordHash pass = new PasswordHash();
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			user.setUsername("Jimmy");
			user.setPasswd("eshan");
			user.setSalt("123");
			
			String password = pass.createHash(user.getPasswd());
			String query = "INSERT INTO user VALUES (null, ?, ?, ?);";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, password);
			pstmt.setString(3, user.getSalt());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}

	

	
}
