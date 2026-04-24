# DÍA 1
He logrado terminar de implementar la prueba de usar únicamente HashMaps y HashSet para el grafo, modificando todos los métodos, además de eliminar el atributo grado de los vertices, ya que se puede calcular fácilmente usando el size del vertice en cuestión.

Me surgió un pequeño bug con el toString, pero logré resolverlo luego de pensarlo un poco.

También he ido diseñando el menú en el programa principal, aunque me da algunos errores.

# DÍA 2
He continuado implementando el menú, aunque me están surgiendo unos cuantos errores, lo cuales buscaré la manera de solucionarlo. 

# DÍA 3
Después de preguntar a Claude por el error que tenía con los Scanneres, me explicó sobre los buffers, el System y el Scanner. 

Realicé un atributo privado del Scanner, para que cada llamada sea al mismo Scanner del sistema y ya pude arreglar el menú.

# DÍA 4
El día de hoy quería empezar con los algoritmos, pero me he quedado con la duda de cómo implementarlo.

Por ahora, creo que lo mejor es realizar los algoritmos de búsqueda en profundidad y en anchura, aunque resulta un poco más complicado de lo que parece, por lo que lo mejor es que divida el problema, empiece con ciertas características como el de iterar y observar las adyaciencias. 

En resumen, voy a tener que plantear los algoritmos en papel, ya que así lo visualizo mejor y consultar a Claude mis puntos de vista sobre qué tipo de modulos, clases y métodos debería de implementar para que todo este lo mejor estructurado posible.

# DÍA 5
He pasado el tiempo en el tren y en mi casa mirando cómo poder implementar la busqueda en profundidad, y me ha parecido muy interesante intentar resolverlo.

Los principales problemas que tuve fueron:

- La implementación del bucle y su parada (¿Paro si todos los vertices por los que he pasado no tienen arista adyacente? ¿Paro si llego al nivel -1, resultado de que el vertice de n=0 no tenga más aristas?) 

- El retroceso y el recordar por los vertices y aristas por los que he pasado.