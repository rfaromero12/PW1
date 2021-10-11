package managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import data.Review;

/**
 * The reviews' manager.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class ReviewsManager {
	
	private static ReviewsManager instance = null;
	
	private static int idcounter = 1;
	
	private ArrayList<Review> reviews_ = new ArrayList<Review>();
	
	private SpectaclesManager SManager = SpectaclesManager.getInstance();

	private ReviewsManager() {
		
	}
	
	public static ReviewsManager getInstance() {
		
		if (instance==null) {
			instance = new ReviewsManager();
		}
		return instance;
	}
	
	/**
	 * 
	 * @param spectacle
	 * @param score
	 * @param review
	 * @param username
	 */
	public void create(int spectacle, int score, String review, String username) {
		
		Review r = new Review(idcounter, spectacle, score, review, username);

		reviews_.add(r);
		
		idcounter++;
		
		save();
		
	}
	
	/**
	 * Delete a review by a review's identifier given
	 * @param id Review's identifier
	 * @param user Identifier of the user who wants to delete
	 * @return 1 if the review is deleted, 0 if the user that is trying to delete it is not the owner
	 * 		   or -1 if the review with that identifier doesn't exists
	 */
	public int delete(int id, String user) {
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if( reviews_.get(i).getId() == id){
				
				if(reviews_.get(i).getUsername().equals(user)) {
					
					reviews_.remove(i);
					save();
					return 1;
				 }
				
				return 0;
			}
			
		}
		return -1;
		
	}
	
	/**
	 * Get all the reviews of a user
	 * @param user User's identifier
	 * @return A string with all the review's done by an user
	 */
	public String getReviewsUser(String user) {

		String result = "\n------------";
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if(reviews_.get(i).getUsername().equals(user)) {
				
				result = result + "\nId. " + reviews_.get(i).getId() + "\nEspectáculo: " + SManager.getTitle( reviews_.get(i).getSpectacle() )+ "\nReseña: " + reviews_.get(i).getReview() + "\nPuntuación espectáculo: " + reviews_.get(i).getScore() +
						 "\nMedia valoraciones: ";
				if(reviews_.get(i).getValuationsAverage() == -1) {
					
					result = result + "Aún no hay valoraciones.\n------------";
				}
				else {
					
					result = result + reviews_.get(i).getValuationsAverage() + "\n------------";
				}
			}
		}
		
		return result;
		
	}
	
	/**
	 * Get reviews by an spectacle given
	 * @param spectacle Spectacle's identifier
	 * @return A string with all the reviews done for that spectacle.
	 */
	public String getReviewsSpectacle(int spectacle) {
		
		String result = "";
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if(reviews_.get(i).getSpectacle() == spectacle) {
				
				result = "\nId. " + reviews_.get(i).getId() + "\nEspectáculo: " + SManager.getTitle( reviews_.get(i).getSpectacle() ) + "\nReseña: " + reviews_.get(i).getReview() + "\nPuntuación: " + reviews_.get(i).getScore() +
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
	
	/**
	 * Get a review by an identifier given
	 * @param id Review's identifier
	 * @return A string with the review's info
	 */
	public String getReviewsId(int id) {
		
		String result = "";
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if(reviews_.get(i).getId() == id) {
				
				result = "\nId. " + reviews_.get(i).getId() + "\nUsuario: " + reviews_.get(i).getUsername() + "\nEspectáculo: " + SManager.getTitle(reviews_.get(i).getSpectacle()) + "\nReseña: " + reviews_.get(i).getReview() + "\nPuntuación: " + reviews_.get(i).getScore() +
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
	
	/**
	 * Add a valuation to a review
	 * @param id Review's identifier
	 * @param user Username of the user who wants to valuate
	 * @param valuation Puntuation given to the review
	 * @return 1 if the valuation has been done, 0 if the user that is trying to valuate is the 
	 * 		   review's owner or -1 if the indicate review doesn't exists
	 */
	public int valuate(int id, String user, int valuation) {
		
		for(int i=0; i<reviews_.size(); i++) {
			
			if(reviews_.get(i).getId() == id) {
				
				if(!reviews_.get(i).getUsername().equals(user)) {
					
					reviews_.get(i).setValuation(valuation);
					save();
					return 1;
				}
				else {
					return 0;
				}
				
			}
		
		}
		
		return -1;
		
	}
	
	/**
	 * Load the reviews from a file
	 */
	public void load() {
		
		File reviewsFile = new File(properties());
		
		if(reviewsFile.exists()) {
			
			if(reviewsFile.length() != 0) {
				
				try(BufferedReader br = new BufferedReader(new FileReader(properties()))) 
		        {
		        	idcounter = Integer.parseInt(br.readLine());
		        	String idS;
		            while ((idS = br.readLine()) != null) {
		            	int id = Integer.parseInt(idS);
		            	
		            	int spectacle = Integer.parseInt(br.readLine());
		            	
		            	int score = Integer.parseInt(br.readLine());
		            	
		            	String review = br.readLine();
		            	
		            	String valuations = br.readLine();
		            		
		            	String user = br.readLine();
		
		            	br.readLine();
		            	
		            	Review r = new Review(id, spectacle, score, review, user);
		            
		            	if(!valuations.equals("")) {
		            		String[] valuationsArray = valuations.split(" ");
		            		r.setValuationArray(valuationsArray);
		            	}
		            	reviews_.add(r);
		            }
		        }
		        catch (IOException e) {
		            System.out.println("An error occurred.");
		            e.printStackTrace();
		        }     	
			
			}		
		}
	}
	
	/**
	 * Save all the reviews into a file
	 */
	public void save() {
		
		try {
		       
	        FileWriter reviewsFile = new FileWriter(properties());
	                	        
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
	
	/**
	 * Get the reviews' file name from the properties file
	 * @return The reviews' file name
	 */
	public String properties() {
		Properties properties = new Properties();
		
		String filename = "config.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			properties.load(reader);
			
			String reviewsS = properties.getProperty("reviews");

			return reviewsS;
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
