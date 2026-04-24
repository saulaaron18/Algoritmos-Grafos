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
		ArrayList<Vertice> verticesEncontrados = new ArrayList<Vertice>();
		HashSet<Arista> aristasEncontradas = new HashSet<Arista>();
		
		arbolEnProfundidad.añadirVertice(nombreRaiz);
		verticesEncontrados.add(arbolEnProfundidad.buscarVertice(nombreRaiz));
		
		while(verticesEncontrados.size()>0) {
			HashSet<Arista> aristasBusqueda = (HashSet<Arista>) grafo.getVerticesAdyacentes().
					get(verticesEncontrados.get(verticesEncontrados.size())).clone();
			
			aristasBusqueda.removeAll(aristasEncontradas);
			
			if(aristasBusqueda.isEmpty()) {
				verticesEncontrados.remove(verticesEncontrados.size()-1);
			}
			
			else {
				Arista arista = (Arista) aristasBusqueda.toArray()[0];
				Vertice vertice = arista.getVf();
				
				aristasEncontradas.add(arista);
				verticesEncontrados.add(vertice);
				
				arbolEnProfundidad.añadirVertice(vertice.toString());
				arbolEnProfundidad.añadirArista(
						verticesEncontrados.get(verticesEncontrados.size()-2).toString(), 
						vertice.toString(), 
						arista.getPeso());
			}
		}
		
		return arbolEnProfundidad;
	}
}
