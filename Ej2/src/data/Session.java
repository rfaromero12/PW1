package data;

import java.util.Date;

/**
 * A class to represent an spectacle session
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class Session {
	
	private int sold_;
	private Date date_;
	
	/**
	 * Parameterized constructor
	 * @param date Session's date
	 * @param sold Session's sold tickets
	 */
	public Session(Date date, int sold) {
		date_ = date;
		sold_ = sold;
	}
	
	/**
	 * Get the session's sold tickets
	 * @return Session's sold tickets
	 */
	public int getSold() {
		return sold_;
	}
	
	/**
	 * Get the session's date
	 * @return Session's date
	 */
	public Date getDate() {
		return date_;
	}
	
	/**
	 * Set the session's date
	 * @param date Session's date
	 */
	public void setDate(Date date) {
		
		date_ = date;
	}
	
	/**
	 * Set the session's sold tickets
	 * @param sold Session's sold tickets
	 */
	public void setSold(int sold) {
		
		sold_ = sold;
	}
}