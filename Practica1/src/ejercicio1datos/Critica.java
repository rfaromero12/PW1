package ejercicio1datos;

import java.util.ArrayList;

/**
 * 
 * @author rfaromero12
 *
 */
public class Critica {
	private String titulo,resenia,nick;
	private ArrayList<Integer> valoraciones = new ArrayList<Integer>();
	
	
	public Critica() {
		
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getResenia() {
		return this.resenia;
	}
	
	public ArrayList<Integer> getValoracion() {
		return this.valoraciones;
	}
	public String getNick() {
		return this.nick;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setResenia(String resenia) {
		this.resenia = resenia;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setValoracion(int valoracion) {
		this.valoraciones.add(valoracion);
	}
}
