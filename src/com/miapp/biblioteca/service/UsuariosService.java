package com.miapp.biblioteca.service;

import com.miapp.biblioteca.Libros;
import com.miapp.biblioteca.Usuarios;

import java.util.ArrayList;

public class UsuariosService {
//ATRIBUTOS

    private ArrayList<Usuarios> usuarios; //LISTADO DE USUARIOS

    //CONSTRUCTOR
    public UsuariosService(ArrayList<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    //METODOS
    /*
    *CRUD
     */

    //CREAR (CREATE)

    public void create(String nombre, String id){
        Usuarios nuevoUsuario = new Usuarios(nombre, id);
        usuarios.add(nuevoUsuario);
    }

    //LEER (READ)

    public ArrayList<Usuarios> read() {
        return usuarios;
    }

    //ACTUALIZAR (UPDATE)

    public void update(String nuevoNombre, String id) {
        for (Usuarios usuario : usuarios) {
            if(usuario.getId().equals(id)){
                usuario.setNombre(nuevoNombre);
            }
        }
    }

    //ELIMINAR (DELETE)
    public void delete(String id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    //LEER TODOS LOS USUARIOS
    public ArrayList<Usuarios> readAll() {
        return usuarios;
    }

    //BUSCAR POR ID

    public Usuarios buscarId(String id){
        for (Usuarios usuario : usuarios){
            if(usuario.getId().equals(id)){
                return usuario;
            }
        }

        return null;
    }

    //PRESTAR LIBROS

    public void prestamoLibro(Usuarios usuario, Libros libro){
        if (libro.isDisponible()){
            usuario.getLibrosPrestados().add(libro);
            libro.setDisponible(false);
        }else{
            System.out.println("Este libro no esta disponible");
        }
    }

    //DEVOLVER LIBRO

    public void devolverLibro(Usuarios usuario, Libros libro){
        if(usuario.getLibrosPrestados().contains(libro))
        {
            usuario.getLibrosPrestados().remove(libro);
            libro.setDisponible(true);
        }
    }

    //OBTENER LIBROS PRESTADOS (REGISTRO)


    public ArrayList<Libros> obtenerLibrosPrestados(Usuarios usuario) {
        return usuario.getLibrosPrestados();
    }

    //Listar prestamos
    public void listarPrestamos() {
        for (Usuarios usuario : usuarios) {
            System.out.println("Usuario: " + usuario.getId());
            for (Libros libro : usuario.getLibrosPrestados()) {
                System.out.println("  Libro prestado: " + libro.getISBN());
            }
        }
    }
}
