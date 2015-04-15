package com.rest.crazyquote;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.rest.quotes.MySQLQuotesRepository;

@Path("/hello")
public class Crazyquotes {
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
		
		MySQLQuotesRepository m = new MySQLQuotesRepository();
		
		m.getAllActors();
	
		return Response.status(200).entity(output).build();
 
	}
	
	

}


