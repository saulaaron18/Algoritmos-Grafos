# DIA 1

Durante el primer día, me dispuse a pensar en el objetivo y el tipo de proyecto que iba a realizar.
Mi proposito con este proyecto y los siguientes es adelantarme o/y aprender más sobre la gestión de proyectos,
resolución de problemas más interesantes y implementar las herramientas enseñadas en la universidad, además de otras que no las enseñan.

Empezamos con la creación de las tres primeras clases: Vertice, Arista y Grafo.
Pensé implementar en Vertice únicamente el atributo nombre, en Arista los atributos de clase Vertice (los nodos inicial y final) y un peso,
y en el Grafo ya añadir dos ArrayList de estas dos clases.

Por último, añadí los getters en las clases Vertice y Arista, y los metodos añadirAristas() y añadirVertices().

# DIA 2

Mayor avance en el código, además de conocer lo que es un HashMap<E>() en java, resultando ser para mi impresión,
muy similar a los diccionarios de Python o los json de JavaScript.

Para mostrar en el toString del Grafo, una matriz de adyaciencia de vertices, resultó mucho más complicado de lo que pensé, la creación
de una matriz que fuese realmente óptima, ya que iterando fila y columna, y comprobando que el vertice de origen y destino es igual a
una de las aristas de la lista, y que luego si ponemos o 0 o el peso, hacía muy confuso el código y con una complejidad muy alta (creo que O(n^3)).
Por ello, tuve que recurrir a Cloude para que me sugiera alguna manera de poder optimizar más mi codigo, dando con el TAD HashMap<E>().

Tras leer un poco los métodos que más me interesaban y ver unos cuantos ejemplos, me dispuse a ver la manera de implementarlo.
Logré mejorarlo más, pero no creí que fuera suficiente, por lo tanto, le pedí un poco más de ayuda a Cloude, dandome cuenta que 
el get del HashMap tiene complejidad O(1), pudiendo resolver incluso mejor el problema de optimización de la matriz de adyaciencia.

Al final del día, quería implementar métodos que eliminasen los vertices o aristas del ArrayList<>(). Y me he quedado con la pequeña duda
de ¿Si elimino una arista de la lista, también eliminaré el de los vertices del que está instanciado? Y ¿Si elimino un vertice de la lista, la arista actualiza el vertice instanciado a null?

Después de preguntarselo a Cloude, me recordó lo principal en java, y es que los objetos no se copian, se guardan referencias.
