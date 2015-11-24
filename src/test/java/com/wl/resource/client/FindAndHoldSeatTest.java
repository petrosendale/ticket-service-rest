package com.wl.resource.client;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import com.wl.model.SeatHold;
import com.wl.ticketServices.TicketService;
import com.wl.ticketServices.TicketServiceImpl;

/**
* Test FindAndHoldSeatTest
* at TicketServiceImpl 
* @author petro_000
*/

public class FindAndHoldSeatTest {

	private TicketService ticketService = TicketServiceImpl.getInstance();

	private Optional<Integer> minLevel;

	private Optional<Integer> maxLevel;

	@Test
	public void Successtest() {
		this.minLevel = Optional.of(new Integer(1));
		this.maxLevel = Optional.of(new Integer(4));
          
		assertTrue (ticketService.findAndHoldSeats(10, minLevel, maxLevel, "johndoe@john.doe") instanceof SeatHold);
	}

	// test for invalid email input, should throw ilegal argument exception
	@Test(expected = IllegalArgumentException.class)
	public void invalidEmailTest() {

		ticketService.findAndHoldSeats(10, minLevel, maxLevel, "johndoejohn.doe");

	}

	// test for invalid  input, should throw illegal argument exception
	@Test(expected = IllegalArgumentException.class)
	public void invalidInputTest() {
		this.minLevel = Optional.of(new Integer(1));
		this.maxLevel = Optional.of(new Integer(24));
		ticketService.findAndHoldSeats(10, minLevel, maxLevel, "johndoejohn.doe");

	}

	// test venueLevel reversed...max is min, min is max
	@Test(expected = IllegalArgumentException.class)
	public void holdInReversedVenueLevelTest() {
		this.minLevel = Optional.of(new Integer(4));
		this.maxLevel = Optional.of(new Integer(1));
		// try to hold 2 times more than available
		int beforeHold = ticketService.numSeatsAvailable(null);
		SeatHold sh = ticketService.findAndHoldSeats(2 * ticketService.numSeatsAvailable(null), this.minLevel,
				this.maxLevel, "johndoe@john.doe");
		assertEquals(beforeHold, sh.getBookedSeat().size());

	}

	// test try to hold more than available
	@Test
	public void holdMorethanAvailableTest() {
	

		// try to hold 2 times more than available
		int avaialbleBeforHold=ticketService.numSeatsAvailable(null);
		SeatHold sh = ticketService.findAndHoldSeats(2 * ticketService.numSeatsAvailable(null), this.minLevel,
				this.maxLevel, "johndoe@john.doe");
		assertEquals(avaialbleBeforHold, sh.getBookedSeat().size());

	}

	// Test number of available then hold seat and the compare number of
	// available
	@Test
	public void checkHoldCheckTest() {
		this.minLevel = Optional.of(new Integer(1));
		this.maxLevel = Optional.of(new Integer(4));
		int beforeHold = ticketService.numSeatsAvailable(null);
		SeatHold sh = ticketService.findAndHoldSeats(100, this.minLevel, this.maxLevel, "johndoe@john.doe");
		assertEquals(beforeHold - sh.getBookedSeat().size(), ticketService.numSeatsAvailable(null));

	}
	
	
	//Test two client's produce two separate row numbers'
	

}
