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
			listaUsuarios.add(nuevo);
		}
	
		public boolean eliminarUsuario(String correo) {
			for(int i = 0; i < listaUsuarios.size(); i++){
				if(listaUsuarios.get(i).getCorreo().equals(correo)){
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
				if(listaUsuarios.get(i).getCorreo().equals(correo)){
					datos = listaUsuarios.get(i);
					return datos;
				}
			}	
			return null;
		}
	
	//Funciones del gestor de criticas
	public void crearCritica(String titulo, String resenia,String nick) {
		Critica nuevaCritica = new Critica();
		
		
		//System.out.println("Titulo del espectaculo: ");
		//String titulo = scan.next();
		nuevaCritica.setTitulo(titulo);
		
		//System.out.println("Reseña del espectaculo: ");
		//String resenia = scan.next();
		nuevaCritica.setResenia(resenia);
		
		//System.out.println("Puntuacion del espectaculo: ");
		//int puntuar = scan.nextInt();
		nuevaCritica.setNick(nick);
		
		listaCritica.add(nuevaCritica);
		
		//El array de las valoraciones no se si iniciarlo
	}
	
	
	//Hay que cambiarlo
	public ArrayList<String> verCriticas() {
		ArrayList<Integer> auxiliar = new ArrayList<Integer>();
		ArrayList<String> stringcriticas = new ArrayList<String>();
		String insertador;
		int media = 0;
		int contador = 0;
		
			for(int i = 0; i < listaCritica.size(); i++) {
				auxiliar = listaCritica.get(i).getValoracion();
				for(int j = 0;j < auxiliar.size();j++) {
					media = media + auxiliar.get(i);
					contador++;
				}
				media = media/contador;
				insertador = "Titulo: " + listaCritica.get(i).getTitulo() + 
						", Nick: "+ listaCritica.get(i).getNick() +", Reseña: " 
						+ listaCritica.get(i).getResenia() + ", Valoracion: " + media;
				stringcriticas.add(insertador);
			}
			return stringcriticas;
	}
		
	
	//Borrar la critica en funcion del titulo
	public boolean borrarCritica(String usernick, String titulobuscado) {
		for(int i = 0; i < listaCritica.size(); i++) {
			if(listaCritica.get(i).getTitulo().equals(titulobuscado) && listaCritica.get(i).getNick().equals(usernick)) {
				listaCritica.remove(i);
				return true;
			}
		}
		return false;
	}
	//Hay que hablarlo
	public boolean votarCritica(String titulo,String nickvotante,int valoracionnueva) {
		for(int i = 0; i < listaCritica.size(); i++) {
			if(listaCritica.get(i).getTitulo().equals(titulo) && listaCritica.get(i).getNick().equals(nickvotante)){
				listaCritica.get(i).setValoracion(valoracionnueva);
				return true;
			}
		}
		return false;
	}
	public ArrayList<Critica> buscarCriticas(String nickcreador) {
		ArrayList<Critica> lista_criticasbuscadas = new ArrayList<Critica>();
		for(int i = 0; i < listaCritica.size(); i++) {
			if(listaCritica.get(i).getNick().equals(nickcreador)) {
				lista_criticasbuscadas.add(listaCritica.get(i));
			}
		}
		return lista_criticasbuscadas;
	}
}
