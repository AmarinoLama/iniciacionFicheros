package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class BusquedaCarac {

    public static void main(String[] args) {

        System.out.println("Dime la ruta del archivo:");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();

        System.out.println("Dime la letra que quieres buscar:");
        String input = sc.nextLine();
        char letra = input.charAt(0);

        Path path = Path.of(nombre);
        String texto = "";

        try (BufferedReader lector = new BufferedReader(new
                FileReader(path.toFile()));) {
                String linea;
            while ((linea=lector.readLine()) != null)
                texto += linea;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("El número de veces que aparece la letra es de : " + contarRepes(texto, letra).toString());

        System.out.println("La letra más repetida es la : " + letraMasRepe(texto));
    }

    public static Long contarRepes(String texto, char letra) {
        return texto.chars().filter(x -> x == letra).count();
    }

    public static String letraMasRepe(String texto) {
        HashMap<Character, Long> letrasContar = new HashMap<>();
        for (Character letra : texto.toCharArray()) {
            letrasContar.put(letra, contarRepes(texto, letra));
        }

        Optional<HashMap.Entry<Character, Long>> valorMax = letrasContar.entrySet()
                .stream()
                .max(HashMap.Entry.comparingByValue());

        return valorMax.isPresent() ? valorMax.get().getKey().toString() : "Null :(";
    }
}