package data;

import java.util.ArrayList;

/**
 * A subclass that inherits from Spectacle.It represent a multiple spectacle (it has multiple sessions)
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class SpectacleM extends Spectacle{
	
	/**
	 * Parameterized constructor
	 * @param id Spectacle's identifier
	 * @param title Spectacle's title
	 * @param description Spectacle's description
	 * @param category Spectacle's category
	 * @param capacity Spectacle's theatre capacity
	 * @param sessions Spectacle's sessions
	 * */
	public SpectacleM(int id, String title, String description, String category, int capacity, ArrayList<Session> sessions) {
		
		super(title, description, category, capacity);
		sessions_ = sessions;
		id_ = id;
	}

}
