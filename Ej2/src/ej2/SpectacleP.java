package ej2;

public class SpectacleP extends Spectacle{
	
	public SpectacleP(int id, String title, String description, String category, int capacity, Session session) {
		
		super(title, description, category, capacity);
		sessions_.add(session);
		id_ = id;
	}

}
