package ej2;

import java.util.ArrayList;
import java.util.Date;

public class SpectaclesFactory extends SpectacleAbstractFactory {
	
	@Override
	public SpectacleP createSpectacleP(String title, String description, String category, int capacity, Session session) {
				
		SpectacleP p = new SpectacleP(title, description, category, capacity, session);
	
		return p;
	}
	
	@Override
	public SpectacleM createSpectacleM(String title, String description, String category, int capacity, ArrayList<Session> sessions) {
		
		SpectacleM m = new SpectacleM(title, description, category, capacity, sessions);
		
		return m;
	}
	
	@Override
	public SpectacleT createSpectacleT(String title, String description, String category, int capacity, Date begin, Date end, int day) {
		
		SpectacleT t = new SpectacleT();
		
		return t;
	}
	
}
