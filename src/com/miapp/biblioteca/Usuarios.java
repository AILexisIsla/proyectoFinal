package com.miapp.biblioteca;

import java.util.ArrayList;

//ATRIBUTOS

public class Usuarios {
    private String nombre;
    private String id;
    private ArrayList<Libros> librosPrestados;

    //Constructores
    //VACIO

    public Usuarios() {
    }

    //PARAMETRIZADO
    public Usuarios(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.librosPrestados = new ArrayList<>();
    }

    //GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Libros> getLibrosPrestados() {
        return librosPrestados;
    }



    //Funcion de Informacion


    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", librosPrestados=" + librosPrestados +
                '}';
    }
}
