package com.wl.resource.RESTclient;



import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.wl.resource.AvailableSeats;


public class RESTTicketClient {
	private Client client;
	
	public RESTTicketClient()
	{
		client= ClientBuilder.newClient();
	}
	
	
	public List<AvailableSeats> get(String id)
	{
		
		WebTarget target = client.target("http://localhost:8080//webapi/");
		List<AvailableSeats> response=null;
		if(Integer.parseInt(id)<5) //temporary solution 
		{
		 response=  target.path("ticket/" +id).request().get(new GenericType<List<AvailableSeats>>(){});
		}
		
		 return response;
		
	}
	public List<AvailableSeats> getResponse(String id)
	{
		
		WebTarget target = client.target("http://localhost:8090/webapi/");
		 Response response=  target.path("ticket/" +id).request().get(Response.class);
		if(response.getStatus()!=200)
		{
			throw new RuntimeException(response.getStatus() + ": there was an Error");
		}
		 return response.readEntity(new GenericType<List<AvailableSeats>>(){});
		
	}

}
