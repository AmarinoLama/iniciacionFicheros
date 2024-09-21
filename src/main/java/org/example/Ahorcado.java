package org.example;

// Tarea sin acabar

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class Ahorcado {

    private static String state = "";
    private static int errors = 0;
    private static String secretWord = "";

    public static void setSecretWord() {
        ArrayList<String> words = new ArrayList<>();
        words.add("cereza");
        words.add("elemento");
        words.add("marioneta");
        words.add("todoterreno");
        words.add("mariposa");

        Random rand = new Random();
        secretWord = words.get(rand.nextInt(words.size()));
    }

    public static String getSecretWord() {
        return secretWord;
    }

    public void addError() {
        errors += 1;
    }

    public static void setState() {
        state = "_".repeat(secretWord.length());
    }

    public static String getState() {
        return state;
    }


    public static void main(String[] args) {
        setSecretWord();
        setState();
        loadGame();
        System.out.println(getSecretWord() + getState() + errors);
    }

    public void isInSecretWord(String character) {
        if (secretWord.contains(character)) {
            state = null; // remplazar las barras bajas por letras
        } else {
            addError();
        }
    }

    public static void loadGame() {

        try (BufferedReader lector = new BufferedReader(new
                FileReader(Path.of("ref/game.txt").toFile()));) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.startsWith("state")) {
                    state = linea.split("=")[1];
                } else if (linea.startsWith("secretWord")) {
                    secretWord = linea.split("=")[1];
                } else if (linea.startsWith("errors")) {
                    errors = Integer.parseInt(linea.split("=")[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Partida cargada correctamente");
    }

    public static void saveGame() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ref/game.txt"))) {
            writer.write("state=" + state + "\n");
            writer.write("errors=" + errors + "\n");
            writer.write("secretWord=" + secretWord + "\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Partida guardada correctamente");
    }
}