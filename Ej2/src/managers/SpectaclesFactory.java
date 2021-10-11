package managers;

import java.util.ArrayList;
import java.util.Date;

import data.SpectacleP;
import data.SpectacleT;
import data.SpectacleM;
import data.Session;

/**
 * The concrete factory that creates diferents types of spectacles
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class SpectaclesFactory extends SpectacleAbstractFactory {
	
	@Override
	public SpectacleP createSpectacleP(int id, String title, String description, String category, int capacity, Session session) {
				
		SpectacleP p = new SpectacleP(id, title, description, category, capacity, session);
	
		return p;
	}
	
	@Override
	public SpectacleM createSpectacleM(int id, String title, String description, String category, int capacity, ArrayList<Session> sessions) {
		
		SpectacleM m = new SpectacleM(id, title, description, category, capacity, sessions);
		
		return m;
	}
	
	@Override
	public SpectacleT createSpectacleT(int id, String title, String description, String category, int capacity, Date start, Date end, String time, int day) {
		
		SpectacleT t = new SpectacleT(id, title, description, category, capacity, start, end, time, day);
		
		return t;
	}
	
}
