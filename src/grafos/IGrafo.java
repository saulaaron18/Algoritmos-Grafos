package grafos;

import java.util.HashMap;
import java.util.HashSet;

public interface IGrafo {

	/**
	 * PRE: nombreVertice != null && !nombreVertice.isEmpty()
	 * POST: Crea y añade un vertice a la lista de vertices
	 * 
	 * @param nombreVertice nombre del vertice
	 * @return true si se añadió correctamente
	 */
	public boolean addVertex(String nombreVertice);

	/**
	 * PRE: nombreVertice != null && !nombreVertice.isEmpty()
	 * POST: Elimina las aristas conectadas al vertice y el vertice mismo
	 * 
	 * @param nombreVertice nombre del vertice
	 * @return true si se removió correctamente
	 */
	public boolean removeVertex(String nombreVertice);

	/**
	 * Crea la arista que conecta el vertice de origen al de destino.
	 * Puede ser dirigida a un sentido en un Digrafo o de ambos sentidos
	 * en caso de un Grafo
	 * PRE: peso != 0
	 * 
	 * @param nombreOrigen  nombre del vertice de origen
	 * @param nombreDestino nombre del vertice de destino
	 * @param peso          peso de la arista
	 * @return true si se añadio correctamente
	 */
	public boolean addEdge(String nombreOrigen, String nombreDestino, int peso);
	
	/**
	 * Crea la arista que conecta el vertice de origen al de destino.
	 * Puede ser dirigida a un sentido en un Digrafo o de ambos sentidos
	 * en caso de un Grafo
	 * PRE: Cierto
	 * 
	 * @param nombreOrigen  nombre del vertice de origen
	 * @param nombreDestino nombre del vertice de destino
	 * @return true si se añadio correctamente
	 */
	public boolean addEdge(String nombreOrigen, String nombreDestino);

	/**
	 * PRE: Cierto
	 * POST: Elimina la arista del Grafo
	 * 
	 * @param nombreOrigen  nombre del vertice de origen
	 * @param nombreDestino nombre del vertice destino
	 * @return true si se ha removido correctamente
	 */
	public boolean removeEdge(String nombreOrigen, String nombreDestino);

	/**
	 * 
	 * @return String del HashSet<Vertice>
	 */
	public String showVertexs();

	/**
	 * 
	 * @return String del HashSet<Arista>
	 */
	public String showEdges();

	/**
	 * PRE: nombreVertice != null && !nombreVertice.isEmpty()
	 * 
	 * @param nombreVertice nombre del vertice
	 * @return objeto vertice de busqueda
	 */
	public Vertice getVertex(String nombreVertice);

	/**
	 * PRE: nombreOrigen != null && !nombreOrigen.isEmpty()
	 * && nombreDestino != null && !nombreDestino.isEmpty()
	 * 
	 * @param nombreOrigen  nombre del vertice origen
	 * @param nombreDestino nombre del vertice destino
	 * @return objeto arista de busqueda
	 */
	public Arista getEdge(String nombreOrigen, String nombreDestino);

	/**
	 * PRE: nombreVertice != null && !nombreVertice.isEmpty()
	 * 
	 * @param nombreVertice nombre del vertice
	 * @return aristas del vertice
	 */
	public HashSet<Arista> getEdgesOfVertex(String nombreVertice);

	/**
	 * 
	 * @return aristas del Grafo
	 */
	public HashSet<Arista> getEdges();

	/**
	 * 
	 * @return vertices del grafo
	 */
	public HashSet<Vertice> getVertexs();

	/**
	 * 
	 * @return hash table de vertices (keys) y sus aristas adyacentes (values)
	 */
	public HashMap<Vertice, HashSet<Arista>> getVerticesAdyacentes();

	/**
	 * 
	 * @return numero de Vertices
	 */
	public int numOfVertexs();

	/**
	 * 
	 * @return numero de Aristas
	 */
	public int numOfEdges();
}
