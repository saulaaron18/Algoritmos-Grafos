# Proyecto 1: Simulador de Grafos y Caminos Mínimos
Construir una aplicación de consola en Java que permita al usuario crear un grafo simple, dirigido y ponderado, y sobre él ejecutar distintas operaciones. 
El programa es interactivo, con un menú que ofrece las siguientes funcionalidades:
1. Gestión del grafo
Añadir nodos (identificados por etiqueta).
Añadir aristas con peso entre dos nodos.
Eliminar nodos y aristas.
Mostrar el grafo como lista de adyacencia.
3. Algoritmo de Dijkstra
Dado un nodo origen, calcular el camino más corto hacia todos los demás nodos.
Mostrar tanto la distancia como el camino recorrido.
4. Detección de ciclos
Indicar si el grafo contiene algún ciclo (con el algoritmo de BEP)
5. Exportar el grafo a fichero .csv
Guardar la lista de aristas en formato: origen,destino,peso. (por aprender)

Ejemplo de sesión:

--- MENÚ ---

1. Añadir nodo
2. Añadir arista
3. Mostrar grafo
4. Camino mínimo (Dijkstra)
5. Detectar ciclos
6. Exportar a CSV
7. Cargar desde CSV
0. Salir
