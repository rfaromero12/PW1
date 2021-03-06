package ejercicio1operaciones;
	
import java.io.*;
import java.util.ArrayList;
import ejercicio1datos.Critica;
import ejercicio1datos.Usuario;
import java.util.Properties;

public class GestorCriticas {

	private static GestorCriticas instance = null;
	
	private ArrayList<Critica> listaCritica = new ArrayList<Critica>(); 
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	//Constructor privado
	private GestorCriticas() {
		
	}
	
	//Punto de acceso;
	public static GestorCriticas getInstance() {
		if(instance == null) {
			instance = new GestorCriticas();
		}
		return instance;
	}
	
	
	//Funciones de usuario
	//Funciona
	public int login(String user, String password) {
		
		for(int i=0; i < listaUsuarios.size(); i++) {
			
			if((listaUsuarios.get(i).getNick().equals(user)) && (listaUsuarios.get(i).getPassword().equals(password))) {
				
				
					return 1;
				}
			}
		return 0;
	}
	//Funciona
	public int verifyMail(String mail) {
		for (int i = 0; i < listaUsuarios.size(); i++) {
			
			if(listaUsuarios.get(i).getCorreo().equals(mail)) {
			
				return 0;
				
			}
		}
		
		return 1;
	}
	//Funciona
	public int verifyUser(String username) {
		
		int key = 1;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			
			if(listaUsuarios.get(i).getNick().equals(username)) {
			
				key = 0;
				
			}
		}
		
		return key;
	}
		//funciona
		public int crearUsuario(String correo,String nombre, String nick,String password) {
			Usuario nuevo = new Usuario();
			nuevo.setNombre(nombre);
			nuevo.setCorreo(correo);
			nuevo.setNick(nick);
			nuevo.setPassword(password);
			listaUsuarios.add(nuevo);
			return 1;
		}
		//funciona
		public boolean eliminarUsuario(String correo) {
			for(int i = 0; i < listaUsuarios.size(); i++){
				if(listaUsuarios.get(i).getCorreo().equals(correo)){
					listaUsuarios.remove(i);
					return true;
				}
			}
			return false;
		}
	
		public void actualizarDatos(String oldnick,String newnick, String newNombre, String newPassword) {
			for(int i = 0; i < listaUsuarios.size(); i++) {
				if(listaUsuarios.get(i).getNick().equals(oldnick)) {
					listaUsuarios.get(i).setNick(newnick);
					listaUsuarios.get(i).setNombre(newNombre);
					listaUsuarios.get(i).setPassword(newPassword);
					save();
				}
			}
		}
		//Funciona pero hablar si hacerlo con correo o dejarlo como usuario
		public String verDatos(String username) {
			String datos = "";
			for(int i = 0; i < listaUsuarios.size(); i++){
				if(listaUsuarios.get(i).getNick().equals(username)){
					datos = "Nick: " + listaUsuarios.get(i).getNick() + ", Nombre: " 
							+ listaUsuarios.get(i).getNombre() + ", Correo: " 
							+ listaUsuarios.get(i).getCorreo() + ", Password: "+ listaUsuarios.get(i).getPassword();
				}
			}	
			return datos;
		}
	
	//Funciones del gestor de criticas
	//Funciona
	
	public void crearCritica(String titulo, String resenia,String nick) {
		Critica nuevaCritica = new Critica();
		
		nuevaCritica.setTitulo(titulo);
		
		nuevaCritica.setResenia(resenia);
		
		nuevaCritica.setNick(nick);
		
		listaCritica.add(nuevaCritica);
	
	}
	
	//Funciona
	public ArrayList<String> verCriticas() {
		ArrayList<Integer> auxiliar = new ArrayList<Integer>();
		ArrayList<String> stringcriticas = new ArrayList<String>();
		String insertador;
		float media = 0;
		float contador = 0;
		
			for(int i = 0; i < listaCritica.size(); i++) {
				auxiliar = listaCritica.get(i).getValoracion();
				for(int j = 0;j < auxiliar.size();j++) {
					media = media + auxiliar.get(j);
					contador++;
				}
				try {
				media = media/contador;
				}
				catch(Exception e) {
					System.out.println("Nadie ha puntuado la critica");
				}
				insertador = "Titulo: " + listaCritica.get(i).getTitulo() + 
						", Nick: "+ listaCritica.get(i).getNick() +", Rese??a: " 
						+ listaCritica.get(i).getResenia() + ", Valoracion: " + media;
				stringcriticas.add(insertador);
				media = 0;
				contador = 0;
			}
			return stringcriticas;
	}
		
	
	//Borrar la critica en funcion del titulo
	//Funciona
	public boolean borrarCritica(String usernick, String titulobuscado) {
		for(int i = 0; i < listaCritica.size(); i++) {
			if(listaCritica.get(i).getTitulo().equals(titulobuscado) && listaCritica.get(i).getNick().equals(usernick)) {
				listaCritica.remove(i);
				return true;
			}
		}
		return false;
	}
	//Funciona
	public boolean votarCritica(String titulo,String nickvotante,int valoracionnueva) {
		for(int i = 0; i < listaCritica.size(); i++) {
			if(listaCritica.get(i).getTitulo().equals(titulo) && !listaCritica.get(i).getNick().equals(nickvotante)){
				listaCritica.get(i).setValoracion(valoracionnueva);
				return true;
			}
		}
		return false;
	}
	//Funciona
	public ArrayList<Critica> buscarCriticas(String nickcreador) {
		ArrayList<Critica> lista_criticasbuscadas = new ArrayList<Critica>();
		for(int i = 0; i < listaCritica.size(); i++) {
			if(listaCritica.get(i).getNick().equals(nickcreador)) {
				lista_criticasbuscadas.add(listaCritica.get(i));
			}
		}
		return lista_criticasbuscadas;
	}
	//Funciona
	public void save() {
		ArrayList<String> nombreficheros = new ArrayList<String>();
		nombreficheros = ficheroProper();
		
		try {
       
			FileWriter usersFile = new FileWriter(nombreficheros.get(0));
        
			for (int i = 0; i < listaUsuarios.size(); i++) {
        	usersFile.write(listaUsuarios.get(i).getNick() + "\n");
        	usersFile.write(listaUsuarios.get(i).getCorreo() + "\n");
        	usersFile.write(listaUsuarios.get(i).getNombre() + "\n");
        	usersFile.write(listaUsuarios.get(i).getPassword() + "\n");
        }
        
        usersFile.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
		       
			FileWriter criticasFile = new FileWriter(nombreficheros.get(1));
			ArrayList<Integer> aux = new ArrayList<Integer>();
			for (int i = 0; i < listaCritica.size(); i++) {
        	criticasFile.write(listaCritica.get(i).getTitulo() + "\n");
        	criticasFile.write(listaCritica.get(i).getResenia() + "\n");
        	criticasFile.write(listaCritica.get(i).getNick() + "\n");
        	aux = listaCritica.get(i).getValoracion();
        	for(int j = 0; j < aux.size(); j++) {
        	criticasFile.write(aux.get(j) + " ");
        	}
        	criticasFile.write("\n");
        }
        
       criticasFile.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Funciona
	public void load() {
		ArrayList<String> nombreficheros = new ArrayList<String>();
		nombreficheros = ficheroProper();
		
		File usersFile = new File(nombreficheros.get(0));
		
		if(usersFile.exists()) {
			
			try(BufferedReader br = new BufferedReader(new FileReader(nombreficheros.get(0)))) 
	        {
	        	String username;
	            while ((username = br.readLine()) != null) {
	            	
	            	String mail = br.readLine();
	            	String name = br.readLine();
	            	String password = br.readLine();
	            	
	            	Usuario u = new Usuario();
	            	u.setNick(username);
	            	u.setNombre(name);
	            	u.setCorreo(mail);
	            	u.setPassword(password);
	            	listaUsuarios.add(u);
	            }
	        }
	        catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
		}
			
		
		File criticasFile = new File(nombreficheros.get(1));
		
		if(criticasFile.exists()) {
			
			try(BufferedReader br = new BufferedReader(new FileReader(nombreficheros.get(1)))) 
	        {
	        	String titulo;
	            while ((titulo = br.readLine()) != null) {
	            	Critica aux = new Critica();
	            	String resenia = br.readLine();
	            	String nick = br.readLine();  
	            	String puntuaciones = br.readLine();
	            	String [] parts = puntuaciones.split(" ");
	            	
	            	for(int i = 0; i < parts.length; i++) {
	            		aux.setValoracion(Integer.parseInt(parts[i]));
	            	}
	            
	            	aux.setNick(nick);
	            	aux.setResenia(resenia);
	            	aux.setTitulo(titulo);
	            	listaCritica.add(aux);
	            }
	        }
	        catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }     			
		}
		
	
	}

	public ArrayList<String> ficheroProper() {
		Properties prop = new Properties();
		ArrayList<String> aux = new ArrayList<String>();
		String filename = "config.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);
			
			String criticas = prop.getProperty("ficherocritica");
			String usuarios = prop.getProperty("ficherousuarios");
			aux.add(usuarios);
			aux.add(criticas);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return aux;
		
	}
	
}


