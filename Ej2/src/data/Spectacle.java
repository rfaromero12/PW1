package data;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * A class to represent the abstract spectacle.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public abstract class Spectacle {
	
	protected int id_;
	protected String title_;
	protected String description_;
	protected String category_;
	protected int capacity_;
	protected ArrayList<Session> sessions_ = new ArrayList<Session>();
	
	public Spectacle() {
		
	}
	
	/**
	 * Parameterized constructor
	 * @param title Spectacle's title
	 * @param description Spectacle's description
	 * @param category Spectacle's category
	 * @param capacity Spectacle's theatre capacity 
	 */
	public Spectacle(String title, String description, String category, int capacity) {
	
		title_ = title;
		description_ = description;
		category_ = category;
		capacity_ = capacity;
	}
	
	/**
	 * Set the spectacle's title
	 * @param title Spectacle's title
	 */
	public void setTitle(String title) {
		
		title_ = title;
	}
	
	/**
	 * Set the spectacle's description
	 * @param description Spectacle's description
	 */
	public void setDescription(String description) {
		
		description_ = description;
	}
	
	/**
	 * Set the spectacle's category
	 * @param category Spectacle's category
	 */
	public void setCategory(String category) {
		
		category_ = category;
	}

	/**
	 * Set the spectacle's theatre capacity
	 * @param capacity Spectacle's theatre capacity
	 */
	public void setCapacity(int capacity) {
		
		capacity_ = capacity;
	}
	
	/**
	 * Get the spectacle's identifier
	 * @return Spectacle's identifier
	 */
	public int getId() {
		
		return id_;
	}
	
	/**
	 * Get the spectacle's title
	 * @return Spectacle's title
	 */
	public String getTitle() {
		
		return title_;
	}
	
	/**
	 * Get the spectacle's description
	 * @return Spectacle's description
	 */
	public String getDescription() {
		
		return description_;
	}
	
	/**
	 * Get the spectacle's category
	 * @return Spectacle's category
	 */
	public String getCategory() {
		
		return category_;
	}
	
	/**
	 * Get the spectacle's theatre capacity
	 * @return Spectacle's theatre capacity
	 */
	public int getCapacity() {
		return capacity_;
	}
	
	/**
	 * Add a session to the spectacle
	 * @param date Session's date
	 * @param sold Session's sold tickets
	 */
	public void addSession(Date date, int sold) {
		
		Session s = new Session(date, sold);
		sessions_.add(s);
	}
	
	/**
	 * Add a session to the spectacle
	 * @param session The session to add
	 */
	public void addSession(Session session) {
		
		sessions_.add(session);
	}
	
	/**
	 * Get the next spectacles' sessions that have avaiable tickets
	 * @return String with the sessions' info
	 */
	public String getAvaiableSessions() {
		
		String result = "";
		
		Date hoy = new Date(System.currentTimeMillis());
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		for(int i=0; i<sessions_.size(); i++) {
			
			if ( (sessions_.get(i).getSold() < capacity_)  && (sessions_.get(i).getDate().after(hoy)) ) { //
				
				String aux = "\n-Fecha: " + df.format(sessions_.get(i).getDate()) + ". Entradas vendidas: " + sessions_.get(i).getSold() +
							", disponibles: " + (getCapacity() - sessions_.get(i).getSold());
				result =  result.concat(aux);
			}
		}
		
		return result;
	}
	
	/**
	 * Get all the spectacle's sessions
	 * @return String of an spectacle's sessions
	 */
	public String getSessions() {
		
		String result = "";
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		for(int i=0; i<sessions_.size(); i++) {
			
			result = result + "\n" + (i+1) + ". " + df.format(sessions_.get(i).getDate()) + "   Entradas vendidas: " + sessions_.get(i).getSold();
			
		}
		
		return result;
		
	}
	
	/**
	 * Delete the indicate sessions 
	 * @param arraySessions Array with the sessions' number to delete
	 */
	public void deleteSessions(String[] arraySessions) {
		
		int count = 0;
		
		for(int x=0; x<arraySessions.length; x++) {
				
				sessions_.remove((Integer.parseInt(arraySessions[x])-1-count));
				count++;
		}
		
	}
	
	/**
	 * Get the sold tickets for a session
	 * @param date The session's date
	 * @return The sold tickets
	 */
	public int soldTickets(Date date) {
		
		for(int i=0; i<sessions_.size(); i++) {
			
			if(sessions_.get(i).getDate().equals(date)) {
				
				return sessions_.get(i).getSold();
			}
		}
		
		return -1;
		
	}
	
	/**
	 * Get all the spectacle's sessions
	 * @return Array with the spectacle's sessions
	 */
	public ArrayList<Session> sessionsArray(){
		return sessions_;
	}
	
	/**
	 * Set a new date for an existing session
	 * @param session Session's number
	 * @param date Session's new date
	 */
	public void setDate(int session, Date date) {
		
		sessions_.get(session).setDate(date);
	}
	
	/**
	 * Set a new number of sold tickets for an existing session
	 * @param session Session's number
	 * @param sold New ticket's sold for the session
	 */
	public void setSold(int session, int sold) {
		
		sessions_.get(session).setSold(sold);
	}
}
