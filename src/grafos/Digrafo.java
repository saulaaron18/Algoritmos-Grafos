package grafos;

import java.util.HashMap;
import java.util.HashSet;

public class Digrafo implements IGrafo {
	private final HashSet<Vertice> vertices;
	private final HashSet<Arista> aristas;
	private final HashMap<Vertice, HashSet<Arista>> verticesAdyacentes;

	public Digrafo() {
		this.verticesAdyacentes = new HashMap<>();
		this.vertices = new HashSet<>();
		this.aristas = new HashSet<>();
	}

	/**
	 * PRE: No hay un vertice null y no es vacio
	 * POST: Crea y añade un vertice a la lista de vertices
	 * 
	 * @param nombreVertice
	 * @return true si se añadió correctamente
	 */
	@Override
	public boolean addVertex(String nombreVertice) {
		Vertice verticeNuevo = new Vertice(nombreVertice);

		verticesAdyacentes.put(verticeNuevo, new HashSet<>());
		return vertices.add(verticeNuevo);
	}

	/**
	 * PRE: nombreVertice != null
	 * POST: Elimina las aristas conectadas al vertice y el vertice mismo
	 * 
	 * @param nombreVertice
	 * @return true si se añadio correctamente
	 */
	@Override
	public boolean removeVertex(String nombreVertice) {
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

	/**
	 * PRE: peso != 0
	 * POST: Crea la arista conectada desde el vertice de origen al de destino
	 * y aumenta el grado del vertice origen
	 * 
	 * @param nombreOrigen
	 * @param nombreDestino
	 * @param peso
	 * @return true si se añadio correctamente
	 */
	@Override
	public boolean addEdge(String nombreOrigen, String nombreDestino, int peso) {
		// Buscamos los vertices de la lista vertices
		Vertice origen = getVertex(nombreOrigen);
		Vertice destino = getVertex(nombreDestino);

		// Comprobamos si no son nulos para añadir la arista a la lista
		if (origen == null || destino == null) {
			return false;
		}

		Arista aristaNueva = new Arista(origen, destino, peso);

		verticesAdyacentes.get(origen).add(aristaNueva);
		return aristas.add(aristaNueva);

	}

	/**
	 * PRE: Cierto
	 * POST: Elimina la arista
	 * 
	 * @param nombreOrigen
	 * @param nombreDestino
	 * @return true si se ha removido correctamente
	 */
	@Override
	public boolean removeEdge(String nombreOrigen, String nombreDestino) {
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
		int[][] matriz = new int[vertices.size()][vertices.size()];
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

	/**
	 * PRE: nombreVertice != null && !nombreVertice.isEmpty()
	 * POST: Devuelve el vertices
	 * 
	 * @param nombreVertice
	 * @return el vertice de busqueda
	 */
	@Override
	public Vertice getVertex(String nombreVertice) {
		Vertice verticeBusqueda = new Vertice(nombreVertice);

		return (vertices.contains(verticeBusqueda)) ? verticeBusqueda : null;
	}

	public HashMap<Vertice, HashSet<Arista>> getVerticesAdyacentes() {
		return this.verticesAdyacentes;
	}

	@Override
	public HashSet<Arista> getEdgesOfVertex(String nombreVertice) {
		Vertice vertice = getVertex(nombreVertice);
		return (vertice != null) ? verticesAdyacentes.get(vertice) : null;
	}

	@Override
	public Arista getEdge(String nombreOrigen, String nombreDestino) {
		Arista aristaBusqueda = null;
		Vertice origen = getVertex(nombreOrigen);
		Vertice destino = getVertex(nombreDestino);

		if (origen != null && destino != null) {
			for (Arista arista : verticesAdyacentes.get(origen)) { // O(grado vertice)
				if (arista.getVf().equals(destino)) {
					aristaBusqueda = arista; // Obtiene su referencia y consigo el peso
				}
			}
		}

		return aristaBusqueda;
	}

	/**
	 * PRE: cierto
	 * POST: devuelve todas las aristas del grafo
	 * 
	 * @return referencia de todas las aristas del grafo
	 */
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
