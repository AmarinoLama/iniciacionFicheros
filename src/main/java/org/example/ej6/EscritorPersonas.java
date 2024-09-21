package org.example.ej6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EscritorPersonas {


    public static void main(String[] args) {
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Paco"));
        personas.add(new Persona("Martin"));
        personas.add(new Persona("Juan"));

        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("ref/pruebaPersona.bin"));) {
            for (Persona m : personas) {
                escritor.writeObject(m);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}