package com.miapp.biblioteca.ui;

import com.miapp.biblioteca.Libros;
import com.miapp.biblioteca.Usuarios;
import com.miapp.biblioteca.service.LibrosService;
import com.miapp.biblioteca.service.UsuariosService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //ArrayList<Libros> biblio = new ArrayList<>();

       // LibrosService libroService = new LibrosService(biblio);

        //libroService.create("La inteligencia emocional", "Daniel Coleman","950","Desarrollo Personal");
        //create("Inteligencia social", "Daniel Coleman","111","Desarrollo Personal");

        //System.out.println(libroService.readAll());
        //System.out.println(libroService.readByISBN("950"));
        //System.out.println();
        //libroService.update("950","Daniel Coleman","Coherencia emocional","Desarrollo Personal");
        //System.out.println();
        //System.out.println(libroService.readByISBN("950"));

        //libroService.delete("950");
        //System.out.println(libroService.readAll());

        //ARRAYLIST DE LIBROS
        ArrayList<Libros> biblioteca = new ArrayList<>();
        //ARRAYLIST DE USUARIOS
        ArrayList<Usuarios> usuarios = new ArrayList<>();

        //OBJETOS DE LIBRO SERVICE Y USUARIO SERVICE
        LibrosService ls = new LibrosService(biblioteca);
        UsuariosService us = new UsuariosService(usuarios);

        Scanner sc = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("=== Biblioteca de Alexandria ===");
            System.out.println("=== 1.  Crear Nuevo Libro ===");
            System.out.println("=== 2.  Actualizar Libro ===");
            System.out.println("=== 3.  Buscar ISBN ===");
            System.out.println("=== 4.  Buscar Titulo Del Libro ===");
            System.out.println("=== 5.  lista De Libros ===");
            System.out.println("=== 6.  Eliminar Libros ===");
            System.out.println("=== 7.  Prestar Libro ===");
            System.out.println("=== 8.  Devoluciones de Libros ===");
            System.out.println("=== 10. Crear usuario ===");
            System.out.println("=== 11. Lista de usuarios ===");
            //System.out.println("=== 12. Lista de prestamos ===");
            System.out.println("=== 0.  Salir ===");
            System.out.println("=== Seleccione una Opcion ===");


            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    //crear libro
                    System.out.println("Ingrese Titulo");
                    String titulo = sc.nextLine();
                    System.out.println("Ingrese Autor");
                    String autor = sc.nextLine();
                    System.out.println("Ingrese ISBN");
                    String isbn = sc.nextLine();
                    System.out.println("Ingrese Genero");
                    String genero = sc.nextLine();

                    ls.create(titulo, autor, isbn, genero);
                    break;

                case 2:
                    //ACTUALIZAR LIBRO POR ISBN
                    System.out.println("Ingrese el ISBN del Libro para ACTUALIZAR");
                    String isbnNuevo = sc.nextLine();
                    if(ls.existeISBN(isbnNuevo)) {
                        System.out.println("Ingrese Titulo");
                        String nuevoTitulo = sc.nextLine();
                        System.out.println("Ingrese Autor");
                        String nuevoAutor = sc.nextLine();
                        System.out.println("Ingrese Genero");
                        String nuevoGenero = sc.nextLine();

                        ls.update(isbnNuevo, nuevoTitulo, nuevoAutor, nuevoGenero);
                    }else{
                        System.out.println("El ISBN ingresado no existe en la base de datos");
                    }
                    break;

                case 3:
                    //BUSCAR LIBRO POR ISBN
                    System.out.println("Ingrese el ISBN del libro");
                    String isbnBuscar = sc.nextLine();
                    Libros librosISBN = ls.readByISBN(isbnBuscar);
                    if (librosISBN !=null){
                        System.out.println("Libro encontrado: "+ librosISBN.getTitulo());
                    }else {
                        System.out.println("Libro no encontrado");
                    }
                    break;

                case 4:
                    //BUSCAR POR TITULO
                    System.out.println("Ingrese el titulo del libro a buscar");
                    String tituloBuscar = sc.nextLine();
                    ArrayList<Libros> libroPorTitulo = ls.readByBook(tituloBuscar);
                    if(!libroPorTitulo.isEmpty()){
                        System.out.println("Libros encontrados: ");
                        for (Libros libro : libroPorTitulo){
                            System.out.println(libro.getTitulo());
                        }
                    }else {
                        System.out.println("No se encontro el libro por titulo");
                    }
                    break;

                case 5:
                    //LISTA DE LIBROS
                    ArrayList<Libros> listarLibros = ls.readAll();
                    for (Libros libro : listarLibros){
                        System.out.println(libro.getTitulo() + "(" + libro.getISBN() + ")");
                    }
                    break;

                case 6:
                    //ELIMINAR LIBRO
                    System.out.println("Ingrese el ISBN del libro que desea eliminar: ");
                    String isbnEliminar = sc.nextLine();
                    ls.delete(isbnEliminar);
                    System.out.println("Libro ELIMINADO");
                    break;

                case 7:
                    //Prestar Libro
                    System.out.println("Ingrese el ID del usuario: ");
                    String idUsuario = sc.nextLine();
                    Usuarios usuarioPrestamo = us.buscarId(idUsuario);

                    System.out.println("Ingrese el ISBN del LIBRO");
                    String libroPrestado = sc.nextLine();

                    Libros libroPrestamo = ls.readByISBN(libroPrestado);

                    if (usuarioPrestamo != null && libroPrestamo != null && libroPrestamo.isDisponible()){
                        us.prestamoLibro(usuarioPrestamo, libroPrestamo);
                        System.out.println("Libro PRESTADO CON EXITO");

                    }else {
                        System.out.println("Usuario no encontrado o libro no encontrado/disponible");
                    }

                case 8:
                        //DEVOLVER LIBRO
                    System.out.println("Ingrese ID usuario");
                    String idUser = sc.nextLine();
                    Usuarios userDev = us.buscarId(idUser);

                    System.out.println("Ingrese el ISBN dsel libro: ");
                    String libroDev = sc.nextLine();

                    Libros libroDevuelto = ls.readByISBN(libroDev);
                    if (userDev != null && libroDevuelto != null && !libroDevuelto.isDisponible()){
                        us.devolverLibro(userDev, libroDevuelto);
                        System.out.println("Libro devuelto");
                    }else{
                        System.out.println("no coincide");
                    }
                    break;

                case 10:
                    //CREAR USUARIO
                    System.out.println("Ingrese Nombre");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese ID");
                    String id = sc.nextLine();


                    us.create(nombre, id);
                    break;

                case 11:
                    //LISTA DE USUARIOS
                    ArrayList<Usuarios> listarUsuarios = us.readAll();
                    for (Usuarios usuario : listarUsuarios){
                        System.out.println(usuario.getNombre() + "(" + usuario.getId() + ")");
                    }
                    break;

                /*case 12:
                    // Listar préstamos
                    ArrayList<> listarPrestamos = us.listarPrestamos();
                    for (Usuarios usuario : listarPrestamos){
                        System.out.println(usuario.getLibrosPrestados());
                    }
                    break;*/



                default:
                    System.out.println("La opcion ingresada no es valida");
                    break;
            }


        }while (opcion !=0);


    }
}
