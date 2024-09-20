package org.example;

import java.io.File;
import java.util.Scanner;

public class VSimpleLs {
    public static void main(String[] args) {

        System.out.println("Dime la ruta"); // "ref/texto.txt" o "ref/"

        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        File path = new File(nombre);

        StringBuilder salida = new StringBuilder();

        for (File element : path.listFiles()) {
            salida.append(element.isDirectory() ? "d" : "-")
                    .append(element.canRead() ? "r" : "-")
                    .append(element.canWrite() ? "w" : "-")
                    .append(element.canExecute() ? "x" : "-")
                    .append(element.isDirectory() ? " directorio" : " archivo")
                    .append("\n");
        }
        System.out.printf(salida.toString());
    }
}