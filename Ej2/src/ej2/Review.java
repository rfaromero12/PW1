package ej2;

import java.util.ArrayList;

public class Review {
	
	private String review_, username_;
	private int id_, spectacle_, score_;
	private ArrayList<Integer> valuations_ = new ArrayList<Integer>();
	
	public Review(int id, int spectacle, int score, String review, String username) {
		
		id_ = id;
		spectacle_ = spectacle;
		score_ = score;
		review_ = review;
		username_ = username;
	}
	
	public int getId() {
		
		return this.id_;
	}
	
	public int getScore() {
		
		return this.score_;
	}
	
	public int getSpectacle() {
		return this.spectacle_;
	}
	
	public String getReview() {
		
		return this.review_;
	}
	
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
	
	public ArrayList<Integer> getValuations(){
		
		return this.valuations_;
	}
	
	
	public String getUsername() {
		
		return this.username_;
	}
	
	public void setValuation(int valuation) {
		
		this.valuations_.add(valuation);
	}
	
	public void setValuationArray(String[] valuations) {
		
		for(int i=0; i<valuations.length; i++) {
			
			valuations_.add(Integer.parseInt(valuations[i]));
		}
		
	}
	
}
