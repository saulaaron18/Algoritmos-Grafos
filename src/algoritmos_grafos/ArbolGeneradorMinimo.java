package algoritmos_grafos;

import grafos.*;

public abstract class ArbolGeneradorMinimo {
	
	public static Digrafo Boruvka(Digrafo grafo) {
		Digrafo arbolGeneradorMinimo = new Digrafo();
		
		for(Vertice vertice:grafo.getVertexs()) {
			arbolGeneradorMinimo.addVertex(vertice.getNombre());
		}
		
		//Arbol -> |A| = |V| - 1
		//n-1 iteraciones 
		
		
		return arbolGeneradorMinimo;
	}
}
