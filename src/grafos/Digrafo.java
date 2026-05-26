package grafos;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Digrafo simple, que no acepta bucles y aristas multiples de mismo sentido
 * 
 * @author Saúl Aarón
 * @version 1.0
 */
public class Digrafo implements IGrafo {
	private final HashSet<Vertice> vertices;
	private final HashSet<Arista> aristas;
	private final HashMap<Vertice, HashSet<Arista>> verticesAdyacentes;

	/**
	 * Constructor de la clase Digrafo que inicializa los atributos de un Grafo: 
	 * vertices, aristas, vertices adyacentes.
	 */
	public Digrafo() {
		this.verticesAdyacentes = new HashMap<>();
		this.vertices = new HashSet<>();
		this.aristas = new HashSet<>();
	}

	@Override
	public boolean addVertex(String nombreVertice) throws IllegalArgumentException {
		Vertice verticeNuevo = new Vertice(nombreVertice);

		verticesAdyacentes.put(verticeNuevo, new HashSet<>());
		return vertices.add(verticeNuevo);
	}

	@Override
	public boolean removeVertex(String nombreVertice) throws IllegalArgumentException {
		Vertice verticeEliminacion = getVertex(nombreVertice);

		// Comprobamos que no es nulo
		if (verticeEliminacion == null) {
			return false;
		}

		// Eliminamos las aristas que contengan ese vertice

		// En aristas
		aristas.removeIf(arista -> arista.contieneVertice(nombreVertice));

		// En verticesAdyacentes
		for (Vertice verticeKey : verticesAdyacentes.keySet()) {
			Arista arista = new Arista(verticeKey, verticeEliminacion);

			verticesAdyacentes.get(verticeKey).remove(arista);
		}

		// Eliminamos el vertice de la lista de vertices
		verticesAdyacentes.remove(verticeEliminacion);

		return true;

	}

	@Override
	public boolean addEdge(String nombreOrigen, String nombreDestino, int peso) throws IllegalArgumentException {
		// Buscamos los vertices de la lista vertices
		Vertice origen = getVertex(nombreOrigen);
		Vertice destino = getVertex(nombreDestino);

		// Comprobamos si no son nulos para añadir la arista a la lista
		if (origen == null || destino == null || origen.equals(destino)) {
			return false;
		}

		Arista aristaNueva = new Arista(origen, destino, peso);

		verticesAdyacentes.get(origen).add(aristaNueva);
		return aristas.add(aristaNueva);

	}
	
	@Override
	public boolean addEdge(String nombreOrigen, String nombreDestino) throws IllegalArgumentException {
		return addEdge(nombreOrigen, nombreDestino, 1);
	}

	@Override
	public boolean removeEdge(String nombreOrigen, String nombreDestino) throws IllegalArgumentException {
		Vertice origen = getVertex(nombreOrigen);
		Vertice destino = getVertex(nombreDestino);

		// Comprobamos si son nulos
		if (origen == null || destino == null) {
			return false;
		}
		Arista aristaEliminar = new Arista(origen, destino);

		verticesAdyacentes.get(origen).remove(aristaEliminar); // Eliminamos de verticesAdyacentes

		return aristas.remove(aristaEliminar); // Eliminamos de aristas
	}

	@Override
	public String showVertexs() {
		return this.vertices.toString();
	}

	@Override
	public String showEdges() {
		return this.aristas.toString();
	}

	@Override
	public String toString() {
		String encabezado = " ";
		String grafo = "";
		int numVertices = verticesAdyacentes.size();

		// Bucle para diseñar el encabezado
		for (Vertice verticesKey : verticesAdyacentes.keySet()) {
			encabezado += verticesKey + " ";
		}

		// Creación de la matriz de Adyaciencias
		int[][] matriz = matrizAdyacencias();

		// Bucle para mostrar los vertices.toString() y los valores de matriz de
		// adyaciencias
		int i = 0;
		for (Vertice verticesKey : verticesAdyacentes.keySet()) {
			grafo += verticesKey + " | ";
			for (int j = 0; j < numVertices; j++) {
				grafo += matriz[i][j] + " ";
			}
			grafo += '\n';
			i++;
		}

		return encabezado + '\n' + grafo;
	}

	/**
	 * 
	 * @return matriz de adyaciencias por vertices
	 */
	protected int[][] matrizAdyacencias() {
		int[][] matriz = new int[numOfVertexs()][numOfVertexs()];
		HashMap<Vertice, Integer> indicesVertices = new HashMap<>();

		// Rellenamos los keys con vertices de la lista y los valores de los indices,
		// para acceder rapidamente con el hash
		int i = 0;
		for (Vertice verticeKey : vertices) {
			indicesVertices.put(verticeKey, i++);
		}

		// Accedemos a la lista aristas, obtenemos los vertices de origen y destino,
		// comprobamos su indice por el HashMap con complejidad O(1), y en
		for (Arista arista : aristas) {
			int fila = indicesVertices.get(arista.getV0());
			int columna = indicesVertices.get(arista.getVf());

			matriz[fila][columna] = arista.getPeso();
		}

		return matriz;
	}

	@Override
	public Vertice getVertex(String nombreVertice) throws IllegalArgumentException{
		Vertice verticeBusqueda = new Vertice(nombreVertice);

		return (vertices.contains(verticeBusqueda)) ? verticeBusqueda : null;
	}

	@Override
	public HashMap<Vertice, HashSet<Arista>> getVerticesAdyacentes() {
		return this.verticesAdyacentes;
	}

	@Override
	public HashSet<Arista> getEdgesOfVertex(String nombreVertice) throws IllegalArgumentException {
		Vertice vertice = getVertex(nombreVertice);
		return (vertice != null) ? verticesAdyacentes.get(vertice) : null;
	}

	@Override
	public Arista getEdge(String nombreOrigen, String nombreDestino) throws IllegalArgumentException {
		Arista aristaBusqueda = null;
		Vertice origen = getVertex(nombreOrigen);
		Vertice destino = getVertex(nombreDestino);

		if (origen != null && destino != null) {
			for (Arista arista : verticesAdyacentes.get(origen)) { // O(grado vertice origen)
				if (arista.getVf().equals(destino)) {
					aristaBusqueda = arista; // Obtiene su referencia y consigo el peso
				}
			}
		}

		return aristaBusqueda;
	}

	@Override
	public HashSet<Arista> getEdges() {
		return this.aristas;
	}

	@Override
	public HashSet<Vertice> getVertexs() {
		return this.vertices;
	}

	@Override
	public int numOfVertexs() {
		return verticesAdyacentes.size();
	}

	@Override
	public int numOfEdges() {
		return getEdges().size();
	}
}
