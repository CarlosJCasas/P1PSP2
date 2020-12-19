import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {

		int i = 0;
		int j = 0;
		int contador = 0;
		int sumatorio = 0;
		ProcessBuilder pb;
		String linea = "";
		String[] charArray = { "a", "e", "i", "o", "u" };
		String ficheroEntrada = args[0];
		String filesManag = "FilesManagedment";
		String classPath = ".";
		BufferedReader bf = null;
		PrintWriter pw;

			while (i != charArray.length) {
				pb = new ProcessBuilder("java", "-cp", classPath, filesManag, ficheroEntrada, charArray[i],
						"resultado_" + charArray[i] + ".txt");
				try {
					pb.start();
				} catch (IOException e) {
					System.out.println(
							"Error en comando: Main <path fichero> <pathFilesProperties.java> <pathFilesManagedment.java>");
				}
				i++;
			}
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				while (j != charArray.length) {
					bf = FilesProperties.getBufferedReader("resultado_" + charArray[j] + ".txt");
					contador = Integer.parseInt(bf.readLine());
					linea = linea + charArray[j].toUpperCase() + ": " + contador + " ";
					sumatorio = sumatorio + contador;
					j++;
				}
				bf.close();
				pw = FilesProperties.getPrintWriter("Resultado_" + ficheroEntrada);
				pw.println(linea);
				pw.println("El total de las vocales es: " + sumatorio);
				pw.close();
			} catch (NumberFormatException e) {
				System.out.println("El archivo no continene un número");
			} catch (IOException ex) {
				System.out.println("No se encuentra el archivo resultado_" + charArray[j] + ".txt");
			}

	}
}
