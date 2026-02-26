/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.bibliotecaapp;

/**
 *
 * @author Felix
 */
public class Libro extends Publicacion {

    private String autor;
    private String editorial;
    private int anio;

    public Libro(int id, String nombre, String autor, String editorial, int anio) {
        super(id, nombre);
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
    }

    public String getAutor() { return autor; }
    public String getEditorial() { return editorial; }
    public int getAnio() { return anio; }
}
