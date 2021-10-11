package managers;

import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import data.Session;
import data.Spectacle;
import data.SpectacleM;
import data.SpectacleP;
import data.SpectacleT;

/**
 * The spectacles' manager.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class SpectaclesManager {
	
	private static SpectaclesManager instance = null;
	
	private static int idcounter = 1;
	
	private ArrayList<Spectacle> spectacles = new ArrayList<Spectacle>();
	
	//private SpectaclesFactory factory;
	
	private SpectaclesManager() {
			
		}
		
		public static SpectaclesManager getInstance() {
			
			if (instance==null) {
				instance = new SpectaclesManager();
			}
			return instance;
		}

	/**
	 * 
	 * @param title
	 * @param description
	 * @param category
	 * @param capacity
	 * @param session
	 */
	public void create(String title, String description, String category, int capacity, Session session) {
		
		SpectacleP p = new SpectacleP(idcounter, title, description, category, capacity, session);
		spectacles.add(p);
		
		idcounter++;
		//SpectacleP s = factory.createSpectacleP(idcounter, title, description, category, capacity, session);
		//spectacles.add(p);
		
		save();
	}
	
	/**
	 * 
	 * @param title
	 * @param description
	 * @param category
	 * @param capacity
	 * @param sessions
	 */
	public void create(String title, String description, String category, int capacity, ArrayList<Session> sessions) {
		
		SpectacleM m = new SpectacleM(idcounter, title, description, category, capacity, sessions);
		spectacles.add(m);
		idcounter++;
		//spectacles.add(factory.createSpectacleM(title, description, category, capacity, sessions));
		
		save();
		
	}
	
	/**
	 * 
	 * @param title
	 * @param description
	 * @param category
	 * @param capacity
	 * @param start
	 * @param end
	 * @param time
	 * @param day
	 */
	public void create(String title, String description, String category, int capacity, Date start, Date end, String time, int day) {
		
		SpectacleT t = new SpectacleT(idcounter, title, description, category, capacity, start, end, time, day);
		spectacles.add(t);
		
		idcounter++;
		save();
		
	}
	
	//Imprime todas las sesiones de un espectáculo
	/**
	 * 
	 * @param id
	 * @return
	 */
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
	/**
	 * 
	 * @param delete
	 */
	public void deleteAll(int delete) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == delete) {
				
				spectacles.remove(i);
			}
		}
		
		save();
	}
	
	//Borra las sesiones pasadas por array del espectáculo con ese id
	/**
	 * 
	 * @param id
	 * @param sessionsArray
	 */
	public void deleteSessions(int id, String[] sessionsArray) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).deleteSessions(sessionsArray);
			}
		}
		
		save();
	}
	
	//Buscar espectáculo por título o categoría.
	/**
	 * 
	 * @param search
	 * @return
	 */
	public String search(String search) {
		
		String result = "";
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if( (spectacles.get(i).getCategory().toLowerCase().equals(search)) || (spectacles.get(i).getTitle().toLowerCase().equals(search)) ) {
				
				result = printInfo(spectacles.get(i).getId());
			}
		}
		
		return result;
		
	}
	
	//Devuelve los espectáculos y sus sesiones para las cuales hay entradas disponibles y aún no se han realizado
	/**
	 * 
	 * @return
	 */
	public String nextAvaiableSessions() {
		
		String result = "";
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(!spectacles.get(i).getAvaiableSessions().equals("")) {
				
				result = result + "\n" + spectacles.get(i).getTitle() + spectacles.get(i).getAvaiableSessions() + "\n";
				
			}
		}
		
		return result;
	}
	
	//Devuelve el número de entradas disponibles para una sesión dado el id del espectáculo y la fecha de la sesión
	/**
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	public int avaiableTickets(int id, Date date) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				return (spectacles.get(i).getCapacity() - spectacles.get(i).soldTickets(date));
				
			}
		}
		
		return 0;
	}
	
	//Devuelve las entradas vendidas para una sesión dada su fecha
	/**
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	public int soldTickets(int id, Date date) {
	
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				return spectacles.get(i).soldTickets(date);
			}
		}
		
		return -1;
	}
	
	/**
	 * 
	 */
	public void load() {
		
		File spectaclesFile = new File(properties());
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		if(spectaclesFile.exists()) {
			
			if(spectaclesFile.length() != 0) {
				
				try(BufferedReader br = new BufferedReader(new FileReader(properties()))) 
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
	}
	
	/**
	 * 
	 */
	public void save() {
		
		try {
		       
	        FileWriter spectaclesFile = new FileWriter(properties());
	        
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
	
	/**
	 * 
	 * @param id
	 * @param title
	 */
	public void setTitle(int id, String title) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setTitle(title);
				
			}
			
		}
		
		save();
		
	}
	
	/**
	 * 
	 * @param id
	 * @param description
	 */
	public void setDescription(int id, String description) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setDescription(description);
				
			}
			
		}
		
		save();
		
	}
	
	/**
	 * 
	 * @param id
	 * @param category
	 */
	public void setCategory(int id, String category) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setCategory(category);
				
			}
			
		}
		
		save();
		
	}
	
	/**
	 * 
	 * @param id
	 * @param capacity
	 */
	public void setCapacity(int id, int capacity) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setCapacity(capacity);
				
			}
			
		}
		
		save();
		
	}
	
	/**
	 * 
	 * @param id
	 * @param session
	 * @param date
	 */
	public void setDate(int id, int session, Date date) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setDate(session, date);
				
			}
		}
		save();
	}
	
	/**
	 * 
	 * @param id
	 * @param session
	 * @param sold
	 */
	public void setSold(int id, int session, int sold) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				spectacles.get(i).setSold(session, sold);
			}
		}
		save();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String printInfo(int id) {
		
		String result = "";
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				result = "\nId: " + spectacles.get(i).getId() + "\nTítulo: " + spectacles.get(i).getTitle() + "\nDescripción: " + spectacles.get(i).getDescription() +
						"\nCategoría: " + spectacles.get(i).getCategory() + "\nAforo: " + spectacles.get(i).getCapacity()
						+ "\nSesiones:" + spectacles.get(i).getSessions();
			}
			
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public String allSpectacles() {
		
		String result = "------------";
		
		for(int i=0; i<spectacles.size(); i++) {
				
				result = result + "\nId: " + spectacles.get(i).getId() + "\nTítulo: " + spectacles.get(i).getTitle() + "\nDescripción: " + spectacles.get(i).getDescription() +
						"\nCategoría: " + spectacles.get(i).getCategory() + "\nAforo: " + spectacles.get(i).getCapacity()
						+ "\nSesiones:" + spectacles.get(i).getSessions() + "\n------------";

		}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public String properties() {
		Properties properties = new Properties();
		
		String filename = "config.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			properties.load(reader);
			
			String spectaclesS = properties.getProperty("spectacles");

			return spectaclesS;
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Get an spectacle's title
	 * @param id Spectacle's identifier
	 * @return The spectacle's title
	 */
	public String getTitle(int id) {
		
		String result = "";
		
		for (int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId() == id) {
				
				result = spectacles.get(i).getTitle();
			}
		}
		
		return result;
	}
	
	/**
	 * Verify if an spectacle exists
	 * @param id Spectacle's identifier
	 * @return true if the id exists, false if the id doesn't exists
	 */
	public boolean verifyId(int id) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId()==id) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Add a session to the spectacle given
	 * @param id Spectacle's identifier
	 * @param session Session to add
	 */
	public void addSession(int id, Session session) {
		
		for(int i=0; i<spectacles.size(); i++) {
			
			if(spectacles.get(i).getId()==id) {
				spectacles.get(i).addSession(session);
			}
		}
	}
	
}
