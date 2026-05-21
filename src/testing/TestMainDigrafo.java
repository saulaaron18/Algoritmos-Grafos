package testing;

import grafos.*;

public class TestMainDigrafo {

	public static void main(String[] args) {
		Digrafo grafo = new Digrafo();
		
		grafo.addVertex("A");
		grafo.addVertex("B");
		grafo.addVertex("C");
				
		System.out.println(grafo);

		System.out.println(grafo.addEdge("A", "B", 1)); //True
		grafo.addEdge("A", "C", 3);
		grafo.addEdge("C", "B", -2);
		
		System.out.println(grafo);
		System.out.println(grafo.showEdges());
		System.out.println(grafo.showEdges());
		
		grafo.removeEdge("A", "B");
		
		System.out.println(grafo);
		System.out.println(grafo.showEdges());
		System.out.println(grafo.showEdges());
	}

}
