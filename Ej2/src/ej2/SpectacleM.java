package ej2;

import java.util.ArrayList;

public class SpectacleM extends Spectacle{
	
	public SpectacleM(int id, String title, String description, String category, int capacity, ArrayList<Session> sessions) {
		
		super(title, description, category, capacity);
		sessions_ = sessions;
		id_ = id;
	}

}
