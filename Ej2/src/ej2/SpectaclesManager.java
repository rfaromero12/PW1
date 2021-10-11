package ej2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SpectaclesManager {
	
	private static SpectaclesManager instance = null;
	
	private static int idcounter = 1;
	
	private ArrayList<Spectacle> spectacles = new ArrayList<Spectacle>();
	
	private SpectacleAbstractFactory factory;
	
	private SpectaclesManager() {
			
		}
		
		public static SpectaclesManager getInstance() {
			
			if (instance==null) {
				instance = new SpectaclesManager();
			}
			return instance;
		}

	public void create(String title, String description, String category, int capacity, Session session) {
		
		SpectacleP p = new SpectacleP(idcounter, title, description, category, capacity, session);
		spectacles.add(p);
		
		idcounter++;
		//SpectacleP s = factory.createSpectacleP(title, description, category, capacity, session);
		//spectacles.add(s);
		//spectacles.add(factory.createSpectacleP(title, description, category, capacity, session));
		
		save();
	}
	
	public void create(String title, String description, String category, int capacity, ArrayList<Session> sessions) {
		
		SpectacleM m = new SpectacleM(idcounter, title, description, category, capacity, sessions);
		spectacles.add(m);
		idcounter++;
		//spectacles.add(factory.createSpectacleM(title, description, category, capacity, sessions));
		
		save();
		
	}
	
	public void create(String title, String description, String category, int capacity, Date start, Date end, String time, int day) {
		
		SpectacleT t = new SpectacleT(idcounter, title, description, category, capacity, start, end, time, day);
		spectacles.add(t);
		
		idcounter++;
		save();
		
	}
	
	//Imprime todas las sesiones de un espectáculo
	public String printSessions(int id) {
		
		String result = "";
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				result = "\nESPECTACULO\n" + spectacles.get(i).getTitle() + "\nSesiones:" + spectacles.get(i).getSessions() + "\n";
			}
		}
		
		return result;
	}
	
	//Borra toda la información de un espectáculo incluidas todas sus sesiones.
	public void deleteAll(int delete) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == delete) {
				
				spectacles.remove(i);
			}
		}
		
		save();
	}
	
	//Borra las sesiones pasadas por array del espectáculo con ese id
	public void deleteSessions(int id, String[] sessionsArray) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).deleteSessions(sessionsArray);
			}
		}
		
		save();
	}
	
	//Buscar espectáculo por título o categoría.
	public String search(String search) {
		
		String result = "";
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if( (spectacles.get(i).getCategory().toLowerCase().equals(search)) || (spectacles.get(i).getTitle().toLowerCase().equals(search)) ) {
				
				result = "Id: " + spectacles.get(i).getId() + ". " + spectacles.get(i).getTitle() + "\n";
			}
		}
		
		return result;
		
	}
	
	//Devuelve los espectáculos y sus sesiones para las cuales hay entradas disponibles y aún no se han realizado
	public String nextAvaiableSessions() {
		
		String result = "";
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getAvaiableSessions() != "") {
				
				result = "\n" + spectacles.get(i).getTitle() + spectacles.get(i).getAvaiableSessions();
				
			}
		}
		
		return result;
	}
	
	//Devuelve el número de entradas disponibles para una sesión dado el id del espectáculo y la fecha de la sesión
	public int avaiableTickets(int id, Date date) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				return (spectacles.get(i).getCapacity() - spectacles.get(i).soldTickets(date));
				
			}
		}
		
		return 0;
	}
	
	//Devuelve las entradas vendidas para una sesión dada su fecha
	public int soldTickets(int id, Date date) {
	
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				return spectacles.get(i).soldTickets(date);
			}
		}
		
		return -1;
	}
	
	public void load() {
		
		File spectaclesFile = new File("spectacles.txt");
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		if(spectaclesFile.exists()) {
			
			try(BufferedReader br = new BufferedReader(new FileReader("spectacles.txt"))) 
	        {
				idcounter = Integer.parseInt(br.readLine());

	        	String idS;
	            while ((idS = br.readLine()) != null) {
	            	
	            	int id = Integer.parseInt(idS);

	            	String title = br.readLine();

	            	String description = br.readLine();

	            	String category = br.readLine();

	            	int capacity = Integer.parseInt(br.readLine());
	            	
	            	ArrayList<Session> sessions = new ArrayList<Session>();
	            	
	            	String dateS;
	            	while(!(dateS = br.readLine()).equals("-")) {
	            		
						Date date = null;
						try {
							date = df.parse(dateS);
						} catch (Exception e) {}
	            		
						int sold = Integer.parseInt(br.readLine());
						
						Session s = new Session(date, sold);
						
						sessions.add(s);
						
	            	}
	            	
	            	//br.readLine();
	            	
	            	SpectacleM s = new SpectacleM(id, title, description, category, capacity, sessions);
	            
	            	spectacles.add(s);
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
		       
	        FileWriter spectaclesFile = new FileWriter("spectacles.txt");
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        
	        ArrayList<Session> sessions;
	        
	        spectaclesFile.write(idcounter + "\n");
	        
	        for (int i = 0; i < spectacles.size(); i++) {
	        	spectaclesFile.write(spectacles.get(i).getId() + "\n");
	        	spectaclesFile.write(spectacles.get(i).getTitle() + "\n");
	        	spectaclesFile.write(spectacles.get(i).getDescription() + "\n");
	        	spectaclesFile.write(spectacles.get(i).getCategory() + "\n");
	        	spectaclesFile.write(spectacles.get(i).getCapacity() + "\n");
	        	
	        	sessions = spectacles.get(i).sessionsArray();
	        	
	        	for (int x = 0; x < sessions.size(); x++) {
	        		
	        		spectaclesFile.write(sdf.format(sessions.get(x).getDate()) + "\n");
	        		spectaclesFile.write(sessions.get(x).getSold() + "\n");
	        	}
	        	
	        	spectaclesFile.write("-\n");
	        	
	        }
	        
	        spectaclesFile.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	public void setTitle(int id, String title) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setTitle(title);
				
			}
			
		}
		
		save();
		
	}
	
	public void setDescription(int id, String description) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setDescription(description);
				
			}
			
		}
		
		save();
		
	}
	
	public void setCategory(int id, String category) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setCategory(category);
				
			}
			
		}
		
		save();
		
	}
	
	public void setCapacity(int id, int capacity) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setCapacity(capacity);
				
			}
			
		}
		
		save();
		
	}
	
	public String printInfo(int id) {
		
		String result = "";
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				result = "Título: " + spectacles.get(i).getTitle() + "\nDescripción: " + spectacles.get(i).getDescription() +
						"\nCategoría: " + spectacles.get(i).getCategory() + "\nAforo: " + spectacles.get(i).getCapacity(); 
			}
			
		}
		
		return result;
	}
	
}
