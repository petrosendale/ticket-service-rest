package com.wl.resource;

import java.util.Optional;
/**
 * @author petro_000
 * this class is to bind model and validation 
 * post parameters for hold are binded and validated using the object of this class
 */

public class SeatHoldRequest {
	
	private int numSeats;
	private Optional<Integer>minLevel;
	private Optional<Integer>maxLevel;
	private String customerEmail;
	
	public SeatHoldRequest(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
		super();
		this.numSeats = numSeats;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.customerEmail = customerEmail;
	}
	
	public SeatHoldRequest()
	{
		
	}
	
	
	
	public int getNumSeats() {
		return numSeats;
	}
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}
	public Optional<Integer> getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(Optional<Integer> minLevel) {
		this.minLevel = minLevel;
	}
	public Optional<Integer> getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(Optional<Integer> maxLevel) {
		this.maxLevel = maxLevel;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}
