package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Corrector {
    public static void main(String[] args) {
        System.out.println("Dime la ruta del archivo:"); // "ref/test.txt"
        Scanner sc = new Scanner(System.in);
        Path path = Path.of(sc.nextLine());

        String calificaciones = "";
        String solTot = "";
        ArrayList<String> notas = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new
                FileReader(path.toFile()));) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (solTot.length() != 0 && linea.length() != 0) {
                    calificaciones += "El alumno " + linea.substring(0, 7) + " has sacado un " + calcularNota(linea.substring(9, 29), solTot) +
                            " lo que equibale a un " + darPuntuacion(calcularNota(linea.substring(9, 29), solTot)) + "\n";
                    notas.add(darPuntuacion(calcularNota(linea.substring(9, 29), solTot)));
                } else {
                    solTot += linea;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.printf(solTot + "\n \n" + calificaciones +
                "\n \nTabla puntuaciones : \n");

        for (Map.Entry<String, Double> tabla : tablaPorcentajes(notas).entrySet()) {
            System.out.println("\t" + tabla.getKey() + " -> " + tabla.getValue() + "%");
        }

    }

    // ARREGLAR cuyando sean espacios

    public static String calcularNota(String respuestas, String solTot) {

        Double nota = 0.0;
        for (int i = 0; i < 20; i++) {
            String respuesta = respuestas.substring(i, i + 1);
            String solucion = solTot.substring(i, i + 1);
            if (respuesta.equals(solucion)) {
                nota += 0.5;
            } else if (respuestas.substring(i, i + 1).equals(" ")) {
                nota += 0;
            } else {
                nota -= 0.15;
            }
        }
        return String.format("%.2f", nota); //esto sirve para redondear al segundo decimal
    }

    public static String darPuntuacion(String nota) {
        double num = Double.parseDouble(nota.replace(',', '.'));
        if (0.00 <= num && num <= 4.99) {
            return "suspenso";
        } else if (5.00 <= num && num <= 5.99) {
            return "aprobado";
        } else if (6.00 <= num && num <= 6.99) {
            return "bien";
        } else if (7.00 <= num && num <= 8.49) {
            return "notable";
        } else if (8.50 <= num && num <= 10.00) {
            return "excelente";
        }
        return "something is wrong...";
    }

    public static HashMap<String, Double> tablaPorcentajes(ArrayList<String> calificaciones) {
        HashMap<String, Double> tabla = new HashMap<>();

        for (String calificacion : calificaciones) {
            if (!tabla.containsKey(calificacion)) {
                long repeticiones = calificaciones.stream().filter(x -> x.equals(calificacion)).count();
                tabla.put(calificacion, (((double) repeticiones / calificaciones.size()) * 100));
            }
        }
        return tabla;
    }
}