/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.ejercicio_biblioteca;

/**
 *
 * @author Felix
 */
import com.sis258.ejercicio_biblioteca.BibliotecaDAO;
import com.sis258.ejercicio_biblioteca.ArmarioDAO;
import com.sis258.ejercicio_biblioteca.PublicacionDAO;

public class Ejercicio_biblioteca {

    public static void main(String[] args) {

        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        ArmarioDAO armarioDAO = new ArmarioDAO();
        PublicacionDAO publicacionDAO = new PublicacionDAO();

        // Insertar Biblioteca
        bibliotecaDAO.insertar("Biblioteca Central", 500);

        // Insertar Armario (suponiendo biblioteca id = 1)
        armarioDAO.insertar("A1", "Madera", 1);

        // Insertar Publicación (suponiendo armario id = 1)
        publicacionDAO.insertar("Java Avanzado", "Libro",
                "Autor: Juan Perez, Editorial: Alfa, Año: 2024", 1);
    }
}