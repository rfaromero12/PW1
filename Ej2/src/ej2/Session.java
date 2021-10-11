package ej2;

import java.util.Date;

public class Session {
	
	private int sold_;
	private Date date_;
	
	public Session(Date date, int sold) {
		date_ = date;
		sold_ = sold;
	}
	
	public int getSold() {
		return sold_;
	}
	
	public Date getDate() {
		return date_;
	}
}