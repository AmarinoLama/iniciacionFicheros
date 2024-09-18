package org.example;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Coches {

    public static void main (String[] args) {

        System.out.println("Dime la ruta del archivo:"); // "ref/coches.txt"
        Scanner sc = new Scanner(System.in);
        Path path = Path.of(sc.nextLine());

        ArrayList<String> coches = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new
                FileReader(path.toFile()));) {
            String linea;
            while ((linea=lector.readLine()) != null && lector.readLine() != " ") {
                coches.add(linea); }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        HashMap<String, String> modelos = modelos(coches);

        String ruta = "ref/marcas.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta.toString()))) {

            ArrayList<String> marcas = new ArrayList<>(modelos.keySet());
            marcas.sort(String::compareTo);

            for (String marca : marcas) {
                writer.write(marca + " : " + modelos.get(marca) + "\n");
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Archivo ordenado generado: " + ruta);
    }

    public static HashMap<String, String> modelos (ArrayList<String> coches) {

        HashMap<String, String> modelos = new HashMap<>();

        for (String coche : coches) {
            String marca = coche.split(" ",2)[0];
            String modelo = coche.split(" ",2)[1];
            
            if (modelos.get(marca) == null) {
                modelos.put(marca, modelo); //poner de posicion [1,n]
            } else {
                String newValue =  (modelos.get(coche.split(" ")[0])) + ", " + (coche.split(" ")[1]);
                modelos.replace(coche.split(" ")[0], modelos.get(coche.split(" ")[0]), newValue);
            }
        }

        return modelos;
    }
}