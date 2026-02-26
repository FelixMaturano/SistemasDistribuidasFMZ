/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.ejercicio_biblioteca;

/**
 *
 * @author Felix
 */

public class Libro extends Publicacion {

    private String autor;
    private String editorial;
    private int anio;

    public Libro(String nombre, String autor, String editorial, int anio) {
        super(nombre);
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
    }

    @Override
    public String mostrarDetalle() {
        return "Libro: " + nombre + " - " + autor + " - " + editorial + " - " + anio;
    }
}