package ej2;

import java.util.ArrayList;
import java.util.Date;

public abstract class SpectacleAbstractFactory {
	
	public abstract SpectacleP createSpectacleP(String title, String description, String category, int capacity, Session session);
	
	public abstract SpectacleM createSpectacleM(String title, String description, String category, int capacity, ArrayList<Session> sessions);
	
	public abstract SpectacleT createSpectacleT(String title, String description, String category, int capacity, Date begin, Date end, int day);

}
