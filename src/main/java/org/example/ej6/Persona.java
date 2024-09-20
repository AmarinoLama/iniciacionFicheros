package org.example.ej6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private List<Mascota> mascotas = new ArrayList<Mascota>();

    public Persona(String nombre) {
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void addMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public List<Mascota> getMascotas() {
        return Collections.unmodifiableList(mascotas);
    }

    @Override
    public String toString() {
        return "Persona "+nombre;
    }
}