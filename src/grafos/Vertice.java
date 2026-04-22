package grafos;
import java.util.Objects;

public class Vertice {
	private String nombre="";
	
	/**
	 * PRE: nombre != null && !nombre.isEmpty()
	 * POST: Asigna nombre y el grado por defecto
	 * @param nombre
	 */
	public Vertice(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public String toString() {
		return nombre;
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
