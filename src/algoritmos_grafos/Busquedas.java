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
	public static Grafo busquedaEnProfundidad(String nombreRaiz, Grafo grafo) {
		Grafo arbolEnProfundidad = new Grafo();

		//Cola de vertices encontrados durante el algoritmo y
		//HashSet de vertices visitados
		Deque<String> verticesCola = new ArrayDeque<String>();
		HashSet<String> verticesVisitados = new HashSet<String>();

		//Estado inicial, con Arbol trivial, la cola de busqueda con raíz y 
		//HashSet con raíz
		arbolEnProfundidad.añadirVertice(nombreRaiz);
		verticesCola.push(nombreRaiz);
		verticesVisitados.add(nombreRaiz);

		while(!verticesCola.isEmpty()) {
			boolean encontrado = false;
			HashSet<Arista> aristas = grafo.getAristas(verticesCola.peek());
			
			for(Arista arista:aristas) {
				String verticeDestino = arista.getVf().toString();
				
				if(!verticesVisitados.contains(verticeDestino)) {
					arbolEnProfundidad.añadirVertice(verticeDestino);
					arbolEnProfundidad.añadirArista(verticesCola.peek(), verticeDestino, arista.getPeso());
					
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
}
