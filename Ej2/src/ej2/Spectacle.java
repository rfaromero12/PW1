package ej2;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public abstract class Spectacle {
	
	protected int id_;
	protected String title_;
	protected String description_;
	protected String category_;
	protected int capacity_;
	protected ArrayList<Session> sessions_ = new ArrayList<Session>();
	
	public Spectacle() {
		
	}
	
	public Spectacle(String title, String description, String category, int capacity) {
	
		title_ = title;
		description_ = description;
		category_ = category;
		capacity_ = capacity;
	}
	
	public void setTitle(String title) {
		
		title_ = title;
	}
	
	public void setDescription(String description) {
		
		description_ = description;
	}
	
	public void setCategory(String category) {
		
		category_ = category;
	}

	public void setCapacity(int capacity) {
		
		capacity_ = capacity;
	}
	
	public int getId() {
		
		return id_;
	}
	
	public String getTitle() {
		
		return title_;
	}
	
	public String getDescription() {
		
		return description_;
	}
	
	public String getCategory() {
		
		return category_;
	}
	
	public int getCapacity() {
		return capacity_;
	}
	
	public void addSession(Date date, int sold) {
		
		Session s = new Session(date, sold);
		sessions_.add(s);
	}
	
	public void addSession(Session session) {
		
		sessions_.add(session);
	}
	
	//Devuelve las sesiones de un espectáculo que tienen entradas disponibles y (son posteriores a la fecha actual)
	public String getAvaiableSessions() {
		
		String result = "";
		
		Date hoy = new Date(System.currentTimeMillis());
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		for(int i=0; i<sessions_.size(); i++) {
			
			if ( (sessions_.get(i).getSold() < capacity_)  && (sessions_.get(i).getDate().before(hoy)) ) { //
				
				String aux = "\n-Fecha: " + df.format(sessions_.get(i).getDate()) + ". Entradas vendidas: " + sessions_.get(i).getSold() +
							", disponibles: " + (getCapacity() - sessions_.get(i).getSold());
				result =  result.concat(aux);
			}
		}
		
		return result;
	}
	
	//Devuelve todas las sesiones de un espectáculo
	public String getSessions() {
		
		String result = "";
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		for(int i=0; i<sessions_.size(); i++) {
			
			result = result + "\n" + (i+1) + ". " + df.format(sessions_.get(i).getDate());
			
		}
		
		return result;
		
	}
	
	//Elimina las sesiones que le llegan por array
	public void deleteSessions(String[] arraySessions) {
		
		int count = 0;
		
		for(int x=0; x<arraySessions.length; x++) {
				
				sessions_.remove((Integer.parseInt(arraySessions[x])-1-count));
				count++;
		}
		
	}
	
	//Devuelve las entradas vendidas para una sesión dada su fecha
	public int soldTickets(Date date) {
		
		for(int i=0; i<sessions_.size(); i++) {
			
			if(sessions_.get(i).getDate().equals(date)) {
				
				return sessions_.get(i).getSold();
			}
		}
		
		return -1;
		
	}
	
	public ArrayList<Session> sessionsArray(){
		return sessions_;
	}
}
