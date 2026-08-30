package menus;

import grafos.*;
import java.io.IOException;
import java.util.Scanner;

public class MenuConsolaSimple {
	private final static Scanner sc = new Scanner(System.in);

	public static void menuDeSeleccion(IGrafo grafo) {
		char seleccion;

		do {
			interfazGraficaDeSeleccion();

			System.out.println("Escriba el numero: ");
			seleccion = sc.nextLine().trim().charAt(0);

			if (seleccion >= '0' && seleccion <= '5') {
				accionSeleccionMenu(seleccion, grafo);
			}

		} while (seleccion != '0');
	}

	private static void interfazGraficaDeSeleccion() {
		System.out.println("=== MENÚ ===");

		System.out.println("1. Añadir o eliminar nodo/vertice");
		System.out.println("2. Añadir o eliminar arista");
		System.out.println("3. Mostrar Grafo (matriz de adyaciencias por vertices)");
		System.out.println("4. Guardar Grafo");
		System.out.println("5. Cargar Grafo");

		System.out.println("\n0. Salir\n");

	}

	private static void accionSeleccionMenu(char seleccion, IGrafo grafo) {
		switch (seleccion) {
			case '0':
				System.out.println("FIN.");
				break;
			case '1':
				menuVertice(grafo);
				break;
			case '2':
				menuArista(grafo);
				break;
			case '3':
				System.out.println(grafo);
				menuDeSeleccion(grafo);
				break;
			case '4':
				guardarGrafo(grafo);
				menuDeSeleccion(grafo);
				break;
			case '5':
				cargarGrafo();
				break;
			default:
				System.out.println("ERROR");
		}
	}

	private static void guardarGrafo(IGrafo grafo) {
		System.out.print("Indique el nombre del archivo (sin extension): ");
		String nombreArchivo = sc.nextLine().trim();

		try {
			ArchivoGrafo.guardar(grafo, nombreArchivo + ".txt");
			System.out.println("Grafo guardado correctamente en " + nombreArchivo + ".txt");
		} catch (IOException e) {
			System.out.println("Error al guardar el grafo: " + e.getMessage());
		}
	}

	private static void cargarGrafo() {
		System.out.print("Indique el nombre del archivo a cargar (sin extension): ");
		String nombreArchivo = sc.nextLine().trim();

		try {
			IGrafo grafoCargado = ArchivoGrafo.cargar(nombreArchivo + ".txt");

			System.out.println("Grafo '" + nombreArchivo + "' cargado correctamente.");

			menuDeSeleccion(grafoCargado);
		} catch (IOException e) {
			System.out.println("Error al cargar el grafo: " + e.getMessage());
		}
	}

	private static void menuVertice(IGrafo grafo) {
		char seleccion;
		do {
			interfazGraficaMenuVertice();

			System.out.println("Escriba el numero: ");
			seleccion = sc.nextLine().trim().charAt(0);

			if (seleccion >= '0' && seleccion <= '4') {
				accionSeleccionMenuVertice(seleccion, grafo);
			}
		} while (seleccion >= '1' && seleccion <= '4');
	}

	private static void interfazGraficaMenuVertice() {
		System.out.println("¿Qué desea hacer?");
		System.out.println("1. Añadir nodo/vertice");
		System.out.println("2. Eliminar nodo/vertice");
		System.out.println("3. Mostrar vertices adyacentes");
		System.out.println("4. Mostrar Vertices");

		System.out.println("\n0. Volver atras\n");
	}

	private static void accionSeleccionMenuVertice(char seleccion, IGrafo grafo) {
		switch (seleccion) {

			case '0':
				System.out.println("Saliendo al Menú.");

				break;

			case '1':
				añadirVertice(grafo);

				System.out.println("Volviendo al menú del Vertice.");

				break;

			case '2':
				eliminarVertice(grafo);

				System.out.println("Volviendo al menú del Vertice.");

				break;

			case '3':

				System.out.println(grafo.getVerticesAdyacentes());
				System.out.println("Volviendo al menú del Vertice.");

				break;

			case '4':
				System.out.println(grafo.showVertexs());

				System.out.println("Volviendo al menú del Vertice.");

				break;

			default:
				System.out.println("ERROR");
		}
	}

	private static void añadirVertice(IGrafo grafo) {
		String nombreVertice;
		boolean complete;
		do {
			System.out.print("Indique el nombre del vertice: ");

			nombreVertice = sc.nextLine().trim();

			complete = grafo.addVertex(nombreVertice);
		} while (!complete);
	}

	private static void eliminarVertice(IGrafo grafo) {
		String nombreVertice;
		boolean complete;

		System.out.println(grafo.showVertexs());

		do {
			System.out.print("Indique el nombre del vertice que desea eliminar: ");

			nombreVertice = sc.nextLine().trim();

			complete = grafo.removeVertex(nombreVertice);
		} while (!complete);
	}

	private static void menuArista(IGrafo grafo) {
		char seleccion;
		do {
			interfazGraficaMenuArista();

			System.out.println("Escriba el numero: ");
			seleccion = sc.nextLine().trim().charAt(0);

			if (seleccion >= '0' && seleccion <= '3') {
				accionSeleccionMenuArista(seleccion, grafo);
			}
		} while (seleccion >= '1' && seleccion <= '3');
	}

	private static void interfazGraficaMenuArista() {
		System.out.println("¿Qué desea hacer?");
		System.out.println("1. Añadir arista");
		System.out.println("2. Eliminar arista");
		System.out.println("3. Mostrar aristas");

		System.out.println("\n0. Volver atras\n");
	}

	private static void accionSeleccionMenuArista(char seleccion, IGrafo grafo) {
		switch (seleccion) {
			case '0':
				System.out.println("Saliendo al Menú.");

				break;

			case '1':
				añadirArista(grafo);

				System.out.println("Volviendo al menú del Vertice.");

				break;

			case '2':
				eliminarArista(grafo);

				System.out.println("Volviendo al menú del Vertice.");

				break;

			case '3':
				System.out.println(grafo.showEdges());

				System.out.println("Volviendo al menú del Vertice.");

				break;

			default:
				System.out.println("ERROR");
		}
	}

	private static void añadirArista(IGrafo grafo) {
		boolean complete;
		String nombreVerticeOrigen;
		String nombreVerticeDestino;
		int peso;

		do {
			System.out.print("Indique el nombre del vertice origen: ");

			nombreVerticeOrigen = sc.nextLine().trim();

			System.out.print("Indique el nombre del vertice destino: ");

			nombreVerticeDestino = sc.nextLine().trim();

			System.out.print("Indique el peso (No puede ser 0): ");

			peso = Integer.parseInt(sc.nextLine().trim());

			complete = grafo.addEdge(nombreVerticeOrigen, nombreVerticeDestino, peso);
		} while (!complete);
	}

	private static void eliminarArista(IGrafo grafo) {
		boolean complete;
		String nombreVerticeOrigen;
		String nombreVerticeDestino;
		do {
			System.out.print("Indique el nombre del vertice origen de la arista a eliminar: ");

			nombreVerticeOrigen = sc.nextLine().trim();

			System.out.print("Indique el nombre del vertice destino de la arista a eliminar: ");

			nombreVerticeDestino = sc.nextLine().trim();

			complete = grafo.removeEdge(nombreVerticeOrigen, nombreVerticeDestino);
		} while (!complete);
	}

	private static IGrafo crearGrafo() {
		char opcion;

		do {
			System.out.println("¿Qué tipo de grafo desea crear?");
			System.out.println("1. Grafo (no dirigido)");
			System.out.println("2. Digrafo (dirigido)");
			System.out.println("3. Cargar grafo");
			System.out.print("Escriba el numero: ");

			opcion = sc.nextLine().trim().charAt(0);
		} while (opcion < '1' || opcion > '3');

		if (opcion == '3') {
			cargarGrafo();
		}

		return (opcion == '1') ? new Grafo() : new Digrafo();
	}

	public static void main(String[] args) {
		IGrafo grafo = crearGrafo();

		menuDeSeleccion(grafo);
	}

}
