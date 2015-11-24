package com.wl.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * List of seats hold for the user identified by his/her  customeremail
 * the hold id is a unique id generated for each hold request 
 *  */

@XmlRootElement
public class SeatHold {
	private String customeremail;
	private int holdID;
	ArrayList<Seat> bookedSeat = new ArrayList<Seat>();
	static final AtomicInteger NEXT_ID = new AtomicInteger(100);

	public SeatHold(ArrayList<Seat> bookedSeat, String customeremail) {

		this.bookedSeat = bookedSeat;
		this.customeremail = customeremail;
		this.holdID = NEXT_ID.getAndIncrement();
	}
	public SeatHold()
	{
		
	}

	public ArrayList<Seat> getBookedSeat() {
		return bookedSeat;
	}

	public void setBookedSeat(ArrayList<Seat> bookedSeat) {
		this.bookedSeat = bookedSeat;
	}

	public String getCustomeremail() {
		return customeremail;
	}

	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}

	public int getHoldID() {
		return holdID;
	}
	public void setHoldID(int holdID) {
		this.holdID = holdID;
	}

}
