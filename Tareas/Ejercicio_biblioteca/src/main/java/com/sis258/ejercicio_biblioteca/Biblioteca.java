/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.ejercicio_biblioteca;

/**
 *
 * @author Felix
 */
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nombre;
    private double tamanio;
    private List<Armario> armarios;

    public Biblioteca(String nombre, double tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.armarios = new ArrayList<>();
    }

    public void agregarArmario(Armario armario) {
        armarios.add(armario);
    }

    public void listarBiblioteca() {
        System.out.println("Biblioteca: " + nombre + " - Tamaño: " + tamanio + " m2");
        for (Armario a : armarios) {
            System.out.println(a.tipoArmario());
            a.listarPublicaciones();
        }
    }
}