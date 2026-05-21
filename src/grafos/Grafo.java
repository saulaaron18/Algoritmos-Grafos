package grafos;

public class Grafo extends Digrafo implements IGrafo {
	
	public Grafo() {
		super();
	}
	
	@Override
	public boolean addEdge(String nombreOrigen, String nombreDestino, int peso) {
		return super.addEdge(nombreOrigen, nombreDestino, peso) &&
				super.addEdge(nombreDestino, nombreOrigen, peso);
	}
	
	@Override
	public boolean removeEdge(String nombreOrigen, String nombreDestino) {
		return super.removeEdge(nombreOrigen, nombreDestino) && super.removeEdge(nombreDestino, nombreOrigen);
	}
}