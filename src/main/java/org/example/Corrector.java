package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Corrector {
    public static void main(String[] args) {
        System.out.println("Dime la ruta del archivo:"); // "ref/test.txt"
        Scanner sc = new Scanner(System.in);
        Path path = Path.of(sc.nextLine());

        String notas = "";
        String solTot = "";

        try (BufferedReader lector = new BufferedReader(new
                FileReader(path.toFile()));) {
            String linea;
            while ((linea=lector.readLine()) != null) {
                if (solTot.length() != 0) {
                    notas += linea;
                    System.out.printf(linea);
                    break;
                    /*notas += calcularNota(linea.substring(8,28), solTot);*/
                } else {
                    solTot += linea;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.printf("NOTAS : " + notas.toString() + "\n \n" + solTot);
    }

    public static String calcularNota(String respuestas, String solTot) {

        Double nota = 0.0;
        for (Character respuesta : respuestas.toCharArray()) {
            for (Character solucion : solTot.toCharArray()) {
                nota += respuesta.equals(" ") ? 0.0 : (respuesta.equals(solucion) ? 0.5 : -0.15);
            }
        }
        return nota.toString();
    }
}
