package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class VSimpleLs {
    public static void main(String[] args) {

        System.out.println("Dime la ruta");

        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        Path path = Path.of(nombre);

        StringBuilder salida = new StringBuilder("");
        salida.append(Files.isDirectory(path) ? "d" : "-")
                .append(Files.isReadable(path) ? "r" : "-")
                .append(Files.isWritable(path) ? "w" : "-")
                .append((path.toFile().canRead() && path.toFile().canWrite() && path.toFile().canExecute()) ? "x" : "-")
                .append(Files.isDirectory(path) ? " directorio" : " archivo");

        System.out.printf(salida.toString());
    }
}