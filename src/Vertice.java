import java.util.Objects;

public class Vertice {
	private String nombre="";
	private int grado;
	
	public Vertice(String nombre) {
		this.nombre = nombre;
		this.grado = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void aumentoGrado() {
		grado++;
	}
	
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
