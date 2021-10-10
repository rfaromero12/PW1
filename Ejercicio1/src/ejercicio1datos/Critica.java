package ejercicio1datos;

import java.util.ArrayList;

/**
 * A class that represents a review
 * @author Rafael Romero Pérez
 * @author Francisco Ruiz Roldán
 * @author Miguel Ángel Ruiz Fernández 
 * 
 */
public class Critica {
	
	//Attributes
	
	private String titulo,resenia,nick;
	private ArrayList<Integer> valoraciones = new ArrayList<Integer>();
	
	
	//empty constructor
	
	public Critica() {
		
	}
	
	//Getters and setters
	
	
	/**
	 * 
	 * @return review's title 
	 */
	public String getTitulo() {
		return this.titulo;
	}
	/**
	 * 
	 * @return review's description
	 */
	public String getResenia() {
		return this.resenia;
	}
	
	/**
	 * 
	 * @return a list with review's score
	 */
	public ArrayList<Integer> getValoracion() {
		return this.valoraciones;
	}
	
	/**
	 * 
	 * @return author's nick
	 */
	public String getNick() {
		return this.nick;
	}
	
	/**
	 * 
	 * @param titulo review's title 
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * 
	 * @param resenia review's description
	 */
	public void setResenia(String resenia) {
		this.resenia = resenia;
	}
	
	/**
	 * 
	 * @param nick author's nick
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/**
	 * 
	 * @param valoracion review's score
	 */
	public void setValoracion(int valoracion) {
		this.valoraciones.add(valoracion);
	}
}
