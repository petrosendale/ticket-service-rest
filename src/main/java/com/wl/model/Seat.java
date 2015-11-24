package com.wl.model;

import javax.xml.bind.annotation.XmlRootElement;
/**
 *  The seat class is a a template to hold seats detail once the seat is hold
 * 
 * 
 *  */

@XmlRootElement
public class Seat {

	private String levelName;
	private int rows;
	private int seatsInRow;

	public Seat(String name, int rows, int seatsInRow) {

		this.levelName = name;
		this.setRows(rows);
		this.setSeatsInRow(seatsInRow);

	}
	//default constructor for Jersey marshaling 
	public Seat()
	{
	
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSeatsInRow() {
		return seatsInRow;
	}

	public void setSeatsInRow(int seatsInRow) {
		this.seatsInRow = seatsInRow;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
