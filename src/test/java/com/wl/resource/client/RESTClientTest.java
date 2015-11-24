package com.wl.resource.client;

//not used in Maven build process Test

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


import com.wl.resource.AvailableSeats;
import com.wl.resource.RESTclient.RESTTicketClient;

public class RESTClientTest {

	//@Test
	public void testGet() {
		RESTTicketClient client = new RESTTicketClient();
		
		List<AvailableSeats> avilableSeat = client.get("2");
		
		assertNotNull(avilableSeat);
	}
	//@Test
	public void testGetwithInvalidId() {
		RESTTicketClient client = new RESTTicketClient();
		
		List<AvailableSeats> avilableSeat = client.get("24");
		
		assertNull(avilableSeat);
	}
	
	
	//@Test(expected=RuntimeException.class)
	public void testBadRequest()
	{
		RESTTicketClient client= new RESTTicketClient();
		
		
		client.getResponse("2");
		
		
		
	}
	
	
	

}
