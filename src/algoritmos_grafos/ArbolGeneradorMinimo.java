package algoritmos_grafos;

import grafos.*;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Clase cuyos servicios es la creación de un
 * árbol generador mínimo, es decir, de peso mínimo
 * 
 * La complejidad se resume en:
 * Time: O(E log E)
 * Space: O(V+E)
 * 
 * 
 * @author Saúl Aarón
 * @version 1.0
 */
public final class ArbolGeneradorMinimo {

	/**
	 * PRE: el grafo es conexo y no dirigido
	 * 
	 * @param grafo
	 * @param nombreRaiz
	 * @return
	 */
	public static Grafo Prim(Grafo grafo, String nombreRaiz) {
		Grafo arbolGeneradorMinimo = new Grafo();

		if (Busquedas.busquedaEnProfundidad(nombreRaiz, grafo).numOfVertexs() != grafo.numOfVertexs()) {
			throw new IllegalArgumentException(
					"El grafo dado no es conexo. Inserte un grafo conexo.");
		}

		// Inicialización
		int numOfVertexsGrafo = grafo.numOfVertexs();

		PriorityQueue<Arista> aristasPrioridad = new PriorityQueue<>();// O(E) (Space)
		Vertice vertice = grafo.getVertex(nombreRaiz);
		arbolGeneradorMinimo.addVertex(nombreRaiz);

		// E = V-1 Propiedad arboles
		while (arbolGeneradorMinimo.numOfEdges() != numOfVertexsGrafo - 1) {
			HashSet<Vertice> verticesArbol = arbolGeneradorMinimo.getVertexs();

			// Tomamos aquellas aristas cuyo vertice final no pertenezca a
			// los vertices del Árbol.
			// Logramos tomar aristas que no producen ciclos y aristas repetidas.
			// Aprovechamos que sabemos que V0 es vertice.
			for (Arista arista : grafo.getEdgesOfVertex(vertice.getNombre())) {
				if (!verticesArbol.contains(arista.getVf())) {
					aristasPrioridad.add(arista); // O(log E)
				}
			}

			// Eliminamos aquellas aristas residuo (antiguas) que forman ciclos.
			aristasPrioridad.removeIf(((arista) -> verticesArbol.contains(arista.getVf())));
			Arista aristaMenorPeso = aristasPrioridad.poll(); // O(log E)

			arbolGeneradorMinimo.addVertex(aristaMenorPeso.getVf().getNombre());
			arbolGeneradorMinimo.addEdge(
					aristaMenorPeso.getV0().getNombre(),
					aristaMenorPeso.getVf().getNombre(),
					aristaMenorPeso.getPeso());

			vertice = aristaMenorPeso.getVf();
		}

		return arbolGeneradorMinimo;
	}
}
