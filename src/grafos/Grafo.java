package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Grafo {
	private HashMap<Vertice, HashSet<Arista>> verticesAdyacentes;

	public Grafo() {
		this.verticesAdyacentes = new HashMap<Vertice, HashSet<Arista>>();
	}

	/**
	 * PRE: No hay un vertice null y no es vacio
	 * POST: Crea y añade un vertice a la lista de vertices
	 * 
	 * @param 		nombreVertice
	 * @return		true si se añadió correctamente
	 */
	public boolean añadirVertice(String nombreVertice) {
		verticesAdyacentes.put(new Vertice(nombreVertice), new HashSet<Arista>());
		return true;
	}

	/**
	 * PRE: Cierto
	 * POST: Elimina las aristas conectadas al vertice y el vertice mismo 
	 * 
	 * @param 		nombreVertice
	 * @return		true si se añadio correctamente
	 */
	public boolean eliminarVertice(String nombreVertice) {
		Vertice verticeEliminacion = buscarVertice(nombreVertice);
		
		//Comprobamos que no es nulo
		if(verticeEliminacion != null) {
			
			
			//Eliminamos las aristas que contengan ese vertice
			for(Vertice verticeKey:verticesAdyacentes.keySet()) {
				HashSet<Arista> aristas = verticesAdyacentes.get(verticeKey);
				Arista aristaBusqueda = new Arista(verticeKey, verticeEliminacion, 1);
				
				//ELiminamos la arista que contenga el vertice de eliminacion
				aristas.remove(aristaBusqueda);
			}
			//Eliminamos el vertice de la lista de vertices
			verticesAdyacentes.remove(verticeEliminacion);
			
			return true;
		}							 

		//Caso en el que el nombre no sea correcto, que sea null o vacio
		return false;
	}

	/**
	 * PRE: peso != 0
	 * POST: Crea la arista conectada desde el vertice de origen al de destino
	 * y aumenta el grado del vertice origen 
	 * 
	 * @param 		nombreOrigen
	 * @param 		nombreDestino
	 * @param 		peso
	 * @return		true si se añadio correctamente
	 */
	public boolean añadirArista(String nombreOrigen, String nombreDestino, int peso) {
		//Buscamos los vertices de la lista vertices
		Vertice origen = buscarVertice(nombreOrigen);
		Vertice destino = buscarVertice(nombreDestino);

		//Comprobamos si no son nulos para añadir la arista a la lista
		if(origen != null && destino != null) {
			Arista aristaAñadir = new Arista(origen, destino, peso);
			 
			 return verticesAdyacentes.get(origen).add(aristaAñadir);
		}

		return false;
	}

	/**
	 * PRE: Cierto
	 * POST: Elimina la arista
	 * 
	 * @param nombreOrigen
	 * @param nombreDestino
	 * @return
	 */
	public boolean eliminarArista(String nombreOrigen, String nombreDestino) {
		Vertice origen = buscarVertice(nombreOrigen);
		Vertice destino = buscarVertice(nombreDestino);

		//Comprobamos si no son nulos para realizar la dismiución de grado y eliminar la arista de la lista
		if(origen != null && destino != null) {
			return verticesAdyacentes.get(origen).remove(new Arista(origen, destino, 1)); //Solamente se encuentra vivo en la lista
		}

		return false;
	}
	
	public String mostrarVertices() {
		return verticesAdyacentes.keySet().toString();
	}
	
	public String mostrarAristas() {
		HashSet<Arista> aristas = new HashSet<Arista>();
		
		for(Vertice verticeKey:verticesAdyacentes.keySet()) {
			aristas.addAll(verticesAdyacentes.get(verticeKey));
		}
		
		return aristas.toString();
	}

	@Override
	public String toString() {
		String encabezado=" ";
		String grafo="";
		int numVertices = verticesAdyacentes.size();

		//Bucle para diseñar el encabezado
		for(Vertice verticesKey:verticesAdyacentes.keySet()) {
				encabezado += verticesKey + " ";
		}

		//Creación de la matriz de Adyaciencias
		int[][] matriz = matrizAdyacencias();

		//Bucle para mostrar los vertices.toString() y los valores de matriz de adyaciencias
		int i=0;
		for(Vertice verticesKey:verticesAdyacentes.keySet()) {
			grafo += verticesKey + " | ";
			for(int j=0;j<numVertices;j++) {
				grafo += matriz[i][j] + " ";
			}
			grafo += '\n';
			i++;
		}

		return encabezado+'\n'+grafo;
	}

	/**
	 * 
	 * @return		matriz de adyaciencias por vertices
	 */
	private int[][]	matrizAdyacencias() {
		int[][]	matriz = new int[verticesAdyacentes.size()][verticesAdyacentes.size()];
		HashMap<Vertice, Integer> indicesVertices = new HashMap<Vertice, Integer>();

		//Rellenamos los keys con vertices de la lista y los valores de los indices,
		//para acceder rapidamente con el hash
		int i=0;
		for(Vertice verticeKey:verticesAdyacentes.keySet()) {
			indicesVertices.put(verticeKey, i++);
		}

		//Accedemos a la lista aristas, obtenemos los vertices de origen y destino,
		//comprobamos su indice por el HashMap con complejidad O(1), y en 
		for(Vertice verticeKey:verticesAdyacentes.keySet()) {
			HashSet<Arista> aristas = verticesAdyacentes.get(verticeKey);
			for(Arista arista : aristas) {
				int fila = indicesVertices.get(arista.getV0());
				int columna = indicesVertices.get(arista.getVf());

				matriz[fila][columna] = arista.getPeso();
			}
		}


		return matriz;
	}

	/**
	 * PRE: nombreVertice != null && !nombreVertice.isEmpty()
	 * POST: Devuelve el vertices
	 * 
	 * @param 		nombreVertice
	 * @return		el vertice de busqueda
	 */
	public Vertice buscarVertice(String nombreVertice) {
		Vertice verticeBusqueda = new Vertice(nombreVertice);

		return (verticesAdyacentes.get(verticeBusqueda) == null) ? null : verticeBusqueda;
	}
}
