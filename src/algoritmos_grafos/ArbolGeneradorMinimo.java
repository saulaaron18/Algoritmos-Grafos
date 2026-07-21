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
 * @version 1.1
 */
public final class ArbolGeneradorMinimo {

	/**
	 * PRE: el grafo debe ser conexo y no nulo.
	 * 
	 * @param grafo
	 * @param nombreRaiz
	 * @return objeto de clase Grafo de pesos minimos
	 */
	public static Grafo Prim(Grafo grafo, String nombreRaiz) {
		Grafo arbolGeneradorMinimo = new Grafo();

		if (grafo == null) {
			throw new IllegalArgumentException(
					"El grafo es nulo. Inserte un grafo no nulo.");
		}

		if (Busquedas.busquedaEnProfundidad(nombreRaiz, grafo).numOfVertexs() != grafo.numOfVertexs()) {
			throw new IllegalArgumentException(
					"El grafo dado no es conexo. Inserte un grafo conexo.");
		}

		// Inicialización
		int numOfVertexsGrafo = grafo.numOfVertexs();

		PriorityQueue<Arista> aristasPrioridad = new PriorityQueue<>();// O(E) (Space)
		Vertice vertice = grafo.getVertex(nombreRaiz);
		arbolGeneradorMinimo.addVertex(nombreRaiz);

		// número vertices arbol == número de vertices grafo original
		while (arbolGeneradorMinimo.numOfVertexs() != numOfVertexsGrafo) {
			HashSet<Vertice> verticesArbol = arbolGeneradorMinimo.getVertexs();

			// Tomamos aquellas aristas cuyo vertice final no pertenezca a
			// los vertices del Árbol.
			// Logramos tomar aristas que no producen ciclos y aristas repetidas.
			// Aprovechamos que sabemos que V0 es vertice.
			for (Arista arista : grafo.getEdgesOfVertex(vertice)) {
				if (!verticesArbol.contains(arista.getVf())) {
					aristasPrioridad.add(arista); // O(log E)
				}
			}

			// Eliminamos aquellas aristas residuo (antiguas) que forman ciclos.
			aristasPrioridad.removeIf(((arista) -> verticesArbol.contains(arista.getVf())));
			Arista aristaMenorPeso = aristasPrioridad.poll(); // O(log E)

			vertice = aristaMenorPeso.getVf();

			arbolGeneradorMinimo.addVertex(vertice);
			arbolGeneradorMinimo.addEdge(
					aristaMenorPeso.getV0(),
					vertice,
					aristaMenorPeso.getPeso());
		}

		return arbolGeneradorMinimo;
	}
}
