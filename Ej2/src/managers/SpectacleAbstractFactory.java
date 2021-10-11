package managers;

import java.util.ArrayList;
import java.util.Date;

import data.SpectacleM;
import data.SpectacleP;
import data.SpectacleT;
import data.Session;

/**
 * The abstract factory that creates spectacles.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public abstract class SpectacleAbstractFactory {
	
	public abstract SpectacleP createSpectacleP(int id, String title, String description, String category, int capacity, Session session);
	
	public abstract SpectacleM createSpectacleM(int id, String title, String description, String category, int capacity, ArrayList<Session> sessions);
	
	public abstract SpectacleT createSpectacleT(int id, String title, String description, String category, int capacity, Date start, Date end, String time, int day);

}
