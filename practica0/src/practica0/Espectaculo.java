package practica0;
import java.util.Date;


public class Espectaculo {
	private int categoria_;
	private String titulo_,descripcion_;
	private Date fecha_;
	private int entrDisponibles_;
	private int entrVendidas_;
	
	public Espectaculo() {
		
	}
	
	public Espectaculo(String titulo,int categoria, String descripcion) {
		this.titulo_ = titulo;
		this.categoria_ = categoria;
		this.descripcion_ = descripcion;
	}
	
	public int getCategoria() {
		return this.categoria_;
	}
	
	public String getTitulo() {
		return this.titulo_;
	}
	
	public String getDescripcion() {
		return this.descripcion_;
	}
	
	public Date getFecha() {
		return this.fecha_;
	}
	
	public int getDisponibles() {
		return this.entrDisponibles_;
	}
	
	public int getVendidas() {
		return this.entrVendidas_;
	}
	
	public void setCategoria(int categoria) {
		this.categoria_= categoria;
	}

	public void setTitulo(String titulo) {
		this.titulo_ = titulo;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion_ = descripcion;
	}
	
	public void setFecha(Date fecha) {
		this.fecha_ = fecha;
	}
	
	public void setDisponibles(int disponibles) {
		this.entrDisponibles_ = disponibles;
	}
	
	public void setVendidas(int vendidas) {
		this.entrVendidas_ = vendidas;
	}
	
	
	public String toString() {
		String info = "Titulo: " + this.titulo_+ ", Categoria: " + this.categoria_  + ", Descripcion: " + this.descripcion_ + ", Fecha: "
				+ this.fecha_ + ", Entradas disponibles: " + this.entrDisponibles_ + ", Entradas vendidas: " + this.entrVendidas_;
		return info;
	}	
	
}
