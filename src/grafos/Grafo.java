package grafos;
import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {
	private ArrayList<Vertice> vertices;
	private ArrayList<Arista> aristas;

	public Grafo() {
		this.vertices = new ArrayList<Vertice>();
		this.aristas = new ArrayList<Arista>();
	}

	/**
	 * PRE: No hay un vertice con el mismo nombre
	 * POST: Crea y añade un vertice a la lista de vertices
	 * 
	 * @param 		nombreVertice
	 * @return		true si se añadió correctamente
	 */
	public boolean añadirVertice(String nombreVertice) {
		return vertices.add(new Vertice(nombreVertice));
	}

	/**
	 * PRE: Cierto
	 * POST: Elimina las aristas conectadas al vertice y el vertice mismo 
	 * 
	 * @param 		nombreVertice
	 * @return		true si se añadio correctamente
	 */
	public boolean eliminarVertice(String nombreVertice) {
		Vertice vertice = buscarVertice(nombreVertice);

		//Comprobamos que no es nulo
		if(vertice != null) {

			//Eliminamos las aristas que contengan ese vertice
			for(Arista arista:aristas) {
				if(arista.contieneVertice(nombreVertice)) {
					aristas.remove(arista);
				}
			}

			//Eliminamos el vertice de la lista de vertices
			return vertices.remove(vertice); 
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

		//Comprobamos si no son nulos para realizar el aumento de grado y añadir la arista a la lista
		if(origen != null && destino != null) {
			origen.aumentoGrado();
			return aristas.add(new Arista(origen, destino, peso));
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
			origen.disminuirGrado();
			return aristas.remove(new Arista(origen, destino, 0)); //Solamente se encuentra vivo en la lista
		}

		return false;
	}

	@Override
	public String toString() {
		String encabezado=" ";
		String grafo="";

		//Bucle para diseñar el encabezado
		for(int i=0;i<vertices.size();i++) {
			if(i==vertices.size()-1) {
				encabezado += vertices.get(i).getNombre() + '\n';
			}
			else{
				encabezado += vertices.get(i).getNombre() + ", ";
			}
		}

		//Creación de la matriz de Adyaciencias
		int[][] matriz = matrizAdyacencias();

		//Bucle para mostrar los vertices.toString() y los valores de matriz de adyaciencias
		for(int i=0;i<vertices.size();i++) {
			grafo += vertices.get(i) + " | ";
			for(int j=0;j<vertices.size();j++) {
				grafo += matriz[i][j] + " ";
			}
			grafo += '\n';
		}

		return encabezado+grafo;
	}

	/**
	 * 
	 * @return		matriz de adyaciencias por vertices
	 */
	private int[][]	matrizAdyacencias() {
		int[][]	matriz = new int[vertices.size()][vertices.size()];
		HashMap<Vertice, Integer> indicesVertices = new HashMap<Vertice, Integer>();

		//Rellenamos los keys con vertices de la lista y los valores de los indices,
		//para acceder rapidamente con el hash
		for(int i=0;i<vertices.size();i++) {
			indicesVertices.put(vertices.get(i), i);
		}

		//Accedemos a la lista aristas, obtenemos los vertices de origen y destino,
		//comprobamos su indice por el HashMap con complejidad O(1), y en 
		for(Arista arista:aristas) {
			int fila = indicesVertices.get(arista.getV0());
			int columna = indicesVertices.get(arista.getVf());

			matriz[fila][columna] = arista.getPeso();
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
	private Vertice buscarVertice(String nombreVertice) {
		Vertice verticeBusqueda = null;

		//Comprobación O(n)
		for(int i=0;i<vertices.size() && verticeBusqueda==null;i++) {
			if(vertices.get(i).getNombre().equals(nombreVertice)) {
				verticeBusqueda = vertices.get(i);
			}
		}

		return verticeBusqueda;
	}
}
