package testing;

import algoritmos_grafos.CaminosMinimos;
import grafos.Grafo;
import grafos.IGrafo;
import java.util.Arrays;

public class TestDijkstra {

    public static void main(String[] args) {
        ejecutarEscenario("Escenario 1: grafo simple", crearGrafoSimple(), "A");
        ejecutarEscenario("Escenario 2: grafo con varias rutas", crearGrafoVariasRutas(), "A");
        ejecutarEscenario("Escenario 3: grafo con mas nodos", crearGrafoMasNodos(), "A");
    }

    private static void ejecutarEscenario(String nombre, IGrafo grafo, String raiz) {
        System.out.println("==================== " + nombre + " ====================");
        System.out.println("Grafo original:\n" + grafo);

        CaminosMinimos caminosMinimos = new CaminosMinimos();
        IGrafo resultado = caminosMinimos.Dijkstra(grafo, raiz);

        System.out.println("Resultado de Dijkstra desde " + raiz + ":\n" + resultado);
    }

    private static IGrafo crearGrafoSimple() {
        Grafo grafo = new Grafo();
        Arrays.asList("A", "B", "C", "D").forEach(grafo::addVertex);

        grafo.addEdge("A", "B", 1);
        grafo.addEdge("A", "C", 4);
        grafo.addEdge("B", "C", 2);
        grafo.addEdge("B", "D", 5);
        grafo.addEdge("C", "D", 1);

        return grafo;
    }

    private static IGrafo crearGrafoVariasRutas() {
        Grafo grafo = new Grafo();
        Arrays.asList("A", "B", "C", "D", "E").forEach(grafo::addVertex);

        grafo.addEdge("A", "B", 3);
        grafo.addEdge("A", "C", 7);
        grafo.addEdge("B", "C", 1);
        grafo.addEdge("B", "D", 2);
        grafo.addEdge("C", "D", 1);
        grafo.addEdge("C", "E", 4);
        grafo.addEdge("D", "E", 1);

        return grafo;
    }

    private static IGrafo crearGrafoMasNodos() {
        Grafo grafo = new Grafo();
        Arrays.asList("A", "B", "C", "D", "E", "F").forEach(grafo::addVertex);

        grafo.addEdge("A", "B", 2);
        grafo.addEdge("A", "C", 5);
        grafo.addEdge("B", "C", 1);
        grafo.addEdge("B", "D", 4);
        grafo.addEdge("C", "D", 2);
        grafo.addEdge("C", "E", 6);
        grafo.addEdge("D", "E", 1);
        grafo.addEdge("D", "F", 3);
        grafo.addEdge("E", "F", 2);

        return grafo;
    }
}
