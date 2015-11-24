/**
 * 
 */
package com.wl.resource.client;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import com.wl.ticketServices.TicketServiceImpl;

/**
 * @author petro_000
 *
 */

public class AnumberOfAvailableSeatTest {
	
	TicketServiceImpl ticketService = TicketServiceImpl.getInstance();

	@Test
	public void numSeatsAvailable() {
		
		assertTrue(this.ticketService.numSeatsAvailable(null)>=0);
	}
	
	

	@Test
	public void numSeatsAvailableAfterHold() {
		
		int initialAvailable= ticketService.numSeatsAvailable(null);
		ticketService.findAndHoldSeats(120, null, null, "johndoe@john.test");
		
		assertEquals(this.ticketService.numSeatsAvailable(null), initialAvailable-120);
	}
	
	
	
	
	//Test the whole available seat is the same as the sum of all the 4
	@Test
	public void theWholeisTheSum()
	{
		Integer input1 = new Integer(1);
		Integer input2 = new Integer(2);
		Integer input3 = new Integer(3);
		Integer input4 = new Integer(4);
		
		Optional<Integer> optionalInput1 = Optional.of(input1);
		Optional<Integer> optionalInput2 = Optional.of(input2);
		Optional<Integer> optionalInput3 = Optional.of(input3);
		Optional<Integer> optionalInput4 = Optional.of(input4);
		
		
		assertEquals(ticketService.numSeatsAvailable(null), 
				ticketService.numSeatsAvailable(optionalInput1)+ticketService.numSeatsAvailable(optionalInput2)+ticketService.numSeatsAvailable(optionalInput3)+
				ticketService.numSeatsAvailable(optionalInput4));
		
		
			}
	
	//request with string
	@Test(expected=IllegalArgumentException.class)
	public void numSeatAvailablewithilegArgs()
	{
		Integer input = new Integer(24);
		Optional<Integer> optionalInput = Optional.of(input);
		ticketService.numSeatsAvailable(optionalInput);
	}
	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void numSeatAvailablewithnegative()
	{
		Integer input = new Integer(-1);
		Optional<Integer> optionalInput = Optional.of(input);
		ticketService.numSeatsAvailable(optionalInput);
	}

}
