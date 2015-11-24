package com.wl.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import com.wl.model.SeatHold;
import com.wl.ticketServices.TicketService;
import com.wl.ticketServices.TicketServiceImpl;
/**
 * web service requests  both get and post 
 * pass through this class
 * 
 * @author petro_000
 *
 */

@Path("ticket")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketResource {
	private TicketService ticketService = TicketServiceImpl.getInstance();
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<AvailableSeats> numOfAvailableSeats()
	{
		List<AvailableSeats> ListAvailableSeat = new ArrayList<AvailableSeats>();
		
		ListAvailableSeat.add(new AvailableSeats( ticketService.numSeatsAvailable(null)));
		return ListAvailableSeat;
		
	} 
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{levelID}")
	public Response numOfAvailableSeats(@PathParam("levelID") String levelId)
	{
		if(Integer.parseInt(levelId)>4 || Integer.parseInt(levelId)<1 )
		{
		return Response.status(Status.BAD_REQUEST).build();	
		}
		List<AvailableSeats> listAvailableSeat = new ArrayList<AvailableSeats>();
		Integer ld = new Integer(Integer.parseInt(levelId));
		Optional<Integer> venueLevel = Optional.of(ld);
		listAvailableSeat.add(new AvailableSeats( ticketService.numSeatsAvailable(venueLevel)));
		
	
		if(listAvailableSeat.size()==0)
		{
		return Response.status(Status.NO_CONTENT).build();
		}
		
		return Response.ok().entity(listAvailableSeat).build();
		
		
	}
	
	@POST
	@Path("hold")
	public Response seatHold(SeatHoldRequest seatHoldRequest)
	{
		if(seatHoldRequest.getCustomerEmail()==null || seatHoldRequest.getNumSeats()<=0 )
		{
		return Response.status(Status.BAD_REQUEST).build();	
		}
	 SeatHold sh= ticketService.findAndHoldSeats(seatHoldRequest.getNumSeats(), seatHoldRequest.getMinLevel(), seatHoldRequest.getMaxLevel(), seatHoldRequest.getCustomerEmail());
		
	 return Response.ok().entity(sh).build();
		
		
	}
	
	
	@POST
	@Path("reserve")
	public Response seatReserve(ReserveRequest reserveRequest)
	{
		if(reserveRequest.getCustomerEmail()==null || reserveRequest.getSeatHoldID()<=0 )
		{
		return Response.status(Status.BAD_REQUEST).build();	
		}
		
		String code=ticketService.reserveSeats(reserveRequest.getSeatHoldID(), reserveRequest.getCustomerEmail());
		return Response.ok().entity(code).build();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
