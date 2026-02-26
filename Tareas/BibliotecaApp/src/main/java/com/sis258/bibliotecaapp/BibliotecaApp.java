/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.bibliotecaapp;

import java.util.Scanner;
import java.util.List;
/**
 *
 * @author Felix
 */
public class BibliotecaApp {

 public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        ArmarioDAO armarioDAO = new ArmarioDAO();
        PublicacionDAO publicacionDAO = new PublicacionDAO();

        int opcion;

        do {
            System.out.println("\n--- MENU BIBLIOTECA ---");
            System.out.println("1. Crear Biblioteca");
            System.out.println("2. Crear Armario");
            System.out.println("3. Crear Libro");
            System.out.println("4. Listar Biblioteca Completa");
            System.out.println("5. Eliminar Biblioteca");
            System.out.println("6. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Tamaño: ");
                    double tamanio = sc.nextDouble();

                    bibliotecaDAO.insertar(new Biblioteca(0, nombre, tamanio));
                    break;

                case 2:
                    System.out.print("ID Biblioteca: ");
                    int idBib = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Codigo Armario: ");
                    String codigo = sc.nextLine();
                    System.out.print("Material: ");
                    String material = sc.nextLine();

                    armarioDAO.insertar(new Armario(0, codigo, material), idBib);
                    break;

                case 3:
                    System.out.print("ID Armario: ");
                    int idArm = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre Libro: ");
                    String nom = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Editorial: ");
                    String editorial = sc.nextLine();
                    System.out.print("Año: ");
                    int anio = sc.nextInt();

                    publicacionDAO.insertarLibro(
                            new Libro(0, nom, autor, editorial, anio),
                            idArm
                    );
                    break;

                case 4:
                    listarTodo(bibliotecaDAO, armarioDAO, publicacionDAO);
                    break;

                case 5:
                    System.out.print("ID Biblioteca a eliminar: ");
                    int idEliminar = sc.nextInt();
                    bibliotecaDAO.eliminar(idEliminar);
                    break;
            }

        } while (opcion != 6);
    }

    private static void listarTodo(BibliotecaDAO bDAO,
                                    ArmarioDAO aDAO,
                                    PublicacionDAO pDAO) {

        for (Biblioteca b : bDAO.listar()) {
            System.out.println("\nBiblioteca: " + b.getNombre());

            for (Armario a : aDAO.listarPorBiblioteca(b.getId())) {
                System.out.println("  Armario: " + a.getCodigo());

                for (String pub : pDAO.listarPorArmario(a.getId())) {
                    System.out.println("    - " + pub);
                }
            }
        }
    }
}
