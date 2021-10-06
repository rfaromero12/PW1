package ejercicio1datos;

import java.util.ArrayList;
/*
	Las valoraciones podrian ser una lista de valoraciones, ya que cada usuario puede realizar una.
	
*/
public class Critica {
	private String titulo,resenia,nick;
	private int puntuacion;
	private ArrayList<String> valoraciones;
	
	
	public Critica() {
		
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getResenia() {
		return this.resenia;
	}
	
	public ArrayList<String> getValoracion() {
		return this.valoraciones;
	}
	public String getNick() {
		return this.nick;
	}
	public int getPuntuacion() {
		return this.puntuacion;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setResenia(String resenia) {
		this.resenia = resenia;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
}
