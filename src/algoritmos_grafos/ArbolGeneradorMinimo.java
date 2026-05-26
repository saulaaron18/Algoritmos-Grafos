package algoritmos_grafos;

import grafos.*;

public final class ArbolGeneradorMinimo {
	
	public static Digrafo Boruvka(Digrafo grafo) {
		Digrafo arbolGeneradorMinimo = new Digrafo();
		
		// 1. Pillar el arbol trivial T con todos los vertices de grafo
		// 2. En Ti (componente conexo) añadimos la arista que conecta
		// con otro componente conexo en una lista
		// 3. Eliminamos las aristas que son iguales (para evitar duplicados)
		// 4. Añadimos esas aristas a T
		// 5. Si numOfEdges < numOfVertexs, volvemos al paso 2
		
		return arbolGeneradorMinimo;
	}
}
