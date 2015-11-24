/**
 * 
 */
package com.wl.resource;

/**
 * @author petro_000
 * this class is to bind model and validation 
 * post parameters for reserve are binded and validated using this class object
 */
public class ReserveRequest {
	private int seatHoldID;
	private String customerEmail;
	
	public ReserveRequest()
	{
		
	}
	
	public int getSeatHoldID() {
		return seatHoldID;
	}
	public void setSeatHoldID(int seatHoldID) {
		this.seatHoldID = seatHoldID;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}
