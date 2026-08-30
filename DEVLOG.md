# 📋 Dev Log — Graph Simulator / Simulador de Grafos

> Personal learning journal for this project.

---

## 🏁 Milestone 1 — Core Graph Structure / Estructura Base del Grafo

> First working version of the three core classes.

### ✅ What was done / Qué se hizo
- Diseño e implementación de `Vertice`, `Arista` y `Grafo`
- Getters en `Vertice` y `Arista`
- Métodos `añadirArista()` y `añadirVertice()`
- Numero de `Vertice` y `Arista`
- Matriz de Adyaciencias por Vertices

### 🧠 Key learnings / Aprendizajes clave
- **`HashMap<K,V>`** en Java funciona de forma análoga a los diccionarios 
  de Python o los objetos JSON en JS — búsqueda en O(1)
- En Java los objetos no se copian, se pasan por referencia. Eliminar 
  una arista de la lista no afecta a los vértices que referencia

### 🐛 Challenges / Dificultades
- La matriz de adyacencias con triple bucle era O(n³) →  
  **Solución:** usar `HashMap<Vertice, Integer>` para índices, 
  reduciendo a O(n²)

### ⏭️ Next steps / Próximos pasos
- [ X ] Diseñar menú interactivo por consola

---

## 🏁 Milestone 2 — Interactive Menu / Menú Interactivo

> Console menu with full CRUD for nodes and edges.  
> *Menú de consola con CRUD completo para vértices y aristas.*

### ✅ What was done / Qué se hizo
- Menú principal con submenús para vértices y aristas
- Migración de `ArrayList` a `HashMap` + `HashSet` en `Grafo`
- Eliminado el atributo `grado` de `Vertice` (se calcula con `.size()`)

### 🧠 Key learnings / Aprendizajes clave
- El `Scanner` en Java usa un buffer compartido con `System.in` — 
  múltiples instancias generan conflictos. **Un único `Scanner` 
  estático** resuelve el problema

### 🐛 Challenges / Dificultades
- Bug en `toString()` al cambiar a `HashMap` → resuelto analizando 
  el orden de iteración del keySet
- Conflicto entre `sc.nextLine()` y `sc.nextInt()` por el buffer 
  del Scanner

### ⏭️ Next steps / Próximos pasos
- [ ] Implementar algoritmos (Dijkstra, Prim, Busquedas)
- ~~[ ] Exportar/importar CSV~~ 
- [ X ] Sistema guardado con texto
- [ ] Sistema de guardado con JSON

---

## 🏁 Milestone 3 — Algorithms / Algoritmos

> Implementations of some algorithms.  
> *Implementación de algunos algoritmos.*

### ✅ What was done / Qué se hizo
- Implementación de Busqueda en Profundidad (BEP)
- Implementación de Busqueda en Anchura (BEA)

### 🧠 Key learnings / Aprendizajes clave
- Identificar que TADs usar (pila o cola) o que estructura de datos
  para cada algoritmo para facilitar la busqueda
- Aprender el funcionamiento de un arbol binario

### 🐛 Challenges / Dificultades
- Estructurar el proceso algoritmico a partir de mi código
- Combinar las distintas estructuras para obtener un óptimo resultado

### ⏭️ Next steps / Próximos pasos
- [ X ] Implementar algoritmo de Prim
- [ X ] Implementar algoritmo de Dijkstra