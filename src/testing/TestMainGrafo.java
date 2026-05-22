package testing;

import grafos.Grafo;

public class TestMainGrafo {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		
		grafo.addVertex("A");
		grafo.addVertex("B");
		grafo.addVertex("C");
		
		grafo.addEdge("A", "B", -1);
		//grafo.addEdge("B", "A", 3);
		
		System.out.println(grafo);
		
		grafo.removeEdge("B", "A");
		
		System.out.println(grafo);
		System.out.println(grafo.getEdges());
	}

}
