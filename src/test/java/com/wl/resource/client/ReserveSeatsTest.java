package com.wl.resource.client;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.wl.model.SeatHold;
import com.wl.ticketServices.TicketService;
import com.wl.ticketServices.TicketServiceImpl;

/**
* Test the reserve methods
* at TicketServiceImpl 
* @author petro_000
*
*/
public class ReserveSeatsTest {
	private TicketService ticketService = TicketServiceImpl.getInstance();

	

	@Test(expected=IllegalArgumentException.class)
	//reserve with wrong email
	public void ReservewithoutHoldEmail() {
		
		ticketService.reserveSeats(10, "john@dogie.com");
		
	}
	@Test(expected=IllegalArgumentException.class)
      public void ReservewithoutHoldId() {
		
		ticketService.reserveSeats(10, "john@dogie.com");
			
	}
	
    @Test
    public void holdthenReserve() {
    	
    	
    	 SeatHold sh = ticketService.findAndHoldSeats(100, null, null, "myname@john.doe");
    	
		//returned Generated Code
		assertTrue(ticketService.reserveSeats(sh.getHoldID(), sh.getCustomeremail()) instanceof String);
			
	}

}
