package data;

/**
 * A subclass that inherits from Spectacle.It represent an spectacle with only one session.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class SpectacleP extends Spectacle{
	
	/**
	 * Parameterized constructor
	 * @param id Spectacle's identifier
	 * @param title Spectacle's title
	 * @param description Spectacle's description
	 * @param category Spectacle's category
	 * @param capacity Spectacle's theatre capacity
	 * @param sessions Spectacle's session
	 * */
	public SpectacleP(int id, String title, String description, String category, int capacity, Session session) {
		
		super(title, description, category, capacity);
		sessions_.add(session);
		id_ = id;
	}

}
