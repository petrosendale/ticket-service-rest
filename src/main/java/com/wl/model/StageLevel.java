package com.wl.model;


import java.util.ArrayList;
import java.util.List;
/** 
 * Hold and available number methods are in this class
 * each stage level has id, level name , price, 
 * 
 * and a 2d array that represents the seats arrangement 
 * 
 * 
 * */


public class StageLevel {
	private int levelId;
	
	private String levelName;
	private Double price;
	private  boolean[][]seat;

	
	public StageLevel(int levelId, String levelName, Double price, boolean[][] seat) {
		
		this.levelId = levelId;
		this.levelName = levelName;
		this.price = price;
		this.seat = seat;
		
		
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean[][] getSeat() {
		return seat;
	}

	
	
	/**
	 
	
	 * count and returns  the number of false which are not reserved or held 
	 *
	 */
	public synchronized int avialableSeats()
	{
		int remainingSeat=0;
		for(int i=0; i<= seat.length-1; i++)
		{
			for(int j=0; j<=seat[i].length-1; j++)
				if(seat[i][j]==false)
				{
					remainingSeat++;	
				}
		}
		
		return remainingSeat;
		
	}
	
	/**  takes a numeric values requested number of Seats requested as a parameter and returns and a List of seats reserved
	 * 
	 * @param numofSeats requested number of seats to be held
	 
	 *  
	 *  */
	
	
	public synchronized ArrayList<Seat> hold(int numofSeats)
	{
		int holdSeat= numofSeats;
		ArrayList<Seat> hold= new ArrayList<Seat>();
		for(int i=0; i<= seat.length-1; i++)
		{
			for(int j=0; j<=seat[i].length-1; j++)
				if(seat[i][j]==false)
				{
					
					//create a seat object , put into hold list 
					
					seat[i][j]=true;
					Seat s= new Seat(this.levelName, i, j);
					
					hold.add(s);
					
					//initally hold list size was the number of requested decrement at each added hold
					holdSeat--;
					//if our requested number if seats satisfied break the loop					
					if(holdSeat==0)
						break;
									
				}
			//if our number of requested seat satisfied break 
			if(holdSeat==0)
				break;
		}
		
		return hold;
		
	}

	
	
	

}
