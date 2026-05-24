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
	 * @throws IllegalArgumentException
	 */
	public boolean addVertex(String nombreVertice) throws IllegalArgumentException;

	/**
	 * PRE: nombreVertice != null && !nombreVertice.isEmpty()
	 * POST: Elimina las aristas conectadas al vertice y el vertice mismo
	 * 
	 * @param nombreVertice nombre del vertice
	 * @return true si se removió correctamente
	 * @throws IllegalArgumentException
	 */
	public boolean removeVertex(String nombreVertice) throws IllegalArgumentException;

	/**
	 * Crea la arista que conecta el vertice de origen al de destino.
	 * Puede ser dirigida a un sentido en un Digrafo o de ambos sentidos
	 * en caso de un Grafo
	 * 
	 * PRE: peso != 0 && !nombreOrigen.equals(nombreDestino)
	 * && nombreOrigen != null && !nombreOrigen.isEmpty()
	 * && nombreDestino != null && !nombreDestino.isEmpty()
	 * 
	 * @param nombreOrigen  nombre del vertice de origen
	 * @param nombreDestino nombre del vertice de destino
	 * @param peso          peso de la arista
	 * @return true si se añadio correctamente
	 * @throws IllegalArgumentException
	 */
	public boolean addEdge(String nombreOrigen, String nombreDestino, int peso) throws IllegalArgumentException;
	
	/**
	 * Crea la arista que conecta el vertice de origen al de destino.
	 * Puede ser dirigida a un sentido en un Digrafo o de ambos sentidos
	 * en caso de un Grafo
	 * PRE: !nombreOrigen.equals(nombreDestino)
	 * && nombreOrigen != null && !nombreOrigen.isEmpty()
	 * && nombreDestino != null && !nombreDestino.isEmpty()
	 * 
	 * @param nombreOrigen  nombre del vertice de origen
	 * @param nombreDestino nombre del vertice de destino
	 * @return true si se añadio correctamente
	 * @throws IllegalArgumentException
	 */
	public boolean addEdge(String nombreOrigen, String nombreDestino) throws IllegalArgumentException;

	/**
	 * PRE: nombreOrigen != null && !nombreOrigen.isEmpty()
	 * && nombreDestino != null && !nombreDestino.isEmpty()
	 * 
	 * @param nombreOrigen  nombre del vertice de origen
	 * @param nombreDestino nombre del vertice destino
	 * @return true si se ha removido correctamente
	 * @throws IllegalArgumentException
	 */
	public boolean removeEdge(String nombreOrigen, String nombreDestino) throws IllegalArgumentException;

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
	 * @throws IllegalArgumentException
	 */
	public Vertice getVertex(String nombreVertice) throws IllegalArgumentException;

	/**
	 * PRE: nombreOrigen != null && !nombreOrigen.isEmpty()
	 * && nombreDestino != null && !nombreDestino.isEmpty()
	 * 
	 * @param nombreOrigen  nombre del vertice origen
	 * @param nombreDestino nombre del vertice destino
	 * @return objeto arista de busqueda
	 * @throws IllegalArgumentException
	 */
	public Arista getEdge(String nombreOrigen, String nombreDestino) throws IllegalArgumentException;

	/**
	 * PRE: nombreVertice != null && !nombreVertice.isEmpty()
	 * 
	 * @param nombreVertice nombre del vertice
	 * @return aristas del vertice
	 * @throws IllegalArgumentException
	 */
	public HashSet<Arista> getEdgesOfVertex(String nombreVertice) throws IllegalArgumentException;

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
