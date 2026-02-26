/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.ejercicio_biblioteca;

/**
 *
 * @author Felix
 */
public class Revista extends Publicacion {

    private String mes;
    private int anio;
    private String tipo;

    public Revista(String nombre, String mes, int anio, String tipo) {
        super(nombre);
        this.mes = mes;
        this.anio = anio;
        this.tipo = tipo;
    }

    @Override
    public String mostrarDetalle() {
        return "Revista: " + nombre + " - " + mes + " - " + anio + " - " + tipo;
    }
}