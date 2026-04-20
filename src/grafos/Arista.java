package grafos;
import java.util.Objects;

public class Arista {
	private int peso;	//Peso de la arista
	private Vertice v0; //Vertice inicial
	private Vertice vf; //Vertice final
	
	/**
	 * PRE: v0 != null && vf != null
	 * POST: Construye una arista conectado a dos vertices existentes y 
	 * se le asigna un peso
	 * 
	 * @param 	v0
	 * @param 	vf
	 * @param 	peso
	 */
	public Arista(Vertice v0, Vertice vf,int peso) {
		this.peso = peso;
		this.v0 = v0;
		this.vf = vf;
	}
	
	/**
	 * PRE: v0 != null && vf != null
	 * POST: Construye una arista conectado a dos vertices existentes y 
	 * se le asigna el peso por defecto 1
	 * 
	 * @param v0
	 * @param vf
	 */
	public Arista(Vertice v0, Vertice vf) {
		this.peso = 1;
		this.v0 = v0;
		this.vf = vf;
	}
	
	/**
	 * 
	 * @return	peso
	 */
	public int getPeso() {
		return peso;
	}
	
	/**
	 * 
	 * @return	vertice de origen
	 */
	public Vertice getV0() {
		return v0;
	}
	/**
	 * 
	 * @return	vertice de destino
	 */
	public Vertice getVf() {
		return vf;
	}
	
	/**
	 * 
	 * @param nombreVertice
	 * @return	true si el nombre del vertice introducido coincide 
	 * con uno de los vertices de la arista
	 */
	public boolean contieneVertice(String nombreVertice) {
		return v0.toString().equals(nombreVertice) || vf.toString().equals(nombreVertice);
	}
	
	@Override
	public String toString() {
		return "[" + v0 + ", " + vf + "," + peso + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!Arista.class.isInstance(obj)) {
			return false;
		}
		
		Arista arista = (Arista) obj;
		
		return v0.equals(arista.v0) && vf.equals(arista.vf);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(v0, vf);
	}
}
