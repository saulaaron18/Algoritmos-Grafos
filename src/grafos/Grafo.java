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
	 * 
	 * @return 
	 */
	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Arista> getAristas() {
		return aristas;
	}

	/**
	 * 
	 * @param nombre
	 */
	public boolean añadirVertice(String nombre) {
		return vertices.add(new Vertice(nombre));
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public boolean eliminarVertice(String nombre) {
		Vertice vertice = buscarVertice(nombre);
		
		//Comprobamos que no es nulo
		if(vertice != null) {
			
			//Eliminamos las aristas que contengan ese vertice
			for(Arista arista:aristas) {
				if(arista.contieneVertice(nombre)) {
					aristas.remove(arista);
				}
			}
			
			//Eliminamos el vertice de la lista de vertices
			return vertices.remove(vertice); 
		}							 


		return false;
	}

	/**
	 * 
	 * @param nombreOrigen
	 * @param nombreDestino
	 * @param peso
	 * @return
	 */
	public boolean añadirArista(String nombreOrigen, String nombreDestino, int peso) {
		//Buscamos los vertices de la lista vertices
		Vertice origen = buscarVertice(nombreOrigen);
		Vertice destino = buscarVertice(nombreDestino);
		
		//Comprobamos si no son nulos para realizar el aumento de grado y añadir la arista de la lista
		if(origen != null && destino != null) {
			origen.aumentoGrado();
			return aristas.add(new Arista(origen, destino, peso));
		}

		return false;
	}

	/**
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
	 * @return
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
	 * 
	 * @param nombreVertice
	 * @return
	 */
	private Vertice buscarVertice(String nombreVertice) {
		Vertice verticeBusqueda = null;
		
		//Comprobación O(n), quizás modificable
		for(int i=0;i<vertices.size() && verticeBusqueda==null;i++) {
			if(vertices.get(i).getNombre().equals(nombreVertice)) {
				verticeBusqueda = vertices.get(i);
			}
		}

		return verticeBusqueda;
	}
}
