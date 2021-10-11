package data;

import java.util.ArrayList;

/**
 * A class to represent an spectacle review
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class Review {
	
	private String review_, username_;
	private int id_, spectacle_, score_;
	private ArrayList<Integer> valuations_ = new ArrayList<Integer>();
	
	/**
	 * Parameterized constructor
	 * @param id Review's identifier
	 * @param spectacle Reviewed spectacle
	 * @param score Spectacle's score
	 * @param review Review's text
	 * @param username Review's owner
	 * */
	public Review(int id, int spectacle, int score, String review, String username) {
		
		id_ = id;
		spectacle_ = spectacle;
		score_ = score;
		review_ = review;
		username_ = username;
	}
	
	/**
	 * Get the Review's identifier
	 * @return The review's identifier
	 */
	public int getId() {
		
		return this.id_;
	}
	
	/**
	 * Get the spectacle's score given by the review's owner
	 * @return The spectacle's score
	 */
	public int getScore() {
		
		return this.score_;
	}
	
	/**
	 * Get the spectacle's identifier for which it has been written the review
	 * @return The spectacle's identifier
	 */
	public int getSpectacle() {
		return this.spectacle_;
	}
	
	/**
	 * Get the review's text
	 * @return The review's text
	 */
	public String getReview() {
		
		return this.review_;
	}
	
	/**
	 * Get the valuations average
	 * @return The valuations average or -1 if there aren't any valuation
	 */
	public int getValuationsAverage() {
		
		int average = 0;
		
		if(valuations_.size()!=0) {
			
			for (int i=0; i<valuations_.size(); i++) {
				
				average = average + valuations_.get(i);
			}
			
			average = (average / valuations_.size());
			
			return average;
			
		}
		
		return -1;
	}
	
	/**
	 * Get all the valuations done to the review
	 * @return Integer array of valuations
	 */
	public ArrayList<Integer> getValuations(){
		
		return this.valuations_;
	}
	
	/**
	 * Get the review's owner
	 * @return The review's owner username
	 */
	public String getUsername() {
		
		return this.username_;
	}
	
	/**
	 * Add a new valuation
	 * @param valuation An user's valuation
	 */
	public void setValuation(int valuation) {
		
		this.valuations_.add(valuation);
	}
	
	/**
	 * Add a list of valuations
	 * @param valuations Array with diferent users' valuations
	 */
	public void setValuationArray(String[] valuations) {
		
		for(int i=0; i<valuations.length; i++) {
			
			valuations_.add(Integer.parseInt(valuations[i]));
		}
		
	}
	
}
