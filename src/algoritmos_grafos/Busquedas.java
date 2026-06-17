package algoritmos_grafos;

import grafos.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

/**
 * Clase cuyos servicios son la creación de arboles de busquedas
 * (BEA,BEP / DFS,BFS) con distinta referencia.
 * 
 * La complejidad de ambas se resume en:
 * Time: O(V+E)
 * Space: O(V)
 * 
 * 
 * @author Saúl Aarón
 * @version 1.0
 */
public final class Busquedas {
	/**
	 * PRE: la raiz pertenece al grafo
	 * 
	 * @param nombreRaiz nombre de la raiz
	 * @param grafo      grafo el cuál se realizará la busqueda
	 * @return arbol de busqueda en profundidad como digrafo a partir de la raiz
	 * @throws IllegalArgumentException
	 */
	public static Digrafo busquedaEnProfundidad(String nombreRaiz, IGrafo grafo) throws IllegalArgumentException {
		if (grafo.getVertex(nombreRaiz) == null) {
			throw new IllegalArgumentException(
					"El nombre del vertice de comienzo (raíz) " + nombreRaiz + "no pertenece al grafo");
		}

		Digrafo arbolEnProfundidad = new Digrafo();

		// Pila de vertices encontrados durante el algoritmo y
		// HashSet de vertices visitados
		Deque<String> verticesPila = new ArrayDeque<>();
		HashSet<String> verticesVisitados = new HashSet<>();

		// Estado inicial, con arbol trivial, la pila de busqueda con raíz y
		// HashSet con raíz
		arbolEnProfundidad.addVertex(nombreRaiz);
		verticesPila.push(nombreRaiz);
		verticesVisitados.add(nombreRaiz);

		while (!verticesPila.isEmpty()) {
			boolean encontrado = false;
			HashSet<Arista> aristas = grafo.getEdgesOfVertex(verticesPila.peek());

			for (Arista arista : aristas) {
				String verticeDestino = arista.getVf().toString();

				if (!verticesVisitados.contains(verticeDestino)) {
					arbolEnProfundidad.addVertex(verticeDestino);
					arbolEnProfundidad.addEdge(verticesPila.peek(), verticeDestino, arista.getPeso());

					verticesPila.push(verticeDestino);
					verticesVisitados.add(verticeDestino);

					encontrado = true;

					break; // Salimos del bucle for()
				}
			} // Fin bucle for()

			if (!encontrado) {
				verticesPila.pop();
			}

		}

		return arbolEnProfundidad;
	}

	/**
	 * PRE: la raíz pertenece al grafo
	 * 
	 * @param nombreRaiz nombre de la raíz
	 * @param grafo      grafo el cual se realizará la busqueda
	 * @return arbol de busqueda en anchura a partir de la raíz
	 * @throws IllegalArgumentException
	 */
	public static Digrafo busquedaEnAnchura(String nombreRaiz, IGrafo grafo) throws IllegalArgumentException {
		if (grafo.getVertex(nombreRaiz) == null) {
			throw new IllegalArgumentException(
					"El nombre del vertice de comienzo (raíz) " + nombreRaiz + "no pertenece al grafo");
		}

		Digrafo arbolDeBusqueda = new Digrafo();

		// Inicialización de la cola y vertices
		Deque<String> verticesCola = new ArrayDeque<>();
		HashSet<String> verticesVisitados = new HashSet<>();

		// Estado inicial
		arbolDeBusqueda.addVertex(nombreRaiz);
		verticesCola.add(nombreRaiz);
		verticesVisitados.add(nombreRaiz);

		while (!verticesCola.isEmpty()) {
			// Obtenemos las aristas de la cabeza de la cola
			HashSet<Arista> aristas = grafo.getEdgesOfVertex(verticesCola.peekFirst());

			for (Arista arista : aristas) {
				String verticeDestino = arista.getVf().toString();

				// Verificamos que no los hayamos visitado antes
				if (!verticesVisitados.contains(verticeDestino)) {
					arbolDeBusqueda.addVertex(verticeDestino);
					arbolDeBusqueda.addEdge(verticesCola.peekFirst(), verticeDestino, arista.getPeso());

					verticesCola.add(verticeDestino);
					verticesVisitados.add(verticeDestino);
				}
			} // FIN bucle for()

			// Eliminamos el primer elemento
			verticesCola.poll();
		}

		return arbolDeBusqueda;
	}
}
