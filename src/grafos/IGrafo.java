package grafos;

//import java.util.HashMap;
import java.util.HashSet;

public interface IGrafo {
	
	public boolean addVertex(String nombreVertice);
	
	public boolean removeVertex(String nombreVertice);
	
	public boolean addEdge(String nombreOrigen, String nombreDestino, int peso);
	
	public boolean removeEdge(String nombreOrigen, String nombreDestino);
	
	public String showVertexs();
	
	public String showEdges();
	
	public Vertice getVertex(String nombreVertice);
	
	public Arista getEdge(String nombreOrigen, String nombreDestino);
	
	public HashSet<Arista> getEdgesOfVertex(String nombreVertice);
	
	public HashSet<Arista> getEdges();
	
	public HashSet<Vertice> getVertexs();
	
	//public HashMap<Vertice, HashSet<Arista>> getAtribute();
	
	public int numOfVertexs();
	
	public int numOfEdges();
}
