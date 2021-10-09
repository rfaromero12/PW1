package ejercicio1datos;

/**
 * 
 * @author rfaromero12
 *
 */
public class Usuario {
	private String nombre;
	private String nick;
	private String correo;
	private String password;
	
	public Usuario() {
		
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getNick() {
		return this.nick;
	}
	
	public String getCorreo() {
		return this.correo;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public void setNick(String nick){
		this.nick = nick;
	}
	
	public void setCorreo(String correo){
		this.correo = correo;
	}
	
}
