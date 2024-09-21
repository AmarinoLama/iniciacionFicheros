package org.example.ej6;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LectorPersonas {
    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<Persona>();

        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream("ref/pruebaPersona.bin"));) {
            while (true) {
                Object o = lector.readObject();
                if (o instanceof Persona) {
                    personas.add((Persona) o);
                }
            }

        } catch (EOFException ex) {
            System.out.println("Hemos llegado al final del archivo.");
            for (Persona m : personas) {
                System.out.println(m);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
}
