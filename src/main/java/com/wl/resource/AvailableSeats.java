package com.wl.resource;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Created because jersey doesnt marshal primitive values values 
//the purpose of this class is to wrap the integer value of available seats 
 * 
 */
//

@XmlRootElement
public class AvailableSeats {
	private int availableSeats;
	
	public AvailableSeats(int numofSeats)
	{
		this.availableSeats=numofSeats;
	}
	public AvailableSeats()
	{
		
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	

}
