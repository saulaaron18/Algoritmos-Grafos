package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import grafos.Arista;
import grafos.Grafo;

public class TestGrafo {

    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();
    }

    // ===== Vertex operations =====

    @Test
    public void addVertex_returnsTrue() {
        assertTrue(grafo.addVertex("A"));
    }

    @Test
    public void addVertex_incrementsCount() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        assertEquals(3, grafo.numOfVertexs());
    }

    @Test
    public void addVertex_duplicate_returnsFalse() {
        grafo.addVertex("A");
        assertFalse(grafo.addVertex("A"));
    }

    @Test
    public void removeVertex_returnsTrue() {
        grafo.addVertex("A");
        assertTrue(grafo.removeVertex("A"));
        assertEquals(0, grafo.numOfVertexs());
    }

    @Test
    public void removeVertex_notFound_returnsFalse() {
        assertFalse(grafo.removeVertex("Z"));
    }

    @Test
    public void removeVertex_removesConnectedEdges() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 5);
        grafo.removeVertex("A");
        assertEquals(0, grafo.numOfEdges());
    }

    // ===== Edge operations =====

    @Test
    public void addEdge_returnsTrue() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        assertTrue(grafo.addEdge("A", "B", 5));
    }

    @Test
    public void addEdge_incrementsEdgeCount() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 5);
        assertEquals(1, grafo.numOfEdges());
    }

    @Test
    public void addEdge_bothDirectionsInAdjacency() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 5);
        // Grafo adds both A→B and B→A to the adjacency map
        assertNotNull(grafo.getEdge("A", "B"));
        assertNotNull(grafo.getEdge("B", "A"));
    }

    @Test
    public void addEdge_missingOrigin_returnsFalse() {
        grafo.addVertex("A");
        assertFalse(grafo.addEdge("X", "A", 1));
    }

    @Test
    public void addEdge_missingDestino_returnsFalse() {
        grafo.addVertex("A");
        assertFalse(grafo.addEdge("A", "X", 1));
    }

    @Test
    public void addEdge_duplicate_returnsFalse() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 5);
        assertFalse(grafo.addEdge("A", "B", 5));
        assertEquals(1, grafo.numOfEdges());
    }

    @Test
    public void removeEdge_originalDirection_returnsTrue() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 5);
        assertTrue(grafo.removeEdge("A", "B"));
        assertEquals(0, grafo.numOfEdges());
    }

    @Test
    public void removeEdge_reverseDirection_returnsTrue() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 5);
        assertTrue(grafo.removeEdge("B", "A"));
        assertEquals(0, grafo.numOfEdges());
    }

    @Test
    public void removeEdge_notFound_returnsFalse() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        assertFalse(grafo.removeEdge("A", "B"));
    }

    @Test
    public void removeEdge_clearsAdjacency() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 5);
        grafo.removeEdge("A", "B");
        assertTrue(grafo.getEdgesOfVertex("A").isEmpty());
        assertTrue(grafo.getEdgesOfVertex("B").isEmpty());
    }

    // ===== showEdges — single direction stored =====

    @Test
    public void showEdges_containsOriginDirection() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 3);
        String result = grafo.showEdges();
        assertEquals("[(A, B, 3)]", result);
    }

    @Test
    public void showEdges_multipleEdges_eachOnce() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        grafo.addEdge("A", "B", 1);
        grafo.addEdge("B", "C", 2);
        grafo.addEdge("A", "C", 3);
        assertEquals(3, grafo.numOfEdges());
        String result = grafo.showEdges();
        // Each undirected pair must appear exactly once across both orderings
        int countAB = countOccurrences(result, "A, B") + countOccurrences(result, "B, A");
        int countBC = countOccurrences(result, "B, C") + countOccurrences(result, "C, B");
        int countAC = countOccurrences(result, "A, C") + countOccurrences(result, "C, A");
        assertEquals(1, countAB);
        assertEquals(1, countBC);
        assertEquals(1, countAC);
    }

    // ===== getEdge and numOfEdges =====

    @Test
    public void getEdge_originDirection() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 7);
        Arista arista = grafo.getEdge("A", "B");
        assertNotNull(arista);
        assertEquals(7, arista.getPeso());
    }

    @Test
    public void getEdge_reverseDirection() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 7);
        // Grafo added B→A to B's adjacency, so getEdge("B","A") must find it
        assertNotNull(grafo.getEdge("B", "A"));
    }

    @Test
    public void getEdge_notFound_returnsNull() {
        grafo.addVertex("A");
        grafo.addVertex("C");
        assertNull(grafo.getEdge("A", "C"));
    }

    @Test
    public void numOfEdges_emptyGraph() {
        assertEquals(0, grafo.numOfEdges());
    }

    @Test
    public void numOfEdges_afterMultipleAdds() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        grafo.addEdge("A", "B", 1);
        grafo.addEdge("B", "C", 2);
        assertEquals(2, grafo.numOfEdges());
    }

    @Test
    public void numOfEdges_afterRemove() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 1);
        grafo.removeEdge("A", "B");
        assertEquals(0, grafo.numOfEdges());
    }

    // ===== Error / edge cases =====

    @Test
    public void addEdge_selfLoop() {
        // Grafo has no self-loop guard: creates one edge A→A
        grafo.addVertex("A");
        assertFalse(grafo.addEdge("A", "A"));
        assertEquals(0, grafo.numOfEdges());
    }

    @Test
    public void removeVertex_removesFromBothAdjacencies() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        grafo.addEdge("A", "B", 1);
        grafo.addEdge("A", "C", 2);
        grafo.addEdge("B", "C", 3);
        grafo.removeVertex("B");
        // No edge involving B must remain in A's or C's adjacency list
        for (Arista a : grafo.getEdgesOfVertex("A")) {
            assertFalse(a.contieneVertice("B"));
        }
        for (Arista a : grafo.getEdgesOfVertex("C")) {
            assertFalse(a.contieneVertice("B"));
        }
    }

    @Test
    public void getEdgesOfVertex_afterRemoveEdge() {
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addEdge("A", "B", 5);
        grafo.removeEdge("A", "B");
        assertTrue(grafo.getEdgesOfVertex("A").isEmpty());
    }

    // ===== Helper =====

    private int countOccurrences(String text, String pattern) {
        int count = 0;
        int index = text.indexOf(pattern);
        while (index != -1) {
            count++;
            index += pattern.length();
        }
        return count;
    }
}
