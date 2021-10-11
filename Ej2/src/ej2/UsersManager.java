package ej2;

import java.util.ArrayList;
import java.io.*;

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
	public int register(String username, String mail, String name, String password, int admin) {
				
		User u = new User(username, mail, name, password, admin);
		users.add(u);
		
		save();
		
		return 1;
		
	}
	
	//Autentica un usuario
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
	public int verifyMail(String mail) {
		
		for (int i = 0; i < users.size(); i++) {
			
			if(users.get(i).getMail().equals(mail)) {
			
				return 0;
				
			}
		}
		
		return 1;
	}
	
	//Verifica si el usuario está disponible
	public int verifyUser(String username) {
		
		for (int i = 0; i < users.size(); i++) {
			
			if(users.get(i).getUser().equals(username)) {
			
				return 0;
				
			}
		}
		
		return 1;
	}
	
	//Guarda los usuarios en el fichero
	public void save() {
	
	    try {
	       
	        FileWriter usersFile = new FileWriter("users.txt");
	        
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
	
	//Carga los usuarios del fichero
	public void load() {
		
		File usersFile = new File("users.txt");
		
		if(usersFile.exists()) {
			
			try(BufferedReader br = new BufferedReader(new FileReader("users.txt"))) 
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
