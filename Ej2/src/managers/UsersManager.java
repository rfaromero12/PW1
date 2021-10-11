package managers;

import java.util.ArrayList;
import java.util.Properties;
import java.io.*;

import data.User;

/**
 * The users' manager.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class UsersManager {

	private static UsersManager instance = null;
	
	private ArrayList<User> users = new ArrayList<User>();
	
	private UsersManager() {
		
	}
	
	public static UsersManager getInstance() {
		
		if (instance==null) {
			instance = new UsersManager();
		}
		return instance;
	}
	
	//Registra un usuario
	/**
	 * 
	 * @param username
	 * @param mail
	 * @param name
	 * @param password
	 * @param admin
	 * @return
	 */
	public int register(String username, String mail, String name, String password, int admin) {
				
		User u = new User(username, mail, name, password, admin);
		users.add(u);
		
		save();
		
		return 1;
		
	}
	
	/**
	 * Check if an user 
	 * @param user
	 * @param password 
	 * @return
	 */
	public int login(String user, String password) {
		
		for(int i=0; i < users.size(); i++) {
			
			if((users.get(i).getUser().equals(user)) && (users.get(i).getPassword().equals(password))) {
				
				if (users.get(i).admin() == 1) {
					return 2;
				}
				else {
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	//Verifica si el mail está disponible
	/**
	 * Verify if an email is available
	 * @param mail Mail we want to verify
	 * @return 0 if it isn't available, 1 if it's available
	 */
	public int verifyMail(String mail) {
		
		for (int i = 0; i < users.size(); i++) {
			
			if(users.get(i).getMail().equals(mail)) {
			
				return 0;
				
			}
		}
		
		return 1;
	}
	
	/**
	 * Verify if an username is available
	 * @param username Username we want to verify
	 * @return 0 if it isn't available, 1 if it's available
	 */
	public int verifyUser(String username) {
		
		for (int i = 0; i < users.size(); i++) {
			
			if(users.get(i).getUser().equals(username)) {
			
				return 0;
				
			}
		}
		
		return 1;
	}
	
	/**
	 * Add a new administrator user
	 * @param newAdmin Identifier of the user than we want to become administrator
	 * @return 1 if the user exists, 0 if it doesn't exists
	 */
	public int newAdmin(String newAdmin) {
		
		int done = 0;
		
		for(int i=0; i<users.size(); i++) {
			
			if(users.get(i).getUser().equals(newAdmin)) {
				
				done = users.get(i).setAdmin();
			}
		}
		
		save();
		return done;
	}
	
	/**
	 * Save all the users into a file
	 */
	public void save() {
	
	    try {
	       
	        FileWriter usersFile = new FileWriter(properties());
	        
	        for (int i = 0; i < users.size(); i++) {
	        	usersFile.write(users.get(i).getUser() + "\n");
	        	usersFile.write(users.get(i).getMail() + "\n");
	        	usersFile.write(users.get(i).getName() + "\n");
	        	usersFile.write(users.get(i).getPassword() + "\n");
	        	usersFile.write(users.get(i).admin() + "\n\n");
	        
	        }
	        
	        usersFile.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	/**
	 * Load all the users from a file
	 */
	public void load() {
		
		File usersFile = new File(properties());
		
		if(usersFile.exists()) {
			
			if(usersFile.length() != 0) {
				
				try(BufferedReader br = new BufferedReader(new FileReader(properties()))) 
		        {
		        	String username;
		            while ((username = br.readLine()) != null) {
		            	
		            	String mail = br.readLine();
		            	String name = br.readLine();
		            	String password = br.readLine();
		            	int admin = Integer.parseInt(br.readLine());
		            	br.readLine();
		            	
		            	User u = new User(username, mail, name, password, admin);
		            
		            	users.add(u);
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
	 * Get the users' file name from the properties file
	 * @return The users' file name
	 */
	public String properties() {
		Properties properties = new Properties();
		
		String filename = "config.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			properties.load(reader);
			
			String usersS = properties.getProperty("users");

			return usersS;
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
}
