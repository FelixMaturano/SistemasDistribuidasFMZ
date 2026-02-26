/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.bibliotecaapp;

/**
 *
 * @author Felix
 */
public class Periodico extends Publicacion {

    private String fecha;
    private String suplementos; // revista, crucigrama, afiche

    public Periodico(int id, String nombre, String fecha, String suplementos) {
        super(id, nombre);
        this.fecha = fecha;
        this.suplementos = suplementos;
    }

    public String getFecha() { return fecha; }
    public String getSuplementos() { return suplementos; }
}