package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A subclass that inherits from Spectacle.It represent an spectacle performed periodically.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class SpectacleT extends Spectacle{

	/**
	 * Parameterized constructor
	 * @param id Spectacle's identifier
	 * @param title Spectacle's title
	 * @param description Spectacle's description
	 * @param category Spectacle's category
	 * @param capacity Spectacle's theatre capacity
	 * @param start Spectacle's sessions period starts
	 * @param end Spectacle's sessions period ends
	 * @param time Sessions' hour
	 * @param day Sessions' day of the week
	 * */
	public SpectacleT(int id, String title, String description, String category, int capacity, Date start, Date end, String time, int day){
		
		super(title, description, category, capacity);
		id_ = id;
		 
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        
        Calendar c = Calendar.getInstance();

        do {
        	        	
        	try {
        		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        		Date fecha = sdf2.parse(sdf.format(tempStart.getTime()) + " " + time);
        		
            	c.setTime(fecha);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                
                if(dayOfWeek == day+1) {
                	
                	Session s = new Session(fecha, 0);
                	sessions_.add(s);
                }

        	}
        	catch(Exception e) {}
            
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            
        } while (tempStart.before(tempEnd) || tempStart.equals(tempEnd));
		
	}
	
}
