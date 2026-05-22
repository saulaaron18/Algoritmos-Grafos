package grafos;

import java.util.HashMap;
import java.util.HashSet;

public class Grafo extends Digrafo implements IGrafo {

	public Grafo() {
		super();
	}

	@Override
	public boolean addEdge(String nombreOrigen, String nombreDestino, int peso) {
		// Buscamos los vertices de la lista vertices
		Vertice origen = getVertex(nombreOrigen);
		Vertice destino = getVertex(nombreDestino);

		// Comprobamos si no son nulos para añadir la arista a la lista
		if (origen == null || destino == null) {
			return false;
		}

		Arista aristaNuevaOrigen = new Arista(origen, destino, peso);
		Arista aristaNuevaDestino = new Arista(destino, origen, peso);

		getVerticesAdyacentes().get(origen).add(aristaNuevaOrigen);
		getVerticesAdyacentes().get(destino).add(aristaNuevaDestino);
		return getEdges().add(aristaNuevaOrigen);

	}

	@Override
	public boolean removeEdge(String nombreOrigen, String nombreDestino) {
		Vertice origen = getVertex(nombreOrigen);
		Vertice destino = getVertex(nombreDestino);

		// Comprobamos si son nulos
		if (origen == null || destino == null) {
			return false;
		}
		Arista aristaEliminarOrigen = new Arista(origen, destino);
		Arista aristaEliminarDestino = new Arista(destino, origen);

		// Eliminamos de verticesAdyacentes
		getVerticesAdyacentes().get(origen).remove(aristaEliminarOrigen);
		getVerticesAdyacentes().get(destino).remove(aristaEliminarDestino);

		return getEdges().remove(aristaEliminarOrigen) || getEdges().remove(aristaEliminarDestino); // Eliminamos la
																									// arista
	}

	@Override
	protected int[][] matrizAdyacencias() {
		HashSet<Vertice> vertices = getVertexs();
		HashSet<Arista> aristas = getEdges();
		int n = vertices.size();
		int[][] matriz = new int[n][n];
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
			matriz[columna][fila] = arista.getPeso();
		}

		return matriz;
	}
}