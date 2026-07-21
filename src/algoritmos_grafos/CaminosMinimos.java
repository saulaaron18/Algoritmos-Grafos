package algoritmos_grafos;

import grafos.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Clase cuyos servicios es la creación de un árbol de caminos mínimos,
 * es decir, de distancia mínima.
 * 
 * La complejidad se resume en:
 * Time:
 * Space:
 * 
 * 
 * @author Saúl Aarón
 * @version 1.0
 */
public final class CaminosMinimos {

    /**
     * PRE: el grafo debe ser conexo, de pesos positivos y no nulo.
     * 
     * @param grafo      puede ser de clase Grafo o Digrafo
     * @param nombreRaiz
     * @return objeto de la Clase del parametro "grafo" de distancia mínima
     */
    public IGrafo Dijkstra(IGrafo grafo, String nombreRaiz) {
        IGrafo arbolCaminosMinimos = (grafo instanceof Grafo) ? new Grafo() : new Digrafo();

        if (grafo == null) {
            throw new IllegalArgumentException(
                    "El grafo es nulo. Inserte un grafo no nulo.");
        }

        if (Busquedas.busquedaEnProfundidad(nombreRaiz, grafo).numOfVertexs() != grafo.numOfVertexs()) {
            throw new IllegalArgumentException(
                    "El grafo dado no es conexo. Inserte un grafo conexo.");
        }

        for (Arista arista : grafo.getEdges()) {
            if (arista.getPeso() < 0) {
                throw new IllegalArgumentException(
                        "El grafo dado cuenta con una arista negativa: " + arista + ".");
            }
        }

        // Inicialización
        PriorityQueue<AristaDistancia> aristasDistanciaPrioridad = new PriorityQueue<>();
        HashMap<String, Integer> verticeDist = new HashMap<>();
        HashSet<String> verticesVisitados = new HashSet<>(); // Creo que se puede sustituir con el atributo vertices

        verticeDist.put(nombreRaiz, 0);
        verticesVisitados.add(nombreRaiz);
        arbolCaminosMinimos.addVertex(nombreRaiz);

        String pivote = nombreRaiz;

        int grafoNumOfVertexs = grafo.numOfVertexs();

        while (verticesVisitados.size() != grafoNumOfVertexs) {

            // Tomamos las aristas del pivote
            for (Arista aristaPivote : grafo.getEdgesOfVertex(pivote)) {
                String verticeFinal = aristaPivote.getVf().getNombre();

                // Comprobamos que el vertice final de la arista del pivote
                // no pertenezca a los vertices visitados
                if (!verticesVisitados.contains(verticeFinal)) {
                    int distanciaArista = verticeDist.get(pivote) + aristaPivote.getPeso();

                    // Si el vertice final no está en el HashMap la cota inferior, la añadimos
                    if (!verticeDist.containsKey(verticeFinal)) {
                        verticeDist.put(verticeFinal, distanciaArista);
                        aristasDistanciaPrioridad.add(
                                new AristaDistancia(pivote, verticeFinal, distanciaArista, aristaPivote.getPeso()));
                    }

                    // Si el vertice final está en el HashMap y la distancia acumulada es menor que
                    // su cota inferior la sustituimos
                    else if (distanciaArista < verticeDist.get(verticeFinal)) {
                        verticeDist.replace(verticeFinal, distanciaArista);

                        AristaDistancia aristaDistancia = new AristaDistancia(pivote, verticeFinal, distanciaArista,
                                aristaPivote.getPeso());

                        aristasDistanciaPrioridad.remove(aristaDistancia);
                        aristasDistanciaPrioridad.add(aristaDistancia);
                    }
                }
            } // Fin del "for" aristaPivote

            // Eliminamos aristas que al tomarlas, puedan generar ciclos
            aristasDistanciaPrioridad.removeIf(((arista) -> verticesVisitados.contains(arista.getVf())));

            // Tomamos la arista de menor distancia con la raíz
            AristaDistancia mejorAristaDistancia = aristasDistanciaPrioridad.poll();

            // Cambiamos el pivote a el vertice final de la arista de menor distancia de la
            // priority queue
            pivote = mejorAristaDistancia.getVf();

            verticesVisitados.add(pivote);
            arbolCaminosMinimos.addVertex(pivote);
            arbolCaminosMinimos.addEdge(mejorAristaDistancia.getV0(), pivote, mejorAristaDistancia.getPeso());
        }

        return arbolCaminosMinimos;
    }

    private class AristaDistancia implements Comparable<AristaDistancia> {
        private final String v0;
        private final String vf;
        private final int dist;
        private final int peso;

        public AristaDistancia(String v0, String vf, int dist, int peso) {
            this.v0 = v0;
            this.vf = vf;
            this.dist = dist;
            this.peso = peso;
        }

        public String getV0() {
            return this.v0;
        }

        public String getVf() {
            return this.vf;
        }

        public int getPeso() {
            return this.peso;
        }

        @Override
        public int compareTo(AristaDistancia o) {
            return this.dist - o.dist;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AristaDistancia)) {
                return false;
            }

            AristaDistancia o = (AristaDistancia) obj;

            return vf.equals(o.vf);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vf);
        }

    }
}