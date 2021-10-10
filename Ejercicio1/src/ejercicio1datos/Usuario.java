package ejercicio1datos;

/**
 * A class that represents a user
 * @author Rafael Romero Pérez
 * @author Francisco Ruiz Roldán
 * @author Miguel Ángel Ruiz Fernández 
 *
 */
public class Usuario {
	
	
	//Attributes
	
	private String nombre;
	private String nick;
	private String correo;
	private String password;
	
	//empty constructor
	
	public Usuario() {
		
	}
	
	
	//Getters and setters
	
	/**
	 * 
	 * @return user's password
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * 
	 * @return user's name
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * 
	 * @return user's nick
	 */
	public String getNick() {
		return this.nick;
	}
	/**
	 * 
	 * @return user's mail
	 */
	public String getCorreo() {
		return this.correo;
	}
	
	/**
	 * 
	 * @param password user's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @param nombre user's name
	 */
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	/**
	 * 
	 * @param nick user's nick
	 */
	public void setNick(String nick){
		this.nick = nick;
	}
	/**
	 * 
	 * @param correo user's mail
	 */
	public void setCorreo(String correo){
		this.correo = correo;
	}
	
}
