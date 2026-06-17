package testing;

import algoritmos_grafos.ArbolGeneradorMinimo;
import grafos.Grafo;

public class TestPrim {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        grafo.addVertex("D");

        grafo.addEdge("A", "B", 1);
        grafo.addEdge("A", "C", 3);
        grafo.addEdge("B", "C", 2);
        grafo.addEdge("C", "D", 10);

        System.out.println(grafo);

        Grafo arbol = ArbolGeneradorMinimo.Prim(grafo, "A");

        System.out.println(arbol);
    }
}
