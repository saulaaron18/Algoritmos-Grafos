package testing;

import grafos.*;

public class TestMainGrafo {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		
		grafo.añadirVertice("A");
		grafo.añadirVertice("B");
		grafo.añadirVertice("C");
		
		System.out.println(grafo);

		grafo.añadirArista("A", "B", 1);
		grafo.añadirArista("A", "C", 3);
		grafo.añadirArista("C", "B", -2);
		
		System.out.println(grafo.toString());
		
		grafo.eliminarArista("A", "B");
		
		System.out.println(grafo);
	}

}
