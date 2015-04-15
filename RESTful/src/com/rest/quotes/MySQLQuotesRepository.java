package com.rest.quotes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rest.database.Database;

public class MySQLQuotesRepository  implements QuotesRepository{

private List<Quote> quotes;
	
	public MySQLQuotesRepository() {
		quotes = new ArrayList<>();
	}
	
	@Override
	public List<Quote> getAllActors() {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    quotes.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM quote;";
		
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Quote q = new Quote();
				q.setText(rs.getString("text"));
				q.setAuthor(rs.getString("author"));
				q.setYear(rs.getInt("year"));
				q.setUserid(rs.getInt("userid"));
				
			
				quotes.add(q);
				

				
				for (int i = 0; i < quotes.size(); i++) {
					System.out.println(quotes.get(i).getAuthor());
				}
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	    
	    return quotes;
	}

	@Override
	public void insertQuote(Quote quote) {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO users VALUES (null, ?, ?, ?);";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, quote.getText());
			pstmt.setString(2, quote.getAuthor());
			pstmt.setInt(3, quote.getYear());
			
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
