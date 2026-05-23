# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Java console application for graph simulation and shortest-path algorithms, built and run via Eclipse IDE (no Maven/Gradle). The classpath is managed by Eclipse: sources in `src/`, compiled output to `bin/`, JUnit 4 on the classpath via the Eclipse JUnit container.

## Building and Running

This project has no build script — compile and run through Eclipse or `javac`/`java` manually:

```powershell
# Compile (from project root)
javac -cp "bin;path\to\junit-4.jar;path\to\hamcrest-core.jar" -d bin (Get-ChildItem src -Recurse -Filter *.java | % { $_.FullName })

# Run console menu
java -cp bin Menu

# Run a specific manual test main
java -cp bin testing.TestMainGrafo
java -cp bin testing.TestMainDigrafo

# Run JUnit tests (requires junit-4.jar and hamcrest-core.jar on classpath)
java -cp "bin;path\to\junit-4.jar;path\to\hamcrest-core.jar" org.junit.runner.JUnitCore testing.TestGrafo
```

In practice, use Eclipse: **Run As → JUnit Test** for `TestGrafo`, and **Run As → Java Application** for the `Main*` classes and `Menu`.

## Architecture

The graph layer lives in `src/grafos/`:

- `Vertice` — node identified by a trimmed `String` name; `equals`/`hashCode` based on name only.
- `Arista` — directed edge `(v0 → vf, peso)`; `equals`/`hashCode` based on `(v0, vf)` only, so duplicate detection ignores weight.
- `IGrafo` — interface defining all graph operations.
- `Digrafo implements IGrafo` — base directed graph. Stores three collections: `HashSet<Vertice>`, `HashSet<Arista>`, and `HashMap<Vertice, HashSet<Arista>>` (adjacency map). Exposes `protected int[][] matrizAdyacencias()` used by `toString()`.
- `Grafo extends Digrafo` — undirected graph. Overrides `addEdge`/`removeEdge` to maintain **both** directions in the adjacency map, and stores only **one** `Arista` object (origin→destination) in `getEdges()`. Overrides `matrizAdyacencias()` to mirror both directions.

Algorithm layer lives in `src/algoritmos_grafos/`:

- `Busquedas` — abstract class with static methods `busquedaEnProfundidad` (DFS) and `busquedaEnAnchura` (BFS). Both return a `Digrafo` representing the search tree.
- `ArbolGeneradorMinimo` — abstract class with a stub `Boruvka(Digrafo)` (not yet implemented).

Entry point: `src/Menu.java`.

Manual smoke tests: `src/testing/TestMainGrafo.java` and `TestMainDigrafo.java` (plain `main` methods).  
JUnit 4 tests: `src/testing/TestGrafo.java`.

## JUnit Test Conventions

- Use `assertEquals` for `String` comparisons — never `assertTrue(s.contains(...))`.
- Use methods that return `boolean` (e.g., `isEmpty()`, `contieneVertice()`) with `assertTrue`/`assertFalse` — never wrap a non-boolean expression.
- Tests for `showVertexs()`/`showEdges()` must compare against the exact `toString()` output of the underlying `HashSet`.

## Key Behavioral Notes

- `Grafo.addEdge` stores only one `Arista(origen, destino)` in `getEdges()` but adds both directions to the adjacency map — querying `getEdge("B","A")` works even though `(B,A)` is not in `getEdges()`.
- `Arista.equals` ignores `peso`, so adding an edge with the same pair but different weight returns `false` from the `HashSet.add` call (treated as duplicate).
- `removeVertex` in `Digrafo` removes the vertex from `verticesAdyacentes` but does **not** call `vertices.remove`; the vertex set is effectively the keyset of `verticesAdyacentes`.
