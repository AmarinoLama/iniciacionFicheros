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
                if (solTot.length() != 0 && linea.length() != 0) {
                    notas += linea + " Has sacado un : " + calcularNota(linea.substring(9,29), solTot) + "\n";
                } else {
                    solTot += linea;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.printf(solTot + "\n \n" + notas);
    }

    public static String calcularNota(String respuestas, String solTot) {

        Double nota = 0.0;
        for (int i = 0; i<20; i++) {
            String respuesta = respuestas.substring(i,i+1);
            String solucion = solTot.substring(i,i+1);
            if (respuesta.equals(solucion)) {
                nota += 0.5;
            } else if (respuestas.substring(i, i+1).equals(" ")) {
                nota += 0;
            } else {
                nota -= 0.15;
            }
        }
        return nota.toString();
    }
}