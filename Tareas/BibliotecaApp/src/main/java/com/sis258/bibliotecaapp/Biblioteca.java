/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.bibliotecaapp;

/**
 *
 * @author Felix
 */
public class Biblioteca {

    private int id;
    private String nombre;
    private double tamanio;

    public Biblioteca(int id, String nombre, double tamanio) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getTamanio() { return tamanio; }
}