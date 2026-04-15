package grafos;
import java.util.Objects;

public class Vertice {
	private String nombre="";
	private int grado;
	
	/**
	 * PRE: nombre != null && !nombre.isEmpty()
	 * POST: Asigna nombre y el grado por defecto
	 * @param nombre
	 */
	public Vertice(String nombre) {
		this.nombre = nombre;
		this.grado = 0;
	}
	
	/**
	 * 
	 * @return		nombre del vertice
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Aumenta el grado del vertice
	 */
	public void aumentoGrado() {
		grado++;
	}
	
	/**
	 * Disminuye el grado del vertice
	 */
	public void disminuirGrado() {
		grado--;
	}
	
	/**
	 * 
	 * @return		grado del vertice
	 */
	public int getGrado() {
		return grado;
	}
	
	@Override
	public String toString() {
		return "(" + nombre + ", " + grado + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!getClass().isInstance(obj)) {
			return false;
		}
		
		Vertice vertice = (Vertice) obj;
		
		return nombre.equals(vertice.nombre);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
}
