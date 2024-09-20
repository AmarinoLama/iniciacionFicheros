package org.example;

// Tarea sin acabar

public class Ahorcado {
    public static void main(String[] args) throws InterruptedException {
        System.out.printf("hello \n hello \n hello \n hello \n hello \n ");
        countdownAndClear();
        System.out.printf("hola");
    }

    public static void countdownAndClear() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
}