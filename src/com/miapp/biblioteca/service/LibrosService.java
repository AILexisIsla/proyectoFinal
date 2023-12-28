package com.miapp.biblioteca.service;

import com.miapp.biblioteca.Libros;

import java.util.ArrayList;

public class LibrosService {
    //ATRIBUTOS
    private ArrayList<Libros> biblioteca; //listado de libros de la biblioteca en un arraylist

    //CONSTRUCTOR

    public LibrosService(ArrayList<Libros> biblioteca) {
        this.biblioteca = biblioteca;
    }


    //METODOS
    /*
    *CRUD
     */

    //CREAR LIBRO (CREATE)
    public void create (String titulo, String autor, String ISBN, String genero) {
        Libros nuevoLibro = new Libros(titulo, autor, ISBN, genero);
        biblioteca.add(nuevoLibro);
    }

    //LEER TODOS LOS LIBROS
    public ArrayList<Libros> readAll() {
        return biblioteca;
    }

    //LEER LIBRO POR ISBN

    public Libros readByISBN(String ISBN) {

        for (Libros libro : biblioteca){
            if(libro.getISBN().equals(ISBN)){
                return libro;
            }
        }

        return null;
    }

    //LEER LIBRO POR AUTOR

    /*public Libros readByAutor(String autor) {

        for (Libros libro : biblioteca){
            if(libro.getAutor().equals(autor)){
                return libro;
            }
        }

        return null;
    }*/



    public Libros readByAutor(String autor) {

        for (Libros libro : biblioteca){
            if(libro.getAutor().equals(autor)){
                return libro;
            }
        }

        return null;
    }

    //LEER LIBRO POR TITULO

    /*public Libros readByTitulo(String titulo) {

        for (Libros libro : biblioteca){
            if(libro.getTitulo().equals(titulo)){
                return libro;
            }
        }

        return null;
    }*/

    public ArrayList<Libros> readByBook(String titulo){
        ArrayList<Libros> libroEncontrado = new ArrayList<>();
        for (Libros libro : biblioteca){
            if (libro.getTitulo().equalsIgnoreCase(titulo)){
                libroEncontrado.add(libro);
            }
        }
        return libroEncontrado;
    }

    //LEER LIBRO POR GENERO

    public Libros readByGenero(String genero) {

        for (Libros libro : biblioteca){
            if(libro.getGenero().equals(genero)){
                return libro;
            }
        }

        return null;
    }

    //ACTUALIZAR (UPDATE)

    public void update(String ISBN, String nuevoAutor, String nuevoTitulo, String nuevoGenero) {
        for(Libros libro : biblioteca){
            if(libro.getISBN().equals(ISBN)) {
                libro.setAutor(nuevoAutor);
                libro.setTitulo(nuevoTitulo);
                libro.setGenero(nuevoGenero);
            }
        }

    }

    //ELIMINAR

    public void delete(String ISBN){
        biblioteca.removeIf(libros -> libros.getISBN().equals(ISBN));
    }

    //VERIFICA SI EL LIBRO ESTA DISPONIBLE

    public boolean estaDisponible(Libros libro){
        return libro.isDisponible();
    }

    //VERIFICA SI EXISTE EL ISBN
    public boolean existeISBN(String isbn) {
        for (Libros libro : biblioteca) {
            if (libro.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }
}
