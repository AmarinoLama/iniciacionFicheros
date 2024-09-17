package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Ordenaciones {
    public static void main(String[] args) {
        System.out.println("Dime la ruta del archivo:");
        Scanner sc = new Scanner(System.in);

        System.out.println("Dime como lo quieres odenar " +
                "\n * ascendente case-sensitive, acs \n * ascendente case-insensitive, aci \n * descendente case-sensitive, dcs \n * descendente case-insensitive, dci");
        Scanner sc2 = new Scanner(System.in);

        Path path = Path.of(sc.nextLine());
        String texto = "";

        try (BufferedReader lector = new BufferedReader(new
                FileReader(path.toFile()));) {
            String linea;
            while ((linea=lector.readLine()) != null)
                texto += linea;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

// lista.sort(Comparator.naturalOrder()); >>>>>>> ascendente case-sensitive
// lista.sort(String.CASE_INSENSITIVE_ORDER); >>>>>>>> ascendente case-insensitive
// lista.sort(Comparator.reverseOrder()); >>>>>> descendente case-sensitive
// lista.sort(String.CASE_INSENSITIVE_ORDER.reversed()); >>>>>>>>> descendente case-insensitive