package grafos;
import java.util.Objects;

public class Vertice {
	private String nombre="";
	
	/**
	 * PRE: nombre != null && !nombre.isEmpty()
	 * POST: Asigna nombre y el grado por defecto
	 * @param nombre
	 * @throws IllegalArgumentException
	 */
	public Vertice(String nombre) throws IllegalArgumentException {
		if(nombre==null || nombre.trim().isEmpty()){
			throw new IllegalArgumentException();
		}
		this.nombre = nombre.trim();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	
	@Override
	public String toString() {
		return nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vertice)) {
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
