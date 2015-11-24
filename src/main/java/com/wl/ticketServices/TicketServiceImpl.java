package com.wl.ticketServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wl.model.Seat;
import com.wl.model.SeatHold;
import com.wl.model.StageLevel;

/** 
 * implemented the Ticket Service interface
 * 
 * USed Singllton design so that the same object used for multiple web api requests 
 * @param ticketServiceIml instance of the class
 * @param orchestra  and othes such as main, balconey1, balconey2 Venue Level objects 
 * @param stageLevelList for in memory data usage, in consecutive methods
 * 
 * 
 */

public class TicketServiceImpl implements TicketService {
	
	private static TicketServiceImpl ticketServiceImpl= null;
	private StageLevel orchestra;
	private StageLevel main;
	private StageLevel balconey1;
	private StageLevel balconey2;
	private List<StageLevel> stageLevelList = new ArrayList<StageLevel>();
	private ArrayList<SeatHold> listOfseatHold = new ArrayList<SeatHold>();
	
	/**

	* create instance if it's null  
	* @return return the instance

	*/
	public static TicketServiceImpl getInstance()
	{
		//lazy loading
		if(ticketServiceImpl==null)
		{
			ticketServiceImpl=new TicketServiceImpl();
		}
		return ticketServiceImpl ;
	}
	
	
	
	
	
	/**
	 *  initialize the 4 venuLevels, named stageLevel 
	 *  boolean 2D arrays of seats that represent rows and seats in rows 
	 *  initially all the values of the arrays are set to false, when held will be set to true
	 *   level name
	* create instance if it's null  



	*/

	/**
	 * 
	 */
	private TicketServiceImpl() {
		

		boolean orchestraSeats[][] = new boolean[25][50];
		int levelID = 1;
		double price = 100.00;
		String name = "Orchestra";
		// Set all seats to false , which means all are avilable initially
		java.util.Arrays.fill(orchestraSeats[0], false);
		java.util.Arrays.fill(orchestraSeats[1], false);
		this.orchestra = new StageLevel(levelID, name, price, orchestraSeats);

		boolean mainSeats[][] = new boolean[20][100];
		levelID = 2;
		price = 75.00;
		name = "Main";
		java.util.Arrays.fill(mainSeats[0], false);
		java.util.Arrays.fill(mainSeats[1], false);
		this.main = new StageLevel(levelID, name, price, mainSeats);

		levelID = 3;
		price = 50.00;
		name = "Balconey1";
		boolean balconey1Seats[][] = new boolean[15][100];
		java.util.Arrays.fill(balconey1Seats[0], false);
		java.util.Arrays.fill(balconey1Seats[1], false);
		this.balconey1 = new StageLevel(levelID, name, price, balconey1Seats);

		levelID = 4;
		price = 25.00;
		name = "Balconey2";
		boolean balconey2Seats[][] = new boolean[15][100];
		java.util.Arrays.fill(balconey2Seats[0], false);
		java.util.Arrays.fill(balconey2Seats[1], false);
		this.balconey2 = new StageLevel(levelID, name, price, balconey2Seats);

		// since we are not persisiting our data , we need to hold the list of
		// venue levels to access them in
		// other methods

		this.stageLevelList.add(this.orchestra);
		this.stageLevelList.add(this.main);
		this.stageLevelList.add(this.balconey1);
		this.stageLevelList.add(this.balconey2);

	}

	/**

	* The number of seats in the requested level that are neither held nor reserved

	* 

	* @param venueLevel a numeric venue level identifier to limit the search

	* @return numberofAvailableSeats, the number of tickets available on the provided level or 0 if there is no
	* available Seats 
	* 	 if the user hasn't specified the venueLevel the method returns all available seats

	*/
	
	
	@Override
	public int numSeatsAvailable(Optional<Integer> venueLevel) throws IllegalArgumentException  {

		int numberofAvailableSeats=0;
		if (venueLevel == null) {

			numberofAvailableSeats= ((orchestra.avialableSeats()) + (main.avialableSeats()) + (balconey1.avialableSeats())
					+ (balconey2.avialableSeats()));
		}

		// if the requested level is within a range of Level numbers
		// return the available seats of the requested level
		else if (venueLevel.get() > 0 && venueLevel.get() <= stageLevelList.size()) {
			for (StageLevel sl : stageLevelList) {
				if (venueLevel.get() == sl.getLevelId())
					numberofAvailableSeats= sl.avialableSeats();

			}
		}
		// the caller web client or console application should handle the Exception
		// handling
		else {
			throw new IllegalArgumentException(
					"Invalid level ID, Level ID should be between 1 and " + stageLevelList.size());
		}
	 return numberofAvailableSeats;

	}
	/**
	 * override the interface level method findAndHoldSeats

	* best seat is considered the the lower level, orchestra and go up to Main, Balconey

	* 

	* @param numSeats the number of seats to find and hold

	* @param minLevel the minimum venue level 

	* @param maxLevel the maximum venue level 

	* @param customerEmail unique identifier for the customer

	* @return a SeatHold object identifying the specific seats and related

	information 

	*/
	
	

	@Override
	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel,
			String customerEmail) throws IllegalArgumentException  {
		
		
		if(!isEmailValid(customerEmail))
				{
			throw new IllegalArgumentException("Invalid email Adress "  );	
				}
	
		
		int numberofBookedSeats = 0;
		ArrayList<Seat> listOfbookedSeats = new ArrayList<Seat>();
		
		
		
//if the user hasn't provided , level preference, start from the best level, and book all requested
//Assumption is orchestra is the best level seat
		
		if (minLevel == null && maxLevel == null)

		{
    		for (StageLevel sl : stageLevelList) {
					//call hold methods of each level with Number of requested seats minus number of 
					//booked so far the current request 
					listOfbookedSeats.addAll(sl.hold(numSeats - numberofBookedSeats));
					numberofBookedSeats = listOfbookedSeats.size();
					//if  requested is fulfilled stop iterating to the next VenueLevel
					if (numberofBookedSeats == numSeats)
						break;
				}
			
		
		} else if (minLevel == null || maxLevel == null) {
			//assign the user given venueLevel to the variable, iterate through the list of Levels 
			
			int userGivenLevelid = (maxLevel == null) ? minLevel.get() : maxLevel.get();
			
			if (isUserGivenLevelIdOutofrange(userGivenLevelid))
			{
				throw new IllegalArgumentException("Invalid level ID, Level ID should be between 1 and " + stageLevelList.size() );	
				
			}
				for (StageLevel sl : stageLevelList) {

				if (sl.getLevelId() <= userGivenLevelid && userGivenLevelid>0)
				{//if user Given Level Id is between 1 and stageLee level number max
					//call hold method of each stageLevel with requested number of seats minus already booked
					//add the list returned by the hold method of each level to the list of booked and iterate if the request is fuffiled break
					listOfbookedSeats.addAll(sl.hold(numSeats - numberofBookedSeats));
					numberofBookedSeats = listOfbookedSeats.size();
					if (numberofBookedSeats == numSeats)
						break;
				}
				//if the requested level is out of range of available stage level
				
					
			}
				
			
			
			
			
		}
		// both max and min venuelevel are provided by the user
		else {
			if (isUserGivenLevelIdOutofrange(minLevel.get()) || isUserGivenLevelIdOutofrange(maxLevel.get()) || minLevel.get()>maxLevel.get())
					{
				throw new IllegalArgumentException("Invalid level ID, Level ID should be between and 1 " + stageLevelList.size() );
					}
			
			
				for (StageLevel sl : stageLevelList) {
					if (sl.getLevelId() == minLevel.get() || sl.getLevelId() <= maxLevel.get()) 
					{
						//add the List from each level hold into the list of booked seats 
						listOfbookedSeats.addAll(sl.hold(numSeats - numberofBookedSeats));
					numberofBookedSeats = listOfbookedSeats.size();
				
				//if number of goal is reached or all seats are booked break
					if (numberofBookedSeats == numSeats || this.numSeatsAvailable(null)==0)
						break;
				}
				}
			}
		//}
		//create seathold object using list of booked seats and customer mail

		SeatHold seathold = new SeatHold(listOfbookedSeats, customerEmail);
		

		this.listOfseatHold.add(seathold);

		return seathold;
	}

	
	/**

	* Commit seats held for a specific customer

	* 

	* @param seatHoldId the seat hold identifier

	* @param customerEmail the email address of the customer to which the seat hold

	is assigned

	* @return a reservation confirmation code 

	*/ 
	

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) throws IllegalArgumentException {
		
		//Generate Code by appending email address with a hashcode
		StringBuilder userCode = new StringBuilder(customerEmail);

		for (SeatHold seatInHold : listOfseatHold) {
			
			if (seatInHold.getHoldID() == seatHoldId) 
			{
				
				if (seatInHold.getCustomeremail() == customerEmail) {
					return(userCode.append(generateCode()).toString());
				}
				throw new IllegalArgumentException("No hold in this email ID " );

			}
			
		}
		throw new IllegalArgumentException("You have no held seatin  HoldID " );
		
		
	}

	private int generateCode() {
		return this.hashCode();
	}
	/**
	 * check if the email provided is valid 

	* @param customerEmail string from the caller the customer

	* @return a boolean whether the String provided is valid email or not

	information 

	*/
	
	
	private boolean isEmailValid(String customerEmail) {
		//pattern copied from stackoverflow user
		final String emailPattern = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(customerEmail);
		return matcher.matches();
		
		
	}
	
	/**
	 * validate if the user given minLevel and maxLevel are out of range

	* @param userGivenLevelid a mumber ID provided by the user

	* @return a boolean whether number provided is out of range or not

	information 

	*/
	private boolean isUserGivenLevelIdOutofrange(int userGivenLevelid) {
		
		for (StageLevel sl : stageLevelList)
		{
		if(sl.getLevelId()==userGivenLevelid)
		{
			return false;
		}
		}
		
		return true;
		
		
	}

	

}
