package ejercicio1operaciones;
	
import java.util.ArrayList;
import ejercicio1datos.Critica;
import ejercicio1datos.Usuario;



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
		public void crearUsuario(String correo,String nombre, String apellidos, String nick) {
			Usuario nuevo = new Usuario();
			nuevo.setNombre(nombre);
			nuevo.setApellidos(apellidos);
			nuevo.setCorreo(correo);
			nuevo.setNick(nick);
		}
	
		public boolean eliminarUsuario(String correo) {
			for(int i = 0; i < listaUsuarios.size(); i++){
				if(listaUsuarios.get(i).getCorreo() == correo){
					listaUsuarios.remove(i);
					return true;
				}
			}
			return false;
		}
	
		public void actualizarDatos() {
			
		}
	
		public Usuario verDatos(String correo) {
			Usuario datos = new Usuario();
			for(int i = 0; i < listaUsuarios.size(); i++){
				if(listaUsuarios.get(i).getCorreo() == correo){
					datos = listaUsuarios.get(i);
					return datos;
				}
			}	
			return null;
		}
	
	//Funciones del gestor de criticas
	public void crearCritica(String titulo, String resenia,int puntua) {
		Critica nuevaCritica = new Critica();
		
		
		//System.out.println("Titulo del espectaculo: ");
		//String titulo = scan.next();
		nuevaCritica.setTitulo(titulo);
		
		//System.out.println("Reseña del espectaculo: ");
		//String resenia = scan.next();
		nuevaCritica.setResenia(resenia);
		
		//System.out.println("Puntuacion del espectaculo: ");
		//int puntuar = scan.nextInt();
		nuevaCritica.setPuntuacion(puntua);
		
		listaCritica.add(nuevaCritica);
		
		//El array de las valoraciones no se si iniciarlo
	}
	
	
	//Hay que cambiarlo
	public void verCriticas() {
		
		if(listaCritica.size() == 0) {
			System.out.println("No hay ninguna critica disponible");
		}
		else {
			for(int i = 0; i < listaCritica.size(); i++) {
				System.out.println("Titulo: " + listaCritica.get(i).getTitulo());
				System.out.println("Reseña: " + listaCritica.get(i).getResenia());
				System.out.println("Puntuacion: " + listaCritica.get(i).getPuntuacion());				
			}
		}
	}
	//Borrar la critica en funcion del titulo
	public int borrarCritica(String usernick, String titulobuscado) {
		int aux = 0;
		for(int i = 0; i < listaCritica.size(); i++) {
			if(listaCritica.get(i).getTitulo() == titulobuscado && listaCritica.get(i).getNick() == usernick) {
				listaCritica.remove(i);
				aux = 1;
			}
		}
		return aux;
	}
	//Hay que hablarlo
	public void votarCritica(String titulo,String nickvotante) {
		
	}
	public ArrayList<Critica> buscarCriticas(String nickcreador) {
		ArrayList<Critica> lista_criticasbuscadas = new ArrayList<Critica>();
		for(int i = 0; i < listaCritica.size(); i++) {
			if(listaCritica.get(i).getNick() == nickcreador) {
				lista_criticasbuscadas.add(listaCritica.get(i));
			}
		}
		return lista_criticasbuscadas;
	}
}
