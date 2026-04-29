package algoritmos_grafos;

import grafos.*;

public abstract class ArbolGeneradorMinimo {
	
	public static Grafo Boruvka(Grafo grafo) {
		Grafo arbolGeneradorMinimo = new Grafo();
		
		for(Vertice vertice:grafo.getVertices()) {
			arbolGeneradorMinimo.añadirVertice(vertice.getNombre());
		}
		
		//Arbol -> |A| = |V| - 1
		int n = arbolGeneradorMinimo.numOfVertices();
		
		for(int i=0;i<n;i++) {
			
		}
		
		return arbolGeneradorMinimo;
	}
}
