package ej2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReviewsManager {
	
	private static ReviewsManager instance = null;
	
	private static int idcounter = 1;
	
	private ArrayList<Review> reviews_ = new ArrayList<Review>();

	private ReviewsManager() {
		
	}
	
	public static ReviewsManager getInstance() {
		
		if (instance==null) {
			instance = new ReviewsManager();
		}
		return instance;
	}
	
	public void create(int spectacle, int score, String review, String username) {
		
		Review r = new Review(idcounter, spectacle, score, review, username);

		reviews_.add(r);
		
		idcounter++;
		
		save();
		
	}
	
	/**
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	public boolean delete(int id, String user) {
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if( (reviews_.get(i).getId() == id) && (reviews_.get(i).getUsername() == user)){
				reviews_.remove(i);
				save();
				return true;
			}
			
		}
		save();
		return false;
		
	}
	
	
	public String getReviewsUser(String user) {

		String result = "";
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if(reviews_.get(i).getUsername().equals(user)) {
				
				result = result + "\n\nId. " + reviews_.get(i).getId() + "\nEspectáculo: " + reviews_.get(i).getSpectacle() + "\nReseña: " + reviews_.get(i).getReview() + "\nPuntuación: " + reviews_.get(i).getScore() +
						 "\nMedia valoraciones: ";
				if(reviews_.get(i).getValuationsAverage() == -1) {
					
					result = result + "Aún no hay valoraciones.";
				}
				else {
					
					result = result + reviews_.get(i).getValuationsAverage();
				}
			}
		}
		
		return result;
		
	}
	
	public String getReviewsSpectacle(int spectacle) {
		
		String result = "";
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if(reviews_.get(i).getSpectacle() == spectacle) {
				
				result = "\nId. " + reviews_.get(i).getId() + "\nEspectáculo: " + reviews_.get(i).getSpectacle() + "\nReseña: " + reviews_.get(i).getReview() + "\nPuntuación: " + reviews_.get(i).getScore() +
						 "\nMedia valoraciones: ";
				if(reviews_.get(i).getValuationsAverage() == -1) {
					
					result = result + "Aún no hay valoraciones.";
				}
				else {
					
					result = result + reviews_.get(i).getValuationsAverage();
				}
			}
		}
		
		return result;
		
	}
	
	public String getReviewsId(int id) {
		
		String result = "";
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if(reviews_.get(i).getId() == id) {
				
				result = "\nId. " + reviews_.get(i).getId() + "\nUsuario: " + reviews_.get(i).getUsername() + "\nEspectáculo: " + reviews_.get(i).getSpectacle() + "\nReseña: " + reviews_.get(i).getReview() + "\nPuntuación: " + reviews_.get(i).getScore() +
						 "\nMedia valoraciones: ";
				if(reviews_.get(i).getValuationsAverage() == -1) {
					
					result = result + "Aún no hay valoraciones.";
				}
				else {
					
					result = result + reviews_.get(i).getValuationsAverage();
				}
			}
		}
		
		return result;
		
	}
	
	public int valuate(int id, String user, int valuation) {
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if(reviews_.get(i).getId() == id) {
				
				if(!reviews_.get(i).getUsername().equals(user)) {
					
					reviews_.get(i).setValuation(valuation);
					//save();
					return 1;
				}
				else {
					return 0;
				}
				
			}
		
		}
		
		return -1;
		
	}
	
	public void load() {
		
		File reviewsFile = new File("reviews.txt");
		
		if(reviewsFile.exists()) {
			
			try(BufferedReader br = new BufferedReader(new FileReader("reviews.txt"))) 
	        {
	        	idcounter = Integer.parseInt(br.readLine());
	        	String idS;
	            while ((idS = br.readLine()) != null) {
	            	int id = Integer.parseInt(idS);
	            	
	            	int spectacle = Integer.parseInt(br.readLine());
	            	
	            	int score = Integer.parseInt(br.readLine());
	            	
	            	String review = br.readLine();
	            	
	            	String valuations = br.readLine();
	            	String[] valuationsArray = valuations.split(" ");
	            	
	            	String user = br.readLine();
	
	            	br.readLine();
	            	
	            	Review r = new Review(id, spectacle, score, review, user);
	            
	            	r.setValuationArray(valuationsArray);
	            	
	            	reviews_.add(r);
	            }
	        }
	        catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }     	
			
		}
		
	}
	
	public void save() {
		
		try {
		       
	        FileWriter reviewsFile = new FileWriter("reviews.txt");
	                	        
	        reviewsFile.write(idcounter + "\n");
	        
	        for (int i = 0; i < reviews_.size(); i++) {
	        	
	        	reviewsFile.write(reviews_.get(i).getId() + "\n");
	        	reviewsFile.write(reviews_.get(i).getSpectacle() + "\n");
	        	reviewsFile.write(reviews_.get(i).getScore() + "\n");
	        	reviewsFile.write(reviews_.get(i).getReview() + "\n");
	        	
	        	if(reviews_.get(i).getValuations().size() != 0) {
	        		
	        		for (int x=0; x<reviews_.get(i).getValuations().size(); x++) {
	        			
	        			reviewsFile.write(reviews_.get(i).getValuations().get(x) + " ");
		        	}
		        	
	        	}
	        	
	        	reviewsFile.write("\n" + reviews_.get(i).getUsername() + "\n\n");

	        }
	        
	        reviewsFile.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
}
