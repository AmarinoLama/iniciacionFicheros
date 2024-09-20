package org.example.ej6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Personas {
    public ArrayList<Persona> personas = new ArrayList<>();

    public Personas() {
        this.personas = new ArrayList<>();
        this.personas.add(new Persona("Paco"));
        this.personas.add(new Persona("Martin"));
        this.personas.add(new Persona("Juan"));
    }

    public void escribir() {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("ref/pruebaPersona.bin"));) {
            for (Persona m : personas) {
                escritor.writeObject(m);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
