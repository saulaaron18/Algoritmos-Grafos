package algoritmos_grafos;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

import grafos.*;

public abstract class Busquedas {

	/**
	 * PRE: la raiz pertenece al grafo
	 * 
	 * @param raiz
	 * @param grafo
	 * @return
	 */
	public static IGrafo busquedaEnProfundidad(String nombreRaiz, IGrafo grafo) {
		IGrafo arbolEnProfundidad = new Digrafo();

		//Cola de vertices encontrados durante el algoritmo y
		//HashSet de vertices visitados
		Deque<String> verticesCola = new ArrayDeque<String>();
		HashSet<String> verticesVisitados = new HashSet<String>();

		//Estado inicial, con Arbol trivial, la cola de busqueda con raíz y 
		//HashSet con raíz
		arbolEnProfundidad.addVertex(nombreRaiz);
		verticesCola.push(nombreRaiz);
		verticesVisitados.add(nombreRaiz);

		while(!verticesCola.isEmpty()) {
			boolean encontrado = false;
			HashSet<Arista> aristas = grafo.getEdgesOfVertex(verticesCola.peek());

			for(Arista arista:aristas) {
				String verticeDestino = arista.getVf().toString();

				if(!verticesVisitados.contains(verticeDestino)) {
					arbolEnProfundidad.addVertex(verticeDestino);
					arbolEnProfundidad.addEdge(verticesCola.peek(), verticeDestino, arista.getPeso());

					verticesCola.push(verticeDestino);
					verticesVisitados.add(verticeDestino);

					encontrado = true;

					break; //Salimos del bucle for()
				}
			}//Fin bucle for()

			if(!encontrado) {
				verticesCola.pop();
			}

		}

		return arbolEnProfundidad;
	}

	/**
	 * PRE: la raíz pertenece al grafo
	 * 
	 * @param nombreRaiz
	 * @param grafo
	 * @return
	 */
	public static Digrafo busquedaEnAnchura(String nombreRaiz, Digrafo grafo) {
		Digrafo arbolDeBusqueda = new Digrafo();

		//Inicialización de la cola y vertices
		Deque<String> verticesCola = new ArrayDeque<String>();
		HashSet<String> verticesVisitados = new HashSet<String>();

		//Estado inicial
		arbolDeBusqueda.addVertex(nombreRaiz);
		verticesCola.add(nombreRaiz);
		verticesVisitados.add(nombreRaiz);

		while(!verticesCola.isEmpty()) {
			//Obtenemos las aristas de la cabeza de la cola
			HashSet<Arista> aristas = grafo.getEdgesOfVertex(verticesCola.peekFirst());
			
			for(Arista arista:aristas) {
				String verticeDestino = arista.getVf().toString();
				
				//Verificamos que no los hayamos visitado antes
				if(!verticesVisitados.contains(verticeDestino)) {
					arbolDeBusqueda.addVertex(verticeDestino);
					arbolDeBusqueda.addEdge(verticesCola.peekFirst(), verticeDestino, arista.getPeso());

					verticesCola.add(verticeDestino);
					verticesVisitados.add(verticeDestino);
				}
			}//FIN bucle for()
			
			//Eliminamos el primer elemento
			verticesCola.poll();
		}

		return arbolDeBusqueda;
	}
}
