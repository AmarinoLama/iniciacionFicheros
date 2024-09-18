package org.example;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

        System.out.printf(modelos.toString());

        //crear fichero

        /*
        String ruta = "home/luis/Documentos/archivo.txt";
        File file = new File(ruta);
        if(!file.exists()) {
            file.createNewFile();
        }

        try (PrintWriter escritor = new PrintWriter("res/marcas.txt")) {
            for (Map.Entry<String, String> modelo : modelos.entrySet()) {
                escritor.println(modelo.getKey() + ": " + modelo.getValue());
            }
            System.out.println("Escritura realizada.");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }*/
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