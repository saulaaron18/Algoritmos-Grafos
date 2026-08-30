package menus;

import grafos.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase de utilidad para guardar y cargar un {@link IGrafo} en un archivo de
 * texto plano, conservando su tipo (Grafo/Digrafo), titulo, vertices y
 * aristas.
 *
 * Formato del archivo:
 * 
 * <pre>
 * TIPO:GRAFO|DIGRAFO
 * TITULO:&lt;titulo&gt;
 * VERTICES:&lt;cantidad&gt;
 * &lt;nombreVertice&gt;
 * ...
 * ARISTAS:&lt;cantidad&gt;
 * &lt;origen&gt;;&lt;destino&gt;;&lt;peso&gt;
 * ...
 * </pre>
 *
 * @author Saúl Aarón
 * @version 1.0
 */
public abstract class ArchivoGrafo {
	private static final String TIPO_GRAFO = "GRAFO";
	private static final String TIPO_DIGRAFO = "DIGRAFO";

	/**
	 * PRE: {@code grafo != null && titulo != null && rutaArchivo != null}
	 * POST: Escribe el grafo (tipo, titulo, vertices y aristas) en el archivo
	 * indicado
	 *
	 * @param grafo         grafo a guardar
	 * @param titulo        titulo descriptivo del grafo
	 * @param nombreArchivo ruta o nombre del archivo destino
	 * @throws IOException si ocurre un error de escritura
	 */
	public static void guardar(IGrafo grafo, String nombreArchivo) throws IOException {
		File archivo = createSaveFile(nombreArchivo);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
			writer.write("TIPO:" + (grafo instanceof Grafo ? TIPO_GRAFO : TIPO_DIGRAFO));
			writer.newLine();

			writer.write("VERTICES:" + grafo.numOfVertexs());
			writer.newLine();

			for (Vertice vertice : grafo.getVertexs()) {
				writer.write(vertice.getNombre());
				writer.newLine();
			}

			writer.write("ARISTAS:" + grafo.numOfEdges());
			writer.newLine();

			for (Arista arista : grafo.getEdges()) {
				writer.write(arista.getV0().getNombre() + ";" + arista.getVf().getNombre() + ";" + arista.getPeso());
				writer.newLine();
			}
		}
	}

	/**
	 * 
	 * @param nombreArchivo ruta o nombre del archivo destino
	 * @return el archivo de texto que se desea guardar
	 */
	public static File createSaveFile(String nombreArchivo) {

		File srcFile = new File("src");
		File saveFile = new File(srcFile.getAbsolutePath() + "//saves", nombreArchivo);

		try {
			saveFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return saveFile;
	}

	/**
	 * PRE: {@code rutaArchivo != null}, el archivo debe existir y respetar el
	 * formato escrito por {@link #guardar(IGrafo, String, String)}
	 * POST: Reconstruye el grafo (Grafo o Digrafo, segun corresponda) a partir
	 * del archivo
	 *
	 * @param rutaArchivo ruta del archivo a leer
	 * @return el grafo reconstruido
	 * @throws IOException si ocurre un error de lectura o el archivo no respeta
	 *                     el formato esperado
	 */
	public static IGrafo cargar(String rutaArchivo) throws IOException {
		File srcFile = new File("src");
		File archivo = new File(srcFile.getAbsolutePath() + "//saves//" + rutaArchivo);
		IGrafo grafo;

		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String tipo = leerValor(reader.readLine(), "TIPO:");

			grafo = TIPO_GRAFO.equals(tipo) ? new Grafo() : new Digrafo();

			int numVertices = Integer.parseInt(leerValor(reader.readLine(), "VERTICES:"));

			for (int i = 0; i < numVertices; i++) {
				grafo.addVertex(reader.readLine().trim());
			}

			int numAristas = Integer.parseInt(leerValor(reader.readLine(), "ARISTAS:"));

			for (int i = 0; i < numAristas; i++) {
				String[] partes = reader.readLine().split(";");
				grafo.addEdge(partes[0].trim(), partes[1].trim(), Integer.parseInt(partes[2].trim()));
			}
		}

		return grafo;
	}

	private static String leerValor(String linea, String prefijo) throws IOException {
		if (linea == null || !linea.startsWith(prefijo)) {
			throw new IOException("Formato de archivo invalido, se esperaba la etiqueta '" + prefijo + "'");
		}

		return linea.substring(prefijo.length()).trim();
	}
}
