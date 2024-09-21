package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class lsRVariante {

    public static void main(String[] args) {

        System.out.println("Dime la ruta \n"); // "ref/texto.txt" o "ref/"
        Scanner sc = new Scanner(System.in);
        String ruta = sc.nextLine();

        Deque<Path> stack = new ArrayDeque<Path>();
        stack.add(Path.of(ruta));
        stack.addAll(foldersLocator(ruta));

        for (Path path : stack) {
            System.out.println(path.toString() + "\\.");
            printDirectory(path);
            System.out.println("\n");
        }
    }

    public static ArrayList<Path> foldersLocator(String ruta) {

        ArrayList<Path> paths = new ArrayList<Path>();

        try {
            for (Path element1 : Files.newDirectoryStream(Path.of(ruta))) {
                File element = element1.toFile();
                if (element.isDirectory()) {
                    paths.add(Path.of(ruta + "\\" + element.getName()));
                    paths.addAll(foldersLocator(ruta + "\\" + element.getName()));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return paths;
    }

    public static void printDirectory(Path path) {
        try {
            for (Path element1 : Files.newDirectoryStream(path)) {
                StringBuilder salida = new StringBuilder();
                File element = element1.toFile();
                salida.append(element.isDirectory() ? "d" : "-")
                        .append(element.canRead() ? "r" : "-")
                        .append(element.canWrite() ? "w" : "-")
                        .append(element.canExecute() ? "x" : "-")
                        .append(" ")
                        .append(element.getName());
                System.out.println(salida);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}