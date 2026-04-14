package grafos;
import java.util.Objects;

public class Arista {
	private int peso = 1;//Caso base
	private Vertice v0; //Vertice inicial
	private Vertice vf; //Vertice final
	
	public Arista(Vertice v0, Vertice vf,int peso) {
		this.peso = peso;
		this.v0 = v0;
		this.vf = vf;
		this.v0.aumentoGrado();
	}
	
	public int getPeso() {
		return peso;
	}
	
	public Vertice getV0() {
		return v0;
	}
	
	public Vertice getVf() {
		return vf;
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
