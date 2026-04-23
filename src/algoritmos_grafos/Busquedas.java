package algoritmos_grafos;

import java.util.ArrayList;
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
		Vertice vertice = new Vertice(nombreRaiz);
		
		ArrayList<Vertice> busqueda = new ArrayList<Vertice>();
		
		busqueda.add(vertice);
		while(!busqueda.isEmpty()) {
			HashSet<Arista> aristas = grafo.getVerticesAdyacentes().get(vertice);
			if(aristas.isEmpty()) {
				busqueda.remove(vertice);
			}
			
		}
		
		return arbolEnProfundidad;
	}
}
