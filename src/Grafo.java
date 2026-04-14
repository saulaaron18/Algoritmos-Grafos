import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {
	private ArrayList<Vertice> vertices;
	private ArrayList<Arista> aristas;
	
	public Grafo() {
		this.vertices = new ArrayList<Vertice>();
		this.aristas = new ArrayList<Arista>();
	}
	
	public void añadirVertice(String nombre) {
		vertices.add(new Vertice(nombre));
	}
	
	public void añadirArista(String nombreOrigen, String nombreDestino, int peso) {
		Vertice origen = new Vertice(nombreOrigen);
		Vertice destino = new Vertice(nombreDestino);
		
		if(vertices.contains(origen) && vertices.contains(destino)) {
			Arista arista = new Arista(origen, destino, peso);
			aristas.add(arista);
		}
	}
	
	@Override
	public String toString() {
		String encabezado="";
		String grafo="";
		
		for(int i=0;i<vertices.size();i++) {
			if(i==vertices.size()-1) {
				encabezado = vertices.get(i).getNombre() + '\n';
			}
			else{
				encabezado = vertices.get(i).getNombre() + ", ";
			}
		}
		
		int[][] matriz = matrizAdyacencias();
				
		for(int i=0;i<vertices.size();i++) {
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
}
