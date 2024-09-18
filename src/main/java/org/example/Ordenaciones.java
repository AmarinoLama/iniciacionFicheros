package org.example;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Ordenaciones {
    public static void main(String[] args) {
        System.out.println("Dime la ruta del archivo:"); // "ref/ordenar.txt"
        Scanner sc = new Scanner(System.in);
        Path path = Path.of(sc.nextLine());

        System.out.println("Dime como lo quieres odenar " +
                "\n * ascendente case-sensitive, acs " +
                "\n * ascendente case-insensitive, aci " +
                "\n * descendente case-sensitive, dcs " +
                "\n * descendente case-insensitive, dci");
        Scanner sc2 = new Scanner(System.in);
        String orden = sc2.nextLine();

        ArrayList<String> palabras = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new
                FileReader(path.toFile()));) {
            String linea;
            while ((linea=lector.readLine()) != null)
                palabras.add(linea);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        switch (orden) {
            case "acs":
                palabras.sort(Comparator.naturalOrder());
                break;
            case "aci":
                palabras.sort(String.CASE_INSENSITIVE_ORDER);
                break;
            case "dcs":
                palabras.sort(Comparator.reverseOrder());
                break;
            case "dci":
                palabras.sort(String.CASE_INSENSITIVE_ORDER.reversed());
                break;
        }

        System.out.println(palabras.toString());

        String ruta = "home/luis/Documentos/archivo.txt";
        File file = new File(ruta);
        if(!file.exists()) {
            file.createNewFile();
        }

        try (PrintWriter escritor = new PrintWriter("res/marcas.txt")) {

            System.out.println("Escritura realizada.");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}