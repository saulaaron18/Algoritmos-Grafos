import java.util.Scanner;
import grafos.*;

public class Menu {

	public static void menuDeSeleccion(Grafo grafo) {
		try (Scanner sc = new Scanner(System.in)) {
			char seleccion=' ';

			do {
				interfazGraficaDeSeleccion();

				System.out.println("Escriba el numero: ");
				seleccion = sc.next().charAt(0);

				if(seleccion>='0' && seleccion<='3') {
					accionSeleccionMenu(seleccion, grafo);
				}

			}while(seleccion<'0' || seleccion>'3');
		}
	}

	private static void interfazGraficaDeSeleccion() {
		System.out.println("=== MENÚ ===");

		System.out.println("1. Añadir o eliminar nodo/vertice");
		System.out.println("2. Añadir o eliminar arista");
		System.out.println("3. Mostrar Grafo (matriz de adyaciencias por vertices)");

		System.out.println("\n0. Salir\n");

	}

	private static void accionSeleccionMenu(char seleccion, Grafo grafo) {
		switch(seleccion) {
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
		default:
			System.out.println("ERROR");
		}
	}

	private static void menuVertice(Grafo grafo) {
		try(Scanner sc = new Scanner(System.in)){
			char seleccion = ' ';
			do {
				interfazGraficaMenuVertice();

				System.out.println("Escriba el numero: ");
				seleccion = sc.next().charAt(0);

				if(seleccion>='0' && seleccion<='4') {
					accionSeleccionMenuVertice(seleccion, grafo);
				}
			}while(seleccion<'0' || seleccion>'4');
		}
	}

	private static void interfazGraficaMenuVertice() {
		System.out.println("¿Qué desea hacer?");
		System.out.println("1. Añadir nodo/vertice");
		System.out.println("2. Eliminar nodo/vertice");
		System.out.println("3. Mostrar vertices adyacentes");
		System.out.println("4. Mostrar Vertices");

		System.out.println("\n0. Volver atras\n");
	}

	private static void accionSeleccionMenuVertice(char seleccion, Grafo grafo) {
		switch(seleccion) {
		case '0':
			System.out.println("Saliendo al Menú.");
			
			menuDeSeleccion(grafo);
			
			break;
			
		case '1':
			añadirVertice(grafo);
			
			System.out.println("Volviendo al menú del Vertice.");
			
			menuVertice(grafo);

			break;
		
		case '2':
			eliminarVertice(grafo);
			
			System.out.println("Volviendo al menú del Vertice.");
			
			menuVertice(grafo);
			
			break;
		
		case '3':
			//TODO
			
			System.out.println("En desarrollo");
			System.out.println("Volviendo al menú del Vertice.");
			
			menuVertice(grafo);
			
			break;
			
		case '4':
			System.out.println(grafo.mostrarVertices());
			
			System.out.println("Volviendo al menú del Vertice.");
			
			menuVertice(grafo);
			
			break;
		
		default:
			System.out.println("ERROR");
		}
	}

	private static void añadirVertice(Grafo grafo) {
		try(Scanner sc = new Scanner(System.in)){
			String nombreVertice="";
			boolean complete = false;
			do {
				System.out.print("Indique el nombre del vertice: ");

				nombreVertice = sc.nextLine();

				complete = grafo.añadirVertice(nombreVertice);
			}while(!complete);
		}
	}
	
	private static void eliminarVertice(Grafo grafo) {
		try(Scanner sc = new Scanner(System.in)){
			String nombreVertice="";
			
			System.out.println(grafo.mostrarVertices());
			
			do {
				System.out.print("Indique el nombre del vertice que desea eliminar: ");

				nombreVertice = sc.nextLine();

				grafo.eliminarVertice(nombreVertice);
			}while(nombreVertice.isEmpty());
		}
	}
	
	private static void menuArista(Grafo grafo) {
		try(Scanner sc = new Scanner(System.in)){
			char seleccion = ' ';
			do {
				interfazGraficaMenuArista();

				System.out.println("Escriba el numero: ");
				seleccion = sc.next().charAt(0);

				if(seleccion>='0' && seleccion<='4') {
					accionSeleccionMenuArista(seleccion, grafo);
				}
			}while(seleccion<'0' || seleccion>'4');
		}
	}
	
	private static void interfazGraficaMenuArista() {
		System.out.println("¿Qué desea hacer?");
		System.out.println("1. Añadir arista");
		System.out.println("2. Eliminar arista");
		System.out.println("3. Mostrar aristas adyacentes");
		System.out.println("4. Mostrar aristas");

		System.out.println("\n0. Volver atras\n");
	}
	
	private static void accionSeleccionMenuArista(char seleccion, Grafo grafo) {
		switch(seleccion) {
		case '0':
			System.out.println("Saliendo al Menú.");
			
			menuDeSeleccion(grafo);
			
			break;
			
		case '1':
			añadirArista(grafo);

			System.out.println("Volviendo al menú del Vertice.");
			
			menuArista(grafo);

			break;
		
		case '2':
			eliminarArista(grafo);
			
			System.out.println("Volviendo al menú del Vertice.");
			
			menuArista(grafo);
			
			break;
		
		case '3':
			//TODO
			
			System.out.println("En desarrollo");
			System.out.println("Volviendo al menú del Vertice.");
			
			menuArista(grafo);
			
			break;
			
		case '4':
			System.out.println(grafo.mostrarAristas());
			
			System.out.println("Volviendo al menú del Vertice.");
			
			menuArista(grafo);
			
			break;
		
		default:
			System.out.println("ERROR");
		}
	}
	
	private static void añadirArista(Grafo grafo) {
		try(Scanner sc = new Scanner(System.in)){
			boolean complete = false;
			String nombreVerticeOrigen="";
			String nombreVerticeDestino="";
			int peso=1;
			do {
				System.out.print("Indique el nombre del vertice origen: ");

				nombreVerticeOrigen = sc.nextLine();

				System.out.print("Indique el nombre del vertice destino: ");
				
				nombreVerticeDestino = sc.nextLine();
				
				System.out.print("Indique el peso (No puede ser 0): ");
				
				peso = sc.nextInt();
				
				complete = grafo.añadirArista(nombreVerticeOrigen, nombreVerticeDestino, peso);
			}while(!complete);
		}
	}
	
	private static void eliminarArista(Grafo grafo) {
		try(Scanner sc = new Scanner(System.in)){
			boolean complete = false;
			String nombreVerticeOrigen="";
			String nombreVerticeDestino="";
			do {
				System.out.print("Indique el nombre del vertice origen de la arista a eliminar: ");

				nombreVerticeOrigen = sc.nextLine();

				System.out.print("Indique el nombre del vertice destino de la arista a eliminar: ");
				
				nombreVerticeDestino = sc.nextLine();
				
				complete = grafo.eliminarArista(nombreVerticeOrigen, nombreVerticeDestino);
			}while(!complete);
		}
	}

	public static void main(String[] args) {
		Grafo grafo = new Grafo();

		menuDeSeleccion(grafo);
	}

}
