package com.rest.crazyquote;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.rest.quotes.MySQLQuoteRepository;
import com.rest.user.MySQLUserRepository;


@Path("/hello")
public class Crazyquotes {
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) throws NoSuchAlgorithmException, InvalidKeySpecException {
 
		String output = "Jersey say : " + msg;
		
		MySQLQuoteRepository m = new MySQLQuoteRepository();
		MySQLUserRepository u = new MySQLUserRepository();
		
		u.insertUser();
		m.insertQuote();  
		
		m.getAllActors();
		
		 
		
	
		return Response.status(200).entity(output).build();
 
	}
	
	

}


