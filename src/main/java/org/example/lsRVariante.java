package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class lsRVariante {

    public static void main(String[] args) {

        System.out.println("Dime la ruta \n"); // "ref/texto.txt" o "ref/"

        Scanner sc = new Scanner(System.in);
        printDirectory(sc.nextLine());

    }

    public static void printDirectory(String ruta) {

        try {
            for (Path element1 : Files.newDirectoryStream(Path.of(ruta))) {
                StringBuilder salida = new StringBuilder();
                File element = element1.toFile();
                salida.append(element.isDirectory() ? "d" : "-")
                        .append(element.canRead() ? "r" : "-")
                        .append(element.canWrite() ? "w" : "-")
                        .append(element.canExecute() ? "x" : "-")
                        .append(" ")
                        .append(element.getName());
                System.out.println(salida);
                if (element.isDirectory()) {
                    printDirectory(ruta + "\\" + element.getName(), 1);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printDirectory(String ruta, int tab) {

        try {
            for (Path element1 : Files.newDirectoryStream(Path.of(ruta))) {
                StringBuilder salida = new StringBuilder();
                File element = element1.toFile();
                for (int i = 0; i < tab; i++) {
                    salida.append("\t");
                }
                salida.append(element.isDirectory() ? "d" : "-")
                        .append(element.canRead() ? "r" : "-")
                        .append(element.canWrite() ? "w" : "-")
                        .append(element.canExecute() ? "x" : "-")
                        .append(" ")
                        .append(element.getName());
                System.out.println(salida);
                if (element.isDirectory()) {
                    printDirectory(ruta + "\\" + element.getName(), tab + 1);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}