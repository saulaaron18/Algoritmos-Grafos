import java.util.Scanner;

public class Menu {

	public static void menuDeSeleccion() {
		Scanner sc = new Scanner(System.in);
		char seleccion=' ';

		do {
			interfazGraficaDeSeleccion();

			System.out.println("Escriba el numero: ");
			seleccion = sc.next().charAt(0);
		}while(seleccion<'0' || seleccion>'3');
	}

	private static void interfazGraficaDeSeleccion() {
		System.out.println("=== MENÚ ===");

		System.out.println("1. Añadir o eliminar nodo/vertice");
		System.out.println("2. Añadir o eliminar arista");
		System.out.println("3. Mostrar Grafo\n");

		System.out.println("0. Salir\n");

	}

	public static void main(String[] args) {

	}

}
