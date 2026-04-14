package grafos;
import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {
	private ArrayList<Vertice> vertices;
	private ArrayList<Arista> aristas;
	
	public Grafo() {
		this.vertices = new ArrayList<Vertice>();
		this.aristas = new ArrayList<Arista>();
	}
	
	public ArrayList<Vertice> getVertices() {
		return vertices;
	}
	
	public ArrayList<Arista> getAristas() {
		return aristas;
	}
	
	public void añadirVertice(String nombre) {
		vertices.add(new Vertice(nombre));
	}
	
	public boolean añadirArista(String nombreOrigen, String nombreDestino, int peso) {
		Vertice origen = buscarVertice(nombreOrigen);
		Vertice destino = buscarVertice(nombreDestino);
		
		if(origen != null && destino != null) {
			Arista arista = new Arista(origen, destino, peso);
			return aristas.add(arista);
		}
		
		return false;
	}
	
	public boolean eliminarArista(String nombreOrigen, String nombreDestino) {
		Vertice origen = buscarVertice(nombreOrigen);
		Vertice destino = buscarVertice(nombreDestino);
		
		if(origen != null && destino != null) {
			origen.disminuirGrado();
			return aristas.remove(new Arista(origen, destino, 0)); //Solamente se encuentra vivo en la lista
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		String encabezado=" ";
		String grafo="";
		
		for(int i=0;i<vertices.size();i++) {
			if(i==vertices.size()-1) {
				encabezado += vertices.get(i).getNombre() + '\n';
			}
			else{
				encabezado += vertices.get(i).getNombre() + ", ";
			}
		}
		
		int[][] matriz = matrizAdyacencias();
				
		for(int i=0;i<vertices.size();i++) {
			grafo += vertices.get(i) + " | ";
			for(int j=0;j<vertices.size();j++) {
				grafo += matriz[i][j] + " ";
			}
			grafo += '\n';
		}
			
		return encabezado+grafo;
	}
	
	private int[][]	matrizAdyacencias() {
		int[][]	matriz = new int[vertices.size()][vertices.size()];
		HashMap<Vertice, Integer> indicesVertices = new HashMap<Vertice, Integer>();
		
		for(int i=0;i<vertices.size();i++) {
			indicesVertices.put(vertices.get(i), i);
		}
		
		for(Arista arista:aristas) {
			int fila = indicesVertices.get(arista.getV0());
			int columna = indicesVertices.get(arista.getVf());
			
			matriz[fila][columna] = arista.getPeso();
		}
		
		
		return matriz;
	}
	
	private Vertice buscarVertice(String nombreVertice) {
		Vertice verticeBusqueda = null;
		
		for(int i=0;i<vertices.size() && verticeBusqueda==null;i++) {
			if(vertices.get(i).getNombre().equals(nombreVertice)) {
				verticeBusqueda = vertices.get(i);
			}
		}
			
			return verticeBusqueda;
	}
}
