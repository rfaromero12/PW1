package ej2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SpectacleT extends Spectacle{

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
