package Pishing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BuscadorDeTerminos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la ruta del archivo de texto: ");
        String rutaArchivo = scanner.nextLine();

        // Mapea las palabras clave o frases con sus puntos respectivos
        Map<String, Integer> palabrasClave = new HashMap<>();
        palabrasClave.put("palabra1", 5); // Ejemplo: Cambia "palabra1" por tu palabra o frase
        palabrasClave.put("frase2", 10); // Cambia "frase2" por otra palabra o frase

        int totalPuntos = 0;

        try {
            totalPuntos = buscarTerminos(rutaArchivo, palabrasClave);
            System.out.println("Total de puntos acumulados: " + totalPuntos);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static int buscarTerminos(String rutaArchivo, Map<String, Integer> palabrasClave) throws IOException {
        int totalPuntos = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                for (Map.Entry<String, Integer> entry : palabrasClave.entrySet()) {
                    String palabraClave = entry.getKey();
                    int puntos = entry.getValue();

                    int ocurrenciasEnLinea = contarOcurrenciasEnLinea(linea, palabraClave);
                    if (ocurrenciasEnLinea > 0) {
                        System.out.println("Palabra/Frase: " + palabraClave +
                                ", Ocurrencias: " + ocurrenciasEnLinea +
                                ", Puntos: " + (ocurrenciasEnLinea * puntos));

                        totalPuntos += ocurrenciasEnLinea * puntos;
                    }
                }
            }
        }

        return totalPuntos;
    }

    private static int contarOcurrenciasEnLinea(String linea, String palabraClave) {
        int contadorOcurrencias = 0;
        int indice = linea.indexOf(palabraClave);

        while (indice != -1) {
            contadorOcurrencias++;
            indice = linea.indexOf(palabraClave, indice + 1);
        }

        return contadorOcurrencias;
    }
}