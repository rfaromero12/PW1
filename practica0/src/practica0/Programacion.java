package practica0;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;

public class Programacion {
	private ArrayList<Espectaculo> listaEspectaculo = new ArrayList<Espectaculo>();
	
	public Programacion() {
				
	}
	public void programarEspectaculo(Espectaculo espectaculo) {
		listaEspectaculo.add(espectaculo);
	}
	
	public ArrayList<Espectaculo> verEspectaculo(){
		return listaEspectaculo;
	}
	
	public void imprimirEventos() {
		for(int i = 0; i < listaEspectaculo.size(); i++) {
			System.out.println(listaEspectaculo.get(i).toString());
		}
	}
	
	public ArrayList<String> verTitulos(){
		
		ArrayList<String> aux = new ArrayList<String>();
		for (int i = 0; i < listaEspectaculo.size(); i++) {
			aux.add(listaEspectaculo.get(i).getTitulo());
		}
		return aux;
	}
	
	public boolean eliminarEspectaculo(String titulo) {
			
		for(int i = 0; i < listaEspectaculo.size() ; i++) {
			if(listaEspectaculo.get(i).getTitulo() == titulo) {
				listaEspectaculo.remove(i);
				return true;
			}
		}
		return false;
		
	}
	public ArrayList<Espectaculo> verProximosEspectaculos(LocalDate fecha){
		//Esto es una clase de java que tiene una funcion para devolver conseguir la fecha actual en formato Date
		LocalDate fechaActual = LocalDate.now();
		if(fechaActual.isAfter(fecha)) {
			return null;
		}
		else {
			
			ArrayList<Espectaculo> nextPerformances = new ArrayList<Espectaculo>();
			for(int i = 0; i < listaEspectaculo.size(); i++) {
				if(listaEspectaculo.get(i).getFecha().before(fecha)) {
					nextPerformances.add(listaEspectaculo.get(i));
				}
			}
			
			return nextPerformances;
		}
		
	}
	
	
}
